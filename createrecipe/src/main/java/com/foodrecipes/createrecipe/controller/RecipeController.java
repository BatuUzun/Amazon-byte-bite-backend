package com.foodrecipes.createrecipe.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/create-draft")
	public Recipe createRecipeDraftWithImage(
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
	        @RequestParam("dateCreated") LocalDateTime dateCreated,
	        @RequestParam("ownerId") Long ownerId,
	        @RequestParam("type") Boolean type,
	        @RequestParam("isImgChanged") Boolean isImgChanged
			) throws IOException 
	{
		
		if(profileProxy.isUserExistById(ownerId) && !name.trim().equals("")) {
			ResultResponse response = amazons3Proxy.uploadRecipe(file);
			
			String imageName = "";
		    if (response.getResult() instanceof String) {
		        imageName = (String) response.getResult();
		        if(imageName.equals("")) {
		        	return null;
		        }
		    }
		    
		    // CHECK IF USER CHANGES RECIPE IMAGE
			// IF ISIMGCHANGED TRUE OR FALSE
		    
		    Recipe recipe = new Recipe(0L, name, description, cuisine, course, diet, prepTime, ingredients, instructions, image, dateCreated, ownerId, type); 
	        
	        if(imageName.equals("")) {
	        	recipe.setImage(null);
	        }
	        else {
	        	recipe.setImage(imageName);	
	        }

			return recipeService.saveRecipe(recipe);
		}

		return null;
	}
	
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
	        @RequestParam("dateCreated") LocalDateTime dateCreated,
	        @RequestParam("ownerId") Long ownerId,
	        @RequestParam("type") Boolean type
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
			
			Recipe recipe = new Recipe(0L, name, description, cuisine, course, diet, prepTime, ingredients, instructions, image, dateCreated, ownerId, type);
	        
			return recipeService.saveRecipe(recipe);
		}

		return null;
	}

	@GetMapping("/drafts")
    public List<Recipe> getDraftsByUserId(
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("page") int page) {
		if(profileProxy.isUserExistById(ownerId)) {
			return recipeService.getDrafts(ownerId, page);
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
	
	@PutMapping("/update-recipe")
	public ResponseEntity<Recipe> updateRecipe(
			@RequestParam("file") MultipartFile file,
			@RequestParam("id") Long id,
			@RequestParam("name") String name,
	        @RequestParam("description") String description,
	        @RequestParam("cuisine") String cuisine,
	        @RequestParam("course") String course,
	        @RequestParam("diet") String diet,
	        @RequestParam("prepTime") String prepTime,
	        @RequestParam("ingredients") String ingredients,
	        @RequestParam("instructions") String instructions,
	        @RequestParam("image") String image,
	        @RequestParam("type") Boolean type,
	        @RequestParam("isImgChanged") Boolean isImgChanged
			) {
		
		System.out.println(file);
	    Recipe recipe = recipeService.findById(id);
	    if (recipe != null && !name.trim().isEmpty()) {
	        ResultResponse response = null;
	        if (isImgChanged) {
	        	if(recipe.getImage() != null)
	        		amazons3Proxy.deleteRecipe(recipe.getImage());
	        	if(file != null && !file.isEmpty())
	        		response = amazons3Proxy.uploadRecipe(file);

	            String imageName = null;
	            if(response != null) {
	            	if (response.getResult() instanceof String) {
		                imageName = (String) response.getResult();
		                if (imageName.isEmpty()) {
		                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		                }
	            	}
	            }
	            
	            recipe.setImage(imageName);
	        }
	        
	        recipe.setId(id);
	        recipe.setName(name);
	        recipe.setDescription(description);
	        recipe.setCuisine(cuisine);
	        recipe.setCourse(course);
	        recipe.setDiet(diet);
	        recipe.setPrepTime(prepTime);
	        recipe.setIngredients(ingredients);
	        recipe.setInstructions(instructions);	        
	        recipe.setDateCreated(LocalDateTime.now());
	        recipe.setType(type);
	        
	        if (ingredients.isBlank() || instructions.isBlank() || recipe.getImage() == null || recipe.getImage().trim().isEmpty()) {
	            recipe.setType(false);
	        } else {
	            recipe.setType(type);
	        }

	        recipeService.updateRecipe(recipe);
	        return ResponseEntity.ok(recipe);
	    }

	    return ResponseEntity.notFound().build();
	}

}
