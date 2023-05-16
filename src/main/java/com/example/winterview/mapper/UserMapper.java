package com.example.winterview.mapper;

import com.example.winterview.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(UserDto user);

    UserDto login(String userId, String pw);
}
