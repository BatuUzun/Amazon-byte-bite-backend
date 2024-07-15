package com.foodrecipes.profilerecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}