package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper  extends BaseMapper<User> {



    @Select("select  * from user where   id = #{id}")
    List<User> getbyid(Long id);

    @Select("select  * from user where   `name` = #{name} ")
    List<User> judgename(String name);

    @Select("select  * from user where   phone = #{phone} ")
    List<User> judgephone(String phone);



    @Select("select  * from user where id = #{id}  and answernum = 0 and groupnum = 0 and qnnum = 0")
    List<Rent> queryRelation(Integer id);


    @Update("update rent set usernum=usernum+1 where  id = #{leader}")
    void addonetorent(Integer leader);
}
