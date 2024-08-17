package com.foodrecipes.searchrecipe.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.foodrecipes.searchrecipe.entity.Recipe;
import com.foodrecipes.searchrecipe.entity.RecipeSearch;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    @Query("SELECT new com.foodrecipes.searchrecipe.entity.RecipeSearch(r.id, r.name, r.image, r.ownerId, r.description, r.dateCreated) FROM Recipe r WHERE r.name LIKE CONCAT(:recipeName, '%')")
    List<RecipeSearch> findTop5ByRecipeStartingWith(@Param("recipeName") String recipeName, Pageable pageable);
}
