package com.bkbk.qsdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkbk.qsdemo.common.Constants;
import com.bkbk.qsdemo.common.Result;
import com.bkbk.qsdemo.controller.dto.FeeDto;
import com.bkbk.qsdemo.controller.dto.RentDto;

import com.bkbk.qsdemo.entity.Answer;
import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.mapper.RentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService extends ServiceImpl<RentMapper,Rent> {


    @Autowired
    private RentMapper rentMapper;



    public boolean saveRent(Rent rent) {




        // return saveOrUpdate(rent);
        if (rent.getId() == null) {
            List<Rent> xname = rentMapper.judgename(rent.getName());
            if(xname.size()!=0){
                return false;
            }
            List<Rent> xphone = rentMapper.judgephone(rent.getPhone());
            if(xphone.size()!=0){
                return false;
            }

            return save(rent);
        } else {
            List<Rent> x =  rentMapper.getbyid(rent.getId());


            String ininame = x.get(0).getName();
            String iniphone = x.get(0).getPhone();

            if(!ininame.equals(rent.getName())){
                List<Rent> xname = rentMapper.judgename(rent.getName());
                if(xname.size()!=0){
                    return false;
                }
            }
            if(!iniphone.equals(rent.getPhone())){
                List<Rent> xphone = rentMapper.judgephone(rent.getPhone());
                if(xphone.size()!=0){
                    return false;
                }
            }

            return updateById(rent);
        }
    }

    public RentDto loginx(RentDto rentDto) {

        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",rentDto.getName());
        queryWrapper.eq("password",rentDto.getPassword());
        Rent one = getOne(queryWrapper);

        if(one!=null){
            BeanUtil.copyProperties(one,rentDto,true);
            return  rentDto;
        }
        return   null;
    }




    public boolean queryUsernum(Integer id) {
        if(rentMapper.queryusernum(id).size()==0){
            return false;
        }
        return true;
    }




//     public int save(Rent rent){
//        if(rent.getId() == null){
//           return  rentMapper.insert(rent);
//        }else{
//            return   rentMapper.update(rent);
//        }
//
//    }
        public Result regis(String name, String password, String phone) {


            List<Rent> x= rentMapper.judgename(name);
            if(x.size()!=0){
                return Result.error(Constants.CODE_400,"用户名已存在");
            }


            List<Rent> xx = rentMapper.judgephone(phone);
            if(xx.size()!=0){
                return Result.error(Constants.CODE_401,"手机号已存在");
            }


            rentMapper.regis(name,password,phone);
            return Result.success(Constants.CODE_200);

        }

    public Result fee(String id) {
        List<FeeDto> x = rentMapper.getfeeinfo(id);
        double a = x.get(0).getAnswernum();
        double b = x.get(0).getQnnum();
        double c = x.get(0).getGroupnum();

        double r = fee(a,b,c);
        r = (double) Math.round(r * 100) / 100;

        rentMapper.setfee(id,r);
        return  Result.success(x);
    }

    public double fee(double answernum,double qnnum,double groupnum){
        return answernum*(qnnum+groupnum)*0.02;
    }
}
