package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.ArticleComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentRequestDto {
    private Long articleId;
    private String content;

    public ArticleComment toDomain(String userAccountId, LocalDateTime now) {
        return ArticleComment.of(articleId, userAccountId, content, now);
    }
}
