package com.example.winterview.mapper;

import com.example.winterview.dto.QuestionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    int insert(QuestionDto question);
}
