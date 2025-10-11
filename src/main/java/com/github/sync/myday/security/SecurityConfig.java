package com.github.sync.myday.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔓 Permite acesso sem login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/**",                   // página inicial
                                "/v1/api/user/add"
                        ).permitAll()              // todas essas rotas são livres
                        .anyRequest().authenticated() // as outras exigem login
                )

                // Desativa login e logout padrão (para testes ou API)
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())

                // Desativa CSRF (necessário para testes com Postman ou API REST)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
