package com.foodrecipes.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodrecipes.profileapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);	
	
}
