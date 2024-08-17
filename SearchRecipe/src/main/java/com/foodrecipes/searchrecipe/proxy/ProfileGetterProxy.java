package com.foodrecipes.searchrecipe.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile-getter")
public interface ProfileGetterProxy {
	@GetMapping("/profile-getter/get-user-profiles-username")
    List<String> getUserProfiles(@RequestParam List<Long> ids);
}
