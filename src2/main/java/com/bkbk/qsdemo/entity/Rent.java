package com.bkbk.qsdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rent{

    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    //@JsonIgnore
    private String password;
    private String phone;
    private String permiss;
    private String checka;
    private Integer usernum;
    private double fee;


}
