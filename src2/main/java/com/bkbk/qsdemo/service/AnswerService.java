package com.bkbk.qsdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.AnswerDto;



import com.bkbk.qsdemo.entity.Answer;

import com.bkbk.qsdemo.mapper.AnswerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnswerService  extends ServiceImpl<AnswerMapper, Answer> {


    @Autowired
    private AnswerMapper answerMapper;


    public boolean saveAnswer(Answer answer) {

        // return saveOrUpdate(rent);
        if (answer.getAnswerId()== null) {
            return save(answer);
        } else {
            List<Answer> x =  answerMapper.getbyid(answer.getAnswerId());


            String ininame = x.get(0).getAnswerName();
            String iniphone = x.get(0).getAnswerPhone();

            if(!ininame.equals(answer.getAnswerName())){
                List<Answer> xname = answerMapper.judgename(answer.getAnswerName());
                if(xname.size()!=0){
                    return false;
                }
            }
            if(!iniphone.equals(answer.getAnswerPhone())){
                List<Answer> xphone = answerMapper.judgephone(answer.getAnswerPhone());
                if(xphone.size()!=0){
                    return false;
                }
            }

            return updateById(answer);
        }
    }



    public Answer loginx(Answer answer) {

        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("answer_name",answer.getAnswerName());
        queryWrapper.eq("answer_password",answer.getAnswerPassword());
        Answer one = getOne(queryWrapper);
        if(one!=null){
            //BeanUtil.copyProperties(one,answerDto,true);
            return  one;
        }
        return null;
    }

    public Result regis(String answerName,String answerPassword,String answerPhone) {


            List<Answer> x= answerMapper.judgename(answerName);
            if(x.size()!=0){
                return Result.error(Constants.CODE_400,"用户名已存在");
            }


            List<Answer> xx = answerMapper.judgephone(answerPhone);
            if(xx.size()!=0){
                return Result.error(Constants.CODE_401,"手机号已存在");
             }


        answerMapper.regis(answerName,answerPassword,answerPhone);
            return Result.success(Constants.CODE_200);

    }
}
