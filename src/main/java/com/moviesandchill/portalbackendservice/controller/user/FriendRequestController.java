package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.friendrequest.FriendRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.NewFriendRequestDto;
import com.moviesandchill.portalbackendservice.service.user.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/friend_requests",
        produces = "application/json"
)
@Slf4j
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping
    List<FriendRequestDto> getAllFriendRequests() {
        return friendRequestService.getAllFriendRequests();
    }

    @PostMapping
    @Secured("ROLE_USER")
    FriendRequestDto addFriendRequest(@RequestBody NewFriendRequestDto newFriendRequestDto) {
        return friendRequestService.addFriendRequest(newFriendRequestDto);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    void deleteAllFriendRequests() {
        friendRequestService.deleteAllFriendRequests();
    }
}
