package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;

import java.util.List;

public interface UserGlobalRoleService {
    List<GlobalRoleDto> getAllGlobalRoles(long userId);
}
