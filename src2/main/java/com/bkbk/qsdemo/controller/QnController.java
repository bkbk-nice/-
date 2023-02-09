package com.bkbk.qsdemo.controller;



import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.entity.*;
import com.bkbk.qsdemo.service.QnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qn")
public class QnController {

    @Autowired
    private QnService qnService;

    @PostMapping
    public boolean save(@RequestBody Qn qn){
        return qnService.saveQn(qn);
    }

    @GetMapping("/loadQn")
    public List<Qnchoice> getQn(

            @RequestParam(defaultValue = "") String qnId
    ){
        return  qnService.getQn(qnId);
    }


    @GetMapping("/del")
    public Result delqn(
            @RequestParam(defaultValue = "") Long qnId

    ){

        return  qnService.delqn(qnId);
    }

    @GetMapping("/submitQn")
    public Result submitQn(
            @RequestParam(defaultValue = "") String out


    ){
        QnResult qnResult = JSONUtil.toBean(out, QnResult.class);
      //  System.out.println(qnResult.getQnId());
       // QnResult qnResult = new QnResult(qnId,id,answerId,qnchoiceId,answerOut);
        return  qnService.submitQn(qnResult);
    }


    @GetMapping("/ifDone")
    public Result ifDone(
            @RequestParam(defaultValue = "") String answerId,
             @RequestParam(defaultValue = "") String qnId,
                     @RequestParam(defaultValue = "") String id


    ){

        return  qnService.ifDone(answerId,qnId,id);
    }

    @GetMapping("/release")
    public Result release(

            @RequestParam(defaultValue = "") String qnId,
             @RequestParam(defaultValue = "") String groupId,
              @RequestParam(defaultValue = "") String groupLeader
    ){
        return  qnService.release(qnId,groupId,groupLeader);
    }



    @GetMapping("/pageForAnswer")
    public Result pageForAnswer(

            @RequestParam(defaultValue = "") String answerId
    ){
        return  qnService.pageForAnswer(answerId);
    }



    @GetMapping("/addChoice")
    public Result addChoice(@RequestParam String qnId,
                               @RequestParam String qnchoiceWord,
                            @RequestParam (defaultValue = "") String qnchoiceA,
                             @RequestParam(defaultValue = "")  String qnchoiceB,
                            @RequestParam(defaultValue = "") String qnchoiceC

    ){
       // System.out.println("invite  arrive con");
        return qnService.addChoice(qnId,qnchoiceWord,qnchoiceA,qnchoiceB,qnchoiceC);
    }


    @GetMapping("/answer")
    public Result getanswer(@RequestParam String qnId,
                            @RequestParam (defaultValue = "")  String answerName

    ){

        return qnService.getanswer(qnId,answerName);
    }


    @GetMapping("/loadUserName")
    public Result loadUserName(@RequestParam String Id
    ){
        // System.out.println("invite  arrive con");
        return qnService.loadUserName(Id);
    }

    @GetMapping("/loadDoneQn")
    public Result loadDoneQn(@RequestParam String qnId,
                             @RequestParam String id,
                             @RequestParam String answerId
    ){
        // System.out.println("invite  arrive con");
        return qnService.loadDoneQn(qnId,id,answerId);
    }


    @GetMapping("/sta")
    public Result qnSta(@RequestParam String qnId

    ){
        // System.out.println("invite  arrive con");
        return qnService.qnSta(qnId);
    }



    @GetMapping("/page")
    public IPage<Qn> findPage(

            @RequestParam(defaultValue = "") String qnUser,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam (defaultValue = "") String  qnId,
            @RequestParam(defaultValue = "") String qnName,
            @RequestParam(defaultValue = "") String qnState


    ){
        IPage<Qn> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Qn> queryWrapper = new QueryWrapper<>();
        if( !"".equals(qnId)){
            queryWrapper.like("qn_id",qnId);
        }if(!"".equals(qnName)){
            queryWrapper.like("qn_name",qnName);
        }if(!"".equals(qnState)){
            queryWrapper.like("qn_state",qnState);
        }
        queryWrapper.orderByDesc("qn_id");
        queryWrapper.eq("qn_user",qnUser);
        queryWrapper.eq("qn_del",0);
        return  qnService.page(page,queryWrapper);

    }




}
