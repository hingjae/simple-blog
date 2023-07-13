package com.honey.simpleblog.controller;

import com.honey.simpleblog.controller.api.ArticleApiController;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ArticleApiController articleApiController;
    private final static int START_PAGE = 1;

    @GetMapping("/")
    public String getArticleList(Model model) {
        model.addAttribute("articles", articleApiController.getArticleList(START_PAGE));
        return "index";
    }


}
