package com.github.sync.myday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "PASSWORD")
@Getter
@Setter
@ToString
public class PasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passwordId;
    private String userPassword;

    public PasswordEntity() {
    }

    public PasswordEntity(long passwordId, String userPassword) {
        this.passwordId = passwordId;
        this.userPassword = userPassword;
    }
}
