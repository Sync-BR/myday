package com.github.sync.myday.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileUrlDto {
    private long id;
    private String url;

    public ProfileUrlDto() {
    }

    public ProfileUrlDto(long id, String url) {
        this.id = id;
        this.url = url;
    }
}
