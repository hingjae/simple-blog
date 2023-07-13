package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.ArticleRequestDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
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
    public String createArticle(@RequestBody ArticleRequestDto articleDto) {
        articleDto.setUsername("honey");
        articleService.saveArticle(articleDto);
        return "success";
    }

    @PutMapping
    public String updateArticle(@RequestBody ArticleRequestDto articleDto) {
        articleService.updateArticle(articleDto);
        return "success";
    }
}
