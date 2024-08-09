package com.foodrecipes.feedfollowinggetter.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_follows")
@IdClass(UserFollowsId.class)
public class UserFollows {

    @Id
    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @Id
    @Column(name = "followed_id", nullable = false)
    private Long followedId;

    @Column(name = "date_created", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    // Getters and setters
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
