package com.foodrecipes.comment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    	
    	Sort sort = Sort.by(Sort.Order.asc("dateCreated"));

        // Create a Pageable instance with sorting
        PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE, sort);
    	
    	//PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE);
        return commentRepository.findByRecipeId(recipeId, pageRequest);
    }
    
    public Comment addComment(Comment comment) {
        comment.setDateCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }
    
    public void deleteCommentById(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Comment not found with id: " + id);
        }
    }
}