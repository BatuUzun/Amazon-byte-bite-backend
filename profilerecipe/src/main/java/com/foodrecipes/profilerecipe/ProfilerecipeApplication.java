package com.foodrecipes.profilerecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProfilerecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilerecipeApplication.class, args);
	}

}
