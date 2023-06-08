package com.example.winterview.service;

import com.example.winterview.dto.QnaDto;
import com.example.winterview.mapper.QnaMapper;
import org.springframework.stereotype.Service;

@Service
public class QnaServiceImp implements QnaService {
    private QnaMapper qnaMapper;

    public QnaServiceImp(QnaMapper qnaMapper) {
        this.qnaMapper = qnaMapper;
    }

    public int register(QnaDto qna) {
        return qnaMapper.insert(qna);
    }
    public QnaDto getRandomQuestion(int userIdx) {
        return qnaMapper.selectRandomQuestion(userIdx);
    }

    public QnaDto selectOne(int questionIdx) {
        return qnaMapper.selectOne(questionIdx);
    }

    public int addAnswer(QnaDto qnaDto) {
        return qnaMapper.updateAnswer(qnaDto);
    }
}
