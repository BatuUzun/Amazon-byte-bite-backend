package com.foodrecipes.createrecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodrecipes.createrecipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	
	void deleteById(Long id);
	
	
}