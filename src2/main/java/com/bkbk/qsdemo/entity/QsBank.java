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

public class QsBank {


    private Integer userId;

    @TableId(type= IdType.AUTO)
    private Integer qsbankId;

    private String qsbankWord;
    private String qsbankA;
    private String qsbankB;
    private String qsbankC;
}
