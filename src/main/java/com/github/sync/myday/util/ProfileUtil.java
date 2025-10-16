package com.github.sync.myday.util;
import org.springframework.stereotype.Component;
import java.text.Normalizer;


@Component
public class ProfileUtil {

    private final static int idMin = 1;
    private final static int idMax = 99999;

    private static String letterFormat(String letter) {
        String normalized = Normalizer.normalize(letter, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        normalized = normalized.replaceAll("[^a-zA-Z0-9\\s]", "");

        normalized = normalized.trim().replaceAll("\\s+", "-");

        normalized = normalized.toLowerCase();

        return normalized;
    }


    public String generateUrl(String name){
        return "profile?ref="+ letterFormat(name)+"?user="+(int) ((Math.random() * (idMax - idMin)) + idMin);
    }

}
