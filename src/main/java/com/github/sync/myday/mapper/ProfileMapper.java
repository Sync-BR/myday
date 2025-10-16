package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.ProfileDto;
import com.github.sync.myday.entity.ProfileEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper implements MapperImp<ProfileEntity, ProfileDto> {

    private final ProfileUrlMapper mapper;

    public ProfileMapper(ProfileUrlMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProfileDto convertToDto(ProfileEntity object) {
        return new ProfileDto(
                object.getProfileId(),
                object.getProfileName(),
                object.getProfileBios(),
                mapper.convertToDto(object.getProfileUrl())
        );
    }

    @Override
    public ProfileEntity convertToEntity(ProfileDto object) {
        return new ProfileEntity(
            object.getId(),
                object.getName(),
                object.getBios(),
                mapper.convertToEntity(object.getUrl())
        );
    }
}
