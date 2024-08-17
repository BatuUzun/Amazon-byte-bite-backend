package com.foodrecipes.searchrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SearchRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchRecipeApplication.class, args);
	}

}
