package com.foodrecipes.recipegetter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT new com.foodrecipes.recipegetter.dto.RecipeSpecificDTO(r.cuisine, r.course, r.diet, r.prepTime, r.ingredients, r.instructions) " +
	           "FROM Recipe r WHERE r.type = true AND r.id = :id")
	    RecipeSpecificDTO findSpecificFieldsByIdWhereTypeIsTrue(@Param("id") Long id);
}