package com.foodrecipes.likedislike.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodrecipes.likedislike.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	long countByRecipeId(Long recipeId);
	
	Optional<Like> findByUserIdAndRecipeId(Long userId, Long recipeId);
	
	boolean existsByRecipeIdAndUserId(Long recipeId, Long userId);

	@Query("SELECT l.recipeId, COUNT(l) FROM Like l GROUP BY l.recipeId")
    List<Object[]> findLikeCounts();
    
    @Query("SELECT l.recipeId FROM Like l WHERE l.userId = :ownerId ORDER BY l.dateCreated DESC")
    List<Long> findByOwnerId(Long ownerId, PageRequest pageRequest);
    
    
    @Query("SELECT COUNT(l) FROM Like l WHERE l.userId = :userId")
    long countByUserId(Long userId);
}