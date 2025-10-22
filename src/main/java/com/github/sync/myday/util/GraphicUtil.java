package com.github.sync.myday.util;

import com.github.sync.myday.repository.PostRepository;
import com.github.sync.myday.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GraphicUtil {
    private final UserRepository repositoryUser;
    private final PostRepository repositoryPost;
    public GraphicUtil(UserRepository repositoryUser, PostRepository repositoryPost) {
        this.repositoryUser = repositoryUser;
        this.repositoryPost = repositoryPost;
    }

    public long getGraphicByPost(LocalDate date, long maxDate) {
        LocalDate startDate = date.minusDays(maxDate);
        LocalDate endDate = LocalDate.now();

        // Garante que a data inicial seja sempre menor que a final
        if (startDate.isAfter(endDate)) {
            LocalDate temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        return repositoryPost.countByCreatedDatePostBetween(startDate, endDate);
    }

    public long getGraphicByUserRegistered(LocalDate date, long maxDate) {
        // Calcula o intervalo
        LocalDate startDate = date.minusDays(maxDate);
        LocalDate endDate = LocalDate.now();

        // Garante que a data inicial seja sempre menor que a final
        if (startDate.isAfter(endDate)) {
            LocalDate temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        // Faz a contagem dentro do intervalo corrigido
        return repositoryUser.countByUserCreatedDateBetween(startDate, endDate);
    }


    public static void main(String[] args) {
        System.out.println(LocalDate.now().plusDays(-7));
    }
}
