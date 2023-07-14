package com.honey.simpleblog.domain;

import lombok.Getter;

@Getter
public class UserAccount {
    private String loginId;
    private String password;
    private String name;

    private UserAccount(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public static UserAccount of(String loginId, String password, String name) {
        return new UserAccount(loginId, password, name);
    }
}
