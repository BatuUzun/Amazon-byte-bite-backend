package com.foodrecipes.profileapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.profileapi.entity.UserProfile;

import jakarta.transaction.Transactional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	@Query("SELECT up FROM UserProfile up JOIN up.user u WHERE u.email = :email")
    UserProfile findByUserEmail(@Param("email") String email);
	
	@Transactional
    @Modifying
	@Query("UPDATE UserProfile up SET up.profilePicture = :newProfilePicture WHERE up.id = :userProfileId")
	void updateProfilePicture(@Param("userProfileId") Long userProfileId, @Param("newProfilePicture") String newProfilePicture);
	
	@Query("SELECT u.profilePicture FROM UserProfile u WHERE u.id = :id")
    String findUserProfilePictureById(@Param("id") Long id);
	
	List<UserProfile> findByIdIn(List<Long> ids);
}
