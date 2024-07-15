package com.foodrecipes.searchprofile.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodrecipes.searchprofile.dto.SearchCriteria;
import com.foodrecipes.searchprofile.entity.UserProfile;
import com.foodrecipes.searchprofile.service.UserProfileService;

@RestController
@RequestMapping("/search-profile")
public class SearchController {
	
	@Autowired
    private UserProfileService userProfileService;
	@Autowired
	private Environment environment;
	
    @PostMapping("/search")
    public List<UserProfile> searchUserProfiles(@RequestBody SearchCriteria searchCriteria) {
    	String port = environment.getProperty("local.server.port");
        System.out.println("port: " + port);
        return userProfileService.searchUserProfilesByUsername(searchCriteria.getUsername(), searchCriteria.getPage());
    }
	
}
