package com.github.sync.myday.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Entity(name = "LIKE_POST")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLike;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private UserEntity likeDateUser;
    private LocalDate likeDate;
    private LocalTime likeHour;

    public LikeEntity() {
    }

    public LikeEntity(long idLike, PostEntity post, UserEntity user, LocalDate likeDate, LocalTime likeHour) {
        this.idLike = idLike;
        this.post = post;
        this.likeDateUser = user;
        this.likeDate = likeDate;
        this.likeHour = likeHour;
    }
}
