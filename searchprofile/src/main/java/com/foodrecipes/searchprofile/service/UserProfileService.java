package com.foodrecipes.searchprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.foodrecipes.searchprofile.constants.Constants;
import com.foodrecipes.searchprofile.entity.UserProfile;
import com.foodrecipes.searchprofile.repository.UserProfileRepository;

@Service
public class UserProfileService {
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public List<UserProfile> searchUserProfilesByUsername(String username, int page) {
        PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_LIMIT); // First page with 5 results
        return userProfileRepository.findByUsernameStartingWith(username, pageRequest);
    }
}
