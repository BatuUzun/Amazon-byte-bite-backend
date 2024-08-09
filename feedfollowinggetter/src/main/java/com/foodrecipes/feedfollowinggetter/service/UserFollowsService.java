package com.foodrecipes.feedfollowinggetter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.feedfollowinggetter.repository.UserFollowsRepository;

@Service
public class UserFollowsService {

	@Autowired
    private UserFollowsRepository userFollowsRepository;

    public List<Long> getFollowedUserIdsByFollowerId(Long followerId) {
        return userFollowsRepository.findFollowedUserIdsByFollowerId(followerId);
    }
}
