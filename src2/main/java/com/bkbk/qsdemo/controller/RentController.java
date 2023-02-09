package com.bkbk.qsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.AdminDto;
import com.bkbk.qsdemo.controller.dto.RentDto;
import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.mapper.RentMapper;
import com.bkbk.qsdemo.service.RentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Component
@RestController
@RequestMapping("/rent")
public class RentController {

//    Autowired
//    private RentMapper rentMapper;@

   @Autowired
   private RentService rentService;


    @PostMapping
    public boolean save(@RequestBody Rent rent){
        return rentService.saveRent(rent);
    }


    @GetMapping("/register")
    public Result save(@RequestParam String name,
                       @RequestParam String password,
                       @RequestParam String phone

    ){
        return rentService.regis(name,password,phone);
    }


    @GetMapping("/fee")
    public Result fee(@RequestParam String id

    ){
        return rentService.fee(id);
    }



    @PostMapping("/login")
    public Result login(@RequestBody RentDto rentDto ){
        RentDto tx =  rentService.loginx(rentDto);

        if(tx==null){
            return Result.error(Constants.CODE_400,"无结果");
        }else{
            tx.setPermiss("20");
            tx.setPassword("");
            return Result.success(tx);
        }

    }



//    @GetMapping("/")
//    public List<Rent> findAll(){
//        return rentService.list();
//    }
//



    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        if(!rentService.queryUsernum(id)){
                return false;
        }
        return rentService.removeById(id);
    }


    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){

        return rentService.removeByIds(ids);
    }


        //  分页
//    @GetMapping("/page")
//    public Map findPage(@RequestParam Integer pageNum,
//                        @RequestParam Integer pageSize,
//                        @RequestParam String rentId
//    ){
//        pageNum = (pageNum - 1 ) * pageSize;
//        rentId = "%"+rentId+"%";
//
//        List<Rent> data = rentMapper.selectPage(pageNum,pageSize,rentId);
//
//        Integer total = rentMapper.selectTotal(rentId);
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return  res;
//
//    }

    //  分页
    @GetMapping("/page")
    public IPage<Rent> findPage(@RequestParam Integer pageNum,
                          @RequestParam Integer pageSize,
                          @RequestParam(defaultValue = "") String rentId,
                                @RequestParam(defaultValue = "") String rentName,
                                            @RequestParam(defaultValue = "") String rentPhone


    ){
         IPage<Rent> page = new Page<>(pageNum,pageSize);
         QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
         if(!"".equals(rentId)){
             queryWrapper.or().like("id",rentId);
         }if(!"".equals(rentName)){
            queryWrapper.or().like("name",rentName);
        }if(!"".equals(rentPhone)){
            queryWrapper.or().like("phone",rentPhone);
        }
        queryWrapper.orderByDesc("id");
        return  rentService.page(page,queryWrapper);

    }








}
