package com.github.sync.myday.routes.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminRoutes {
    @GetMapping("/login")
    public String loginIndex(Model model){
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        return "dashboard";
    }

}
