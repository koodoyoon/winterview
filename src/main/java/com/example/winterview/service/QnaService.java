package com.example.winterview.service;

import com.example.winterview.dto.QnaDto;

public interface QnaService {
    int register(QnaDto qna);
    QnaDto getRandomQuestion(int userIdx);

    void submitAnswer(int userIdx, int questionIdx, String answerContent);

    QnaDto selectOne(int questionIdx);
}
