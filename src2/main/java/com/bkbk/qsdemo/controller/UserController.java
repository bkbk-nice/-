package com.bkbk.qsdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.entity.QsBank;
import com.bkbk.qsdemo.controller.dto.UserDto;


import com.bkbk.qsdemo.entity.User;
import com.bkbk.qsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){
        UserDto tx =  userService.loginx(userDto);

        if(tx==null){
            return Result.error(Constants.CODE_400,"无结果");
        }else{
            tx.setPermiss("30");
            tx.setPassword("");
            return Result.success(tx);
        }
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        if(!userService.queryRelation(id)){
            return false;
        }
        return userService.removeById(id);
    }


    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){

        return userService.removeByIds(ids);
    }

    //  分页
    @GetMapping("/page")
    public IPage<User> findPage(
                                @RequestParam(defaultValue = "") Integer leader,
                                 @RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String userId,
                                @RequestParam(defaultValue = "") String userName,
                                @RequestParam(defaultValue = "") String userPhone


    ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(userId)){
            queryWrapper.or().like("id",userId);
        }if(!"".equals(userName)){
            queryWrapper.or().like("name",userName);
        }if(!"".equals(userPhone)){
            queryWrapper.or().like("phone",userPhone);
        }
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("leader", leader);
       // queryWrapper.select("leader",leader);
        return  userService.page(page,queryWrapper);

    }




}
