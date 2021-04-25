package com.moviesandchill.portalbackendservice.service;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.security.SimpleAuthentication;
import com.moviesandchill.portalbackendservice.service.user.UserGlobalRoleService;
import com.moviesandchill.portalbackendservice.service.user.UserService;
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
public class AuthService {

    private final UserService userService;
    private final UserGlobalRoleService userGlobalRoleService;

    public AuthService(UserService userService, UserGlobalRoleService userGlobalRoleService) {
        this.userService = userService;
        this.userGlobalRoleService = userGlobalRoleService;
    }

    public Long login(LoginRequestDto loginRequestDto) {
        log.info("try login user");
        UserDto userDto = userService.login(loginRequestDto);

        long userId = userDto.getUserId();
        log.info("user id: " + userId);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userId);
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.info("authorities: " + authorities);
        Authentication authentication = new SimpleAuthentication(userId, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userDto.getUserId();
    }

    public Optional<Long> register(NewUserDto newUserDto) {
        log.info("try register user");
        UserDto userDto = userService.register(newUserDto);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userDto.getUserId());
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.info("authorities: " + authorities);

        Authentication authentication = new SimpleAuthentication(userDto.getUserId(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Optional.of(userDto.getUserId());
    }


    private List<GrantedAuthority> toGrantedAuthorities(List<GlobalRoleDto> globalRoleDtos) {
        return globalRoleDtos.stream()
                .map(globalRoleDto -> new SimpleGrantedAuthority("ROLE_" + globalRoleDto.getName()))
                .collect(Collectors.toList());
    }
}
