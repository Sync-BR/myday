package com.github.sync.myday.service.admin;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.jwt.JwtUtil;
import com.github.sync.myday.validate.admin.AuthenticAdminValid;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    private final AuthenticAdminValid validateLogin;
    private final JwtUtil util;


    public AdminLoginService(AuthenticAdminValid validateLogin, JwtUtil util) {
        this.validateLogin = validateLogin;
        this.util = util;
    }

    public TokenEntity authenticate(String email, String password) {
       return validateLogin.valida(email, password, util.generateToken(email), true);
    }

}
