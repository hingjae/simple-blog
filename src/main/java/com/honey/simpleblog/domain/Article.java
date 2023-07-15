package com.honey.simpleblog.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class Article {
    private Long id;
    private String userAccountId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private Article(Long id, String userAccountId, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userAccountId = userAccountId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Article of(Long id, String userAccountId, String title, String content, LocalDateTime createdAt) {
        return new Article(id, userAccountId, title, content, createdAt);
    }

    public static Article of(String userAccountId, String title, String content, LocalDateTime createdAt) {
        return Article.of(null, userAccountId, title, content, createdAt);
    }

}
