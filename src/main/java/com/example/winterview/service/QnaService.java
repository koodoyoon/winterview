package com.example.winterview.service;

import com.example.winterview.dto.QnaDto;
import org.springframework.beans.factory.annotation.Value;

public interface QnaService {

    @Value("${img.upload.path}")
    int register(QnaDto qna);

    QnaDto getRandomQuestion(int userIdx);

    QnaDto selectOne(int questionIdx);

    int addAnswer(QnaDto qnaDto);
}
