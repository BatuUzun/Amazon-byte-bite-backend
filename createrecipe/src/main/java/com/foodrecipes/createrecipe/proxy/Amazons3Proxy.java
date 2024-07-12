package com.foodrecipes.createrecipe.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.foodrecipes.createrecipe.config.FeignConfig;
import com.foodrecipes.createrecipe.response.ResultResponse;


@FeignClient(name = "amazon-services", configuration = FeignConfig.class)
public interface Amazons3Proxy {
	
	@PostMapping(value = "/s3/upload/recipe", consumes = "multipart/form-data")
	ResultResponse uploadRecipe(@RequestPart("file") MultipartFile file);
	
	@DeleteMapping("/s3/delete-recipe/{file}")
	ResultResponse deleteRecipe(@PathVariable("file") String fileName);
}
