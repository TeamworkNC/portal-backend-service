package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserFriendService {

    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final CommonMapper commonMapper;

    public UserFriendService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    public List<UserDto> getAllFriends(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);
        return commonMapper.toList(dtos);
    }


    public boolean addFriend(long userId, long friendId) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteAllFriends(long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteFriend(long userId, long friendId) {
        throw new UnsupportedOperationException();
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
