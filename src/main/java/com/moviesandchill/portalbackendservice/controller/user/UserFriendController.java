package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.service.user.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/friends",
        produces = "application/json"
)
@Slf4j
public class UserFriendController {

    private final UserFriendService userFriendService;

    public UserFriendController(UserFriendService userFriendService) {
        this.userFriendService = userFriendService;
    }

    @GetMapping()
    public List<UserDto> getAllFriends(@PathVariable long userId) {
        return userFriendService.getAllFriends(userId);
    }

    @PostMapping()
    public void addFriend(@PathVariable long userId, @RequestBody long friendId) {
        userFriendService.addFriend(userId, friendId);
    }

    @DeleteMapping()
    public void deleteAllFriends(@PathVariable long userId) {
        userFriendService.deleteAllFriends(userId);
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriend(@PathVariable long userId, @PathVariable long friendId) {
        userFriendService.deleteFriend(userId, friendId);
    }
}
