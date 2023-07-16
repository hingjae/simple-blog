package com.honey.simpleblog.mapper;

import com.honey.simpleblog.domain.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleCommentMapper {

    Integer save(@Param("articleComment") ArticleComment articleComment);
}
