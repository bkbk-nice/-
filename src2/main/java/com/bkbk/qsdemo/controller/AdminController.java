package com.bkbk.qsdemo.controller;


import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.AdminDto;
import com.bkbk.qsdemo.controller.dto.RentDto;
import com.bkbk.qsdemo.entity.Admin;
import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.service.AdminService;
import com.bkbk.qsdemo.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public boolean save(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminDto adminDto){
        AdminDto tx =  adminService.loginx(adminDto);

        if(tx==null){
            return Result.error(Constants.CODE_400,"无结果");
        }else{
            tx.setPermiss("10");
            tx.setPassword("");
            return Result.success(tx);
        }
    }

    @GetMapping("/name")
    public Result login(@RequestParam  String name){
        AdminDto tx =  adminService.loadinfo(name);

        if(tx==null){
            return Result.error(Constants.CODE_400,"无结果");
        }else{
            tx.setPassword("******");
            return Result.success(tx);
        }
    }
}
