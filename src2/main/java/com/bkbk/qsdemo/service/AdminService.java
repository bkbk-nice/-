package com.bkbk.qsdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.qsdemo.controller.dto.AdminDto;
import com.bkbk.qsdemo.controller.dto.RentDto;
import com.bkbk.qsdemo.entity.Admin;
import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.mapper.AdminMapper;
import com.bkbk.qsdemo.mapper.RentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@Service
public class AdminService  extends ServiceImpl<AdminMapper,Admin>{


    @Autowired
    private AdminMapper adminMapper;
    public boolean saveAdmin(Admin admin) {



        if (admin.getId() == null) {
            return save(admin);
        } else {
            List<Admin> x =  adminMapper.getbyid(admin.getId());


            String ininame = x.get(0).getName();
            String iniphone = x.get(0).getPhone();

            if(!ininame.equals(admin.getName())){
                List<Admin> xname = adminMapper.judgename(admin.getName());
                if(xname.size()!=0){
                    return false;
                }
            }
            if(!iniphone.equals(admin.getPhone())){
                List<Admin> xphone = adminMapper.judgephone(admin.getPhone());
                if(xphone.size()!=0){
                    return false;
                }
            }

            return updateById(admin);
        }

    }


    public AdminDto loginx(AdminDto adminDto) {

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",adminDto.getName());
        queryWrapper.eq("password",adminDto.getPassword());
        Admin one = getOne(queryWrapper);
        if(one!=null){
            BeanUtil.copyProperties(one,adminDto,true);
            return  adminDto;
        }
        return null;
    }


    public AdminDto loadinfo(String name) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("phone","");
        Admin one = getOne(queryWrapper);
        AdminDto  adminDtonew = new AdminDto();
        if(one!=null){
            BeanUtil.copyProperties(one,adminDtonew,true);
            return  adminDtonew;
        }
        return null;

    }
}
