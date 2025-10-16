package com.github.sync.myday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "PROFILE_URL")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;
    private String profileUrl;

    public UrlEntity() {
    }

    public UrlEntity(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public UrlEntity(long urlId, String profileUrl) {
        this.urlId = urlId;
        this.profileUrl = profileUrl;
    }
}
