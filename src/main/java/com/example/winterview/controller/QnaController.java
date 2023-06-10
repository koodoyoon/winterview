package com.example.winterview.controller;

import com.example.winterview.dto.QnaDto;
import com.example.winterview.dto.UserDto;
import com.example.winterview.service.QnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/question")
public class QnaController {
    private QnaService qnaService;

    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    // QNA 메인 페이지 로딩
    @GetMapping("/main")
    public String main() {
        try {
            return "/qna/main";
        } catch (Exception e) {
            return "redirect:/user/login";
        }
    }

    // 질문 등록
    @PostMapping("/register")
    public String register(
        QnaDto qna,
        @SessionAttribute UserDto user
    ) {
        try {
            qna.setUserIdx(user.getUserIdx());
            System.out.println("Question content: " + qna.getQuestionContent());
            qnaService.register(qna);
            return "/qna/main";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/question/main";
        }
    }

    // 질문 랜덤 생성
    @GetMapping("/randomQuestion")
    @ResponseBody
    public QnaDto getRandomQuestion(
            @SessionAttribute UserDto user) {
        try {
            QnaDto qna = qnaService.getRandomQuestion(user.getUserIdx());
            System.out.println(qna);
            return qna;
        } catch (Exception e) {
            return null;
        }
    }

    // 다음 질문 불러오기
    @GetMapping("/nextQuestion")
    @ResponseBody
    public QnaDto getNextQuestion(
            @SessionAttribute UserDto user
    ) {
        try {
            QnaDto qna = qnaService.getRandomQuestion(user.getUserIdx());
            System.out.println(qna);
            return qna;
        } catch (Exception e) {
            return null;
        }
    }

    // 답변 등록
    @PostMapping("/question/submitAnswer")
    public String submitAnswer(@ModelAttribute QnaDto qna) {
        System.out.println("submitAnswer called");
        qnaService.addAnswer(qna);
        System.out.println(qna);
        return "/qna/main";
    }
}
