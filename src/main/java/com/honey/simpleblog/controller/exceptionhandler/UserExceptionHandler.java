package com.honey.simpleblog.controller.exceptionhandler;

import com.honey.simpleblog.exception.FailedLoginException;
import com.honey.simpleblog.exception.SessionLoginIdNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(FailedLoginException.class)
    public ResponseEntity<String> failedLoginException(FailedLoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> duplicateUserAccountLoginId(DuplicateKeyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SessionLoginIdNotFoundException.class)
    public ResponseEntity<String> sessionLoginIdNotFoundException(DuplicateKeyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
