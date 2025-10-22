package com.github.sync.myday.service;

import com.github.sync.myday.dto.PostDto;
import com.github.sync.myday.entity.PostEntity;
import com.github.sync.myday.mapper.PostMapper;
import com.github.sync.myday.mapper.UserMapper;
import com.github.sync.myday.repository.PostRepository;
import com.github.sync.myday.service.imp.ServiceImp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PostService implements ServiceImp<PostEntity> {
    private final PostRepository repository;
    private final PostMapper mapperPost;
    private final UserMapper mapperUser;
    private final UserService serviceUser;
    public PostService(PostRepository repository, PostMapper mapperPost, UserMapper mapperUser, UserService serviceUser) {
        this.repository = repository;
        this.mapperPost = mapperPost;
        this.mapperUser = mapperUser;
        this.serviceUser = serviceUser;
    }


    public long getAllPostByDate(LocalDate localDate) {
        return repository.countAllPostBycreatedDatePost(localDate);
    }

    public void preparedPost(PostDto memory) {
        memory.setDateCreated(LocalDate.now());
        memory.setHoursCreated(LocalTime.now());
        memory.setUser(mapperUser.convertToDto(serviceUser.searchUserByEmail(memory.getUser().getEmail())));
        System.out.println(mapperPost.convertToEntity(memory));
        save(mapperPost.convertToEntity(memory));
    }

    @Override
    public void save(PostEntity entity) {
        repository.save(entity);
    }

    @Override
    public void update(PostEntity entity) {

    }

    @Override
    public void disable(PostEntity entity) {

    }

    @Override
    public void enable(PostEntity entity) {

    }

    @Override
    public void delete(PostEntity entity) {

    }
}
