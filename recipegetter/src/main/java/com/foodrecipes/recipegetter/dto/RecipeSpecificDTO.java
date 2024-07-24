package com.foodrecipes.recipegetter.dto;

public class RecipeSpecificDTO {
    private String cuisine;
    private String course;
    private String diet;
    private String prepTime;
    private String ingredients;
    private String instructions;

    // Constructor, getters and setters

    public RecipeSpecificDTO(String cuisine, String course, String diet, String prepTime, String ingredients, String instructions) {
        this.cuisine = cuisine;
        this.course = course;
        this.diet = diet;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

	public RecipeSpecificDTO() {
		super();
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
    

    // Getters and Setters
}