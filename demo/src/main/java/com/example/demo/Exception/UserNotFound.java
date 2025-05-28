package com.example.demo.Exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String msg) {
        super(msg);
    }

}
