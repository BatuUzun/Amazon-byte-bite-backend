package com.foodrecipes.likedislike.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodrecipes.likedislike.entity.Like;
import com.foodrecipes.likedislike.repository.LikeRepository;

import jakarta.transaction.Transactional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    private static final String LIKE_COUNT_KEY = "recipe_like_counts";
    private static final String MOST_LIKED_KEY = "most_liked_recipes";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void initializeCache() throws JsonProcessingException {
        // Fetch all like counts
        List<Object[]> likeCounts = likeRepository.findLikeCounts();

        // Store like counts in Redis hash
        for (Object[] entry : likeCounts) {
            Long recipeId = (Long) entry[0];
            Long likeCount = (Long) entry[1];
            redisTemplate.opsForHash().put(LIKE_COUNT_KEY, recipeId.toString(), likeCount);
        }

        // Sort and get the list of recipe IDs based on like counts
        List<Long> allMostLikedRecipeIds = likeCounts.stream()
            .sorted((e1, e2) -> Long.compare((Long) e2[1], (Long) e1[1]))
            .map(e -> (Long) e[0])
            .collect(Collectors.toList());

        // Serialize the full list of recipe IDs to a JSON string
        String allMostLikedJson = objectMapper.writeValueAsString(allMostLikedRecipeIds);

        // Store the full list in Redis
        redisTemplate.opsForValue().set(MOST_LIKED_KEY, allMostLikedJson);
    }

    public List<Long> getMostLikedRecipes(int pageNumber, int pageSize) {
        String mostLikedJson = (String) redisTemplate.opsForValue().get(MOST_LIKED_KEY);
        if (mostLikedJson != null) {
            try {
                // Deserialize JSON string back to List<Long>
                List<Long> allMostLikedRecipeIds = objectMapper.readValue(mostLikedJson, new TypeReference<List<Long>>() {});

                // Calculate start and end indices for pagination
                int totalSize = allMostLikedRecipeIds.size();
                int startIndex = pageNumber * pageSize;
                int endIndex = Math.min(startIndex + pageSize, totalSize);

                // Return the paginated list
                if (startIndex < totalSize) {
                    return allMostLikedRecipeIds.subList(startIndex, endIndex);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error deserializing JSON: " + mostLikedJson);
            }
        }
        return Collections.emptyList(); // Return an empty list if no data or deserialization fails
    }

    public long countLikes(Long recipeId) {
        return likeRepository.countByRecipeId(recipeId); // true for likes
    }

    public Like getLike(Long recipeId, Long userId) {
        System.out.println("GET LIKE: recipeId: " + recipeId);
        return likeRepository.findByUserIdAndRecipeId(userId, recipeId).orElse(null);
    }

    public Like addLike(Like like) {
        Like target = getLike(like.getRecipeId(), like.getUserId());

        if (target != null) {
            likeRepository.delete(target);
        }

        Like savedLike = likeRepository.save(like);

        // Update Redis cache
        updateLikeCountInCache(like.getRecipeId());

        return savedLike;
    }

    @Transactional
    public void removeLike(Long recipeId, Long userId) {
        Like target = getLike(recipeId, userId);
        if (target != null) {
            likeRepository.delete(target);

            // Update Redis cache
            updateLikeCountInCache(recipeId);
        }
    }

    public boolean existsByRecipeIdAndUserId(Long recipeId, Long userId) {
        return likeRepository.existsByRecipeIdAndUserId(recipeId, userId);
    }

    private void updateLikeCountInCache(Long recipeId) {
        Long likeCount = likeRepository.countByRecipeId(recipeId);
        redisTemplate.opsForHash().put(LIKE_COUNT_KEY, recipeId.toString(), likeCount);
        updateMostLikedRecipes();
    }

    private void updateMostLikedRecipes() {
        // Get all like counts from Redis
        Map<Object, Object> likeCounts = redisTemplate.opsForHash().entries(LIKE_COUNT_KEY);

        // Sort and get the top recipes
        List<Long> mostLikedRecipeIds = likeCounts.entrySet().stream()
            .sorted((e1, e2) -> Long.compare((Long) e2.getValue(), (Long) e1.getValue()))
            .map(e -> Long.valueOf((String) e.getKey())) // Ensure correct casting
            .collect(Collectors.toList());

        // Serialize List<Long> to JSON string
        String mostLikedJson;
        try {
            mostLikedJson = objectMapper.writeValueAsString(mostLikedRecipeIds);
            redisTemplate.opsForValue().set(MOST_LIKED_KEY, mostLikedJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Log error details
            System.err.println("Error serializing most liked recipes.");
        }
    }
}
