package com.honey.simpleblog.exception;

public class MisMatchedUserAccountIdException extends RuntimeException{
    public MisMatchedUserAccountIdException(String message) {
        super(message);
    }

    public MisMatchedUserAccountIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
