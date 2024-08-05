package com.foodrecipes.recipegetter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.entity.RecipeProjectionWithoutProfile;
import com.foodrecipes.recipegetter.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeSpecificDTO getSpecificFieldsById(Long id) {
        return recipeRepository.findSpecificFieldsById(id);
    }
    
    public List<RecipeProjectionWithoutProfile> findRecipesByIds(List<Long> ids){
    	return recipeRepository.findRecipesByIds(ids);
    }
}