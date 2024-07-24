package com.foodrecipes.recipegetter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.recipegetter.dto.RecipeDTO;
import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.service.RecipeService;

@RestController
@RequestMapping("/recipe-getter")
public class RecipeController {
	@Autowired
    private RecipeService recipeService;

	
	@GetMapping("/get-recipe/{userId}/{page}")
	public RecipeDTO getRecipe(@PathVariable("userId") Long userId, @PathVariable("page") int page) {
		
		
		
		return null;
	}
	
	
    @GetMapping("/specific-fields/{id}")
    public RecipeSpecificDTO getSpecificFieldsByIdWhereTypeIsTrue(@PathVariable Long id) {
        return recipeService.getSpecificFieldsByIdWhereTypeIsTrue(id);
    }
}
