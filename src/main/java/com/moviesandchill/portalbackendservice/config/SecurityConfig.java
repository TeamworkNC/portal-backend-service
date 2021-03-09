package com.moviesandchill.portalbackendservice.config;

import com.moviesandchill.portalbackendservice.security.CookieAuthConfigurer;
import com.moviesandchill.portalbackendservice.services.AuthService;
import com.moviesandchill.portalbackendservice.services.UsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final UsersService usersService;

    public SecurityConfig(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .mvcMatchers("/api/v1/public/**").permitAll()
                .mvcMatchers("/api/v1/**").hasRole("USER")
                .and()
                .apply(new CookieAuthConfigurer(authService, usersService));
    }
}