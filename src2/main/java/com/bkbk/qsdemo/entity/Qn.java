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
public class Qn {

    @TableId(type= IdType.AUTO)
    private Long qnId;
    private Long qnUser;
    private String qnState;
    private String qnName;

}
