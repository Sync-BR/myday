package com.github.sync.myday.service;

import com.github.sync.myday.dto.ProfileDto;
import com.github.sync.myday.dto.ProfileUrlDto;
import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.ProfileEntity;
import com.github.sync.myday.entity.UrlEntity;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.enums.ActivityEnum;
import com.github.sync.myday.enums.PermissionUser;
import com.github.sync.myday.mapper.UserMapper;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.service.imp.ServiceImp;
import com.github.sync.myday.util.ActivityUtil;
import com.github.sync.myday.util.ProfileUtil;
import com.github.sync.myday.validate.EmailValidate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService implements ServiceImp<UserEntity> {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final EmailValidate validator;
    private final ProfileUtil utilProfile;
    private final ActivityUtil utilActivity;

    public UserService(UserRepository repository, UserMapper mapper, EmailValidate validator, ProfileUtil utilProfile, ActivityUtil utilActivity) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.utilProfile = utilProfile;
        this.utilActivity = utilActivity;
    }


    public long countAllUser(){
        return repository.count();
    }

    public void preparedAccount(UserDto memory){
        memory.setCreatedDate(LocalDate.now());
        memory.setActive(true);
        memory.setPermission(PermissionUser.USER);
      //  memory.setProfile(new ProfileDto(0, memory.getName(), "Bem vindo", new ProfileUrlDto(0, utilProfile.generateUrl(memory.getName()))));
        UserEntity memoryEntity = mapper.convertToEntity(memory);
        // Criar validação de perfil
        validator.valida(memoryEntity);
        save(memoryEntity);
        utilActivity.addActivity(ActivityEnum.REGISTERED, memory.getName());
    }

    public UserEntity searchUserById(long userId) {
        return repository.findByuserId(userId);
    }
    public UserEntity searchUserByEmail(String email) {
        return repository.findByuserEmail(email);
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
