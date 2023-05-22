package com.example.winterview.service;

import com.example.winterview.dto.QuestionDto;
import com.example.winterview.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImp implements QuestionService{
    private QuestionMapper questionMapper;

    public QuestionServiceImp(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public int register(QuestionDto question) {
        return questionMapper.insert(question);
    }

    public QuestionDto getRandomQuestion(int userIdx) {
        return questionMapper.selectRandomQuestion(userIdx);
    }
}
