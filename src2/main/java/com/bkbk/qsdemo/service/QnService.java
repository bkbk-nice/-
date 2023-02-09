package com.bkbk.qsdemo.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.Cnt;
import com.bkbk.qsdemo.controller.dto.QnAnswer;
import com.bkbk.qsdemo.controller.dto.StaDto;
import com.bkbk.qsdemo.entity.*;
import com.bkbk.qsdemo.mapper.QnMapper;
import jdk.nashorn.internal.runtime.ConsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QnService extends ServiceImpl<QnMapper, Qn> {

    @Autowired
    private QnMapper qnMapper;
    public boolean saveQn(Qn qn) {

        qnMapper.addoneqn(qn.getQnUser());

        if (qn.getQnId() == null) {

            return save(qn);
        } else {
            return updateById(qn);
        }
    }

    public List<Qnchoice> getQn(String qnId) {
        return qnMapper.getQn(qnId);
    }

    public Result addChoice(String qnId, String qnchoiceWord, String qnchoiceA, String qnchoiceB, String qnchoiceC) {
        //添加题目  ， 获取题目 id  ,添加info,

         qnMapper.addchoice(qnchoiceWord,qnchoiceA,qnchoiceB,qnchoiceC);
        Long qnchoiceId = qnMapper.getlastid();
        qnMapper.okdata(qnId,qnchoiceId.toString());
        return Result.success();


    }

    public Result pageForAnswer(String answerId) {
        List<Qn>  x = qnMapper.pageForAnswer(answerId);
        return Result.success(x);

    }

    public Result release(String qnId, String groupId,String groupLeader) {

        if(qnMapper.judgerelease(qnId,"未发布").size()==0){
            return Result.error(Constants.CODE_401,"已经发布");
        }
        if(qnMapper.judgehavenowGroup(groupId,groupLeader).size()== 0 ){
            return  Result.error(Constants.CODE_400,"您没有这个群");
        }
        qnMapper.release(qnId,groupId);
        qnMapper.updatestate(qnId);
        return Result.success();
    }

    public Result loadUserName(String id) {
        List<User>  x = qnMapper.loadUserName(id);

        return Result.success(x);
    }

    public Result submitQn(QnResult qnResult) {

        Long t = qnResult.getQnId();
        Long tt = qnResult.getId();
        Long ttt = qnResult.getAnswerId();
        List<Long> l1= qnResult.getQnchoiceId();
        List<String> l2 = qnResult.getAnswerOut();

        for (int i = 0 ;i<l1.size();i++) {
            qnMapper.addout(t,tt,ttt,l1.get(i),l2.get(i));
        }
        return Result.success();
    }

    public Result ifDone(String answerId, String qnId, String id) {

        List<QnResult> x = qnMapper.ifdone(answerId,qnId,id);
        if(x.size()>0) {
            return Result.success();
        }else{
            return Result.error();
        }


    }

    public Result loadDoneQn(String qnId, String id, String answerId) {
        List<QnItem>  x = qnMapper.loadDoneQn(qnId,id,answerId);
        return  Result.success(x);
    }

    public Result getanswer(String qnId,   String answerName) {
        List<QnAnswer>  x;
        if(!answerName.equals("")){
          answerName =  "%"+answerName+"%";
           x = qnMapper.getanswerByname(qnId,answerName);
      }else{
            x = qnMapper.getanswer(qnId);
      }


        return Result.success(x);
    }

    public Result delqn(Long qnId) {
        qnMapper.delqn(qnId);
        return Result.success();

    }

    public Result qnSta(String qnId) {
        //获取全部题目id   逐个查询结果

        StaDto x = new StaDto();
        x.setQnchoiceId(qnMapper.getallqnchoiceid(qnId));
        x.setStaNum(new ArrayList<>());
         x.setStaA(new ArrayList<>());
         x.setStaB(new ArrayList<>());
         x.setStaC(new ArrayList<>());

        int tt = x.getQnchoiceId().size();
        int now ;
        for(int i = 0 ; i<tt;i++){


//            now = i;
//            int chid = x.getQnchoiceId().get(now);
//            System.out.println(chid);

           x.getStaNum().add(i+1);
           now = i;
           int chid = x.getQnchoiceId().get(now);
           //judge

            if(qnMapper.judgeifchoice(chid).size()==0){
                //是选择题

                List<Cnt> tyy =  qnMapper.getchoicea(qnId,chid);
                if(tyy.size()!=0){x.getStaA().add(tyy.get(0).getCnt());}else{ x.getStaA().add(0);}

                List<Cnt> tyy1 =  qnMapper.getchoiceb(qnId,chid);
                if(tyy1.size()!=0){x.getStaB().add(tyy1.get(0).getCnt());}else{ x.getStaB().add(0);}

                List<Cnt> tyy2 =  qnMapper.getchoicec(qnId,chid);
                if(tyy2.size()!=0){x.getStaC().add(tyy2.get(0).getCnt());}else{ x.getStaC().add(0);}

            }else{
                x.getStaA().add(0);
                x.getStaB().add(0);
                x.getStaC().add(0);
            }
        }
        return Result.success(x);
    }
}



















