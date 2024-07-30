package com.foodrecipes.recipegetter.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.foodrecipes.recipegetter.entity.UserProfile;



@FeignClient(name = "profile-api")
public interface UserProfileProxy {
	
	@GetMapping("/profile-api/get-user-profiles")
    List<UserProfile> getUserProfiles(@RequestParam List<Long> ids);
    
}

