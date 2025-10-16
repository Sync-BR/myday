package com.github.sync.myday.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileDto {
    private long id;
    private String name;
    private String bios;
    private ProfileUrlDto url;

    public ProfileDto() {
    }

    public ProfileDto(long id, String name, String bios, ProfileUrlDto url) {
        this.id = id;
        this.name = name;
        this.bios = bios;
        this.url = url;
    }
}
