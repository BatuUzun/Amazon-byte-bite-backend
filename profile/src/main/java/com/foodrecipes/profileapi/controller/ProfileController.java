package com.foodrecipes.profileapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.foodrecipes.profileapi.constants.Constants;
import com.foodrecipes.profileapi.dto.FollowRequest;
import com.foodrecipes.profileapi.entity.UserFollows;
import com.foodrecipes.profileapi.entity.UserProfile;
import com.foodrecipes.profileapi.proxy.Amazons3Proxy;
import com.foodrecipes.profileapi.response.ResultResponse;
import com.foodrecipes.profileapi.service.UserFollowsService;
import com.foodrecipes.profileapi.service.UserProfileService;

@RestController
@RequestMapping("/profile-api")
public class ProfileController {
	
	@Autowired
	private Amazons3Proxy profileProxy;
	
	@Autowired
    private UserFollowsService userFollowsService;
	
	@Autowired
    private UserProfileService userProfileService;
	
	@PostMapping("/change-profile-picture")
	public ResultResponse changeProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userProfileId) {
		ResultResponse response = null;
		UserProfile userProfile = userProfileService.getUserProfileById(userProfileId);
		if(userProfile != null) {
			String currentPP = userProfile.getProfilePicture();
			if(!currentPP.equals(Constants.DEFAULT_PROFILE_IMAGE)) {
				profileProxy.delete(currentPP);
			}
				
			response = profileProxy.upload(file);
		    
		    String imageName = "";
		    if (response.getResult() instanceof String) {
		        imageName = (String) response.getResult();
		    }
		    userProfileService.updateProfilePicture(userProfileId, imageName);
		    // You can now use imageName as needed
		    System.out.println("Uploaded image name: " + imageName);
		    System.out.println("User ID: " + userProfileId);
	    
		}
	    return response;
	}
	
	@PostMapping("/add-user-follows")
    public ResponseEntity<String> addUserFollows(@RequestBody FollowRequest followRequest) {
		UserProfile user1 = userProfileService.getUserProfileById(followRequest.getFollowerId());
		UserProfile user2 = userProfileService.getUserProfileById(followRequest.getFollowedId());
		
		if(user1 == null  || user2 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("One or both users not found");
		}
		
        userFollowsService.addUserFollows(user1, user2);
        return ResponseEntity.status(HttpStatus.CREATED).body("Follow relationship created successfully");    
    }
	
	@PostMapping("/remove-user-follows")
    public ResponseEntity<String> removeUserFollows(@RequestBody FollowRequest followRequest) {
        UserProfile user1 = userProfileService.getUserProfileById(followRequest.getFollowerId());
        UserProfile user2 = userProfileService.getUserProfileById(followRequest.getFollowedId());

        if (user1 == null || user2 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("One or both users not found");
        }
        
        UserFollows userFollows = userFollowsService.getUserFollows(user1, user2);
        if(userFollows == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relation not found");
        }

        userFollowsService.removeUserFollows(user1, user2);
        return ResponseEntity.status(HttpStatus.OK).body("Follow relationship removed successfully");
    }
	
	@GetMapping("/get-user-profile-by-email/")
	public ResponseEntity<UserProfile> getUserProfileByEmail(@RequestParam String email) {
        UserProfile userProfile = userProfileService.getUserProfileByEmail(email);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
	@GetMapping("/is-user-exist-by-id/")
	public Boolean isUserExistById(@RequestParam Long id) {
        return userProfileService.isUserProfileExist(id);
        
    }
	
	@GetMapping("/user/{id}/followers/count")
    public long getFollowersCount(@PathVariable Long id) {
		if(!userProfileService.isUserProfileExist(id)) {
			return -1;
		}
		
        return userFollowsService.getFollowersCount(id);
    }
	
	@GetMapping("/user/{id}/followings/count")
    public long getFollowingsCount(@PathVariable Long id) {
		if(!userProfileService.isUserProfileExist(id)) {
			return -1;
		}
		
        return userFollowsService.getFollowingsCount(id);
    }

}
