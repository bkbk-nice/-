package com.bkbk.qsdemo.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FeeDto {
    private Long qnnum;
    private Long groupnum;
    private Long answernum;
}
