package com.honey.simpleblog.service;

import com.honey.simpleblog.domain.Article;
import com.honey.simpleblog.dto.ArticleDto;
import com.honey.simpleblog.dto.ArticleRequestDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.exception.ArticleNotFoundException;
import com.honey.simpleblog.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final LocalDateTime localDateTime;

    public List<ArticleDto> getArticleList() {
        return articleMapper.findAll().stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }

    public List<ArticleResponseDto> getArticleByPage(Integer page, Integer size) {
        return articleMapper.findByPage(size, (page - 1) * size).stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto getArticle(Long articleId) {
        return ArticleResponseDto.from(
                articleMapper.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 id : " + articleId))
        );
    }

    public boolean saveArticle(ArticleRequestDto articleRequestDto) {
        Integer result = articleMapper.save(articleRequestDto.toEntity(localDateTime.now()));
        return result == 1;
    }

    public boolean updateArticle(ArticleRequestDto articleDto) {
        Integer result = articleMapper.update(articleDto.toEntity(localDateTime.now()));
        return result == 1;
    }

    public boolean deleteArticle(Long articleId) {
        Integer result = articleMapper.delete(articleId);
        return result == 1;
    }
}
