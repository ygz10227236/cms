package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 栏目相关
 * @author admin
 *
 */
@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService categorySesrvice;
	
	@PostMapping("/addCatetory")
	@ApiOperation("新增栏目信息")
	public Message<String> addcategory(Category category){
		try {
			categorySesrvice.saveOrUpdateCatrgory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误: "+e.getMessage());
		}
	}
	
	@PostMapping("/updateCategory")
	@ApiOperation("修改栏目信息")
	public Message<String> updateLink(Category category){
		categorySesrvice.saveOrUpdateCatrgory(category);
		return MessageUtil.success();
	}
	
	@GetMapping("/selectcategorys")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> selectcategory(){
		List<Category> list = categorySesrvice.findAllCategorys();
		return MessageUtil.success(list);
	}
	
	@GetMapping("/deleteCotegoryById")
	@ApiOperation("根据id删除栏目")
	public Message<String> deleteById(int id){
		categorySesrvice.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/selectCategoryById")
	@ApiOperation("根据id查询栏目")
	public Message<Category> selectCategoryById(int id){
		return MessageUtil.success(categorySesrvice.findCategoryById(id));
	}
	
	@GetMapping("/selectArticleByCategory")
	@ApiOperation("根据栏目id查询栏目及其包含文章的信息")
	public Message<CategoryEx> selectAllArticleByCategory(int id){
		return MessageUtil.success(categorySesrvice.findCategoryExByid(id));
	}
}
