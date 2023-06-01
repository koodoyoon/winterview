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

    @GetMapping("/main")
    public String main() {
        try {
            return "/qna/main";
        } catch (Exception e) {
            return "redirect:/user/login";
        }
    }

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

    @GetMapping("/randomQuestion")
    public String getRandomQuestion(
            Model model,
            @SessionAttribute UserDto user) {
        try {
            QnaDto qna = qnaService.getRandomQuestion(user.getUserIdx());
            model.addAttribute("qna", qna);
            return "/qna/main";
        } catch (Exception e) {
            return "/user/login";
        }
    }

    @GetMapping("/nextQuestion")
    @ResponseBody
    public QnaDto getRandomQuestion(
            @SessionAttribute UserDto user
    ) {
        try {
            QnaDto qna = qnaService.getRandomQuestion(user.getUserIdx());
            System.out.println(qna);
            return qnaService.getRandomQuestion(user.getUserIdx());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/submitAnswer")
    public ResponseEntity<?> submitAnswer(
            @RequestParam int questionIdx,
            @RequestParam String answerContent,
            @SessionAttribute UserDto user
    ) {
        try {
            qnaService.submitAnswer(user.getUserIdx(), questionIdx, answerContent);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("success", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
