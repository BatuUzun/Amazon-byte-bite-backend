package com.foodrecipes.profilerecipe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.profilerecipe.entity.Recipe;
import com.foodrecipes.profilerecipe.entity.RecipeProjection;
import com.foodrecipes.profilerecipe.service.RecipeService;


@RestController
@RequestMapping("/profile-recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipe-count/")
    public long countRecipesByOwnerId(@RequestParam("ownerId") Long ownerId) {
        long count = recipeService.countRecipesByOwnerId(ownerId);
        return count;
    }
    
    @GetMapping("/get-recipe/{ownerId}/{page}")
    public List<RecipeProjection> getRecipe(@PathVariable("ownerId") Long ownerId, @PathVariable("page") int page) {
    	List<RecipeProjection> list = recipeService.getRecipesByOwnerId(ownerId, page);
    	if(list.size() == 0) {
    		list = new ArrayList<RecipeProjection>();
    		return list;
    	}
        return list;
    }
    
    
    
}