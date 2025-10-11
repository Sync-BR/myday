package com.github.sync.myday.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity(name = "USER")
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private boolean isActive;
    private int userAge;
    private LocalDate userCreatedDate;
    private String userName;
    private String userEmail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password", referencedColumnName = "passwordId")
    private PasswordEntity passwordEntity;

    public UserEntity() {
    }

    public UserEntity(long userId, boolean isActive, int userAge, LocalDate userCreatedDate, String userName, String userEmail, PasswordEntity passwordEntity) {
        this.userId = userId;
        this.isActive = isActive;
        this.userAge = userAge;
        this.userCreatedDate = userCreatedDate;
        this.userName = userName;
        this.userEmail = userEmail;
        this.passwordEntity = passwordEntity;
    }
}
