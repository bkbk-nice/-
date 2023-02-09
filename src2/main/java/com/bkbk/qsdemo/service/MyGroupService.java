package com.bkbk.qsdemo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.GroupX;
import com.bkbk.qsdemo.entity.Answer;
import com.bkbk.qsdemo.entity.MyGroup;
import com.bkbk.qsdemo.mapper.MyGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyGroupService extends ServiceImpl<MyGroupMapper, MyGroup> {

    @Autowired
    private MyGroupMapper myGroupMapper;

    public boolean saveGroup(MyGroup group) {

        myGroupMapper.addonegroup(group.getGroupLeader());
//        if(group.getManager()==null){
//            return  false;
//        }
        if (group.getGroupId() == null) {

            return save(group);
        } else {
            return updateById(group);
        }
    }



    public List<Answer> getGroup(String groupId) {

        return myGroupMapper.getGroup(groupId);
    }

    public Result inviteAnswer(String groupId, String answerId,String groupLeader) {


        System.out.println("invite  arrive ser");

        List<Answer> li = myGroupMapper.judgeifexistanswer(answerId);
        if(li.size()==0){
            return Result.error(Constants.CODE_400,"无此ID");
        }

        List<Object> lis = myGroupMapper.judgeifexistrelation(groupId,answerId);
        if(lis.size()!=0){
            return Result.error(Constants.CODE_401,"已存在");
        }

        myGroupMapper.addoneanswertouser(groupLeader);
        myGroupMapper.addonetogroupnum(groupId);
        myGroupMapper.addmy(groupId,answerId);
        System.out.println("invite  arrive ser  over");
        return Result.success();
    }

    public Result delgroup(String groupId, String groupLeader) {

        List<MyGroup> x = myGroupMapper.queryAnswernum(groupId);
        if(x.size()!=0) {
            myGroupMapper.removegroup(groupId);
            myGroupMapper.suboneusergroup(groupLeader);
            return Result.success(Constants.CODE_200);
        }else{
            return Result.error(Constants.CODE_400,"有关联答者");
        }

    }
}
