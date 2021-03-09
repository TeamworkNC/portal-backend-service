package com.moviesandchill.portalbackendservice.exceptions.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(long userId) {
        super("user with id: " + userId + " not found");
    }
}
