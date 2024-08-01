package com.foodrecipes.profileapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.foodrecipes.profileapi.constants.Constants;
import com.foodrecipes.profileapi.entity.UserFollows;
import com.foodrecipes.profileapi.entity.UserProfile;
import com.foodrecipes.profileapi.repository.UserFollowsRepository;

@Service
public class UserFollowsService {

    @Autowired
    private UserFollowsRepository userFollowsRepository;
    
    public void addUserFollows(UserProfile follower, UserProfile followed) {
        UserFollows.UserFollowsId id = new UserFollows.UserFollowsId(follower.getId(), followed.getId());
        UserFollows userFollows = new UserFollows();
        userFollows.setId(id);
        userFollows.setFollower(follower);
        userFollows.setFollowed(followed);
        userFollowsRepository.save(userFollows);
    }
    
    public void removeUserFollows(UserProfile follower, UserProfile followed) {
        UserFollows.UserFollowsId id = new UserFollows.UserFollowsId(follower.getId(), followed.getId());
        UserFollows userFollows = userFollowsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Follow relationship not found"));

        userFollowsRepository.delete(userFollows);
    }
    
    public UserFollows getUserFollows(UserProfile follower, UserProfile followed) {
        UserFollows.UserFollowsId id = new UserFollows.UserFollowsId(follower.getId(), followed.getId());
        return userFollowsRepository.findById(id)
                .orElse(null);
    }
    
    public long getFollowersCount(Long userId) {
        return userFollowsRepository.countFollowersByUserId(userId);
    }
    
    public long getFollowingsCount(Long userId) {
        return userFollowsRepository.countFollowingsByUserId(userId);
    }
    
    public boolean isUserFollowing(Long followerId, Long followedId) {
        return userFollowsRepository.isUserFollowing(followerId, followedId);
    }
    
    public List<UserFollows> findFollowersByUserId(Long id, int page) {
    	PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE);
    	return userFollowsRepository.findFollowersByUserId(id, pageRequest);
    }
    
    public List<UserFollows> findFollowingsByUserId(Long id, int page) {
    	PageRequest pageRequest = PageRequest.of(page, Constants.PAGE_SIZE);
    	return userFollowsRepository.findFollowingsByUserId(id, pageRequest);
    }
}