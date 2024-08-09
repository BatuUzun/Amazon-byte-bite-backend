package com.foodrecipes.profilerecipe.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodrecipes.profilerecipe.entity.RecipeProjection;
import com.foodrecipes.profilerecipe.entity.RecipeProjectionWithoutProfile;
import com.foodrecipes.profilerecipe.entity.UserProfile;
import com.foodrecipes.profilerecipe.proxy.FeedFollowingGetterProxy;
import com.foodrecipes.profilerecipe.proxy.UserProfileProxy;
import com.foodrecipes.profilerecipe.service.RecipeService;


@RestController
@RequestMapping("/profile-recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    
    @Autowired
	private UserProfileProxy userProfileProxy;
    
    @Autowired
    private FeedFollowingGetterProxy feedFollowingGetterProxy;

    @GetMapping("/recipe-count/")
    public long countRecipesByOwnerId(@RequestParam("ownerId") Long ownerId) {
        long count = recipeService.countRecipesByOwnerId(ownerId);
        return count;
    }
    
    @GetMapping("/get-recipe/{ownerId}/{page}")
    public List<RecipeProjection> getRecipe(@PathVariable("ownerId") Long ownerId, @PathVariable("page") int page) {
    	List<RecipeProjectionWithoutProfile> list = recipeService.getRecipesByOwnerId(ownerId, page);
    	if(list.size() == 0) {
    		return null;
    	}
    	
    	List<Long> ids = new ArrayList<Long>();
		
		for(int i = 0;i<list.size();i++) {
			ids.add(list.get(i).getOwnerId());
        }
    	
    	List<RecipeProjection> recipes = new ArrayList<RecipeProjection>();
    	List<UserProfile> upList = new ArrayList<>(userProfileProxy.getUserProfiles(ids));
		
		
		
    	for(int i = 0; i < list.size(); i++) {        	
    		RecipeProjection rp= new RecipeProjection(list.get(i).getId(), list.get(i).getName(), list.get(i).getDescription(), 
    				list.get(i).getDateCreated(), list.get(i).getImage(), list.get(i).getOwnerId(), upList.get(i).getUsername(), upList.get(i).getProfilePicture());
    		recipes.add(rp);
    		
        }
		
        return recipes;
    }
    
    
    @GetMapping("/{followerId}/followed")
    public List<Long> getFollowedUserIds(@PathVariable Long followerId) {
        List<Long> list = new ArrayList<Long>(recipeService.getRecipeIdsByUserIds(feedFollowingGetterProxy.getFollowedUserIds(followerId)));
        Collections.shuffle(list);
        return list;
    }
    
    
}