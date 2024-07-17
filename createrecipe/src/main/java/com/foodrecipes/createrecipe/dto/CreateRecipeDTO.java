package com.foodrecipes.createrecipe.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;


public class CreateRecipeDTO {
	
	private Long id;

    private String name;

    private String description;
    
    private String cuisine;
    
    private String course;
    
    private String diet;
    
    private String prepTime;
    
    private String ingredients;    
    
    private String instructions;
    
    private String image;

    private LocalDateTime dateCreated = LocalDateTime.now();

    private Long ownerId;

    private MultipartFile file;
    
    private Boolean type;
    
    private Boolean isImgChanged;
    
	public CreateRecipeDTO(Long id, String name, String description, String cuisine, String course, String diet,
			String prepTime, String ingredients, String instructions, String image, LocalDateTime dateCreated,
			Long ownerId, MultipartFile file, Boolean type, Boolean isImgChanged) 
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.cuisine = cuisine;
		this.course = course;
		this.diet = diet;
		this.prepTime = prepTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.image = image;
		this.dateCreated = dateCreated;
		this.ownerId = ownerId;
		this.file = file;
		this.type = type;
		this.isImgChanged = isImgChanged;
	}
	
	public void setIsImgChanged(Boolean change) {
		this.isImgChanged = change;
	}
	
	public Boolean getIsImgChanged() {
		return this.isImgChanged;
	}
	
	public CreateRecipeDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}
	
}
