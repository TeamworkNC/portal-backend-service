package com.moviesandchill.portalbackendservice.security;

import com.moviesandchill.portalbackendservice.services.AuthService;
import com.moviesandchill.portalbackendservice.services.UsersService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CookieAuthConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthService authService;
    private final UsersService usersService;

    public CookieAuthConfigurer(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        CookieAuthFilter cookieAuthFilter = new CookieAuthFilter(authService, usersService);
        httpSecurity.addFilterBefore(cookieAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
