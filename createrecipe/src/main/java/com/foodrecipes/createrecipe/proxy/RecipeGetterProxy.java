package com.foodrecipes.createrecipe.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "recipe-getter")
public interface RecipeGetterProxy {
	
	@PostMapping("/recipe-getter/add-to-cache")
    void addNewRecipeToCache(@RequestParam("newRecipeId") Long newRecipeId);
}
