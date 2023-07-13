package com.honey.simpleblog.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class Article {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    private Article(Long id, String title, String content, String username, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public static Article of(Long id, String title, String content, String username, LocalDateTime createdAt) {
        return new Article(id, title, content, username, createdAt);
    }

    public static Article of(String title, String content, String username, LocalDateTime createdAt) {
        return Article.of(null, title, content, username, createdAt);
    }

}
