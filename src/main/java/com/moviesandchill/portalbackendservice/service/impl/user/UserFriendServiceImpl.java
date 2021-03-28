package com.moviesandchill.portalbackendservice.service.impl.user;

import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.user.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserFriendServiceImpl implements UserFriendService {

    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final CommonMapper commonMapper;

    public UserFriendServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    @Override
    public List<UserDto> getAllFriends(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);
        return commonMapper.toList(dtos);
    }

    @Override
    public boolean addFriend(long userId, long friendId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteAllFriends(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteFriend(long userId, long friendId) {
        throw new UnsupportedOperationException();
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
