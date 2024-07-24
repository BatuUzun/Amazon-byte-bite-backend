package com.foodrecipes.favorite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.favorite.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@GetMapping("/check-favorite")
    public boolean checkFavorite(@RequestParam Long userId, @RequestParam Long recipeId) {
        return favoriteService.isRecipeFavoritedByUser(userId, recipeId);
    }
	
	@DeleteMapping("/delete/{userId}/{recipeId}")
    public void deleteFavorite(@PathVariable Long userId, @PathVariable Long recipeId) {
        favoriteService.deleteFavorite(userId, recipeId);
    }
	
	@PostMapping("/add")
    public boolean addFavorite(@RequestParam Long userId, @RequestParam Long recipeId) {
        return favoriteService.addFavorite(userId, recipeId);
    }
}
