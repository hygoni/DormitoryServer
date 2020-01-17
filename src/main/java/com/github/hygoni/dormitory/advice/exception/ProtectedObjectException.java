package com.github.hygoni.dormitory.advice.exception;

public class ProtectedObjectException extends RuntimeException {
    public ProtectedObjectException(String msg) {
        super(msg);
    }

    public ProtectedObjectException() {
        super();
    }
}
