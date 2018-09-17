package com.project.rent.exception;

public class UserRoleNotFoundException extends RuntimeException {
    private String roll;

    public UserRoleNotFoundException(String roll) {
        this.roll = roll;
    }

}
