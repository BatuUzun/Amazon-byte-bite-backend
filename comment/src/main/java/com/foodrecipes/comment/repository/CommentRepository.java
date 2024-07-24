package com.foodrecipes.comment.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.foodrecipes.comment.entity.Comment;
import com.foodrecipes.comment.entity.CommentProjection;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.recipeId = :recipeId")
    long countByRecipeId(@Param("recipeId") Long recipeId);
    
    @Query("SELECT c.id as id, c.ownerId as ownerId, c.comment as comment, c.dateCreated as dateCreated FROM Comment c WHERE c.recipeId = :recipeId")
    List<CommentProjection> findByRecipeId(Long recipeId, PageRequest pageRequest);
}