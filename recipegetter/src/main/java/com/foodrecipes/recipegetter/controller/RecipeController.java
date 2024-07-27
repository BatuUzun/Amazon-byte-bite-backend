package com.foodrecipes.recipegetter.controller;

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
import com.foodrecipes.recipegetter.service.RecipeService;

@RestController
@RequestMapping("/recipe-getter")
public class RecipeController {
	@Autowired
    private RecipeService recipeService;

	
	@PostMapping("/get-recipes")
    public List<RecipeProjection> getRecipes(@RequestParam List<Long> ids) {
        return recipeService.findRecipesByIds(ids);
    }
	
	
    @GetMapping("/specific-fields/{id}")
    public RecipeSpecificDTO getSpecificFieldsByIdWhereTypeIsTrue(@PathVariable Long id) {
        return recipeService.getSpecificFieldsByIdWhereTypeIsTrue(id);
    }
}
