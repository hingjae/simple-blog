package com.honey.simpleblog.mapper;

import com.honey.simpleblog.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    List<Article> findAll();

    List<Article> findByPage(@Param("limit") Integer limit, @Param("offset") Integer offset);

    Optional<Article> findById(@Param("id") Long id);

    Integer save(@Param("article") Article article); // 리턴 값은 DB 테이블에 영향받은 row의 수

    Integer update(@Param("article") Article article);

    Integer delete(@Param("id") Long id);
}
