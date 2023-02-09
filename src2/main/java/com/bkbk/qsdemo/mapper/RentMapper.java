package com.bkbk.qsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.qsdemo.controller.dto.FeeDto;
import com.bkbk.qsdemo.entity.Admin;
import com.bkbk.qsdemo.entity.Rent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RentMapper extends BaseMapper<Rent> {

    @Select("select  * from rent where id = #{id}  and usernum = 0")
    List<Rent> queryusernum(Integer id);

    @Select("select  * from rent where   id = #{id}")
    List<Rent> getbyid(Long id);

    @Select("select  * from rent where   `name` = #{name} ")
    List<Rent> judgename(String name);

    @Select("select  * from rent where   phone = #{phone} ")
    List<Rent> judgephone(String phone);

    @Insert("insert into rent  (`name`,password,phone) values (#{name},#{password},#{phone} )  ")
    void regis(String name,String password,String phone);

   @Select("SELECT  SUM(qnnum) AS qnnum,SUM(groupnum) AS groupnum,SUM(answernum) AS answernum FROM  rent \n" +
           "JOIN USER ON rent.id = user.leader\n" +
           "WHERE rent.id = #{id}")
   List<FeeDto> getfeeinfo(String id);

   @Update("update rent set fee = #{r}  where id = #{id}")  void setfee(String id, double r);

//
//
//    @Insert("INSERT  into rent(`name`,passwordr,phone,permiss,checka,usernum) VALUES (#{name},#{passwordr},#{phone},#{permiss},#{checka},#{usernum})")
//    int insert(Rent rent);
//
//
//     boolean update(Rent rent);
//
//    @Delete("delete from rent where id = #{id}")
//    Integer deleteById(@Param("id") String id);
//
//
//    @Select("select * from rent where id like #{rentId} limit #{pageNum},#{pageSize}")
//    List<Rent> selectPage(Integer pageNum, Integer pageSize,String rentId);
//
//
//    @Select("select count(*) from rent where id like #{rentId} ")
//    Integer selectTotal(String rentId);


}
