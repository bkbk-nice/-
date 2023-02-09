package com.bkbk.qsdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnResult {

    private Long qnId;
    private Long Id;
    private Long answerId;
    private List<Long> qnchoiceId;
    private List<String> answerOut;


    private String qnchoiceWord;
    private String qnchoiceA;
    private String qnchoiceB;
    private String qnchoiceC;

}
