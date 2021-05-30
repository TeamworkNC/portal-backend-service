package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.user.globalrole.NewGlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.user.globalrole.UpdateGlobalRoleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service

@Slf4j
public class GlobalRoleService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public GlobalRoleService(@Value("${endpoints.user-service-url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<GlobalRoleDto> getAllGlobalRoles() {
        String url = userServiceUrl + "/api/v1/global-roles";
        var dtos = restTemplate.getForObject(url, GlobalRoleDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public GlobalRoleDto addGlobalRole(NewGlobalRoleDto newGlobalRoleDto) {
        String url = userServiceUrl + "/api/v1/global-roles";
        return restTemplate.postForObject(url, newGlobalRoleDto, GlobalRoleDto.class);
    }

    public void deleteAllGlobalRoles() {
        String url = userServiceUrl + "/api/v1/global-roles";
        restTemplate.delete(url);
    }

    public GlobalRoleDto getGlobalRole(long globalRoleId) {
        String url = userServiceUrl + "/api/v1/global-roles/" + globalRoleId;
        return restTemplate.getForObject(url, GlobalRoleDto.class);
    }

    public void updateGlobalRole(long globalRoleId, UpdateGlobalRoleDto updateGlobalRoleDto) {
        String url = userServiceUrl + "/api/v1/global-roles/" + globalRoleId;
        restTemplate.put(url, updateGlobalRoleDto);
    }

    public void deleteGlobalRole(long globalRoleId) {
        String url = userServiceUrl + "/api/v1/global-roles/" + globalRoleId;
        restTemplate.delete(url);
    }
}
