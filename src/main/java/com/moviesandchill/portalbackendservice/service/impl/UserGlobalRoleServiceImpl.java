package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.service.UserGlobalRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserGlobalRoleServiceImpl implements UserGlobalRoleService {

    private String userServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles";
        GlobalRoleDto[] dtos = restTemplate.getForObject(url, GlobalRoleDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
