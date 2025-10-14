package com.github.sync.myday.validate;

import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.handle.exception.UserNotFound;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.service.TokenService;
import com.github.sync.myday.util.PasswordUtil;
import org.springframework.stereotype.Component;

@Component
public class AuthenticValidate {
    private final UserRepository repository;
    private final TokenService serviceToken;
    private final PasswordUtil util;

    public AuthenticValidate(UserRepository repository, TokenService serviceToken, PasswordUtil util) {
        this.repository = repository;
        this.serviceToken = serviceToken;
        this.util = util;
    }

    public void valida(String email, String password, String  secretToken) {
        UserEntity memory = repository.findByuserEmail(email);
        if (memory == null || !util.checkPassword(password, memory.getPasswordEntity().getUserPassword())) {
            throw new UserNotFound("User not found");
        }
        serviceToken.checkToken(secretToken,  memory);

    }
}
