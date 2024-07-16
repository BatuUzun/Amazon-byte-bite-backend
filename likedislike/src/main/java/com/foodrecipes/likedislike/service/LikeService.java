package com.foodrecipes.likedislike.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.foodrecipes.likedislike.entity.Like;
import com.foodrecipes.likedislike.repository.LikeRepository;


@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like addLike(Like like) {
        return likeRepository.save(like);
    }
    
    
}