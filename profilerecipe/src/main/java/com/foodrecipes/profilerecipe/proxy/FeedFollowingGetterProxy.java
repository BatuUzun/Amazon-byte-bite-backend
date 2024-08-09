package com.foodrecipes.profilerecipe.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feed-following-getter")
public interface FeedFollowingGetterProxy {
	
	@GetMapping("feed-following-getter/{followerId}/followed")
    List<Long> getFollowedUserIds(@PathVariable("followerId") Long followerId);
	
}
