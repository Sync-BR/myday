package com.github.sync.myday.routes.admin;

import com.github.sync.myday.dto.RecentActivityDto;
import com.github.sync.myday.handle.exception.WithoutPermission;
import com.github.sync.myday.service.PostService;
import com.github.sync.myday.service.admin.CountService;
import com.github.sync.myday.util.ActivityUtil;
import com.github.sync.myday.util.GraphicUtil;
import com.github.sync.myday.util.RouteUtil;
import com.github.sync.myday.util.StatusUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class AdminRoutes {
    private final RouteUtil util;
    private final StatusUtil utilStatus;
    private final ActivityUtil utilActivity;
    private final GraphicUtil utilGraphic;
    private final CountService serviceCount;
    public AdminRoutes(RouteUtil util, CountService serviceCount, StatusUtil utilStatus, ActivityUtil utilActivity, GraphicUtil utilGraphic) {
        this.util = util;
        this.serviceCount = serviceCount;
        this.utilStatus = utilStatus;
        this.utilActivity = utilActivity;
        this.utilGraphic = utilGraphic;
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

            //Atividades Recentes
            model.addAttribute("recentActivity", utilActivity.getRecentActivity());


            //Graficos
            model.addAttribute("userRegisteredGraphic", utilGraphic.getGraphicByUserRegistered(LocalDate.now(), 7));
            model.addAttribute("postRegisteredGraphic", utilGraphic.getGraphicByPost(LocalDate.now(), 7));
            System.out.println(utilGraphic.getGraphicByPost(LocalDate.now(), 7));



            model.addAttribute("statusFrontend", utilStatus.statusServiceWeb());
            model.addAttribute("statusCache", utilStatus.statusCache());
            return "dashboard";
        } catch (WithoutPermission e){
            return "redirect:/login?permission";
        }
    }

}
