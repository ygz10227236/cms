package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	//文章的dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	@Override
	public void saveOrUpdateCatrgory(Category category) throws CustomerException {
		CategoryExample example = new CategoryExample();
		if(category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//id为空插入不为空修改
		if (category.getId() == null) {
			List<Category> list = categoryMapper.selectByExample(example);
			for (Category category2 : list) {
				if (category2.getName().equals(category.getName())) {
					throw new CustomerException(StatusCodeUtil.EXISTS_CODE, "栏目存在");
				}
			}
			categoryMapper.insert(category);
		}else {
			categoryMapper.updateByPrimaryKey(category);
		}
	}

	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		//删除栏目的同时需要先删除栏目里的内容
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExByid(Integer id) throws CustomerException {
		return categoryExMapper.findCategoryExById(id);
	}

//	@Override
//	public List<Article> findAllArticelByCategory(int id) throws CustomerException {
//		ArticleExample example = new ArticleExample();
//		example.createCriteria().andCategoryIdEqualTo(id);
//		return articleMapper.selectByExample(example);
//	}
}
