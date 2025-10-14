package com.github.sync.myday.service;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.repository.TokenRepository;
import org.springframework.transaction.annotation.Transactional;

import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TokenService {
    private final TokenRepository repository;

    public TokenService(TokenRepository repository) {
        this.repository = repository;
    }

    public void checkToken(String token, UserEntity user) {
        List<TokenEntity> entity = repository.findAllByuserId(user.getUserId());
        if (entity == null || entity.isEmpty()) {
            preparedToken(token, user);
        } else {
            deleteToken(entity.get(0).getUserId());
            preparedToken(token, user);
        }
    }

    public void preparedToken(String secretToken, UserEntity memoryUser) {
        TokenEntity memoryToken = new TokenEntity(secretToken, LocalDate.now(), memoryUser.getUserId());
        saveToken(memoryToken);
    }


    private void saveToken(TokenEntity memory) {
        repository.save(memory);
    }

    private void deleteToken(long userId) {
        List<TokenEntity> tokenEntities = repository.findAllByuserId(userId);
        repository.deleteAll(tokenEntities);
    }

}
