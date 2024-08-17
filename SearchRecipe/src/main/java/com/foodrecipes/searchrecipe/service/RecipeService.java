package com.foodrecipes.searchrecipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodrecipes.searchrecipe.constants.Constant;
import com.foodrecipes.searchrecipe.entity.RecipeSearch;
import com.foodrecipes.searchrecipe.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	public List<RecipeSearch> searchRecipesByName(String recipeName) {
		Pageable pageable = PageRequest.of(0, Constant.SEARCH_PAGE_RECIPE_LIMIT);
        return recipeRepository.findTop5ByRecipeStartingWith(recipeName, pageable);
    }
}
