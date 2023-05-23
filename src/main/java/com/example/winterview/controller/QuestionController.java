package com.example.winterview.controller;

import com.example.winterview.dto.QuestionDto;
import com.example.winterview.dto.UserDto;
import com.example.winterview.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    private UserDto getAuthenticatedUser(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        return user;
    }

    @GetMapping("/main")
    public String main(
            HttpSession session
    ) {
        try {
            return "/question/main";
        } catch (Exception e) {
            return "redirect:/user/login";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        QuestionDto question,
        HttpSession session
    ) {
        try {
            UserDto user = getAuthenticatedUser(session);
            question.setUserIdx(user.getUserIdx());
            System.out.println("Question content: " + question.getQuestionContent());
            questionService.register(question);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("success", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/randomQuestion")
    public String getRandomQuestion(
            Model model,
            HttpSession session) {
        try {
            UserDto user = getAuthenticatedUser(session);
            QuestionDto question = questionService.getRandomQuestion(user.getUserIdx());
            model.addAttribute("question", question);
            System.out.println("question : " + question);
            return "/question/main";
        } catch (Exception e) {
            return "/user/login";
        }
    }

    @GetMapping("/nextQuestion")
    @ResponseBody
    public QuestionDto getRandomQuestion(HttpSession session) {
        try {
            UserDto user = getAuthenticatedUser(session);
            return questionService.getRandomQuestion(user.getUserIdx());
        } catch (Exception e) {
            return null;
        }
    }
}
