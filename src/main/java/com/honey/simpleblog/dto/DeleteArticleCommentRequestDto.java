package com.honey.simpleblog.dto;

import lombok.Getter;

@Getter
public class DeleteArticleCommentRequestDto {
    private Long commentId;
    private String userAccountId;
}
