package com.github.sync.myday.repository;

import com.github.sync.myday.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    long countAllPostBycreatedDatePost(LocalDate date);
}
