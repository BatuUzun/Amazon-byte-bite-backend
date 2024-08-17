package com.foodrecipes.searchrecipe.entity;

import java.time.LocalDateTime;

public class RecipeSearch {
	private Long id;
	private String name;
    private String image;
    private Long ownerId;
    private String description;
    private LocalDateTime dateCreated;

	public Long getId() {
		return id;
	}

	public RecipeSearch(Long id, String name, String image, Long ownerId, String description,
			LocalDateTime dateCreated) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.ownerId = ownerId;
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

	public void setId(Long id) {
		this.id = id;
	}

	public RecipeSearch(Long id, String name, String image, Long ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.ownerId = ownerId;
	}

	
    public RecipeSearch(String recipeName, String imageName, Long ownerId) {
        this.name = recipeName;
        this.image = imageName;
        this.ownerId = ownerId;
    }

    // Getters and setters
    public String getRecipeName() {
        return name;
    }

    public void setRecipeName(String recipeName) {
        this.name = recipeName;
    }

    public String getImageName() {
        return image;
    }

    public void setImageName(String imageName) {
        this.image = imageName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
