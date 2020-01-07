package com.github.hygoni.dormitory.advice.exception;

public class LoginException extends RuntimeException {
    public LoginException(String msg) {
        super(msg);
    }

    public LoginException() {
        super();
    }
}
