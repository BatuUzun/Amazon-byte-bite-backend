package com.foodrecipes.createrecipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.foodrecipes.createrecipe.constant.Constants;
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
    
    public List<Recipe> getDrafts(long ownerId, int page) {
        PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_LIMIT);
        Page<Recipe> recipePage = recipeRepository.getDraftsByUserId(ownerId, pageRequest);
        return recipePage.getContent();
    }
    
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
    
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
    
    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}