package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    private String userServiceUrl;

    private UserGlobalRoleService userGlobalRoleService;
    private UserAchievementService userAchievementService;
    private UserFavoriteFilmService userFavoriteFilmService;
    private UserFavoriteStaffService userFavoriteStaffService;
    private UserWantWatchFilmService userWantWatchFilmService;
    private UserWatchedFilmService userWatchedFilmService;

    private RestTemplate restTemplate;
    private UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        var dtos = restTemplate.getForObject(url, UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public UserDto addUser(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users";
        return restTemplate.postForObject(url, newUserDto, UserDto.class);
    }

    public void deleteAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        restTemplate.delete(url);
    }

    public UserDto getUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }

    public FullUserDto getFullUser(long userId) {
        var userDto = getUser(userId);
        var fullUserDto = userMapper.mapToFullDto(userDto);

        fullUserDto.setGlobalRoles(userGlobalRoleService.getAllGlobalRoles(userId));
        fullUserDto.setAchievements(userAchievementService.getAllAchievements(userId));
        fullUserDto.setFavoriteFilms(userFavoriteFilmService.getAllFavoriteFilms(userId));
        fullUserDto.setFavoriteStaffs(userFavoriteStaffService.getAllFavoriteStaffs(userId));
        fullUserDto.setWantWatchFilms(userWantWatchFilmService.getAllWantWatchFilms(userId));
        fullUserDto.setWatchedFilms(userWatchedFilmService.getAllWatchedFilms(userId));
        return fullUserDto;
    }

    public void updateUser(long userId, UpdateUserDto updateUserDto) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        restTemplate.put(url, updateUserDto);
    }

    public void deleteUser(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        restTemplate.delete(url);
    }

    public void updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/password";
        restTemplate.put(url, updatePasswordDto, Boolean.class);
    }

    public void updateUserLogo(long userId, MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String url = userServiceUrl + "/api/v1/users/" + userId + "/logo";
        restTemplate.postForEntity(url, requestEntity, Void.class);
    }

    public UserDto login(LoginRequestDto loginRequestDto) {
        String url = userServiceUrl + "/api/v1/users/login";
        return restTemplate.postForObject(url, loginRequestDto, UserDto.class);
    }

    public UserDto register(NewUserDto newUserDto) {
        String url = userServiceUrl + "/api/v1/users/register";
        return restTemplate.postForObject(url, newUserDto, UserDto.class);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Autowired
    public void setUserGlobalRoleService(UserGlobalRoleService userGlobalRoleService) {
        this.userGlobalRoleService = userGlobalRoleService;
    }

    @Autowired
    public void setUserAchievementService(UserAchievementService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @Autowired
    public void setUserFavoriteFilmService(UserFavoriteFilmService userFavoriteFilmService) {
        this.userFavoriteFilmService = userFavoriteFilmService;
    }

    @Autowired
    public void setUserFavoriteStaffService(UserFavoriteStaffService userFavoriteStaffService) {
        this.userFavoriteStaffService = userFavoriteStaffService;
    }

    @Autowired
    public void setUserWantWatchFilmService(UserWantWatchFilmService userWantWatchFilmService) {
        this.userWantWatchFilmService = userWantWatchFilmService;
    }

    @Autowired
    public void setUserWatchedFilmService(UserWatchedFilmService userWatchedFilmService) {
        this.userWatchedFilmService = userWatchedFilmService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
