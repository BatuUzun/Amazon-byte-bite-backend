package com.foodrecipes.likedislike.entity;

import java.time.LocalDateTime;

public class RecipeProjection {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateCreated;
    private String image;
    private Long ownerId;
    private String username;
    private String ownerImage;
	public RecipeProjection(Long id, String name, String description, LocalDateTime dateCreated, String image,
			Long ownerId, String username, String ownerImage) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.image = image;
		this.ownerId = ownerId;
		this.username = username;
		this.ownerImage = ownerImage;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOwnerImage() {
		return ownerImage;
	}
	public void setOwnerImage(String ownerImage) {
		this.ownerImage = ownerImage;
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
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public RecipeProjection(Long id, String name, String description, LocalDateTime dateCreated, String image,
			Long ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.image = image;
		this.ownerId = ownerId;
	}
	public RecipeProjection() {
		super();
	}
    
    
}