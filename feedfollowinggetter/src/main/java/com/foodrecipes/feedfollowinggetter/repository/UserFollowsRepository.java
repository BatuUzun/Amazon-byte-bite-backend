package com.foodrecipes.feedfollowinggetter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodrecipes.feedfollowinggetter.entity.UserFollows;
import com.foodrecipes.feedfollowinggetter.entity.UserFollowsId;

@Repository
public interface UserFollowsRepository extends JpaRepository<UserFollows, UserFollowsId> {

    @Query("SELECT uf.followedId FROM UserFollows uf WHERE uf.followerId = :followerId")
    List<Long> findFollowedUserIdsByFollowerId(Long followerId);
}