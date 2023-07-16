package com.honey.simpleblog.controller.exceptionhandler;

import com.honey.simpleblog.exception.CommentDeleteFailedException;
import com.honey.simpleblog.exception.CommentSaveFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArticleCommentExceptionHandler {

    @ExceptionHandler(CommentSaveFailedException.class)
    public ResponseEntity commentSaveFailed(CommentSaveFailedException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CommentDeleteFailedException.class)
    public ResponseEntity commentDeleteFailed(CommentDeleteFailedException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
