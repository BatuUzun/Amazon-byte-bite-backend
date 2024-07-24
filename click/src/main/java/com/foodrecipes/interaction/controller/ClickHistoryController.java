package com.foodrecipes.interaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodrecipes.interaction.service.ClickHistoryService;

@RestController
@RequestMapping("/click")
public class ClickHistoryController {

    @Autowired
    private ClickHistoryService clickHistoryService;

    @PostMapping("/add-click")
    public void addClick(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId) {
    	System.out.println("CLICKED");
        clickHistoryService.addClick(userId, recipeId);
    }
}