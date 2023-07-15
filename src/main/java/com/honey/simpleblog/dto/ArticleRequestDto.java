package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleRequestDto {

    private Long id;
    private String title;
    private String content;


    private ArticleRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article toDomain(String userAccountId, LocalDateTime now) {
        return Article.of(id, userAccountId, title, content, now);
    }
}
