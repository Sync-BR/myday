package com.github.sync.myday.dto;

import com.github.sync.myday.enums.CategoryPost;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDto {
    private long id;
    private boolean guest;
    private boolean comments;
    private String body;
    @Enumerated(EnumType.STRING)
    private CategoryPost emotion;
    List<LikesDto> listLike;
    private UserDto user;
    private LocalDate dateCreated;
    private LocalTime hoursCreated;

    public PostDto() {
    }

    public PostDto(long id, boolean guest, boolean comments, String body, CategoryPost emotion, List<LikesDto> listLike, UserDto user) {
        this.id = id;
        this.guest = guest;
        this.comments = comments;
        this.body = body;
        this.emotion = emotion;
        this.listLike = listLike;
        this.user = user;
    }
}
