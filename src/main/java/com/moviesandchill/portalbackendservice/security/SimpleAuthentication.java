package com.moviesandchill.portalbackendservice.security;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SimpleAuthentication implements Authentication {

    private final UserDto user;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public SimpleAuthentication(UserDto user) {
        this.user = user;
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public SimpleAuthentication(UserDto user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getDetails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return user.getName();
    }


}
