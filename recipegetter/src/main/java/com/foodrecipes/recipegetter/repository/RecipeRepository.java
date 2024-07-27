package com.foodrecipes.recipegetter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodrecipes.recipegetter.dto.RecipeSpecificDTO;
import com.foodrecipes.recipegetter.entity.Recipe;
import com.foodrecipes.recipegetter.entity.RecipeProjection;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT new com.foodrecipes.recipegetter.dto.RecipeSpecificDTO(r.cuisine, r.course, r.diet, r.prepTime, r.ingredients, r.instructions) " +
	           "FROM Recipe r WHERE r.type = true AND r.id = :id")
	    RecipeSpecificDTO findSpecificFieldsByIdWhereTypeIsTrue(@Param("id") Long id);
	
	
	@Query("SELECT r.id as id, r.name as name, r.description as description, r.dateCreated as dateCreated, r.image as image, r.ownerId as ownerId " +
	           "FROM Recipe r WHERE r.id IN :ids")
	    List<RecipeProjection> findRecipesByIds(@Param("ids") List<Long> ids);
}