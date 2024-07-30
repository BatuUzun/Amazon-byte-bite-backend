package com.foodrecipes.profileapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    public List<UserProfile> getUserProfilesInOrder(List<Long> ids) {
        // Fetch all profiles by IDs
        List<UserProfile> profiles = userProfileRepository.findByIdIn(ids);
        
        // Create a map for quick lookup
        Map<Long, UserProfile> profileMap = profiles.stream()
                .collect(Collectors.toMap(UserProfile::getId, profile -> profile));

        // Order profiles according to the input IDs
        List<UserProfile> orderedProfiles = new ArrayList<>();
        for (Long id : ids) {
            UserProfile profile = profileMap.get(id);
            if (profile != null) {
                orderedProfiles.add(profile);
            }
        }

        return orderedProfiles;
    }
}
