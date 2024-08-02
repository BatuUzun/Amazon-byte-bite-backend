package com.foodrecipes.comment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.comment.entity.CommentProjection;
import com.foodrecipes.comment.entity.CommentProjectionWithProfile;
import com.foodrecipes.comment.entity.UserProfile;
import com.foodrecipes.comment.proxy.UserProfileProxy;
import com.foodrecipes.comment.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserProfileProxy userProfileProxy;
	
	@GetMapping("/count/{recipeId}")
    public long countCommentsByRecipeId(@PathVariable("recipeId") Long recipeId) {
        return commentService.countCommentsByRecipeId(recipeId);
    }
	
	@GetMapping("/get-comments/{recipeId}/{page}")
    public List<CommentProjectionWithProfile> getComment(@PathVariable("recipeId") Long recipeId, @PathVariable("page") int page) {
    	List<CommentProjection> list = commentService.findByRecipeId(recipeId, page);
    	if(list.size() == 0) {
    		return null;
    	}
    	
    	List<Long> idOfUserProfiles = new ArrayList<>();
        for(int i = 0;i<list.size();i++) {
        	
        	idOfUserProfiles.add(list.get(i).getOwnerId());
        }
        List<UserProfile> upList = new ArrayList<>(userProfileProxy.getUserProfiles(idOfUserProfiles));
        
        List<CommentProjectionWithProfile> commentWithProfile = new ArrayList<CommentProjectionWithProfile>();
        
        for(int i = 0; i < list.size(); i++) {
        	CommentProjectionWithProfile cp = new CommentProjectionWithProfile(list.get(i).getId(), recipeId, list.get(i).getOwnerId(), list.get(i).getComment(), list.get(i).getDateCreated(), null, null);
        	commentWithProfile.add(cp);
        	commentWithProfile.get(i).setUsername(upList.get(i).getUsername());
        	commentWithProfile.get(i).setProfilePicture(upList.get(i).getProfilePicture());
        }
        return commentWithProfile;
    }
	
}
