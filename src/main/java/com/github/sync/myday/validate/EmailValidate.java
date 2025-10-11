package com.github.sync.myday.validate;

import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.handle.exception.EmailExistingException;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.validate.imp.ValidateImp;
import org.springframework.stereotype.Component;
@Component
public class EmailValidate implements ValidateImp<UserEntity> {
    private final UserRepository repository;

    public EmailValidate(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void valida(UserEntity object) {
        if(repository.findByuserEmail(object.getUserEmail()) != null){
            throw new EmailExistingException("Email already exists");
        }
    }
}
