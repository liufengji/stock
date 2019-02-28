package com.hyg.service;

import java.util.Scanner;

import com.hyg.model.KGoods;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface GoodsServiceI {
	/**
	 * 显示产品列表
	 */
	public void findGoodsList();
	/**
	 * 根据ID查找产品
	 * @param id
	 * @return
	 */
	public KGoods findGoodsByID(String id);
	/**
	 * 查询产品
	 * @param sc
	 */
	public void searchGoods(Scanner sc);
	/**
	 * 添加产品
	 * @param sc
	 */
	public void addGoods(Scanner sc);
	/**
	 * 修改产品
	 * @param sc
	 * @param id
	 */
	public void updateGoods(Scanner sc, String id);
	/**
	 * 删除产品
	 * @param sc
	 * @param id
	 */
	public void deleteGoods(Scanner sc, String id);
}
