package com.foodrecipes.likedislike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.annotation.PostConstruct;

@Service
public class StartupService {
    @Autowired
    private LikeService likeService;

    @PostConstruct
    public void init() throws JsonProcessingException {
    	likeService.initializeCache();
    }
}
