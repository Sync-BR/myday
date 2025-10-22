package com.github.sync.myday.util;

import com.github.sync.ping.Ping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class StatusUtil {
    @Value("${service.web}")
    private String frontWebSite;
    @Value("${spring.thymeleaf.cache}")
    private boolean thymeleafCache;

    public boolean statusCache() {
        return thymeleafCache;
    }

    public boolean statusServiceWeb() {
        try{
            if (!frontWebSite.isEmpty()) {
                Ping ping = new Ping();
                ping.checkWebSite(frontWebSite);
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }




}
