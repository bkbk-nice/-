package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.bkbk.qsdemo.controller.dto.Cnt;
import com.bkbk.qsdemo.controller.dto.QnAnswer;
import com.bkbk.qsdemo.entity.*;
import jdk.internal.org.objectweb.asm.tree.analysis.SourceValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;

@Mapper
public interface QnMapper  extends BaseMapper<Qn> {

    @Update("update user set qnnum=qnnum+1 where  user.id = #{qnUser}")
    void addoneqn(Long qnUser);



    @Select("SELECT   qnchinfo.qnchoice_id,qnchoice_word,qnchoice_a,qnchoice_b,qnchoice_c"+
            " FROM qnchinfo INNER JOIN qnchoice ON qnchoice.qnchoice_id=qnchinfo.qnchoice_id  WHERE qn_id = #{qnId}")
    List<Qnchoice> getQn(String qnId);

    @Insert("INSERT INTO qnchoice (qnchoice_word,qnchoice_a,qnchoice_b,qnchoice_c) " +
            "VALUES (#{qnchoiceWord},#{qnchoiceA},#{qnchoiceB},#{qnchoiceC}) ")
    void addchoice(String qnchoiceWord, String qnchoiceA, String qnchoiceB, String qnchoiceC);

    @Insert("INSERT INTO qnchinfo (qn_id,qnchoice_id,qnchoice_anum,qnchoice_bnum,qnchoice_cnum) " +
            "VALUES (#{qnId},#{qnchoiceId},0,0,0)")
    void okdata(String qnId, String qnchoiceId);

    @Select("SELECT LAST_INSERT_ID()")
    Long getlastid();

   @Select(  "SELECT qn.qn_id,qn.qn_user,qn.qn_state,qn.qn_name" +
           "           FROM   qngroup  INNER JOIN  groupinfo" +
           "           ON groupinfo.group_id = qngroup.group_id" +
           "           INNER JOIN qn ON" +
           "           qn.qn_id =  qngroup.qn_id" +
           "           WHERE answer_id = #{answerId}" )
   List<Qn> pageForAnswer(String answerId);

    @Select("select * from my_group where group_id = #{groupId} and group_leader =#{groupLeader}")
    List<MyGroup> judgehavenowGroup(String groupId, String groupLeader);

    @Insert("insert into qngroup (qn_id,group_id) values (#{qnId},#{groupId})")
    void release(String qnId, String groupId);

    @Select("select * from qn where qn_id = #{qnId} and qn_state = #{va} ")
    List<Qn> judgerelease(String qnId,String va);

   @Update("UPDATE qn SET qn_state = '已发布' WHERE qn_id = #{qnId} ")
   void updatestate(String qnId);

    @Select("select name from user where id = #{id}")
    List<User> loadUserName(String id);

    @Insert("insert into qnout (qn_id,id,answer_id,qnchoice_id,answerout) values (#{t},#{tt},#{ttt},#{aLong},#{s})")
    void addout(Long t, Long tt, Long ttt, Long aLong, String s);

    @Select("select * from qnout where answer_id=#{answerId} and qn_id=#{qnId} and id = #{id}")
    List<QnResult> ifdone(String answerId, String qnId, String id);

   @Select("SELECT  \n" +
           "qnout.qn_id,qnout.id,qnout.answer_id,qnout.qnchoice_id,qnout.answerout,qnchoice.qnchoice_word,qnchoice.qnchoice_a,qnchoice.qnchoice_b,qnchoice.qnchoice_c\n" +
           "FROM qnout INNER  JOIN  qnchoice ON qnchoice.qnchoice_id = qnout.qnchoice_id \n" +
           "WHERE qnout.qn_id = #{qnId} AND qnout.id = #{id} AND qnout.answer_id = #{answerId}")
   List<QnItem> loadDoneQn(String qnId, String id, String answerId);


   @Select("SELECT DISTINCT  qnout.qn_id,qnout.answer_id,answer.answer_name FROM qnout \n" +
           "JOIN answer ON answer.answer_id = qnout.answer_id\n" +
           "WHERE qn_id =#{qnId}  ")
   List<QnAnswer> getanswer(String qnId );

    @Select("SELECT DISTINCT  qnout.qn_id,qnout.answer_id,answer.answer_name FROM qnout \n" +
            "JOIN answer ON answer.answer_id = qnout.answer_id\n" +
            "WHERE qn_id =#{qnId} AND answer_name LIKE #{answerName}  ")
    List<QnAnswer> getanswerByname(String qnId,  String answerName);

   @Update("update qn set qn_del = 1 where qn_id = #{qnId}")
   void delqn(Long qnId);

   @Select("SELECT qnchoice_id FROM qnout WHERE qn_id = #{qnId}\n" +
           "GROUP BY qnchoice_id\n")
   List<Integer> getallqnchoiceid(String qnId);


   @Select(" SELECT * FROM qnchoice WHERE qnchoice_id = #{chid} AND qnchoice_a = ''")
   List<Qnchoice> judgeifchoice(int chid);

   @Select("SELECT   COUNT(answerout) AS cnt FROM qnout \n" +
           "JOIN qnchoice ON qnchoice.qnchoice_id = qnout.qnchoice_id\n" +
           "WHERE qn_id = #{qnId}    AND  qnout.qnchoice_id = #{chid} AND answerout = 'A'\n" +
           "GROUP BY answerout ")
   List<Cnt> getchoicea(String qnId, int chid);

    @Select("SELECT   COUNT(answerout) AS cnt FROM qnout \n" +
            "JOIN qnchoice ON qnchoice.qnchoice_id = qnout.qnchoice_id\n" +
            "WHERE qn_id = #{qnId}    AND  qnout.qnchoice_id = #{chid} AND answerout = 'B'\n" +
            "GROUP BY answerout ")
    List<Cnt> getchoiceb(String qnId, int chid);

    @Select("SELECT   COUNT(answerout) AS cnt FROM qnout \n" +
            "JOIN qnchoice ON qnchoice.qnchoice_id = qnout.qnchoice_id\n" +
            "WHERE qn_id = #{qnId}    AND  qnout.qnchoice_id = #{chid} AND answerout = 'C'\n" +
            "GROUP BY answerout ")
    List<Cnt> getchoicec(String qnId, int chid);
}
