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
        return likeRepository.countByRecipeIdAndType(recipeId, true); // true for likes
    }

    public long countDislikes(Long recipeId) {
        return likeRepository.countByRecipeIdAndType(recipeId, false); // false for dislikes
    }
    
    public Like getLike(Long recipeId, Long userId) {
    	System.out.println("GET LIKE: recipeId: "+recipeId);
    	return likeRepository.findByUserIdAndRecipeId(userId, recipeId).orElse(null);
    }
    
    public Like addLike(Like like) {
    	Like target = getLike(like.getRecipeId(), like.getUserId());
    	
    	if(target != null) {
    		deleteLike(target);    	
    	}
    	
    	return likeRepository.save(like);
    }
    
    @Transactional
    public void deleteLike(Like like) {
    	likeRepository.delete(like);
    }
    
    public void removeLike(Long recipeId, Long userId) {
    	Like target = getLike(recipeId, userId);
    	if(target != null) {
    		deleteLike(target);    	
    	}
    }
}