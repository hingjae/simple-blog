package com.honey.simpleblog.mapper;

import com.honey.simpleblog.domain.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAccountMapper {

    Integer saveUser(@Param("userAccount") UserAccount userAccount);
}
