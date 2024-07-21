package com.foodrecipes.likedislike.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodrecipes.likedislike.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	long countByRecipeIdAndType(Long recipeId, Boolean type);
	Optional<Like> findByUserIdAndRecipeId(Long userId, Long recipeId);
}