package com.github.sync.myday.validate.admin;

import com.github.sync.myday.entity.TokenEntity;
import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.enums.PermissionUser;
import com.github.sync.myday.handle.exception.UserNotFound;
import com.github.sync.myday.handle.exception.WithoutPermission;
import com.github.sync.myday.mapper.UserMapper;
import com.github.sync.myday.repository.UserRepository;
import com.github.sync.myday.service.TokenService;
import com.github.sync.myday.session.ClientSession;
import com.github.sync.myday.util.PasswordUtil;
import org.springframework.stereotype.Component;

@Component
public class AuthenticAdminValid {
    private final UserRepository repository;
    private final TokenService serviceToken;
    private final UserMapper userMapper;
    private final PasswordUtil util;
    private final ClientSession session;

    public AuthenticAdminValid(UserRepository repository, TokenService serviceToken, UserMapper userMapper, PasswordUtil util, ClientSession session) {
        this.repository = repository;
        this.serviceToken = serviceToken;
        this.userMapper = userMapper;
        this.util = util;
        this.session = session;
    }

    protected void checkPermission(PermissionUser memory){
        switch (memory){
            case ADMIN, MODERATION:
                break;
            default:
            throw new WithoutPermission("Unauthorized");
        }

    }

    public void savedTokenAndUser(TokenEntity token, UserEntity user, boolean saveSession){

        if(saveSession){
            session.setSessionToken(token);
            session.setSessionClient(userMapper.convertToDto(user));
        }
    }

    public TokenEntity valida(String email, String password, String  secretToken, boolean saveSession) {
        UserEntity memory = repository.findByuserEmail(email);
        if (memory == null || !util.checkPassword(password, memory.getPasswordEntity().getUserPassword())) {
            throw new UserNotFound("User not found");
        }
        checkPermission(memory.getUserPermission());
        TokenEntity secretTokenDate = serviceToken.checkToken(secretToken,  memory);
        savedTokenAndUser(secretTokenDate, memory, saveSession);
        return  secretTokenDate;

    }
}
