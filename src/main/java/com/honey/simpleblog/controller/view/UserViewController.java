package com.honey.simpleblog.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpView() {
        return "sign-up";
    }
}
