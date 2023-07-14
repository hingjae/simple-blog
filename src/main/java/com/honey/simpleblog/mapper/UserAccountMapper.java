package com.honey.simpleblog.mapper;

import com.honey.simpleblog.domain.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserAccountMapper {

    String findById(@Param("loginId") String loginId);

    Integer saveUser(@Param("userAccount") UserAccount userAccount);

    Optional<UserAccount> loginUser(@Param("userAccount") UserAccount userAccount);
}
