package com.honey.simpleblog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentResponseDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdAt;
}
