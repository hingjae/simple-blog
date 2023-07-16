package com.honey.simpleblog.service;

import com.honey.simpleblog.dto.ArticleRequestDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.exception.ArticleNotFoundException;
import com.honey.simpleblog.exception.MisMatchedUserAccountIdException;
import com.honey.simpleblog.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final LocalDateTime localDateTime;

    public List<ArticleResponseDto> getArticleByPage(Integer page, Integer size) {
        return articleMapper.findByLimitAndOffset(size, (page - 1) * size);
    }

    public ArticleResponseDto getArticle(Long articleId) {
        return articleMapper.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 id : " + articleId));
    }

    public boolean saveArticle(ArticleRequestDto articleRequestDto, String userAccountId) {
        Integer result = articleMapper.save(articleRequestDto.toDomain(userAccountId, localDateTime.now()));
        return result == 1;
    }

    public boolean updateArticle(ArticleRequestDto articleDto, String userAccountId) {
        String findUserAccountId = articleMapper.findUserAccountIdById(articleDto.getId());
        if (!findUserAccountId.equals(userAccountId)) {
            throw new MisMatchedUserAccountIdException("userAccountId 불일치 : " + userAccountId);
        }
        Integer result = articleMapper.update(articleDto.toDomain(userAccountId, localDateTime.now()));
        return result == 1;
    }

    public boolean deleteArticle(Long articleId, String userAccountId) {
        String findUserAccountId = articleMapper.findUserAccountIdById(articleId);
        if (!findUserAccountId.equals(userAccountId)) {
            throw new MisMatchedUserAccountIdException("userAccountId 불일치 : " + userAccountId);
        }
        Integer result = articleMapper.delete(articleId);
        return result == 1;
    }
}
