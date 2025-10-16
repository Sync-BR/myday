package com.github.sync.myday.controller.admin;

import com.github.sync.myday.handle.exception.UserNotFound;
import com.github.sync.myday.handle.exception.WithoutPermission;
import com.github.sync.myday.service.admin.AdminLoginService;
import com.github.sync.myday.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;

@Controller
public class AdminLoginController {
    private final AdminLoginService serviceLogin;
    private final CookieUtil utilCookie;

    public AdminLoginController(AdminLoginService serviceLogin, CookieUtil utilCookie) {
        this.serviceLogin = serviceLogin;
        this.utilCookie = utilCookie;
    }

    @PostMapping("/v1/admin/login")
    public String authenticate(String email, String password, HttpServletResponse response) {
        try {
            String memoryToken = serviceLogin.authenticate(email, password).getSecretToken();
            response.addCookie(utilCookie.saveCookie("token", memoryToken));
            return "redirect:/dashboard";
        } catch (UserNotFound e) {
            return "redirect:/login?userNotFound";
        } catch (WithoutPermission eP){
            return "redirect:/login?permission";
        }
    }

}
