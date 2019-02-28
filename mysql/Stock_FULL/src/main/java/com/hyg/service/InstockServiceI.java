package com.hyg.service;

import java.util.Scanner;

/**
 * 入库单主单服务类
 * 主要用于对入库单主单信息进行增删改查，以及进行入库记账操作
 * 对入库单明细信息，进行增删改查操作
 * @author Administrator
 *
 */
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface InstockServiceI {
	
	/**
	 * 查询入库单
	 * @param sc
	 */
	public void searchInstock(Scanner sc);
	/**
	 * 添加入库单
	 * @param sc
	 */
	public void addInstock(Scanner sc);
	/**
	 * 修改入库单
	 * @param sc
	 */
	public void updateInstock(Scanner sc,String id);
	/**
	 * 删除入库单
	 * @param sc
	 */
	public void deleteInstock(Scanner sc,String id);
	/**
	 * 添加明细
	 * @param sc
	 * @param id
	 */
	public void addInstockDetails(Scanner sc,String id);
	/**
	 * 修改明细
	 * @param sc
	 * @param id
	 */
	public void updateInstockDetails(Scanner sc,String id);
	/**
	 * 删除明细
	 * @param sc
	 * @param id
	 */
	public void deleteInstockDetails(Scanner sc,String id);

	
	/**
	 * 入库单记账
	 * @param sc
	 */
	public void confirmInstock(Scanner sc);
	
	
}
