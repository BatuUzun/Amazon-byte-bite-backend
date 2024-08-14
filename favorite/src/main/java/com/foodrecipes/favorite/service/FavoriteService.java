package com.foodrecipes.favorite.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.foodrecipes.favorite.constants.Constants;
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
	public boolean deleteFavorite(Long userId, Long recipeId) {
		if(isRecipeFavoritedByUser(userId, recipeId)) {
	        favoriteRepository.deleteByUserIdAndRecipeId(userId, recipeId);
	        return true;
		}
		return false;
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
	public List<Long> getFavoritesByOwnerId(Long ownerId, int page) {
    	PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE);

		return favoriteRepository.findByOwnerId(ownerId, pageRequest);
	}
	
	public long countFavoritesByUserId(Long userId) {
        return favoriteRepository.countByUserId(userId);
    }
	
	
}
