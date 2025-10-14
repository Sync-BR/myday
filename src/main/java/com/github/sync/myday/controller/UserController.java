package com.github.sync.myday.controller;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.handle.exception.EmailExistingException;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.handle.exception.UserNotFound;
import com.github.sync.myday.jwt.JwtUtil;
import com.github.sync.myday.service.AuthenticService;
import com.github.sync.myday.service.TokenService;
import com.github.sync.myday.service.UserService;
import com.github.sync.myday.validate.TokenValidate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")

public class UserController {

    private final UserService service;
    private final AuthenticService serviceAuthentic;
    private final TokenService tokenService;
    private final TokenValidate validate;
    private final JwtUtil jwtUtil;

    public UserController(UserService service, AuthenticService serviceAuthentic, TokenService tokenService, TokenValidate validate, JwtUtil jwtUtil) {
        this.service = service;
        this.serviceAuthentic = serviceAuthentic;
        this.tokenService = tokenService;
        this.validate = validate;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserDto object) {
        try {
            service.preparedAccount(object);
            return ResponseEntity.ok().build();
        } catch (EmailExistingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> loginRequest) {
        System.out.println("loginRequest: " + loginRequest);
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        try {
            String token = jwtUtil.generateToken(email);
            serviceAuthentic.login(email, password, token);
            return ResponseEntity.ok("Conectado com sucesso");
        } catch (UserNotFound | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> protectedEndpoint(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Token ausente ou inválido!");
        }
        try {
            validate.valida(authHeader.substring(7));
            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(401).body("Token inválido ou expirado!");
            }
            String username = jwtUtil.getUsernameFromToken(token);
            return ResponseEntity.ok("Você acessou uma rota protegida! Usuário: " + username);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(401).body("Token inválido ou expirado!");
        }
    }


}
