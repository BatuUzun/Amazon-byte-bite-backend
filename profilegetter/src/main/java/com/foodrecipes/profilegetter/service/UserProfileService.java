package com.foodrecipes.profilegetter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.profilegetter.entity.UserProfileProjection;
import com.foodrecipes.profilegetter.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public Optional<UserProfileProjection> getUserProfileById(Long id) {
        return userProfileRepository.findUserProfileById(id);
    }
}
