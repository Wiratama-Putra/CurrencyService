package com.example.demo.security.filter;

import com.example.demo.security.constant.SecurityConstants;
import com.example.demo.security.service.JwtService;
import com.example.demo.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException, IOException {

        String header =
                request.getHeader(SecurityConstants.AUTHORIZATION);

        if (!StringUtils.hasText(header)
                || !header.startsWith(SecurityConstants.BEARER)) {

            filterChain.doFilter(request, response);

            return;

        }

        String token =
                header.substring(7);

        if (jwtService.validate(token)) {

            String username =
                    jwtService.extractUsername(token);

            UserDetails user =
                    userService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(

                            user,

                            null,

                            user.getAuthorities());

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            log.info("Authenticated {}", username);

        }

        filterChain.doFilter(request, response);

    }

}
