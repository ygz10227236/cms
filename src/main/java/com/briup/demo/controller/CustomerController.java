package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.dao.CustomerDao;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="用户管理相关接口")
public class CustomerController {
	
	@Autowired
	private CustomerDao customer;
	
	@PostMapping("/registerCostomer")
	@ApiOperation("注册用户")
	public Message<String> registerCustomer(String username,String password){
		Customer cus = new Customer();
		cus.setUsername(username);
		cus.setPassword(password);
		if(customer.findByUsername(username) == null) {
			customer.save(cus);
			return MessageUtil.success("注册成功");
		}else {
			return MessageUtil.error(StatusCodeUtil.EXISTS_CODE, "用户已存在");
		}
	}
	
	@GetMapping("/findAllCostomer")
	@ApiOperation("获得所有用户信息")
	public Message<List<Customer>> findAllCostomer(){
		return MessageUtil.success(customer.findAll());
	}
	
	
	@GetMapping("/loginCostomer")
	@ApiOperation("登录")
	public Message<String> loginCostomer(String username,String password){
		Customer cus = customer.findByUsernameAndPassword(username, password);
		Customer cusByUsername = customer.findByUsername(username);
		if(cus==null){
			if(cusByUsername==null) {//查找不到对应用户名
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "没有此用户: ");
			}else{//密码错误
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "密码错误: ");
			}
		}
		return MessageUtil.success("登录成功");
	}
	
}