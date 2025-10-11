package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.PasswordDto;
import com.github.sync.myday.entity.PasswordEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import com.github.sync.myday.util.PasswordUtil;
import org.springframework.stereotype.Component;

@Component
public class PasswordMapper implements MapperImp<PasswordEntity, PasswordDto> {
    private final PasswordUtil util;

    public PasswordMapper(PasswordUtil util) {
        this.util = util;
    }


    @Override
    public PasswordDto convertToDto(PasswordEntity object) {
        return new PasswordDto(
                object.getPasswordId(),
                object.getUserPassword()
        );
    }

    @Override
    public PasswordEntity convertToEntity(PasswordDto object) {
        return new PasswordEntity(
                object.getId(),
                util.encryptPassword(object.getPassword())
        );
    }


}
