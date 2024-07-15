package com.foodrecipes.profilerecipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.profilerecipe.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public long countRecipesByOwnerId(Long ownerId) {
        return recipeRepository.countRecipesByOwnerId(ownerId);
    }
}