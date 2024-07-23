package com.foodrecipes.searchrecipe.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import com.foodrecipes.searchrecipe.dto.SearchRecipeDTO;
import com.foodrecipes.searchrecipe.entity.Recipe;
import com.foodrecipes.searchrecipe.service.RecipeService;

@RestController
@RequestMapping("/search-recipe")
public class SearchRecipeRestController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	private Environment environment;
	
	@PostMapping("/search")
	public List<Recipe> searchRecipes(@RequestBody SearchRecipeDTO searchRecipeDTO) {
		// access port and log it
		String port = environment.getProperty("local.server.port");
		System.out.println("port: " + port);
		
		// recipe list to be returned and logged
		List<Recipe> recipeList = recipeService.searchRecipesByName(searchRecipeDTO.getRecipeName(), searchRecipeDTO.getPage());
	
		System.out.println("Retrieved recipes:");
		for (int i = 0; i < recipeList.size(); i++) {
			System.out.println(recipeList.get(i).getName());
		}
		
		return recipeList;
	}
}
