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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento e autenticação de usuários.")

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
    @Operation(summary = "Cria uma nova conta de usuário",
            description = "Registra um novo usuário no sistema com validação de dados e verificação de e-mail existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "E-mail já existente ou dados inválidos.",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserDto object) {
        try {
            service.preparedAccount(object);
            return ResponseEntity.ok().build();
        } catch (EmailExistingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica um usuário",
            description = "Realiza login com e-mail e senha, gerando um token JWT em caso de sucesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido."),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado ou credenciais inválidas.",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> loginRequest) {
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
    @Operation(summary = "Acessa rota protegida por token JWT",
            description = "Verifica a validade do token JWT e retorna o nome do usuário autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso permitido."),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado.",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
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
