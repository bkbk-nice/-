package com.bkbk.qsdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.GroupX;
import com.bkbk.qsdemo.entity.Answer;
import com.bkbk.qsdemo.entity.MyGroup;

import com.bkbk.qsdemo.service.MyGroupService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class MyGroupController {

    @Autowired
    private MyGroupService myGroupService;

    @PostMapping
    public boolean save(@RequestBody MyGroup group){
        return myGroupService.saveGroup(group);
    }



//    @GetMapping("/")
//    public List<MyGroup> findAll(){
//        return groupService.list();
//    }



//
//    @DeleteMapping("/{id}")
//    public boolean delete(@PathVariable Integer id){
//        if(!myGroupService.queryAnswernum(id)){
//            return false;
//        }
//        return myGroupService.removeById(id);
//    }


//    @PostMapping("/del/batch")
//    public boolean deleteBatch(@RequestBody List<Integer> ids){
//
//        return myGroupService.removeByIds(ids);
//    }

    @GetMapping("/inviteAnswer")
    public Result inviteAnswer(@RequestParam String groupId,
                        @RequestParam String answerId,@RequestParam String groupLeader

    ){
        System.out.println("invite  arrive con");
        return myGroupService.inviteAnswer(groupId,answerId,groupLeader);
    }


    @GetMapping ("/de")
    public Result del(@RequestParam String groupId,
                               @RequestParam String groupLeader

    ){
        System.out.println(" ===================  del  arrive con");
        return myGroupService.delgroup(groupId,groupLeader);
    }




    //  分页
    @GetMapping("/page")
    public IPage<MyGroup> findPage(

            @RequestParam(defaultValue = "") String groupLeader,
            @RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (defaultValue = "") String  groupId,
                                @RequestParam(defaultValue = "") String groupName


    ){
        IPage<MyGroup> page = new Page<>(pageNum,pageSize);
        QueryWrapper<MyGroup> queryWrapper = new QueryWrapper<>();
        if( !"".equals(groupId)){
            queryWrapper.like("group_id",groupId);
        }if(!"".equals(groupName)){
            queryWrapper.like("group_name",groupName);
        }
        queryWrapper.orderByDesc("group_id");
        queryWrapper.eq("group_leader",groupLeader);
        return  myGroupService.page(page,queryWrapper);

    }


     @GetMapping("/groupX")
    public List<Answer> getGroup(

            @RequestParam(defaultValue = "") String groupId
    ){

        return  myGroupService.getGroup(groupId);

    }


}
