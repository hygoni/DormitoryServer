package com.github.hygoni.dormitory.advice.exception;

public class WasherNotFoundException extends RuntimeException {
    public WasherNotFoundException(String msg) {
        super(msg);
    }

    public WasherNotFoundException() {
        super();
    }
}
