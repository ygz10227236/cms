package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文章相关信息的Controller
 * @author admin
 *
 */
@RestController
@Api(description = "文章相关接口")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	
	@PostMapping("/addArticle")
	@ApiOperation("添加文章信息")
	public Message<String> saveArticle(Article article){
		try {
			articleService.saveOrUpdateArticle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			e.printStackTrace();
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误: "+e.getMessage());
		}
	}
	
	//修改文章
	@PostMapping("/updateArticle")
	@ApiOperation("修改文章信息")
	public Message<String> updateArticle(Article article){
		try {
			articleService.saveOrUpdateArticle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			e.printStackTrace();
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误: "+e.getMessage());
		}
	}
	
	//删除文章
	@GetMapping("/deleteByIdArticle")
	@ApiOperation("根据Id删除文章信息")
	public Message<String> deleteByIdArticle(int id){
		articleService.deleteArticleById(id);
		return MessageUtil.success();
	}
	
	//根据条件进行查询文章信息
	@GetMapping("/findArticleByCondition")
	@ApiOperation("根据条件进行查询文章信息")
	public Message<List<Article>> getArticleByCondition(String keyStr,String condition){
		List<Article> list;
		try {
			list = articleService.findArticleByCondition(keyStr, condition);
		return MessageUtil.success(list);
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误: "+e.getMessage());
		}
	}
	
	//根据id查询文章信息
	@GetMapping("/findArticleById")
	@ApiOperation("根据id查询文章信息")
	public Message<Article> getArticleById(int id){
		Article article = articleService.findArticleById(id);
		return MessageUtil.success(article);
	}
	
}
