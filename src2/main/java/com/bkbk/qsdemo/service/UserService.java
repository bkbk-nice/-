package com.bkbk.qsdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkbk.qsdemo.controller.dto.UserDto;


import com.bkbk.qsdemo.entity.User;

import com.bkbk.qsdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  extends ServiceImpl<UserMapper, User> {



    @Autowired
    private UserMapper userMapper;


    public UserDto loginx(UserDto userDto) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userDto.getName());
        queryWrapper.eq("password",userDto.getPassword());
        User one = getOne(queryWrapper);
        if(one!=null){

            BeanUtil.copyProperties(one,userDto,true);
            return  userDto;
        }
        return null;
    }

    public boolean saveUser(User user) {
        if (user.getId() == null) {
            userMapper.addonetorent(user.getLeader());

            return save(user);
        } else {
            List<User> x = userMapper.getbyid(user.getId());


            String ininame = x.get(0).getName();
            String iniphone = x.get(0).getPhone();

            if (!ininame.equals(user.getName())) {
                List<User> xname = userMapper.judgename(user.getName());
                if (xname.size() != 0) {
                    return false;
                }
            }
            if (!iniphone.equals(user.getPhone())) {
                List<User> xphone = userMapper.judgephone(user.getPhone());
                if (xphone.size() != 0) {
                    return false;
                }
            }
            return updateById(user);
        }
    }

    public boolean queryRelation(Integer id) {

        if(userMapper.queryRelation(id).size()==0){
            return false;
        }
        return true;
    }
}
