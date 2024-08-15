package com.foodrecipes.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1) // Ensure this filter is applied before other filters
public class ApiKeyValidationFilter implements GlobalFilter {

    
    private String validApiKey = AWSSecrets.getSecret(); // Fetch the valid API key from environment variable

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("Valid API Key: " + validApiKey);
        // Check if the header is present and matches the expected key
        if (authorizationHeader == null || !authorizationHeader.equals("Bearer " + validApiKey)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
