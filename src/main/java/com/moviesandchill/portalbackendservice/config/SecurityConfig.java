package com.moviesandchill.portalbackendservice.config;

import com.moviesandchill.portalbackendservice.security.JwtConfigurer;
import com.moviesandchill.portalbackendservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //FIXME ?
//                .and()
//                .authorizeRequests()
//                .mvcMatchers("/api/v1/public/**").permitAll()
//                .mvcMatchers("/api/v1/**").hasRole("USER")
//                .mvcMatchers("**").permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
