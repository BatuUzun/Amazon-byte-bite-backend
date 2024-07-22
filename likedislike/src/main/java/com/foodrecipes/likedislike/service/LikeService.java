package com.foodrecipes.likedislike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodrecipes.likedislike.entity.Like;
import com.foodrecipes.likedislike.repository.LikeRepository;

import jakarta.transaction.Transactional;


@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    
    public long countLikes(Long recipeId) {
        return likeRepository.countByRecipeId(recipeId); // true for likes
    }
    
    
    public Like getLike(Long recipeId, Long userId) {
    	System.out.println("GET LIKE: recipeId: "+recipeId);
    	return likeRepository.findByUserIdAndRecipeId(userId, recipeId).orElse(null);
    }
    
    public Like addLike(Like like) {
    	Like target = getLike(like.getRecipeId(), like.getUserId());
    	
    	if(target != null) {
    		likeRepository.delete(target);  
    	}
    	
    	return likeRepository.save(like);
    }
    
    @Transactional
    public void removeLike(Long recipeId, Long userId) {
    	Like target = getLike(recipeId, userId);
    	if(target != null) {
    		likeRepository.delete(target);  	
    	}
    }
    
    public boolean existsByRecipeIdAndUserId(Long recipeId, Long userId) {
        return likeRepository.existsByRecipeIdAndUserId(recipeId, userId);
    }
}