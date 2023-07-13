package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.Article;
import lombok.Setter;

import java.time.LocalDateTime;

public class ArticleRequestDto {

    private Long id;
    private String title;
    private String content;
    @Setter private String username;


    private ArticleRequestDto(Long id, String title, String content, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public Article toEntity(LocalDateTime now) {
        return Article.of(id, title, content, username, now);
    }
}
