package com.example.winterview.service;

import com.example.winterview.dto.QnaDto;

public interface QnaService {
    int register(QnaDto qna);
    QnaDto getRandomQuestion(int userIdx);
    QnaDto selectOne(int questionIdx);

    int addAnswer(QnaDto qnaDto);
}
