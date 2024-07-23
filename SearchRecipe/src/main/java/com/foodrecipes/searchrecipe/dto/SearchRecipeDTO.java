package com.foodrecipes.searchrecipe.dto;

public class SearchRecipeDTO {

	private String recipeName;
	private int page;
	
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
