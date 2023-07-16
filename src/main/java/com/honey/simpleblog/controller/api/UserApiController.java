package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.LoginCheckRequestDto;
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

    /**
     * 회원가입 로직 아이디 중복 검증 이후 DB에 회원 정보를 저장함.
     * @param dto - id, password, name을 담은 객체
     * @return 상태 코드 반환
     */
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
        session.setAttribute("id", dto.getId());
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
        return new ResponseEntity<>(LoginCheckRequestDto.of(result), HttpStatus.OK);
    }

    private boolean isSessionExists(HttpSession session) {
        return session != null ? true : false;
    }
}
