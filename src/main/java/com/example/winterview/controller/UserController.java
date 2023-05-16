package com.example.winterview.controller;

import com.example.winterview.dto.UserDto;
import com.example.winterview.service.UserSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class UserController {
    UserSerivce userSerivce;

    public UserController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    // 회원가입
    @GetMapping("/signup.do")
    public String signup() {
        return "/signup";
    }

    @PostMapping("/signup.do")
    public String signup(
        UserDto user
    ) {
        try {
            userSerivce.register(user);
            return "redirect:/login.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup.do";
        }
    }

    // 로그인
    @GetMapping("/login.do")
    public String login() {
        return "/login";
    }

    @PostMapping("/login.do")
    public String login(
            @RequestParam("userId") String id,
            String pw
    ) {
        try {
            UserDto user = userSerivce.login(id, pw);
            if (user == null) { // 로그인 실패
                return "redirect:/login.do";
            }
            return "redirect:/"; // 로그인 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login.do";
        }
    }
}
