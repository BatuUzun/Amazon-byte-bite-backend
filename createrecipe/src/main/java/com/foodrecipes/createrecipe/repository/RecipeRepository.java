package com.foodrecipes.createrecipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.foodrecipes.createrecipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT r FROM Recipe r WHERE r.ownerId = :ownerId AND r.type = false ORDER BY r.dateCreated DESC")
	Page<Recipe> getDraftsByUserId(@Param("ownerId") Long ownerId, Pageable pageable);
	
	void deleteById(Long id);
	
	
}