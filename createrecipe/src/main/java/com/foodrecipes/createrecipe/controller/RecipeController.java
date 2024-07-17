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

import com.foodrecipes.createrecipe.dto.CreateRecipeDTO;
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
			@RequestBody CreateRecipeDTO createRecipeDTO
			) throws IOException 
	{
		
		if(profileProxy.isUserExistById(createRecipeDTO.getOwnerId()) && !createRecipeDTO.getName().trim().equals("")) {
			ResultResponse response = amazons3Proxy.uploadRecipe(createRecipeDTO.getFile());
			
			String imageName = "";
		    if (response.getResult() instanceof String) {
		        imageName = (String) response.getResult();
		        if(imageName.equals("")) {
		        	return null;
		        }
		    }
			
			Recipe recipe = new Recipe();
			
	        recipe.setName(createRecipeDTO.getName());
	        recipe.setIngredients(createRecipeDTO.getIngredients());
	        recipe.setInstructions(createRecipeDTO.getInstructions());
	        
	        if(imageName.equals("")) {
	        	recipe.setImage(null);
	        }
	        else {
	        	recipe.setImage(imageName);	
	        }
	        recipe.setOwnerId(createRecipeDTO.getOwnerId());
	        recipe.setType(false);
			return recipeService.saveRecipe(recipe);
		}

		return null;
	}
	
	@PostMapping("/create-recipe")
	public Recipe createRecipe(
			@RequestBody CreateRecipeDTO createRecipeDTO
			) throws IOException {
		
		if(profileProxy.isUserExistById(createRecipeDTO.getOwnerId())) {
			if(createRecipeDTO.getName().trim().equals("") || createRecipeDTO.getIngredients().trim().equals("") || createRecipeDTO.getInstructions().trim().equals("")) {
				return null;
			}
			ResultResponse response = amazons3Proxy.uploadRecipe(createRecipeDTO.getFile());
			
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
			
			Recipe recipe = new Recipe();
			
	        recipe.setName(createRecipeDTO.getName());
	        recipe.setIngredients(createRecipeDTO.getIngredients());
	        recipe.setInstructions(createRecipeDTO.getInstructions());
	        recipe.setImage(imageName);
	        recipe.setOwnerId(createRecipeDTO.getOwnerId());
	        recipe.setType(true);
	        
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
			@RequestBody CreateRecipeDTO createRecipeDTO)
	{
		
		System.out.println(createRecipeDTO.getFile());
	    Recipe recipe = recipeService.findById(createRecipeDTO.getId());
	    if (recipe != null && !createRecipeDTO.getName().trim().isEmpty()) {
	        ResultResponse response = null;
	        if (createRecipeDTO.getIsImgChanged()) {
	        	if(recipe.getImage() != null)
	        		amazons3Proxy.deleteRecipe(recipe.getImage());
	        	if(createRecipeDTO.getFile() != null && !createRecipeDTO.getFile().isEmpty())
	        		response = amazons3Proxy.uploadRecipe(createRecipeDTO.getFile());

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
	        
	        recipe.setIngredients(createRecipeDTO.getIngredients());
	        recipe.setInstructions(createRecipeDTO.getInstructions());
	        recipe.setName(createRecipeDTO.getName());
	        recipe.setDateCreated(LocalDateTime.now()); // Update dateCreated here

	        if (createRecipeDTO.getIngredients().isBlank() || createRecipeDTO.getInstructions().isBlank() || recipe.getImage() == null || recipe.getImage().trim().isEmpty()) {
	            recipe.setType(false);
	        } else {
	            recipe.setType(createRecipeDTO.getType());
	        }

	        recipeService.updateRecipe(recipe);
	        return ResponseEntity.ok(recipe);
	    }

	    return ResponseEntity.notFound().build();
	}

}
