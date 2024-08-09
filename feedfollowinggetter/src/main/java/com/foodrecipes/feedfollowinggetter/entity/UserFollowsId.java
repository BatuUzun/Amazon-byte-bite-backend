package com.foodrecipes.feedfollowinggetter.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserFollowsId implements Serializable {

    private Long followerId;
    private Long followedId;

    public UserFollowsId() {}

    public UserFollowsId(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    // Getters, setters, equals, and hashCode methods

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollowsId that = (UserFollowsId) o;
        return Objects.equals(followerId, that.followerId) && Objects.equals(followedId, that.followedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followedId);
    }
}