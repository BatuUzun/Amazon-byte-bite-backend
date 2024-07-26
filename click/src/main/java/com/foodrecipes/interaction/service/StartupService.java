package com.foodrecipes.interaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.annotation.PostConstruct;

@Service
public class StartupService {
    @Autowired
    private ClickHistoryService clickService;

    @PostConstruct
    public void init() throws JsonProcessingException {
        clickService.initializeCache();
    }
}
