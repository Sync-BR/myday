package com.github.sync.myday.service;

import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.validate.AuthenticValidate;
import org.springframework.stereotype.Service;

@Service
public class AuthenticService {
    private final TokenService service;
    private final AuthenticValidate validate;

    public AuthenticService(TokenService service, AuthenticValidate validate) {
        this.service = service;
        this.validate = validate;
    }

    public void login(String email, String password, String secretToken) {
        validate.valida(email, password, secretToken);

    }
}
