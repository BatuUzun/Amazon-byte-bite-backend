package com.foodrecipes.profilerecipe.entity;

public class UserProfile {

	
    private Long id;

    private String username;

    
    private String profilePicture;
    
   
    
    public UserProfile() {
		super();
	}

	// Getters and setters

    public String getUsername() {
        return username;
    }

    public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Long getId() {
		return id;
	}

	public UserProfile(String username, String profilePicture) {
		super();
		this.username = username;
		this.profilePicture = profilePicture;
	}

	public void setUsername(String username) {
        this.username = username;
    }

}
