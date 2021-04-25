package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserGlobalRoleService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public UserGlobalRoleService(@Value("${endpoint.user-service.url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<GlobalRoleDto> getAllGlobalRoles(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles";
        var dtos = restTemplate.getForObject(url, GlobalRoleDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void addGlobalRole(long userId, long globalRoleId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles";
        restTemplate.postForObject(url, globalRoleId, Void.class);
    }

    public void deleteAllGlobalRoles(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles";
        restTemplate.delete(url);
    }

    public void deleteGlobalRole(long userId, long globalRoleId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles/" + globalRoleId;
        restTemplate.delete(url);
    }
}
