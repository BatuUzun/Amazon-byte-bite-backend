package com.foodrecipes.searchrecipe.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.searchrecipe.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	@Query("SELECT r FROM Recipe r WHERE r.name LIKE CONCAT(:recipeName, '%')")
    List<Recipe> findByRecipeStartingWith(@Param("recipeName") String recipeName, PageRequest pageRequest);
}
