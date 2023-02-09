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
public class Qnchoice {

    @TableId(type= IdType.AUTO)
    private Long qnchoiceId;
    private String qnchoiceWord;
    private String qnchoiceA;
    private String qnchoiceB;
    private String qnchoiceC;



}
