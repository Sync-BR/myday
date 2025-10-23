package com.github.sync.myday.service;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.mapper.UserMapper;
import com.github.sync.myday.repository.TokenRepository;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.session.ClientSession;
import org.springframework.transaction.annotation.Transactional;

import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TokenService {
    private final TokenRepository repository;
    private final UserRepository repositoryUser;
    private final UserMapper mapperUser;
    public TokenService(TokenRepository repository, UserRepository repositoryUser, UserMapper mapperUser) {
        this.repository = repository;
        this.repositoryUser = repositoryUser;
        this.mapperUser = mapperUser;
    }

    public long searchToken(String token){
        if (token == null || token.isEmpty()) {
            throw new InvalidTokenException("Invalid token");
        }
        return repository.findBysecretToken(token).getUserId();
    }
    public UserDto compareTokenAndUserEmail(String tokenEmail, String userEmail){
        UserEntity memoryUser = repositoryUser.findByuserEmail(userEmail.toLowerCase());
        if(memoryUser != null){
            if(!memoryUser.getUserEmail().equals(tokenEmail)){
                throw new InvalidTokenException("Token não correspondente!");
            }
        } else{
            throw new InvalidTokenException("Token invalido!");
        }
        return mapperUser.convertToDto(memoryUser);
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
