package com.github.sync.myday.session;

import com.github.sync.myday.dto.UserDto;
import com.github.sync.myday.entity.TokenEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class ClientSession {
    private HttpSession session;

    public ClientSession(HttpSession session) {
        this.session = session;
    }

    public UserDto getSessionUser() {
        return (UserDto) session.getAttribute("user");
    }

    public void setSessionClient(UserDto user) {
        session.setAttribute("user", user);
    }


    public TokenEntity getSessionToken() {
        return (TokenEntity) session.getAttribute("token");
    }

    public void setSessionToken(TokenEntity memoryToken) {
        session.setAttribute("token", memoryToken);
    }
}
