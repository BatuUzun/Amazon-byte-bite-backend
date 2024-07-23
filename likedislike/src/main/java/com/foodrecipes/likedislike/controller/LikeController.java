package com.foodrecipes.likedislike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.likedislike.dto.LikeCountResponse;
import com.foodrecipes.likedislike.entity.Like;
import com.foodrecipes.likedislike.service.LikeService;

@RestController
@RequestMapping("/like-dislike")
public class LikeController {
	
	@Autowired
    private LikeService likeService;
	
	@GetMapping("/count")
    public LikeCountResponse getLikeCounts(@RequestParam Long recipeId) {
        long likes = likeService.countLikes(recipeId);
        System.out.println("COUNT");
        return new LikeCountResponse(likes);
    }
	
	@GetMapping("/check-like")
    public boolean getLike(@RequestParam Long recipeId, @RequestParam Long userId) {
		System.out.println("CHECK LIKE");
		return likeService.existsByRecipeIdAndUserId(recipeId, userId);
    }
	
	@PostMapping("/add-like")
    public Like addLike(@RequestBody Like like) {		
        return likeService.addLike(like);
    }
	
	@DeleteMapping("/remove-like/{recipeId}/{userId}")
	public void removeLike(@PathVariable("recipeId") Long recipeId, @PathVariable("userId") Long userId) {
		likeService.removeLike(recipeId, userId);
    }
}
