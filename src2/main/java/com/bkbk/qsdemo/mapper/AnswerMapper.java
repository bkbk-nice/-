package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.bkbk.qsdemo.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerMapper   extends BaseMapper<Answer> {
    @Select("select  * from answer where   answer_name = #{name} ")
    List<Answer> judgename(String name);


    @Select("select  * from answer where   answer_phone = #{phone} ")
    List<Answer> judgephone(String phone);

    @Select("select  * from answer where   answer_id = #{id}")
    List<Answer> getbyid(Long id);

    @Insert("insert into answer  (answer_name,answer_password,answer_phone) values (#{answerName},#{answerPassword},#{answerPhone} )  ")
    void regis(String answerName,String answerPassword,String answerPhone);
}
