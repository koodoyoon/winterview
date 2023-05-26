package com.example.winterview.mapper;

import com.example.winterview.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {
    int insert(QnaDto qna);
    QnaDto selectRandomQuestion(int userIdx);

    void updateAnswer(@Param("userIdx") int userIdx, @Param("questionIdx") int questionIdx, @Param("answerContent") String answerContent);

    QnaDto selectOne(int questionIdx);
}
