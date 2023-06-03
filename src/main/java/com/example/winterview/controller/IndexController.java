package com.example.winterview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 메인 페이지 연결
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
