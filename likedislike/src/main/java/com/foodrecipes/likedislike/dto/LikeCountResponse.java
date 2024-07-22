package com.foodrecipes.likedislike.dto;

public class LikeCountResponse {
    private long likes;
    

    public LikeCountResponse() {
    }

    public LikeCountResponse(long likes) {
        this.likes = likes;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

   
}