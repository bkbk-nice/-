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
public class User {

    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    //@JsonIgnore
    private String password;
    private String phone;
    private String permiss;
    private String checka;
    private Integer leader;
    private Long up;
    private Integer groupnum;
    private Integer answernum;
    private Integer qnnum;


}
