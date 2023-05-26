package com.example.winterview.dto;

import lombok.Data;

@Data
public class QnaDto {
    private int questionIdx;
    private String questionContent;
    private int userIdx;
    private String answerContent;
}
