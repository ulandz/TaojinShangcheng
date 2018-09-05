package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ItemParamItemService;

/**
 * 展示商品规格参数控制器
 * @author zhc
 *
 */
@Controller
public class ItemParamItemContoller {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	
	
	
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
	
	/**
	 * 获取商品规格列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult itemparamList = itemParamItemService.getParamList(page, rows);
		return itemparamList;
	}
}
