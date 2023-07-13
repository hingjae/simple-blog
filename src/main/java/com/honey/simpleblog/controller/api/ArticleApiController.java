package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.ArticleRequestDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;
    private final static int FIXED_LIST_SIZE = 3;

    /**
     * <p>
     *     ajax 요청을 받아 고정된 사이즈의 게시글 리스트를 비동기로 불러오는 메서드
     * </p>
     * @param page 현재 페이지이다
     * @return 현재 페이지에서 고정된 사이즈의 게시글 리스트를 반환한다.
     */
    @GetMapping
    public List<ArticleResponseDto> getArticleList(@RequestParam(defaultValue = "1") Integer page) {
        return articleService.getArticleByPage(page, FIXED_LIST_SIZE);
    }

    @GetMapping("/{id}")
    public ArticleResponseDto getArticle(@PathVariable("id") Long articleId) {
        return articleService.getArticle(articleId);
    }

    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequestDto articleDto) {
        articleDto.setUsername("honey");
        boolean result = articleService.saveArticle(articleDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateArticle(@RequestBody ArticleRequestDto articleDto) {
        boolean result = articleService.updateArticle(articleDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteArticle(@RequestParam("id") Long articleId) {
        boolean result = articleService.deleteArticle(articleId);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }
}
