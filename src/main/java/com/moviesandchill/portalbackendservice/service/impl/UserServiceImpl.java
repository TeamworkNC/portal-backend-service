package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;
import com.moviesandchill.portalbackendservice.mapper.UserMapper;
import com.moviesandchill.portalbackendservice.service.UserAchievementService;
import com.moviesandchill.portalbackendservice.service.UserFriendService;
import com.moviesandchill.portalbackendservice.service.UserGlobalRoleService;
import com.moviesandchill.portalbackendservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String userServiceUrl;

    private UserFriendService userFriendService;
    private UserAchievementService userAchievementService;
    private UserGlobalRoleService userGlobalRoleService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Override
    public UserDto addUser(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users";
        return restTemplate.postForObject(url, newUserDto, UserDto.class);
    }

    @Override
    public void deleteAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        restTemplate.delete(url);
    }

    @Override
    public UserDto getUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }

    @Override
    public FullUserDto getFullUser(long userId) throws UserNotFoundException {
        UserDto user = getUser(userId);
        FullUserDto fullUser = userMapper.mapToFullDto(user);

        List<UserDto> friends = userFriendService.getAllFriends(userId);
        fullUser.setFriends(friends);

        List<AchievementDto> achievements = userAchievementService.getAllAchievements(userId);
        fullUser.setAchievements(achievements);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userId);
        fullUser.setGlobalRoles(globalRoles);
        return fullUser;
    }

    @Override
    public void updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        restTemplate.put(url, updateUserDto);
    }

    @Override
    public void deleteUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        restTemplate.delete(url);
    }

    @Override
    public boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/password";
        restTemplate.put(url, updatePasswordDto);
        return true; // FIXME
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String url = userServiceUrl + "/api/v1/users/login";
        UserDto user = restTemplate.postForObject(url, loginRequestDto, UserDto.class);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserDto> register(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users/register";
        UserDto userDto = restTemplate.postForObject(url, newUserDto, UserDto.class);
        return Optional.ofNullable(userDto);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Autowired
    public void setUserFriendService(UserFriendService userFriendService) {
        this.userFriendService = userFriendService;
    }

    @Autowired
    public void setUserAchievementService(UserAchievementService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @Autowired
    public void setUserGlobalRoleService(UserGlobalRoleService userGlobalRoleService) {
        this.userGlobalRoleService = userGlobalRoleService;
    }
}
