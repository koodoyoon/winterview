package com.example.winterview.service;

import com.example.winterview.dto.QuestionDto;

public interface QuestionService {
    int register(QuestionDto question);

    QuestionDto getRandomQuestion(int userIdx);
}
