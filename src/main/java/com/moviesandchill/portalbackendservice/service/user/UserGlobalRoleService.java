package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserGlobalRoleService {

    private String userServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final CommonMapper commonMapper;

    public UserGlobalRoleService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    public List<GlobalRoleDto> getAllGlobalRoles(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/global-roles";
        GlobalRoleDto[] dtos = restTemplate.getForObject(url, GlobalRoleDto[].class);
        return commonMapper.toList(dtos);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
