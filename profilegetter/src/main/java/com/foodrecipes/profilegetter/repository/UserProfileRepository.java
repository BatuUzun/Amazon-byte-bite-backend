package com.foodrecipes.profilegetter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.profilegetter.entity.UserProfile;
import com.foodrecipes.profilegetter.entity.UserProfileProjection;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	@Query("SELECT u.username AS username, u.profilePicture AS profilePicture FROM UserProfile u WHERE u.id = :id")
    Optional<UserProfileProjection> findUserProfileById(@Param("id") Long id);
	
	List<UserProfile> findByIdIn(List<Long> ids);
}
