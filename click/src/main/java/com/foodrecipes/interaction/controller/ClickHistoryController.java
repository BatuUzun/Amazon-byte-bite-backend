package com.foodrecipes.interaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.interaction.constants.Constants;
import com.foodrecipes.interaction.service.ClickHistoryService;


@RestController
@RequestMapping("/click")
public class ClickHistoryController {

    @Autowired
    private ClickHistoryService clickHistoryService;
    
    
    @GetMapping("/most-clicked-recipes")
    public ResponseEntity<List<Long>> getMostClickedRecipes() {
        List<Long> mostClickedRecipeIds = clickHistoryService.getTop1000ClickedRecipes();
        return new ResponseEntity<>(mostClickedRecipeIds, HttpStatus.OK);
    }
    

    @PostMapping("/add-click")
    public void addClick(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId) {
    	System.out.println("CLICKED");
        clickHistoryService.addClick(userId, recipeId);
    }
}