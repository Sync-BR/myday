package com.github.sync.myday.controller;

import com.github.sync.myday.dto.PostDto;
import com.github.sync.myday.entity.LikeEntity;
import com.github.sync.myday.entity.PostEntity;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.jwt.JwtUtil;
import com.github.sync.myday.service.LikeService;
import com.github.sync.myday.service.PostService;
import com.github.sync.myday.service.TokenService;
import com.github.sync.myday.util.RequestHeaderUtil;
import com.github.sync.myday.validate.TokenValidate;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/post")
public class PostController {
    private final PostService servicePost;
    private final LikeService serviceLike;
    private final TokenService serviceToken;
    private final TokenValidate validate;
    private final JwtUtil jwtUtil;
    private final RequestHeaderUtil utilRequest;

    public PostController(PostService servicePost, LikeService serviceLike, TokenService serviceToken, TokenValidate validate, JwtUtil jwtUtil, RequestHeaderUtil utilRequest) {
        this.servicePost = servicePost;
        this.serviceLike = serviceLike;
        this.serviceToken = serviceToken;
        this.validate = validate;
        this.jwtUtil = jwtUtil;
        this.utilRequest = utilRequest;
    }


    @PostMapping
    public ResponseEntity<?> createPost(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody PostDto memory) {
        utilRequest.checkHeaderBearer(authHeader);
        validate.valida(authHeader.substring(7));
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Token inválido ou expirado!");
        }
        memory.setUser(serviceToken.compareTokenAndUserEmail(jwtUtil.getUsernameFromToken(token), memory.getUser().getEmail()));
        servicePost.preparedPost(memory);
        return ResponseEntity.ok().body("Post created");

    }

    @PostMapping("/like")
    public ResponseEntity<?> createPost(@RequestBody LikeEntity like) {
        System.out.println(like);
        serviceLike.save(like);
        return ResponseEntity.ok().body("Like!");
    }
}
