package com.foodrecipes.searchrecipe.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodrecipes.searchrecipe.entity.RecipeSearch;
import com.foodrecipes.searchrecipe.entity.RecipeSearchResult;
import com.foodrecipes.searchrecipe.proxy.ProfileGetterProxy;
import com.foodrecipes.searchrecipe.service.RecipeService;

@RestController
@RequestMapping("/search-recipe")
public class SearchRecipeRestController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	private Environment environment;
	
	@Autowired
	private ProfileGetterProxy profileGetterProxy;
	
	@PostMapping("/search")
	public List<RecipeSearchResult> searchRecipes(@RequestParam("query") String query) {
		// access port and log it
		String port = environment.getProperty("local.server.port");
		System.out.println("port: " + port);
		
		// recipe list to be returned and logged
		List<RecipeSearch> recipeList = recipeService.searchRecipesByName(query);
		if(recipeList == null || recipeList.size() == 0) {
			return null;
		}

		List<Long> ids = new ArrayList<Long>();
		for(int i = 0; i< recipeList.size(); i++) {
			ids.add(recipeList.get(i).getOwnerId());
		}
		
		List<String> profileNames = profileGetterProxy.getUserProfiles(ids);
		
		if(profileNames == null || profileNames.size() == 0 || recipeList.size() != profileNames.size()) {
			return null;
		}
		
		List<RecipeSearchResult> recipeSearchResult = new ArrayList<RecipeSearchResult>();
		for(int i = 0; i< profileNames.size(); i++) {
			RecipeSearchResult r = new RecipeSearchResult(recipeList.get(i).getId(), recipeList.get(i).getRecipeName(), recipeList.get(i).getImageName(), recipeList.get(i).getOwnerId(), 
					profileNames.get(i), recipeList.get(i).getDescription(), recipeList.get(i).getDateCreated());
			recipeSearchResult.add(r);
		}
		return recipeSearchResult;
	}
}
