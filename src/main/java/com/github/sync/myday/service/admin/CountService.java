package com.github.sync.myday.service.admin;

import com.github.sync.myday.service.LikeService;
import com.github.sync.myday.service.PostService;
import com.github.sync.myday.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CountService {
    private final UserService serviceUser;
    private final PostService servicePost;
    private final LikeService serviceLike;

    public CountService(UserService serviceUser, PostService servicePost, LikeService serviceLike) {
        this.serviceUser = serviceUser;
        this.servicePost = servicePost;
        this.serviceLike = serviceLike;
    }
    
    
    public long countAllUser(){
        return serviceUser.countAllUser();
    }

    public long countAllPostByDate(){
        return servicePost.getAllPostByDate(LocalDate.now());
    }
    public long countAllLikeByDate(){
        return serviceLike.getLikeByDate(LocalDate.now());
    }
}
