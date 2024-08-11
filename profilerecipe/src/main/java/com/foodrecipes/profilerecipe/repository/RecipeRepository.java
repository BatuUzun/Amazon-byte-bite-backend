package com.foodrecipes.profilerecipe.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodrecipes.profilerecipe.entity.Recipe;
import com.foodrecipes.profilerecipe.entity.RecipeProjectionWithoutProfile;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT COUNT(r) FROM Recipe r WHERE r.ownerId = :ownerId")
    long countRecipesByOwnerId(@Param("ownerId") Long ownerId);
	
	@Query("SELECT r.id AS id, r.name AS name, r.ownerId AS ownerId,r.description AS description, r.dateCreated AS dateCreated, r.image AS image " +
	           "FROM Recipe r WHERE r.ownerId = :ownerId ORDER BY r.dateCreated DESC")
    List<RecipeProjectionWithoutProfile> findByOwnerId(Long ownerId, PageRequest pageRequest);
	
	@Query("SELECT r.id FROM Recipe r WHERE r.ownerId IN :userIds AND r.dateCreated >= :dateThreshold")
    List<Long> findRecipeIdsByOwnerIds(List<Long> userIds, LocalDateTime dateThreshold);
}