package com.bkbk.qsdemo.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkbk.qsdemo.entity.QsBank;
import com.bkbk.qsdemo.service.QsBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/qsbank")

public class QsBankController {
        @Autowired
        private QsBankService qsBankService;

        @PostMapping("/import")
        public void imp(MultipartFile file) throws Exception {

                //1.第一种 头必须和实体一样
                //文件处理成io流
                InputStream in=file.getInputStream();
                //io流给ExcelReader
                ExcelReader excelReader=ExcelUtil.getReader(in);
                //读取数据且转化为list
                List<QsBank> list=excelReader.readAll(QsBank.class);
                //2.第二种导入方式
                //忽略第一行头
               /* List<List<Object>> list=excelReader.read(1);
                List<SysUser> listUser= CollUtil.newArrayList();
                for (List<Object> row: list) {
                    SysUser user=new SysUser();
                    user.setUsername(row.get(0).toString());
                    *//*****类似一一对应*****//*
                }*/
                //批量注册进数据库
                qsBankService.saveBatch(list);
        }


        @GetMapping("/export")
        public void export (HttpServletResponse response) throws Exception {
                List<QsBank> list =    qsBankService.list();
                ExcelWriter writer= ExcelUtil.getWriter(true);
//                writer.addHeaderAlias("user_id", "用户id");
//                writer.addHeaderAlias("qsbank_id", "题号");
//                writer.addHeaderAlias("qsbank_word", "题目");
//                writer.addHeaderAlias("qsbank_a", "选项A");
//                writer.addHeaderAlias("qsbank_b", "选项B");
//                writer.addHeaderAlias("qsbank_c", "选项C");
                writer.write(list,true);

                //设置content—type
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
                //设置标题
                String fileName= URLEncoder.encode("题库信息","UTF-8");
                //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
                response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
                ServletOutputStream outputStream= response.getOutputStream();
                //将Writer刷新到OutPut
                writer.flush(outputStream,true);
                outputStream.close();
                writer.close();

        }



        @GetMapping("/bank")
        public IPage<QsBank> getbank(
                @RequestParam(defaultValue = "") Integer id,
                @RequestParam Integer pageNum,
                @RequestParam Integer pageSize,
                @RequestParam(defaultValue = "") String qnchoiceId,
                @RequestParam(defaultValue = "") String qnchoiceWord



        ){
                IPage<QsBank> page = new Page<>(pageNum,pageSize);
                QueryWrapper<QsBank> queryWrapper = new QueryWrapper<>();
                if(!"".equals(qnchoiceId)){
                        queryWrapper.or().like("qsbank_id",qnchoiceId);
                }if(!"".equals(qnchoiceWord)){
                        queryWrapper.or().like("qsbank_word",qnchoiceWord);
                }
                //queryWrapper.orderByDesc("qsbank_id");
                queryWrapper.eq("user_id", id);
                // queryWrapper.select("leader",leader);
                return qsBankService.page(page,queryWrapper);
                //return  userService.page(page,queryWrapper);

        }

}
