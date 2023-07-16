package com.honey.simpleblog.controller.view;

import com.honey.simpleblog.controller.api.ArticleApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
