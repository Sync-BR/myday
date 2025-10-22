package com.github.sync.myday.entity;

import com.github.sync.myday.enums.CategoryPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@Entity(name = "POST_USER")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPost;
    private boolean guest;
    private boolean comments;
    @Column(length = 550)
    private String body;
    @Enumerated(EnumType.STRING)
    private CategoryPost emotion;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity postUser;
    private LocalDate createdDatePost;
    private LocalTime createdHourPost;

    public PostEntity() {
    }

    public PostEntity(long idPost, boolean guest, boolean comments, String body, CategoryPost emotion, List<LikeEntity> likes, UserEntity postUser) {
        this.idPost = idPost;
        this.guest = guest;
        this.comments = comments;
        this.body = body;
        this.emotion = emotion;
        this.likes = likes;
        this.postUser = postUser;
    }

    public PostEntity(long idPost, boolean guest, boolean comments, String body, CategoryPost emotion, List<LikeEntity> likes, UserEntity postUser, LocalDate createdDatePost, LocalTime createdHourPost) {
        this.idPost = idPost;
        this.guest = guest;
        this.comments = comments;
        this.body = body;
        this.emotion = emotion;
        this.likes = likes;
        this.postUser = postUser;
        this.createdDatePost = createdDatePost;
        this.createdHourPost = createdHourPost;
    }
}
