package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.ProfileUrlDto;
import com.github.sync.myday.entity.UrlEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import org.springframework.stereotype.Component;

@Component
public class ProfileUrlMapper implements MapperImp<UrlEntity, ProfileUrlDto> {
    @Override
    public ProfileUrlDto convertToDto(UrlEntity object) {
        return new ProfileUrlDto(
                object.getUrlId(),
                object.getProfileUrl()
        );
    }

    @Override
    public UrlEntity convertToEntity(ProfileUrlDto object) {
        return new UrlEntity(
                object.getId(),
                object.getUrl()
        );
    }
}
