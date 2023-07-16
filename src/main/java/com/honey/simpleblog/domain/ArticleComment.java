package com.honey.simpleblog.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ArticleComment {
    private Long id;
    private Long articleId;
    private String userAccountId;
    private String content;
    private LocalDateTime createdAt;

    private ArticleComment(Long id, Long articleId, String userAccountId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.articleId = articleId;
        this.userAccountId = userAccountId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ArticleComment of(Long articleId, String userAccountId, String content, LocalDateTime createdAt) {
        return new ArticleComment(null, articleId, userAccountId, content, createdAt);
    }
}
