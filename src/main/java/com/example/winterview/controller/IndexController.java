package com.example.winterview.controller;

import com.example.winterview.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {

    // 메인 페이지 연결
    @GetMapping("/")
    public String index(
            @SessionAttribute(required = false)UserDto loginUser,
            HttpSession session
            ) {
        return "index";
    }
}
