package com.github.sync.myday.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class LikesDto {
    private long id;
    private PostDto post;
    private UserDto user;
    private LocalDate date;
    private LocalTime time;


    public LikesDto(long id, PostDto post, UserDto user, LocalDate date, LocalTime time) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.date = date;
        this.time = time;
    }
}
