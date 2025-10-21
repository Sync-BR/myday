package com.github.sync.myday.service;

import com.github.sync.myday.entity.PostEntity;
import com.github.sync.myday.repository.PostRepository;
import com.github.sync.myday.service.imp.ServiceImp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PostService implements ServiceImp<PostEntity> {
    private final PostRepository repository;
    public PostService(PostRepository repository) {
        this.repository = repository;
    }


    public long getAllPostByDate(LocalDate localDate) {
        return repository.countAllPostBycreatedDatePost(localDate);
    }

    public void preparedPost(PostEntity post) {
        post.setCreatedDatePost(LocalDate.now());
        post.setCreatedHourPost(LocalTime.now());
        save(post);
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
