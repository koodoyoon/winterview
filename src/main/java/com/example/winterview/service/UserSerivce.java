package com.example.winterview.service;

import com.example.winterview.dto.UserDto;

public interface UserSerivce {
    int register(UserDto user);

    UserDto login(String id, String pw);
}
