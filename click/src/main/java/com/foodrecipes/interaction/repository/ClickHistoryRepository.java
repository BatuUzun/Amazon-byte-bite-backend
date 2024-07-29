package com.foodrecipes.interaction.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodrecipes.interaction.entity.ClickHistory;

@Repository
public interface ClickHistoryRepository extends JpaRepository<ClickHistory, Long> {
	@Query("SELECT c.recipeId, COUNT(c) as clickCount FROM ClickHistory c GROUP BY c.recipeId")
    List<Object[]> findClickCounts();
    
    @Query("SELECT ch.recipeId, COUNT(ch) as clickCount " +
            "FROM ClickHistory ch " +
            "WHERE ch.clickDate >= :startDate " +
            "GROUP BY ch.recipeId " +
            "ORDER BY clickCount DESC")
     List<Object[]> findMostClickedRecipes(LocalDateTime startDate, Pageable pageable);
}