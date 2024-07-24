package com.foodrecipes.comment.entity;


import java.time.LocalDateTime;


public interface CommentProjection {

    Long getId();

    Long getOwnerId();

    String getComment();

    LocalDateTime getDateCreated();
}