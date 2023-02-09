package com.bkbk.qsdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnAnswer {
    private Integer qnId;
    private Integer answerId;
    private String  answerName;
}
