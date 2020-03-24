package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 栏目相关的service层
 * @author admin
 *
 */
public interface ICategoryService {
	//查询所有的栏目
	public List<Category> findAllCategorys() throws CustomerException;
	
	//添加或修改栏目信息
	public void saveOrUpdateCatrgory(Category category) throws CustomerException;
	
	//根据id删除栏目信息
	public void deleteCategoryById(int id) throws CustomerException;
	
	//通过id查找指定的栏目信息
	public Category findCategoryById(int id) throws CustomerException;
	
	//查询栏目信息并且级联查询包含的文章信息
	public List<CategoryEx> findAllCategoryEx() throws CustomerException;

//	//查询栏目下所有文章信息
//	public List<Article> findAllArticelByCategory(int id) throws CustomerException;
	/**
	 * 查询栏目及其包含的文章的所有数据
	 */
	CategoryEx findCategoryExByid(Integer id) throws CustomerException;

}
