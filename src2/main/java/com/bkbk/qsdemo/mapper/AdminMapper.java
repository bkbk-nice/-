package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.qsdemo.entity.Admin;
import com.bkbk.qsdemo.entity.Rent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select  * from admin where   `name` = #{name} ")
    List<Admin> judgename(String name);


    @Select("select  * from admin where   phone = #{phone} ")
    List<Admin> judgephone(String phone);

    @Select("select  * from admin where   id = #{id}")
    List<Admin> getbyid(Long id);
}
