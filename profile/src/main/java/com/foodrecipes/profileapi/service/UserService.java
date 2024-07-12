package com.foodrecipes.profileapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.profileapi.entity.User;
import com.foodrecipes.profileapi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
	
	
}
