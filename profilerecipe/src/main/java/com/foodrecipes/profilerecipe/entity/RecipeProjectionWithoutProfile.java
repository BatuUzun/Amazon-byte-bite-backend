package com.foodrecipes.profilerecipe.entity;

import java.time.LocalDateTime;

public interface RecipeProjectionWithoutProfile {
    Long getId();
    String getName();
    String getDescription();
    LocalDateTime getDateCreated();
    String getImage();
    Long getOwnerId();
}