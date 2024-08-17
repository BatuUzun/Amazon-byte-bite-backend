package com.foodrecipes.searchrecipe.entity;

import java.time.LocalDateTime;

public class RecipeSearchResult {
	private Long id;
	private String name;
    private String image;
    private Long ownerId;
    private String ownerUsername;
    private String description;
    private LocalDateTime dateCreated;
    
	public RecipeSearchResult(Long id, String name, String image, Long ownerId, String ownerUsername,
			String description, LocalDateTime dateCreated) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.ownerId = ownerId;
		this.ownerUsername = ownerUsername;
		this.description = description;
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RecipeSearchResult(Long id, String name, String image, Long ownerId, String ownerUsername) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.ownerId = ownerId;
		this.ownerUsername = ownerUsername;
	}

	

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public RecipeSearchResult() {
		super();
	}

	public RecipeSearchResult(String name, String image, Long ownerId, String ownerUsername) {
		super();
		this.name = name;
		this.image = image;
		this.ownerId = ownerId;
		this.ownerUsername = ownerUsername;
	}

	public RecipeSearchResult(String recipeName, String imageName, Long ownerId) {
        this.name = recipeName;
        this.image = imageName;
        this.ownerId = ownerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
