package com.example.winterview.controller;

import com.example.winterview.dto.UserDto;
import com.example.winterview.service.UserSerivce;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {
    UserSerivce userSerivce;

    public UserController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    // 회원가입
    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String signup(
        UserDto user
    ) {
        try {
            userSerivce.register(user);
            return "redirect:/user/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/signup";
        }
    }

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("userId") String id,
            String pw,
            HttpSession session
    ) {
        try {
            UserDto user = userSerivce.login(id, pw);
            if (user == null) { // 로그인 실패
                return "/user/login";
            }
            session.setAttribute("user", user);
            return "redirect:/"; // 로그인 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 저장된 사용자 정보를 삭제
        session.invalidate();

        return "redirect:/";
    }
}
