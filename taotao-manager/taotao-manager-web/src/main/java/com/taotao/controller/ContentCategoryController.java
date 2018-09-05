package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
/**
 * 内容分类管理控制器
 * @author zhc
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 获取所有分类内容列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatgoryList(@RequestParam(value="id", defaultValue="0")Long parentId){
		List<EUTreeNode> categoryList = contentCategoryService.getCategoryList(parentId);
		return categoryList;
	}
	
	/**
	 * 保存新增节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	/**
	 * 删除节点
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(@RequestParam("id")Long id) {
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	
	/**
	 * 更新节点
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult deleteContentCategory(@RequestParam("id")Long id, @RequestParam("name")String name) {
		TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
}
