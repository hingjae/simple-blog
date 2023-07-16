package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.ArticleCommentRequestDto;
import com.honey.simpleblog.dto.ArticleCommentResponseDto;
import com.honey.simpleblog.exception.SessionLoginIdNotFoundException;
import com.honey.simpleblog.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/articles/{articleId}/articleComments")
@RequiredArgsConstructor
@RestController
public class ArticleCommentApiController {

    private final ArticleCommentService articleCommentService;

    @PostMapping
    public ResponseEntity saveArticleComments(
            @RequestBody ArticleCommentRequestDto dto,
            @SessionAttribute(name = "id", required = false) String userAccountId
    ) {
        sessionLoginIdCheck(userAccountId);
        articleCommentService.saveArticleComment(dto, userAccountId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void sessionLoginIdCheck(String userAccountId) {
        if (userAccountId == null) {
            throw new SessionLoginIdNotFoundException("로그인을 해주세요");
        }
    }

    @GetMapping
    public List<ArticleCommentResponseDto> getArticleCommentList(@PathVariable("articleId") Long articleId) {
        return articleCommentService.getArticleComments(articleId);
    }
}
