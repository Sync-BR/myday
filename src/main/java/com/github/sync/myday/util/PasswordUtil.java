package com.github.sync.myday.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private final PasswordEncoder encoder;

    public PasswordUtil(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public boolean checkPassword(String rawPassword, String storeHash) {
        return encoder.matches(rawPassword, storeHash);
    }
}
