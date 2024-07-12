package com.foodrecipes.profileapi.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_follows")
public class UserFollows {

    @EmbeddedId
    private UserFollowsId id;

    @MapsId("followerId")
    @ManyToOne
    @JoinColumn(name = "follower_id", foreignKey = @ForeignKey(name = "fk_follower_id"))
    private UserProfile follower;

    @MapsId("followedId")
    @ManyToOne
    @JoinColumn(name = "followed_id", foreignKey = @ForeignKey(name = "fk_followed_id"))
    private UserProfile followed;

    // Getters and setters
    public UserFollowsId getId() {
        return id;
    }

    public void setId(UserFollowsId id) {
        this.id = id;
    }

    public UserProfile getFollower() {
        return follower;
    }

    public void setFollower(UserProfile follower) {
        this.follower = follower;
    }

    public UserProfile getFollowed() {
        return followed;
    }

    public void setFollowed(UserProfile followed) {
        this.followed = followed;
    }

    @Embeddable
    public static class UserFollowsId implements Serializable {

        @Column(name = "follower_id")
        private Long followerId;

        @Column(name = "followed_id")
        private Long followedId;

        // Constructors, getters, setters, hashCode, and equals
        public UserFollowsId(Long followerId, Long followedId) {
            this.followerId = followerId;
            this.followedId = followedId;
        }

        public UserFollowsId() {
        }

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
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((followerId == null) ? 0 : followerId.hashCode());
            result = prime * result + ((followedId == null) ? 0 : followedId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UserFollowsId other = (UserFollowsId) obj;
            if (followerId == null) {
                if (other.followerId != null) {
                    return false;
                }
            } else if (!followerId.equals(other.followerId)) {
                return false;
            }
            if (followedId == null) {
                if (other.followedId != null) {
                    return false;
                }
            } else if (!followedId.equals(other.followedId)) {
                return false;
            }
            return true;
        }
    }
}
