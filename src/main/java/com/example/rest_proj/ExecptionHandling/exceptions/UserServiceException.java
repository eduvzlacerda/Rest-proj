package com.example.rest_proj.ExecptionHandling.exceptions;


public class UserServiceException extends RuntimeException {

    public UserServiceException(String message) {
        super(message);
    }
}
