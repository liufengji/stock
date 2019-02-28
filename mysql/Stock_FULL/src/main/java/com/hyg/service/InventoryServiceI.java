package com.hyg.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KInventory;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface InventoryServiceI {
	
	/**
	 * 查询库存信息
	 * @param sc
	 */
	public void searchInventory(Scanner sc);
	/**
	 * 根据map中的仓库ID和产品编号，到库存表中进行添加或更新操作
	 * 如果仓库ID和产品编号在库存表中能查询到记录，则进行更新操作
	 * 如果仓库ID和产品编号在库存表中不能查询到记录，则进行添加操作
	 * @param map  
	 */
	public void saveOrUpdate(Map<String,String> map);
	public void showGoodsListByStockId(BigDecimal stockid);
	public KInventory findKInventoryById(String string);

	
}
