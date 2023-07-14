package com.honey.simpleblog.service;

import com.honey.simpleblog.dto.UserAccountRequestDto;
import com.honey.simpleblog.mapper.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserAccountMapper userAccountMapper;

    public boolean signUpUser(UserAccountRequestDto userAccountDto) {
        Integer result = userAccountMapper.saveUser(userAccountDto.toEntity());
        return result == 1;
    }

}
