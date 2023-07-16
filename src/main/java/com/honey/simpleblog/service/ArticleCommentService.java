package com.honey.simpleblog.service;

import com.honey.simpleblog.dto.ArticleCommentRequestDto;
import com.honey.simpleblog.exception.CommentSaveFailedException;
import com.honey.simpleblog.mapper.ArticleCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentMapper articleCommentMapper;
    private final LocalDateTime localDateTime;

    public void saveArticleComment(ArticleCommentRequestDto dto, String userAccountId) {
        Integer result = articleCommentMapper.save(dto.toDomain(userAccountId, localDateTime.now()));
        if (result != 1) {
            throw new CommentSaveFailedException("댓글을 DB에 저장하는데 실패했습니다.");
        }
    }
}
