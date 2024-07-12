package com.foodrecipes.createrecipe.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile-api")
public interface ProfileProxy {

	
	@GetMapping("/profile-api/is-user-exist-by-id/")
	public Boolean isUserExistById(@RequestParam Long id);
	
}
