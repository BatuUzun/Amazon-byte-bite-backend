package com.foodrecipes.searchrecipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.foodrecipes.searchrecipe.constants.Constant;
import com.foodrecipes.searchrecipe.entity.Recipe;
import com.foodrecipes.searchrecipe.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	public List<Recipe> searchRecipesByName(String recipeName, int page) {
        PageRequest pageRequest = PageRequest.of(page, Constant.SEARCH_PAGE_RECIPE_LIMIT); // First page with 5 results
        return recipeRepository.findByRecipeStartingWith(recipeName, pageRequest);
    }
}
