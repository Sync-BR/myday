package com.github.sync.myday.controller;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.handle.exception.EmailExistingException;
import com.github.sync.myday.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
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

}
