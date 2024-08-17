package com.foodrecipes.profilegetter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.profilegetter.entity.UserProfile;
import com.foodrecipes.profilegetter.entity.UserProfileProjection;
import com.foodrecipes.profilegetter.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public Optional<UserProfileProjection> getUserProfileById(Long id) {
        return userProfileRepository.findUserProfileById(id);
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
	
	public List<String> getUsernamesInOrder(List<Long> ids) {
	    // Fetch all profiles by IDs
	    List<UserProfile> profiles = userProfileRepository.findByIdIn(ids);
	    
	    // Create a map for quick lookup
	    Map<Long, String> usernameMap = profiles.stream()
	            .collect(Collectors.toMap(UserProfile::getId, UserProfile::getUsername));

	    // Order usernames according to the input IDs
	    List<String> orderedUsernames = new ArrayList<>();
	    for (Long id : ids) {
	        String username = usernameMap.get(id);
	        if (username != null) {
	            orderedUsernames.add(username);
	        }
	    }

	    return orderedUsernames;
	}
}
