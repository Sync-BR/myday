package com.github.sync.myday.service;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.repository.TokenRepository;
import com.github.sync.myday.session.ClientSession;
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

    public TokenEntity checkToken(String token, UserEntity user) {
        List<TokenEntity> entity = repository.findAllByuserId(user.getUserId());
        TokenEntity memoryToken = new TokenEntity();
        if (entity == null || entity.isEmpty()) {
            memoryToken = preparedToken(token, user);
        } else {
            deleteToken(entity.get(0).getUserId());
            memoryToken = preparedToken(token, user);
        }
        saveToken(memoryToken);
        return memoryToken;
    }

    private TokenEntity preparedToken(String secretToken, UserEntity memoryUser) {
        return new TokenEntity(secretToken, LocalDate.now(), memoryUser.getUserId());
    }


    private void saveToken(TokenEntity memory) {
        repository.save(memory);
    }

    private void deleteToken(long userId) {
        List<TokenEntity> tokenEntities = repository.findAllByuserId(userId);
        repository.deleteAll(tokenEntities);
    }

}
