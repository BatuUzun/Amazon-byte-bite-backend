package com.foodrecipes.profileapi.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile-recipe")
public interface ProfileRecipeProxy {
	
	@GetMapping("/profile-recipe/recipe-count/")
    public long countRecipesByOwnerId(@RequestParam("ownerId") Long ownerId);
    
}
