package com.foodrecipes.recipegetter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.entity.RecipeProjectionWithoutProfile;
import com.foodrecipes.recipegetter.repository.RecipeRepository;

import jakarta.annotation.PostConstruct;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    
private static final String REDIS_KEY = "recipe_ids";
    
    @Autowired
    private RedisTemplate<String, Long> redisTemplate;
    
    @PostConstruct
    public void setup() {
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Long.class));
    }
    
    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void cacheLastTenPercentRecipeIds() {
        // Get the total count of recipes
        long totalRecipes = recipeRepository.count();

        // Calculate the number of recipes to fetch (10% of total)
        int limit = (int) Math.ceil(totalRecipes * 0.1);

        // Fetch the last 10% of recipe IDs
        List<Long> recipeIds = recipeRepository.findLastTenPercentIds(limit);

        // Store the recipe IDs in Redis
        redisTemplate.opsForList().rightPushAll(REDIS_KEY, recipeIds);
    }
    
    /*public List<Long> getCachedRecipeIds() {
        List<Long> recipeIds = redisTemplate.opsForList().range(REDIS_KEY, 0, -1);
        if (recipeIds != null) {
            Collections.shuffle(recipeIds); // Shuffle the list to return in random order
        }
        return recipeIds;
    }*/
    
    public List<Long> getCachedRecipeIds() {
        List<Long> recipeIds = redisTemplate.opsForList().range(REDIS_KEY, 0, -1);
        
        if (recipeIds != null && !recipeIds.isEmpty()) {
            // Find the minimum value
            Long minValue = Collections.min(recipeIds);
            
            // Create a new list excluding the minimum value
            List<Long> remainingIds = new ArrayList<>(recipeIds);
            remainingIds.remove(minValue);
            
            // Shuffle the remaining list
            Collections.shuffle(remainingIds);
            
            // Add the minimum value to the beginning of the list
            List<Long> result = new ArrayList<>();
            result.add(minValue);
            result.addAll(remainingIds);
            
            return result;
        }
        
        return recipeIds; // Return as-is if it's null or empty
    }
    
    public void addNewRecipeToCache(Long newRecipeId) {
        System.out.println("Adding new recipe ID to cache: " + newRecipeId);

        // Ensure the RedisTemplate is correctly configured to handle Long values
        try {
            // Remove the last item from the list (tail)
            redisTemplate.opsForList().rightPop(REDIS_KEY);
            
            // Add the new recipe ID to the start of the list (head)
            redisTemplate.opsForList().leftPush(REDIS_KEY, newRecipeId);
        } catch (Exception e) {
            System.err.println("Error interacting with Redis: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public RecipeSpecificDTO getSpecificFieldsById(Long id) {
        return recipeRepository.findSpecificFieldsById(id);
    }
    
    public List<RecipeProjectionWithoutProfile> findRecipesByIds(List<Long> ids){
    	return recipeRepository.findRecipesByIds(ids);
    }
}