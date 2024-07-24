package com.foodrecipes.interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodrecipes.interaction.entity.ClickHistory;

@Repository
public interface ClickHistoryRepository extends JpaRepository<ClickHistory, Long> {
}