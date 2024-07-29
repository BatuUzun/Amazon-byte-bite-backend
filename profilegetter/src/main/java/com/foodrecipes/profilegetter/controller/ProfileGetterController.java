package com.foodrecipes.profilegetter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.profilegetter.entity.UserProfileProjection;
import com.foodrecipes.profilegetter.service.UserProfileService;

@RestController
@RequestMapping("/profile-getter")
public class ProfileGetterController {
	
	@Autowired
    private UserProfileService userProfileService;
	
	@GetMapping("/{id}")
    public ResponseEntity<UserProfileProjection> getUserProfile(@PathVariable Long id) {
        return userProfileService.getUserProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
}
