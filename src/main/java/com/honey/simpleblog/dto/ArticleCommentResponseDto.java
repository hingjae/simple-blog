package com.honey.simpleblog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentResponseDto {
    private Long id;
    private String userAccountId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    @Setter private boolean isMyComment = false;
}
