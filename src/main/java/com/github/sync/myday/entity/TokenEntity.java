package com.github.sync.myday.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@Entity(name = "TOKEN_USER")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private long userId;
    private String secretToken;
    private LocalDate dateCreated;

    public TokenEntity() {
    }

    public TokenEntity(String secretToken, LocalDate dateCreated, long userId) {
        this.secretToken = secretToken;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }
}
