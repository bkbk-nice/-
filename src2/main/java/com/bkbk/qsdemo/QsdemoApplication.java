package com.bkbk.qsdemo;

import com.bkbk.qsdemo.entity.Rent;
import com.bkbk.qsdemo.mapper.RentMapper;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@SpringBootApplication
public class QsdemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(QsdemoApplication.class, args);
	}


}
