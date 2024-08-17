package com.foodrecipes.searchprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.foodrecipes.searchprofile.constants.Constants;
import com.foodrecipes.searchprofile.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
    @Query(value = "SELECT * FROM user_profile u WHERE u.username LIKE :username% LIMIT "+Constants.PAGE_LIMIT, nativeQuery = true)
	List<UserProfile> findByUsernameStartingWith(@Param("username") String username);
}
