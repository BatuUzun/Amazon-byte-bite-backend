package com.foodrecipes.profileapi.entity;

public class FollowCountsDTO {
    private long followingsCount;
    private long followersCount;

    public FollowCountsDTO(long followingsCount, long followersCount) {
        this.followingsCount = followingsCount;
        this.followersCount = followersCount;
    }

    public long getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(long followingsCount) {
        this.followingsCount = followingsCount;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

	public FollowCountsDTO() {
		
	}
    
    
}
