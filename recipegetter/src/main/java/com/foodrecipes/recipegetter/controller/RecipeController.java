package com.foodrecipes.recipegetter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.recipegetter.dto.RecipeDTO;

@RestController
@RequestMapping("/recipe-getter")
public class RecipeController {
	
	
	@GetMapping("/get-recipe/{userId}/{page}")
	public RecipeDTO getRecipe(@PathVariable("userId") Long userId, @PathVariable("page") int page) {
		
		
		
		return null;
	}
	
	
}
