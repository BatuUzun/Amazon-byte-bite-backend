package com.foodrecipes.recipegetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecipegetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipegetterApplication.class, args);
	}

}
