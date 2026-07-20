package com.example.demo.security.service;

import com.example.demo.security.constant.SecurityConstants;
import com.example.demo.security.model.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final String PASSWORD =
            "$2a$10$Bv/XL91iXf0o4EioEBN9E.a6Ju2tnCWBOga5u6kWK15aj5vvXYX5C";

    @Override
    public UserDetails loadUserByUsername(String username) {

        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(

                "admin",

                PASSWORD,

                List.of(
                        new SimpleGrantedAuthority(
                                SecurityConstants.ROLE_ADMIN
                        )
                )
        );
    }

}
