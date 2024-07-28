package com.foodrecipes.favorite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.favorite.entity.RecipeProjection;
import com.foodrecipes.favorite.proxy.RecipeProxy;
import com.foodrecipes.favorite.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private RecipeProxy recipeProxy;
	
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
	
	@GetMapping("/favorites/count/{userId}")
    public long getFavoriteCountByUserId(@PathVariable Long userId) {
        return favoriteService.countFavoritesByUserId(userId);
    }
	
	@GetMapping("/get-recipe/{ownerId}/{page}")
    public List<RecipeProjection> getRecipe(@PathVariable("ownerId") Long ownerId, @PathVariable("page") int page) {
    	List<Long> ids = favoriteService.getFavoritesByOwnerId(ownerId, page);
    	if(ids.size() == 0) {
    		return null;
    	}
    	List<RecipeProjection> recipes =recipeProxy.getRecipes(ids);
    	
    	Map<Long, RecipeProjection> recipeMap = recipes.stream()
                .collect(Collectors.toMap(RecipeProjection::getId, recipe -> recipe));

        // Create a list with recipes ordered according to the ids
        List<RecipeProjection> orderedRecipes = new ArrayList<>();
        for (Long id : ids) {
            RecipeProjection recipe = recipeMap.get(id);
            if (recipe != null) {
                orderedRecipes.add(recipe);
            }
        }
    	
        return orderedRecipes;
    }
	
}
