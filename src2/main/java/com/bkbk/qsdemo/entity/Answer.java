package com.bkbk.qsdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @TableId(type= IdType.AUTO)
    private Long answerId;
    private String answerName;
    private String answerPassword;
    private String answerPhone;
    private String answerPermiss;
    private String answerChecka;


}
