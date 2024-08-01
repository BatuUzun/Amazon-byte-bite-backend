package com.foodrecipes.profileapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.profileapi.entity.UserFollows;

public interface UserFollowsRepository extends JpaRepository<UserFollows, UserFollows.UserFollowsId> {
	@Query("SELECT COUNT(uf) FROM UserFollows uf WHERE uf.followed.id = :userId")
    long countFollowersByUserId(@Param("userId") Long userId);
	
	@Query("SELECT COUNT(uf) FROM UserFollows uf WHERE uf.follower.id = :userId")
    long countFollowingsByUserId(@Param("userId") Long userId);
	
	
	
	
	
	@Query("SELECT uf FROM UserFollows uf WHERE uf.followed.id = :userId")
    List<UserFollows> findFollowersByUserId(@Param("userId") Long userId);

    @Query("SELECT uf FROM UserFollows uf WHERE uf.follower.id = :userId")
    List<UserFollows> findFollowingsByUserId(@Param("userId") Long userId);
    
    // Check if a specific user is following another user
    @Query("SELECT COUNT(uf) > 0 FROM UserFollows uf WHERE uf.follower.id = :followerId AND uf.followed.id = :followedId")
    boolean isUserFollowing(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

}
