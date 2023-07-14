package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.LoginCheckRequest;
import com.honey.simpleblog.dto.UserAccountRequestDto;
import com.honey.simpleblog.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserAccountService userAccountService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserAccountRequestDto dto) {
        boolean result = userAccountService.signUpUser(dto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(dto.toString());
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto.toString());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserAccountRequestDto dto, HttpServletRequest request) {
        userAccountService.loginUser(dto);
        HttpSession session = request.getSession(true);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/login-check")
    public ResponseEntity loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean result = isSessionExists(session);
        return new ResponseEntity<>(LoginCheckRequest.of(result), HttpStatus.OK);
    }

    private boolean isSessionExists(HttpSession session) {
        return session != null ? true : false;
    }
}
