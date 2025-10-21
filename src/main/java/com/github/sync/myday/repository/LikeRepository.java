package com.github.sync.myday.repository;

import com.github.sync.myday.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    long countByLikeDate(LocalDate Date);
}
