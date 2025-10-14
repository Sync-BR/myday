package com.github.sync.myday.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String keySecret;
    @Value("${jwt.expiration}")
    private long secretExpiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(keySecret.getBytes());
    }

    /**
     * Gerar um token para o usuário
     * @param email receber o email do usuário
     * @return retornar um token gerado
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuer("MyDay API")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + secretExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validar o token
     * @param token recebe o token valido
     * @return retornar true se estiver ok
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Obter nome de usuário do token
     * @param token recebe um token de um determinado usuário
     * @return Retornar qual usuário está autenticado a partir do token JWT.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
