package com.foodrecipes.profilerecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodrecipes.profilerecipe.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT COUNT(r) FROM Recipe r WHERE r.ownerId = :ownerId AND r.type = true")
    long countRecipesByOwnerId(@Param("ownerId") Long ownerId);
}