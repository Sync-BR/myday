package com.github.sync.myday.entity;

import com.github.sync.myday.enums.PermissionUser;
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
    private int userAge;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private PermissionUser userPermission;
    private LocalDate userCreatedDate;
    private String userName;
    private String userEmail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password", referencedColumnName = "passwordId")
    private PasswordEntity passwordEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "profileId")
    private ProfileEntity userProfile;

    public UserEntity() {
    }

    public UserEntity(long userId, int userAge, boolean isActive, PermissionUser userPermission, LocalDate userCreatedDate, String userName, String userEmail, PasswordEntity passwordEntity) {
        this.userId = userId;
        this.userAge = userAge;
        this.isActive = isActive;
        this.userPermission = userPermission;
        this.userCreatedDate = userCreatedDate;
        this.userName = userName;
        this.userEmail = userEmail;
        this.passwordEntity = passwordEntity;
    }
}
