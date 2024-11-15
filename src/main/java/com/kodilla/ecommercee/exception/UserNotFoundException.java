package com.kodilla.ecommercee.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String BY_ID_MESSAGE = "User with given ID has not been found";
    public static final String BY_USERNAME_MESSAGE = "User has not been found";
    public static final String IS_BLOCKED_MESSAGE = "User has already been blocked";

    public UserNotFoundException(String message) {
        super(message);
    }
}
