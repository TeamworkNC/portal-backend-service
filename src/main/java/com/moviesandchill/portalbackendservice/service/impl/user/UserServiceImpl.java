package com.moviesandchill.portalbackendservice.service.impl.user;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.mapper.UserMapper;
import com.moviesandchill.portalbackendservice.service.recommendation.RecommendationService;
import com.moviesandchill.portalbackendservice.service.user.UserAchievementService;
import com.moviesandchill.portalbackendservice.service.user.UserFriendService;
import com.moviesandchill.portalbackendservice.service.user.UserGlobalRoleService;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String userServiceUrl;

    private UserFriendService userFriendService;
    private UserAchievementService userAchievementService;
    private UserGlobalRoleService userGlobalRoleService;
    private RecommendationService recommendationService;

    private final UserMapper userMapper;
    private final CommonMapper commonMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, CommonMapper commonMapper) {
        this.userMapper = userMapper;
        this.commonMapper = commonMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        Optional<UserDto[]> userDtosOptional = RestTemplateUtils.get(url, UserDto[].class);
        return commonMapper.toList(userDtosOptional);
    }

    @Override
    public Optional<UserDto> addUser(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users";
        return RestTemplateUtils.post(url, newUserDto, UserDto.class);
    }

    @Override
    public boolean deleteAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        return RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<UserDto> getUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        return RestTemplateUtils.get(url, null, UserDto.class);
    }

    @Override
    public Optional<FullUserDto> getFullUser(long userId) {
        var userDtoOptional = getUser(userId);
        if (userDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        var userDto = userDtoOptional.get();

        FullUserDto fullUser = userMapper.mapToFullDto(userDto);

        List<UserDto> friends = userFriendService.getAllFriends(userId);
        fullUser.setFriends(friends);

        List<AchievementDto> achievements = userAchievementService.getAllAchievements(userId);
        fullUser.setAchievements(achievements);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userId);
        fullUser.setGlobalRoles(globalRoles);

        List<FilmDto> recommendedFilms = recommendationService.recommend(userId);
        fullUser.setRecommendedFilms(recommendedFilms);
        return Optional.of(fullUser);
    }

    @Override
    public boolean updateUser(long userId, UpdateUserDto updateUserDto) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        return RestTemplateUtils.put(url, updateUserDto);
    }

    @Override
    public boolean deleteUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        return RestTemplateUtils.delete(url);
    }

    @Override
    public boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/password";
        return RestTemplateUtils.put(url, updatePasswordDto);
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String url = userServiceUrl + "/api/v1/users/login";
        return RestTemplateUtils.post(url, loginRequestDto, UserDto.class);
    }

    @Override
    public Optional<UserDto> register(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users/register";
        return RestTemplateUtils.post(url, newUserDto, UserDto.class);
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

    @Autowired
    public void setRecommendationService(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }
}
