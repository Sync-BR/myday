package com.github.sync.myday.util;

import com.github.sync.myday.entity.UserEntity;
import com.github.sync.myday.handle.exception.InvalidTokenException;
import com.github.sync.myday.handle.exception.UserNotFound;
import com.github.sync.myday.jwt.JwtUtil;
import com.github.sync.myday.service.TokenService;
import com.github.sync.myday.service.UserService;
import com.github.sync.myday.validate.admin.AuthenticAdminValid;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RouteUtil {
    private final UserService serviceUser;
    private final TokenService tokenService;
    private final CookieUtil utilCookie;
    private final JwtUtil utilJwt;
    private final AuthenticAdminValid validate;

    public RouteUtil(UserService serviceUser, TokenService tokenService, CookieUtil utilCookie, JwtUtil utilJwt, AuthenticAdminValid validate) {
        this.serviceUser = serviceUser;
        this.tokenService = tokenService;
        this.utilCookie = utilCookie;
        this.utilJwt = utilJwt;
        this.validate = validate;
    }

    private UserEntity checkDate(UserEntity memory) {
        if (memory == null) {
            throw new UserNotFound("Usuário não encontrado");
        }
        return memory;
    }



    private void compareTokenAndUser(Long idUser, String token, boolean permission) {

        UserEntity memoryEntity = checkDate(serviceUser.searchUserById(idUser));
        memoryEntity.setUserEmail(memoryEntity.getUserEmail().toLowerCase());
        if (!Objects.equals(utilJwt.getUsernameFromToken(token).toLowerCase(), memoryEntity.getUserEmail())) {
            throw new InvalidTokenException("Token invalido");
        }
        if (permission) {
            validate.checkPermission(memoryEntity.getUserPermission());
        }
    }

    public void checkRoutePermission(HttpServletRequest request, boolean permission) {
        String secretSearch = utilCookie.getRequestCookie("token", null, request);
        compareTokenAndUser(tokenService.searchToken(secretSearch), secretSearch, permission);
    }

}
