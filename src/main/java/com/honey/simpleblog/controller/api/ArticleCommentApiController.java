package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.ArticleCommentRequestDto;
import com.honey.simpleblog.exception.SessionLoginIdNotFoundException;
import com.honey.simpleblog.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/articleComments")
@RequiredArgsConstructor
@RestController
public class ArticleCommentApiController {

    private final ArticleCommentService articleCommentService;

    @PostMapping
    public ResponseEntity saveArticleComments(
            @RequestBody ArticleCommentRequestDto dto
//            @SessionAttribute(name = "id", required = false) String userAccountId
    ) {
//        sessionLoginIdCheck(userAccountId);
        articleCommentService.saveArticleComment(dto, "user1");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void sessionLoginIdCheck(String userAccountId) {
        if (userAccountId == null) {
            throw new SessionLoginIdNotFoundException("세션에 로그인 아이디가 없습니다.");
        }
    }
}
