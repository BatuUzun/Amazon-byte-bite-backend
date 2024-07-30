package com.foodrecipes.profilerecipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.foodrecipes.profilerecipe.constants.Constants;
import com.foodrecipes.profilerecipe.entity.RecipeProjectionWithoutProfile;
import com.foodrecipes.profilerecipe.repository.RecipeRepository;
@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public long countRecipesByOwnerId(Long ownerId) {
        return recipeRepository.countRecipesByOwnerId(ownerId);
    }

    public List<RecipeProjectionWithoutProfile> getRecipesByOwnerId(Long ownerId, int page) {
    	PageRequest pageRequest = PageRequest.of(page, Constants.RECIPE_PAGE_SIZE);
        return recipeRepository.findByOwnerId(ownerId, pageRequest);
    }
    
    
}