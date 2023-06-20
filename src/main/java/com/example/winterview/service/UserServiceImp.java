package com.example.winterview.service;

import com.example.winterview.dto.UserDto;
import com.example.winterview.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserSerivce{
    private UserMapper userMapper;
    public UserServiceImp(UserMapper userMapper) {this.userMapper = userMapper;}

    public int register(UserDto user) {
        return userMapper.insert(user);
    }

    public UserDto login(String id, String pw) {
        return userMapper.login(id, pw);
    }
}
