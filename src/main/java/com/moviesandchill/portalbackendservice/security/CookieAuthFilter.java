package com.moviesandchill.portalbackendservice.security;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.services.AuthService;
import com.moviesandchill.portalbackendservice.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class CookieAuthFilter extends GenericFilterBean {

    private final AuthService authService;
    private final UsersService usersService;

    public CookieAuthFilter(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            filterChain.doFilter(req, res);
            return;
        }

        var userOptional = usersService.getUserById(userId);

        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            Authentication authentication = new SimpleAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(req, res);
    }
}
