package com.foodrecipes.recipegetter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.entity.RecipeProjection;
import com.foodrecipes.recipegetter.entity.RecipeProjectionWithoutProfile;
import com.foodrecipes.recipegetter.entity.UserProfile;
import com.foodrecipes.recipegetter.proxy.UserProfileProxy;
import com.foodrecipes.recipegetter.service.RecipeService;

@RestController
@RequestMapping("/recipe-getter")
public class RecipeController {
	@Autowired
    private RecipeService recipeService;

	@Autowired
	private UserProfileProxy userProfileProxy;
	
	@PostMapping("/get-recipes")
    public List<RecipeProjection> getRecipes(@RequestParam List<Long> ids) {
		
		List<RecipeProjectionWithoutProfile> recipes = recipeService.findRecipesByIds(ids);
		if(recipes.size() == 0) {
    		return null;
    	}
		
		List<Long> profileIds = new ArrayList<Long>();
		
		for(int i = 0;i<recipes.size();i++) {
			profileIds.add(recipes.get(i).getOwnerId());
        }
		
    	List<UserProfile> upList = new ArrayList<>(userProfileProxy.getUserProfiles(profileIds));
		
    	for(int i = 0;i<recipes.size();i++) {
			profileIds.add(recipes.get(i).getOwnerId());
        }
		
    	List<RecipeProjection> recipesWithProfile = new ArrayList<RecipeProjection>();

    	
    	for(int i = 0; i < recipes.size(); i++) {        	
    		RecipeProjection rp= new RecipeProjection(recipes.get(i).getId(), recipes.get(i).getName(), recipes.get(i).getDescription(), 
    				recipes.get(i).getDateCreated(), recipes.get(i).getImage(), recipes.get(i).getOwnerId(), upList.get(i).getUsername(), upList.get(i).getProfilePicture());
    		recipesWithProfile.add(rp);
    		
        }
    	
        return recipesWithProfile;
    }
	
	
    @GetMapping("/specific-fields/{id}")
    public RecipeSpecificDTO getSpecificFieldsByIdWhereTypeIsTrue(@PathVariable Long id) {
        return recipeService.getSpecificFieldsByIdWhereTypeIsTrue(id);
    }
}
