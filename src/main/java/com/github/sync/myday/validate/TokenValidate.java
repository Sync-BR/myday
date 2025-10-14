package com.github.sync.myday.validate;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.repository.TokenRepository;
import com.github.sync.myday.validate.imp.ValidateImp;
import org.springframework.stereotype.Component;

@Component
public class TokenValidate implements ValidateImp<String> {
    private final TokenRepository repository;

    public TokenValidate(TokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void valida(String object) {
        if(repository.findBysecretToken(object) == null){
            throw new InvalidTokenException("Token invalido, reconecte para gerar um novo token");
        }
    }
}
