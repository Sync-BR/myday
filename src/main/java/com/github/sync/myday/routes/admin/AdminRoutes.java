package com.github.sync.myday.routes.admin;

import com.github.sync.myday.handle.exception.WithoutPermission;
import com.github.sync.myday.service.PostService;
import com.github.sync.myday.service.admin.CountService;
import com.github.sync.myday.util.RouteUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class AdminRoutes {
    private final RouteUtil util;
    private final CountService serviceCount;
    public AdminRoutes(RouteUtil util, CountService serviceCount) {
        this.util = util;
        this.serviceCount = serviceCount;
    }

    @GetMapping("/login")
    public String loginIndex(Model model){
        return "login";
    }
    @GetMapping("/dashboard")

    public String dashboard(HttpServletRequest request, Model model){
        try{
            util.checkRoutePermission(request, true);
            model.addAttribute("countUser", serviceCount.countAllUser());
            model.addAttribute("countPostByDate", serviceCount.countAllPostByDate());
            model.addAttribute("countLikeByDate", serviceCount.countAllLikeByDate());
            return "dashboard";
        } catch (WithoutPermission e){
            return "redirect:/login?permission";
        }
    }

}
