package com.example.winterview.mapper;

import com.example.winterview.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {
    int insert(QnaDto qna);

    QnaDto selectRandomQuestion(int userIdx);

    int updateAnswer(QnaDto qna);

    QnaDto selectOne(int questionIdx);
}
