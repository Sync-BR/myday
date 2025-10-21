package com.github.sync.myday.service;

import com.github.sync.myday.entity.LikeEntity;
import com.github.sync.myday.repository.LikeRepository;
import com.github.sync.myday.service.imp.ServiceImp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LikeService implements ServiceImp<LikeEntity> {
    private final LikeRepository repository;

    public LikeService(LikeRepository repository) {
        this.repository = repository;
    }

    public long getLikeByDate(LocalDate date) {
        return repository.countByLikeDate(date);
    }

    @Override
    public void save(LikeEntity entity) {
        repository.save(entity);
    }

    @Override
    public void update(LikeEntity entity) {

    }

    @Override
    public void disable(LikeEntity entity) {

    }

    @Override
    public void enable(LikeEntity entity) {

    }

    @Override
    public void delete(LikeEntity entity) {

    }
}
