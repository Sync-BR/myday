package com.github.sync.myday.repository;

import com.github.sync.myday.entity.TokenEntity;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    TokenEntity findBysecretToken(String token);
    List<TokenEntity> findAllByuserId(long userId);

}
