package com.github.sync.myday.validate;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.jwt.JwtUtil;
import com.github.sync.myday.repository.TokenRepository;
import com.github.sync.myday.validate.imp.ValidateImp;
import org.springframework.stereotype.Component;

@Component
public class TokenValidate implements ValidateImp<String> {
    private final TokenRepository repository;
    private final JwtUtil jwtUtil;

    public TokenValidate(TokenRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void valida(String object) {
        if(repository.findBysecretToken(object) == null){
            throw new InvalidTokenException("Token invalido, reconecte para gerar um novo token");
        }
    }

    public void validateTokenAndUserDate(String token, String Email){
        if(jwtUtil.validateToken(token)){
            if(!jwtUtil.getUsernameFromToken(token).equals(Email)){
                throw new InvalidTokenException("Token não está mais valido");
            }
        }
        throw new InvalidTokenException("Token invalido, reconecte para gerar um novo token");
    }
}
