package com.foodrecipes.comment.entity;

import java.time.LocalDateTime;


public class CommentProjectionWithProfile{
	
    private Long id;

    private Long recipeId;

    private Long ownerId;

    private String comment;

    private LocalDateTime dateCreated;
    
    private String username;

    private String profilePicture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public CommentProjectionWithProfile(Long id, Long recipeId, Long ownerId, String comment, LocalDateTime dateCreated,
			String username, String profilePicture) {
		super();
		this.id = id;
		this.recipeId = recipeId;
		this.ownerId = ownerId;
		this.comment = comment;
		this.dateCreated = dateCreated;
		this.username = username;
		this.profilePicture = profilePicture;
	}

	public CommentProjectionWithProfile() {
		super();
	}
    
    
}
