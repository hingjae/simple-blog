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

    @GetMapping
    public List<ArticleResponseDto> getArticleList(@RequestParam(defaultValue = "1") Integer page) {
        return articleService.getArticleByPage(page, 3);
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
