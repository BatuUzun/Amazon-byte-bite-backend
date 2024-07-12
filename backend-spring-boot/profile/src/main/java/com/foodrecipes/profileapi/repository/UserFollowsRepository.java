package com.foodrecipes.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.profileapi.entity.UserFollows;

public interface UserFollowsRepository extends JpaRepository<UserFollows, UserFollows.UserFollowsId> {
	@Query("SELECT COUNT(uf) FROM UserFollows uf WHERE uf.followed.id = :userId")
    long countFollowersByUserId(@Param("userId") Long userId);
	
	@Query("SELECT COUNT(uf) FROM UserFollows uf WHERE uf.follower.id = :userId")
    long countFollowingsByUserId(@Param("userId") Long userId);
}
