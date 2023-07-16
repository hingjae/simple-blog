package com.honey.simpleblog.controller.view;

import com.honey.simpleblog.controller.api.ArticleApiController;
import com.honey.simpleblog.controller.api.ArticleCommentApiController;
import com.honey.simpleblog.dto.ArticleCommentResponseDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.exception.SessionLoginIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleViewController {

    private final ArticleApiController articleApiController;
    private final ArticleCommentApiController articleCommentApiController;


    @GetMapping("/{id}")
    public String getArticle(
            @SessionAttribute(name = "id", required = false) String userAccountId,
            @PathVariable("id") Long articleId, Model model
    ) {
        ArticleResponseDto article = articleApiController.getArticle(articleId);
        List<ArticleCommentResponseDto> comments = articleCommentApiController.getArticleCommentList(articleId);
        model.addAttribute("article", article);
        model.addAttribute("isMyArticle", article.getUserAccountId().equals(userAccountId));
        model.addAttribute("comments", comments);
        return "article-detail";
    }

    @GetMapping("/{id}/edit")
    public String articleUpdateView(@PathVariable("id") Long articleId, Model model) {
        model.addAttribute("article", articleApiController.getArticle(articleId));
        return "article-update";
    }

    @GetMapping("/create")
    public String articleWriteView(@SessionAttribute(name = "id", required = false) String userAccountId) {
        sessionLoginIdCheck(userAccountId);
        return "article-write";
    }

    private void sessionLoginIdCheck(String userAccountId) {
        if (userAccountId == null) {
            throw new SessionLoginIdNotFoundException("로그인을 해주세요");
        }
    }
}
