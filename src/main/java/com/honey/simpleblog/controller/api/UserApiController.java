package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.UserAccountRequestDto;
import com.honey.simpleblog.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserAccountService userAccountService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserAccountRequestDto dto) {

        boolean result = userAccountService.signUpUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto.toString());
    }
}
