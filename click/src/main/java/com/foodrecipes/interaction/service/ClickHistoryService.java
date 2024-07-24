package com.foodrecipes.interaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.interaction.entity.ClickHistory;
import com.foodrecipes.interaction.repository.ClickHistoryRepository;

@Service
public class ClickHistoryService {

    @Autowired
    private ClickHistoryRepository clickHistoryRepository;

    public ClickHistory addClick(Long userId, Long recipeId) {
        ClickHistory clickHistory = new ClickHistory();
        clickHistory.setUserId(userId);
        clickHistory.setRecipeId(recipeId);
        return clickHistoryRepository.save(clickHistory);
    }
}