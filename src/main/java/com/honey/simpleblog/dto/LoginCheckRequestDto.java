package com.honey.simpleblog.dto;

import lombok.Getter;

@Getter
public class LoginCheckRequestDto {
    private boolean loggedIn;

    private LoginCheckRequestDto(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static LoginCheckRequestDto of(boolean loggedIn) {
        return new LoginCheckRequestDto(loggedIn);
    }
}
