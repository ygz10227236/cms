package com.briup.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

public interface CustomerDao extends JpaRepository<Customer, Integer>{

	Customer findByUsernameAndPassword(String username,String password) throws CustomerException;
	
	Customer findByUsername(String username) throws CustomerException;
	
}
