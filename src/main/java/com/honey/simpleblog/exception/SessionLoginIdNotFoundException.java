package com.honey.simpleblog.exception;

public class SessionLoginIdNotFoundException extends RuntimeException {

    public SessionLoginIdNotFoundException(String message) {
        super(message);
    }

    public SessionLoginIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
