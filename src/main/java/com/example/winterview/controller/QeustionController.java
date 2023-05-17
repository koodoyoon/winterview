package com.example.winterview.controller;

import com.example.winterview.dto.QuestionDto;
import com.example.winterview.dto.UserDto;
import com.example.winterview.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class QeustionController {
    private QuestionService questionService;

    public QeustionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/register.do")
    public ResponseEntity<?> register(
        QuestionDto question,
        HttpSession session
    ) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.FORBIDDEN);
        }
        question.setUserIdx(user.getUserIdx());
        System.out.println("Question content: " + question.getQuestionContent());
        try {
            questionService.register(question);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("success", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
