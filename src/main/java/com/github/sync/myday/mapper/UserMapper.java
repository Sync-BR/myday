package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperImp<UserEntity, UserDto> {
    private final PasswordMapper mapper;

    public UserMapper(PasswordMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDto convertToDto(UserEntity object) {
        return new UserDto(
                object.getUserId(),
                object.getUserAge(),
                object.isActive(),
                object.getUserCreatedDate(),
                object.getUserName(),
                object.getUserEmail()
        );
    }

    @Override
    public UserEntity convertToEntity(UserDto object) {
        return new UserEntity(
                object.getId(),
                object.isActive(),
                object.getAge(),
                object.getCreatedDate(),
                object.getName(),
                object.getEmail(),
                mapper.convertToEntity(object.getPassword())
        );
    }

}
