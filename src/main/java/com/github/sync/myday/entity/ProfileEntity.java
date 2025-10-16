package com.github.sync.myday.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "PROFILE")
@ToString
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;
    private String profileName;
    private String profileBios;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "urlId")
    private UrlEntity profileUrl;

    public ProfileEntity() {
    }

    public ProfileEntity(String profileName, String profileBios, UrlEntity profileUrl) {
        this.profileName = profileName;
        this.profileBios = profileBios;
        this.profileUrl = profileUrl;
    }

    public ProfileEntity(long profileId, String profileName, String profileBios, UrlEntity profileUrl) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.profileBios = profileBios;
        this.profileUrl = profileUrl;
    }
}
