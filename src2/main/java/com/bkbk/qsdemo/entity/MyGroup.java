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
public class MyGroup {
    @TableId(type= IdType.AUTO)
    private Long groupId;
    private String groupName;
    private Long groupLeader;
    private Long groupAnswernum;
}
