package com.github.sync.myday.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDto {
    private long id;
    @Min(value = 16, message = "A idade mínima é 16 ano.")
    @Max(value = 100, message = "A idade máxima permitida é 100 anos.")
    private int age;
    private boolean active;
    private LocalDate createdDate;
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 4, max = 30, message = "O nome deve ter entre 4 e 30 caracteres.")
    private String name;
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    private String email;
    @Valid
    private PasswordDto password;

    public UserDto() {
    }

    public UserDto(long id, int age, boolean active, LocalDate createdDate, String name, String email) {
        this.id = id;
        this.age = age;
        this.active = active;
        this.createdDate = createdDate;
        this.name = name;
        this.email = email;
    }

    public UserDto(long id, int age, boolean active, LocalDate createdDate, String name, String email, PasswordDto password) {
        this.id = id;
        this.age = age;
        this.active = active;
        this.createdDate = createdDate;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
