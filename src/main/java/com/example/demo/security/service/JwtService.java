package com.example.demo.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String username) {

        Date now = new Date();

        Date expired =
                new Date(now.getTime() + expiration);

        String token = Jwts.builder()

                .subject(username)

                .issuedAt(now)

                .expiration(expired)

                .signWith(getKey())

                .compact();

        log.info("JWT generated for {}", username);

        return token;

    }

    public String extractUsername(String token) {

        return getClaims(token).getSubject();

    }

    public boolean validate(String token) {

        try {

            getClaims(token);

            return true;

        } catch (Exception ex) {

            log.error("Invalid JWT : {}", ex.getMessage());

            return false;

        }

    }

    private Claims getClaims(String token) {

        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

}
