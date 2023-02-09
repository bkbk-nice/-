package com.bkbk.qsdemo.controller;

import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.AnswerDto;
import com.bkbk.qsdemo.entity.Admin;
import com.bkbk.qsdemo.entity.Answer;
import com.bkbk.qsdemo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public boolean save(@RequestBody Answer admin){
        return answerService.saveAnswer(admin);
    }

    @GetMapping("/register")
    public Result save(@RequestParam String answerName,
                       @RequestParam String answerPassword,
                       @RequestParam String answerPhone

                       ){
        return answerService.regis(answerName,answerPassword,answerPhone);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Answer answer){
        Answer tx =  answerService.loginx(answer);

        if(tx==null){
            return Result.error(Constants.CODE_400,"无结果");
        }else{
            tx.setAnswerPassword("");
            return Result.success(tx);
        }
    }
}