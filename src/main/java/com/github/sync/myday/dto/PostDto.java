package com.github.sync.myday.dto;

import com.github.sync.myday.enums.CategoryPost;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Comments não pode ser nulo")
    private boolean comments;
    @NotBlank(message = "Body não pode estar vazio")
    @Size(min = 1, max = 1000, message = "Body deve ter entre 1 e 1000 caracteres")
    private String body;
    @NotNull(message = "Emotion não pode ser nula")
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
