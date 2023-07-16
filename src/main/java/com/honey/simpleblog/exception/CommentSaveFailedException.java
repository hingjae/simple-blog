package com.honey.simpleblog.exception;

public class CommentSaveFailedException extends RuntimeException {
    public CommentSaveFailedException() {
        super();
    }

    public CommentSaveFailedException(String message) {
        super(message);
    }

    public CommentSaveFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
