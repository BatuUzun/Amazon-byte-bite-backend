package com.foodrecipes.likedislike.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodrecipes.likedislike.entity.RecipeProjection;


@FeignClient(name = "recipe-getter")
public interface RecipeProxy {
	@PostMapping("/recipe-getter/get-recipes")
    public List<RecipeProjection> getRecipes(@RequestParam("ids") List<Long> ids);
}
