package com.github.sync.myday.repository;

import com.github.sync.myday.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByuserEmail(String email);
    UserEntity findByuserId(long id);

    Long countByUserCreatedDateBetween(LocalDate startDate, LocalDate endDate);

}
