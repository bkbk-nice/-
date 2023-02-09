package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.qsdemo.controller.dto.GroupX;
import com.bkbk.qsdemo.entity.Answer;
import com.bkbk.qsdemo.entity.MyGroup;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyGroupMapper extends BaseMapper<MyGroup> {


    @Select("select  * from my_group where group_id = #{id}  and  group_answernum =0 ")
    List<MyGroup> queryAnswernum(String id);


    @Select("SELECT  answer.answer_id,answer_name FROM groupinfo INNER JOIN answer ON groupinfo.answer_id=answer.answer_id  WHERE group_id = #{groupId}")
    List<Answer> getGroup(String groupId);

    @Select("select  * from answer where answer_id = #{answerId}   ")
    List<Answer> judgeifexistanswer(String answerId);

    @Select("select  * from groupinfo where group_id = #{groupId}   and  answer_id = #{answerId}")
    List<Object> judgeifexistrelation(String groupId, String answerId);

    @Insert("INSERT INTO groupinfo VALUES (#{groupId}, #{answerId})" )
    void addmy(String groupId, String answerId);

     @Update("update user set groupnum=groupnum+1 where  user.id = #{groupLeader}")
    void addonegroup(Long groupLeader);

    @Update("update user set answernum=answernum+1 where  user.id = #{groupLeader}")
    void addoneanswertouser(String groupLeader);

    @Update("update my_group set group_answernum=group_answernum+1 where group_id = #{groupId}")
    void addonetogroupnum(String groupId);

    @Update("update user set groupnum=groupnum-1 where id = #{groupLeader}")
    void suboneusergroup(String groupLeader);

   @Delete("DELETE FROM my_group WHERE group_id = #{groupId}")
   void removegroup(String groupId);
}
