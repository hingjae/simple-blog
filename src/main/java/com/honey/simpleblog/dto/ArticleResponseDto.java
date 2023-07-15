package com.honey.simpleblog.dto;

import lombok.Getter;

@Getter
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;

    private ArticleResponseDto(Long id, String title, String content, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public static ArticleResponseDto of(Long id, String title, String content, String username) {
        return new ArticleResponseDto(id, title, content, username);
    }
}
