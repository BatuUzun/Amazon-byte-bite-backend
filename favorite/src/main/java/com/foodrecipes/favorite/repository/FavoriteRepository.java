package com.foodrecipes.favorite.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.foodrecipes.favorite.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserIdAndRecipeId(Long userId, Long recipeId);
    void deleteByUserIdAndRecipeId(Long userId, Long recipeId);
    
    
    @Query("SELECT f.recipeId FROM Favorite f WHERE f.userId = :ownerId ORDER BY f.dateCreated DESC")
    List<Long> findByOwnerId(Long ownerId, PageRequest pageRequest);
    
    
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.userId = :userId")
    long countByUserId(Long userId);
}