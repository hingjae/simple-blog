package com.honey.simpleblog.service;

import com.honey.simpleblog.domain.UserAccount;
import com.honey.simpleblog.dto.UserAccountRequestDto;
import com.honey.simpleblog.exception.FailedLoginException;
import com.honey.simpleblog.mapper.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserAccountMapper userAccountMapper;

    public boolean signUpUser(UserAccountRequestDto dto) {
        UserAccount userAccount = dto.toDomain();
        validateDuplicatedLoginId(userAccount.getLoginId());
        Integer result = userAccountMapper.saveUser(userAccount);
        return isOneRowChange(result);
    }

    private void validateDuplicatedLoginId(String loginId) {
        String findId = userAccountMapper.findById(loginId);
        if (findId != null) {
            throw new DuplicateKeyException("이미 존재하는 아이디 입니다.");
        }
    }

    public UserAccount loginUser(UserAccountRequestDto dto) {
        UserAccount userAccount = userAccountMapper.loginUser(dto.toDomain())
                .orElseThrow(() -> new FailedLoginException("아이디/비밀번호가 일치하지 않습니다."));
        return userAccount;
    }

    private boolean isOneRowChange(Integer result) {
        return result == 1;
    }
}
