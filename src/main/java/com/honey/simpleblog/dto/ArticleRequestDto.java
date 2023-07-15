package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.Article;

import java.time.LocalDateTime;

public class ArticleRequestDto {

    private String title;
    private String content;


    private ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toDomain(String userAccountId, LocalDateTime now) {
        return Article.of(userAccountId, title, content, now);
    }
}
