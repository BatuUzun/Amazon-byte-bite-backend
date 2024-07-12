package com.foodrecipes.createrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.foodrecipes.createrecipe")
public class CreaterecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreaterecipeApplication.class, args);
	}

}
