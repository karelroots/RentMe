package com.project.rent.exception;

public class EmailNotFoundException extends RuntimeException {
    private String email;

    public EmailNotFoundException(String email) {
        this.email = email;
    }
}
