package com.foodrecipes.favorite.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.favorite.entity.Favorite;
import com.foodrecipes.favorite.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public boolean isRecipeFavoritedByUser(Long userId, Long recipeId) {
        return favoriteRepository.findByUserIdAndRecipeId(userId, recipeId).isPresent();
    }
	@Transactional
	public void deleteFavorite(Long userId, Long recipeId) {
		if(isRecipeFavoritedByUser(userId, recipeId)) {
	        favoriteRepository.deleteByUserIdAndRecipeId(userId, recipeId);
		}
    }
	
	public boolean addFavorite(Long userId, Long recipeId) {
        // Check if the favorite already exists
        Favorite existingFavorite = favoriteRepository.findByUserIdAndRecipeId(userId, recipeId).orElse(null);
        if (existingFavorite == null) {
            Favorite favorite = new Favorite(0L, userId, recipeId, LocalDateTime.now());
            favoriteRepository.save(favorite);
            return true;
        }
        return false;
    }
	
	
}
