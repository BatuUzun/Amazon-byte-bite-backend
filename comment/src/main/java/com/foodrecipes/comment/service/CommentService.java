package com.foodrecipes.comment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.foodrecipes.comment.constants.Constants;
import com.foodrecipes.comment.entity.Comment;
import com.foodrecipes.comment.entity.CommentProjection;
import com.foodrecipes.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
    private CommentRepository commentRepository;

    public long countCommentsByRecipeId(Long recipeId) {
        return commentRepository.countByRecipeId(recipeId);
    }
    
    public List<CommentProjection> findByRecipeId(Long recipeId, int page) {
    	PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE);
        return commentRepository.findByRecipeId(recipeId, pageRequest);
    }
}