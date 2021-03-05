package com.moviesandchill.portalbackendservice.security;

import com.moviesandchill.portalbackendservice.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class CookieAuthFilter extends GenericFilterBean {

    private final AuthService authService;

    public CookieAuthFilter(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            log.info("cookies not found");
            filterChain.doFilter(req, res);
            return;
        }

        Optional<Cookie> tokenOptional = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("session_token"))
                .findFirst();

        if (tokenOptional.isEmpty()) {
            log.info("token not found");
            filterChain.doFilter(req, res);
            return;
        }

        String token = tokenOptional.get().getValue();
        log.info("token: " + token);
        authService.authorize(token);

        filterChain.doFilter(req, res);
    }


}
