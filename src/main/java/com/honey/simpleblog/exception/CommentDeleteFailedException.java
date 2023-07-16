package com.honey.simpleblog.exception;

public class CommentDeleteFailedException extends RuntimeException {
    public CommentDeleteFailedException() {
        super();
    }

    public CommentDeleteFailedException(String message) {
        super(message);
    }

    public CommentDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
