package com.foodrecipes.recipegetter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class StartupService {
	@Autowired
    private RecipeService recipeService;
	
	@PostConstruct
    public void init() {
    	
		recipeService.cacheLastTenPercentRecipeIds();
        
        
    }
}


