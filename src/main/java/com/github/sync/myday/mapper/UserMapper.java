package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperImp<UserEntity, UserDto> {
    private final PasswordMapper mapper;
    private final ProfileMapper mapperProfile;

    public UserMapper(PasswordMapper mapper, ProfileMapper mapperProfile) {
        this.mapper = mapper;
        this.mapperProfile = mapperProfile;
    }

    @Override
    public UserDto convertToDto(UserEntity object) {
        return new UserDto(
                object.getUserId(),
                object.getUserAge(),
                object.isActive(),
                object.getUserPermission(),
                object.getUserCreatedDate(),
                object.getUserName(),
                object.getUserEmail(),
                mapper.convertToDto(object.getPasswordEntity())

        );
    }

    @Override
    public UserEntity convertToEntity(UserDto object) {
        return new UserEntity(
                object.getId(),
                object.getAge(),
                object.isActive(),
                object.getPermission(),
                object.getCreatedDate(),
                object.getName(),
                object.getEmail(),
                mapper.convertToEntity(object.getPassword())
        );
    }

}
