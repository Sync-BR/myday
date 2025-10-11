package com.github.sync.myday.repository;

import com.github.sync.myday.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByuserEmail(String email);
}
