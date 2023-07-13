package com.honey.simpleblog.controller;

import com.honey.simpleblog.controller.api.ArticleApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleViewController {

    private final ArticleApiController articleApiController;

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") Long articleId, Model model) {
        model.addAttribute("article", articleApiController.getArticle(articleId));
        return "article-detail";
    }

    @GetMapping("/{id}/edit")
    public String articleUpdateView(@PathVariable("id") Long articleId, Model model) {
        model.addAttribute("article", articleApiController.getArticle(articleId));
        return "article-update";
    }

    @GetMapping("/create")
    public String articleWriteView() {
        return "article-write";
    }
}
