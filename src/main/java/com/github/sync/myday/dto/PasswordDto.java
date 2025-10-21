package com.github.sync.myday.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordDto {
    private long id;
    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
    private String password;

    public PasswordDto() {
    }

    public PasswordDto(long id, String password) {
        this.id = id;
        this.password = password;
    }
}
