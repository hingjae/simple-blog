package com.honey.simpleblog.dto;

import lombok.Getter;

@Getter
public class ArticleResponseDto {

    private Long id;
    private String userAccountId;
    private String title;
    private String content;
    private String username;

    private ArticleResponseDto(Long id, String userAccountId, String title, String content, String username) {
        this.id = id;
        this.userAccountId = userAccountId;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public static ArticleResponseDto of(Long id, String userAccountId, String title, String content, String username) {
        return new ArticleResponseDto(id, userAccountId, title, content, username);
    }
}
