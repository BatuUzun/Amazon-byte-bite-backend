package com.foodrecipes.recipegetter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeSpecificDTO getSpecificFieldsByIdWhereTypeIsTrue(Long id) {
        return recipeRepository.findSpecificFieldsByIdWhereTypeIsTrue(id);
    }
}