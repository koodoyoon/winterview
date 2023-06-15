package com.example.winterview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    // 메인 페이지 연결
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("")
    public String push() {
        return "";
    }
}
