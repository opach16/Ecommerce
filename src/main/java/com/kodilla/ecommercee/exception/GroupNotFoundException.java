package com.kodilla.ecommercee.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
        super("Group with given ID has not been found");
    }
}
