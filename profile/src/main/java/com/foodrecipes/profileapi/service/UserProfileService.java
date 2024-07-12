package com.foodrecipes.profileapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodrecipes.profileapi.entity.UserProfile;
import com.foodrecipes.profileapi.repository.UserProfileRepository;

import jakarta.transaction.Transactional;


@Service
public class UserProfileService {
	
	@Autowired
    private UserProfileRepository userProfileRepository; 
	
	
	public UserProfile getUserProfileById(Long id) {
        return userProfileRepository.findById(id).orElse(null);        
	}
	
	public UserProfile getUserProfileByEmail(String email) {
		return userProfileRepository.findByUserEmail(email);	
	}
	
	@Transactional
    public void updateProfilePicture(Long userProfileId, String newProfilePicture) {
        userProfileRepository.updateProfilePicture(userProfileId, newProfilePicture);
    }
    
    public boolean isUserProfileExist(Long id) {
        return userProfileRepository.existsById(id);        
	}
    
    public String getUserProfilePictureById(Long id) {
    	return userProfileRepository.findUserProfilePictureById(id);
    }
}
