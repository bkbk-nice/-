package com.bkbk.qsdemo.controller.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private  String name;
    private String password;
    private String permiss;
    private String phone;

}
