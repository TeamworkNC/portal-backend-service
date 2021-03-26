package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.security.SimpleAuthentication;
import com.moviesandchill.portalbackendservice.service.AuthService;
import com.moviesandchill.portalbackendservice.service.UserGlobalRoleService;
import com.moviesandchill.portalbackendservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserGlobalRoleService userGlobalRoleService;

    public AuthServiceImpl(UserService userService, UserGlobalRoleService userGlobalRoleService) {
        this.userService = userService;
        this.userGlobalRoleService = userGlobalRoleService;
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        log.info("try login user");
        var userOptional = userService.login(loginRequestDto);

        if (userOptional.isEmpty()) {
            log.info("user not found");
            return Optional.empty();
        }

        UserDto user = userOptional.get();
        long userId = user.getUserId();
        log.info("user id: " + userId);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userId);
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.info("authorities: " + authorities);
        Authentication authentication = new SimpleAuthentication(userId, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Optional.of(user);
    }

    @Override
    public Optional<Long> register(NewUserDto newUserDto) {
        log.info("try register user");
        Optional<UserDto> userOptional = userService.register(newUserDto);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        UserDto user = userOptional.get();

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(user.getUserId());
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.info("authorities: " + authorities);

        Authentication authentication = new SimpleAuthentication(user.getUserId(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Optional.of(user.getUserId());
    }


    private List<GrantedAuthority> toGrantedAuthorities(List<GlobalRoleDto> globalRoleDtos) {
        return globalRoleDtos.stream()
                .map(globalRoleDto -> new SimpleGrantedAuthority("ROLE_" + globalRoleDto.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return Optional.empty();
        }

        long userId = (long) auth.getPrincipal();

        return userService.getUser(userId);
    }
}
