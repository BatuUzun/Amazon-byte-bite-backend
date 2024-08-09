package com.foodrecipes.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				
				.route(p -> p.path("/credentials/**")
						.uri("lb://credentials"))
				
				.route(p -> p.path("/amazon-services/**")
						.uri("lb://amazon-services"))
				
				.route(p -> p.path("/email-sender/**")
						.uri("lb://email-sender"))
				
				.route(p -> p.path("/profile-api/**")
						.uri("lb://profile-api"))
				
				.route(p -> p.path("/profile-picture-downloader/**")
						.uri("lb://profile-picture-downloader"))
				
				.route(p -> p.path("/recipe-picture-downloader/**")
						.uri("lb://recipe-picture-downloader"))
				
				.route(p -> p.path("/search-profile/**")
						.uri("lb://search-profile"))
				
				.route(p -> p.path("/create-recipe/**")
						.uri("lb://create-recipe"))
				
				.route(p -> p.path("/profile-recipe/**")
						.uri("lb://profile-recipe"))
				
				.route(p -> p.path("/recipe-getter/**")
						.uri("lb://recipe-getter"))
				
				.route(p -> p.path("/like-dislike/**")
						.uri("lb://like-dislike"))
				
				.route(p -> p.path("/favorite/**")
						.uri("lb://favorite"))

				.route(p -> p.path("/search-recipe/**")
						.uri("lb://search-recipe"))
				
				.route(p -> p.path("/click/**")
						.uri("lb://click"))
				
				.route(p -> p.path("/comment/**")
						.uri("lb://comment"))
				
				.route(p -> p.path("/profile-getter/**")
						.uri("lb://profile-getter"))
				
				.route(p -> p.path("/feed-following-getter/**")
						.uri("lb://feed-following-getter"))
				
				.build();
					
		
	}
	
}
