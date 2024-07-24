package com.foodrecipes.comment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.comment.entity.CommentProjection;
import com.foodrecipes.comment.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/count/{recipeId}")
    public long countCommentsByRecipeId(@PathVariable("recipeId") Long recipeId) {
        return commentService.countCommentsByRecipeId(recipeId);
    }
	
	@GetMapping("/get-comments/{recipeId}/{page}")
    public List<CommentProjection> getRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("page") int page) {
    	List<CommentProjection> list = commentService.findByRecipeId(recipeId, page);
    	if(list.size() == 0) {
    		list = new ArrayList<CommentProjection>();
    		return list;
    	}
        return list;
    }
	
}
