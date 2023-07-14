package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.Article;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    private ArticleDto(Long id, String title, String content, String username, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public static ArticleDto of(Long id, String title, String content, String username, LocalDateTime createdAt) {
        return new ArticleDto(id, title, content, username, createdAt);
    }

    public static ArticleDto from(Article entity) {
        return ArticleDto.of(entity.getId(), entity.getTitle(), entity.getContent(), entity.getUsername(), entity.getCreatedAt());
    }

    public Article toDomain() {
        return Article.of(id, title, content, username, createdAt);
    }
}
