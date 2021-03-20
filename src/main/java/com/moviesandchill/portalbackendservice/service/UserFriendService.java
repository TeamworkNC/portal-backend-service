package com.moviesandchill.portalbackendservice.service;


import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserFriendService {
    List<UserDto> getAllFriends(long userId) throws UserNotFoundException;

    void addFriend(long userId, long friendId) throws UserNotFoundException;

    void deleteAllFriends(long userId) throws UserNotFoundException;

    void deleteFriend(long userId, long friendId) throws UserNotFoundException;
}
