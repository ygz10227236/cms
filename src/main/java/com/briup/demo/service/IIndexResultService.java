package com.briup.demo.service;

import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.utils.CustomerException;

/**
 * 首页数据管理的service层接口
 * @author admin
 *
 */
public interface IIndexResultService {
	
	//查询首页需要的所有数据
	IndexResult findIndexAllResult() throws CustomerException;
}
