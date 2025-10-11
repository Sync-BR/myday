package com.github.sync.myday.service;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.mapper.UserMapper;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.service.imp.ServiceImp;
import com.github.sync.myday.validate.EmailValidate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService implements ServiceImp<UserEntity> {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final EmailValidate validator;

    public UserService(UserRepository repository, UserMapper mapper, EmailValidate validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public void preparedAccount(UserDto memory){
        memory.setCreatedDate(LocalDate.now());
        memory.setActive(true);
        UserEntity memoryEntity = mapper.convertToEntity(memory);
        validator.valida(memoryEntity);
        save(memoryEntity);
    }

    @Override
    public void save(UserEntity entity) {
        repository.save(entity);

    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public void disable(UserEntity entity) {

    }

    @Override
    public void enable(UserEntity entity) {

    }

    @Override
    public void delete(UserEntity entity) {

    }
}
