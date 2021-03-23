package com.moviesandchill.portalbackendservice.service;


import com.moviesandchill.portalbackendservice.dto.user.UserDto;

import java.util.List;

public interface UserFriendService {
    List<UserDto> getAllFriends(long userId);

    boolean addFriend(long userId, long friendId);

    boolean deleteAllFriends(long userId);

    boolean deleteFriend(long userId, long friendId);
}
