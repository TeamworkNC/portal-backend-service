package com.moviesandchill.portalbackendservice.security;

import com.moviesandchill.portalbackendservice.services.AuthService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CookieAuthConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthService authService;

    public CookieAuthConfigurer(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        CookieAuthFilter cookieAuthFilter = new CookieAuthFilter(authService);
        httpSecurity.addFilterBefore(cookieAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
