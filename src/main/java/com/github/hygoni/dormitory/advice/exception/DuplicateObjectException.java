package com.github.hygoni.dormitory.advice.exception;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException(String msg) {
        super(msg);
    }

    public DuplicateObjectException() {
        super();
    }
}
