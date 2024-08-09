package com.foodrecipes.feedfollowinggetter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodrecipes.feedfollowinggetter.service.UserFollowsService;

@RestController
@RequestMapping("/feed-following-getter")
public class FollowController {
	@Autowired
	private UserFollowsService userFollowsService;
	
	@GetMapping("/{followerId}/followed")
    public List<Long> getFollowedUserIds(@PathVariable Long followerId) {
        return userFollowsService.getFollowedUserIdsByFollowerId(followerId);
    }
}
