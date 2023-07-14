package com.honey.simpleblog.dto;

import com.honey.simpleblog.domain.UserAccount;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserAccountRequestDto {
    private String id;
    private String password;
    private String name;

    public UserAccount toEntity() {
        return UserAccount.of(id, password, name);
    }
}
