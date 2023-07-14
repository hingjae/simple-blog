package com.honey.simpleblog.dto;

import lombok.Getter;

@Getter
public class LoginCheckRequest {
    private boolean loggedIn;

    private LoginCheckRequest(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static LoginCheckRequest of(boolean loggedIn) {
        return new LoginCheckRequest(loggedIn);
    }
}
