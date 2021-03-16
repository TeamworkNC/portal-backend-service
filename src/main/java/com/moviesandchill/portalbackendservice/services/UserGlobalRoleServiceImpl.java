package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.globalrole.GlobalRoleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserGlobalRoleServiceImpl implements UserGlobalRoleService {

    @Value("${endpoint.user-service.url}")
    private String USER_SERVICE_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles(long userId) {
        String url = USER_SERVICE_URL + "/api/v1/users/" + userId + "/global-roles";
        GlobalRoleDto[] dtos = restTemplate.getForObject(url, GlobalRoleDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }
}
