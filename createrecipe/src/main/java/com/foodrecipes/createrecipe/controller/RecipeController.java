package com.foodrecipes.createrecipe.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.foodrecipes.createrecipe.entity.Recipe;
import com.foodrecipes.createrecipe.proxy.Amazons3Proxy;
import com.foodrecipes.createrecipe.proxy.ProfileProxy;
import com.foodrecipes.createrecipe.response.ResultResponse;
import com.foodrecipes.createrecipe.service.RecipeService;

@RestController
@RequestMapping("/create-recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private Amazons3Proxy amazons3Proxy;

	@Autowired
	private ProfileProxy profileProxy;

	
	
	@PostMapping("/create-recipe")
	public Recipe createRecipe(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
	        @RequestParam("description") String description,
	        @RequestParam("cuisine") String cuisine,
	        @RequestParam("course") String course,
	        @RequestParam("diet") String diet,
	        @RequestParam("prepTime") String prepTime,
	        @RequestParam("ingredients") String ingredients,
	        @RequestParam("instructions") String instructions,
	        @RequestParam("image") String image,
	        @RequestParam("ownerId") Long ownerId
	        ) throws IOException {
		
		if(profileProxy.isUserExistById(ownerId)) {
			if(name.trim().equals("") || ingredients.trim().equals("") || instructions.trim().equals("")) {
				return null;
			}
			
			ResultResponse response = amazons3Proxy.uploadRecipe(file);
			
			String imageName = "";
		    if (response.getResult() instanceof String) {
		        imageName = (String) response.getResult();
		        if(imageName.equals("")) {
		        	return null;
		        }
		    }
			
			if(imageName.trim().equals("")) {
				return null;
			}
			
			Recipe recipe = new Recipe(0L, name, description, cuisine, course, diet, prepTime, ingredients, instructions, image, ownerId);
	        
			return recipeService.saveRecipe(recipe);
		}

		return null;
	}

	@DeleteMapping("/delete/{id}")
    public void deleteRecipeById(@PathVariable("id") Long id) {
		Recipe recipe = recipeService.getRecipeById(id);
		
		if(recipe != null) {
			if(recipe.getImage() != null && !recipe.getImage().trim().equals("")) {
				amazons3Proxy.deleteRecipe(recipe.getImage());
			}
			
	        recipeService.deleteRecipeById(id);

		}
		
    }

}
