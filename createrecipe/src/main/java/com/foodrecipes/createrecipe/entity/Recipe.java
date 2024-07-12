package com.foodrecipes.createrecipe.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(name = "date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "owner_id")
    private Long ownerId;

    private Boolean type;

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

	public void setDateCreated() {
		this.dateCreated = LocalDateTime.now();
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Recipe(Long id, String name, String ingredients, String instructions, String image,
			LocalDateTime dateCreated, Long ownerId, Boolean type) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.image = image;
		this.dateCreated = dateCreated;
		this.ownerId = ownerId;
		this.type = type;
	}

	public Recipe() {
		super();
	}

    
    
    
}