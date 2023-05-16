package com.example.winterview.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private int questionIdx;
    private String questionContent;
    private int userIdx;
}
