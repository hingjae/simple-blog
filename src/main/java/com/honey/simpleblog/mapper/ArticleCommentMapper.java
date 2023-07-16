package com.honey.simpleblog.mapper;

import com.honey.simpleblog.domain.ArticleComment;
import com.honey.simpleblog.dto.ArticleCommentResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleCommentMapper {

    Integer save(@Param("articleComment") ArticleComment articleComment);

    List<ArticleCommentResponseDto> findByArticleId(@Param("articleId") Long articleId);

    Integer deleteByIdAndUserAccountId(@Param("id") Long articleCommentId, @Param("userAccountId") String userAccountId);
}
