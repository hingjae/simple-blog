package com.honey.simpleblog.controller;

import com.honey.simpleblog.exception.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ArticleExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> articleNotFoundException() {
        return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }
}
