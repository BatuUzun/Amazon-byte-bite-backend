package com.foodrecipes.likedislike.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.foodrecipes.likedislike.entity.RecipeProjection;
import com.foodrecipes.likedislike.proxy.RecipeProxy;
import com.foodrecipes.likedislike.service.LikeService;

@RestController
@RequestMapping("/like-dislike")
public class LikeController {
	
	@Autowired
    private LikeService likeService;
	
	@Autowired
	private RecipeProxy recipeProxy;
	
	@GetMapping("/count")
    public LikeCountResponse getLikeCounts(@RequestParam Long recipeId) {
        long likes = likeService.countLikes(recipeId);
        System.out.println("COUNT");
        return new LikeCountResponse(likes);
    }
	
	@GetMapping("/most-liked-recipes")
    public ResponseEntity<List<Long>> getMostLikedRecipes() {
        List<Long> mostLikedRecipeIds = likeService.getFirst1000RecipeIds();
        return new ResponseEntity<>(mostLikedRecipeIds, HttpStatus.OK);
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
	
	@GetMapping("/likes/count/{userId}")
    public long getLikeCountByUserId(@PathVariable Long userId) {
        return likeService.countLikesByUserId(userId);
    }
	
	@GetMapping("/get-recipe/{ownerId}/{page}")
    public List<RecipeProjection> getRecipe(@PathVariable("ownerId") Long ownerId, @PathVariable("page") int page) {
    	List<Long> ids = likeService.getLikesByOwnerId(ownerId, page);
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
