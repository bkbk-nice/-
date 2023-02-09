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
public class Admin {

    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String permiss;
    private String checka;
}
