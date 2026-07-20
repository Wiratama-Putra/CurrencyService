package com.example.demo.security.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getUsername(),

                        request.getPassword()

                )

        );

        String token = jwtService.generateToken(
                request.getUsername());

        log.info("User {} login success",
                request.getUsername());

        return LoginResponse.builder()

                .accessToken(token)

                .tokenType("Bearer")

                .expiresIn(3600L)

                .build();

    }

}
