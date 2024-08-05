package com.foodrecipes.createrecipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodrecipes.createrecipe.entity.Recipe;
import com.foodrecipes.createrecipe.repository.RecipeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
    
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
    
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
    
}