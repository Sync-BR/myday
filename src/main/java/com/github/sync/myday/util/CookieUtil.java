package com.github.sync.myday.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
@Component
public class CookieUtil {

    public Cookie saveCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 *60);
        return  cookie;

    }

    public String getRequestCookie(String name,String value, HttpServletRequest request) {
        if(value == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if(name.equals(cookie.getName())) {
                    return  cookie.getValue();
                }
            }
        }
        return null;
    }
}
