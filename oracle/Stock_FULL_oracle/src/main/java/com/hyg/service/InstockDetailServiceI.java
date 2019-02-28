package com.hyg.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KInstockDetail;

public interface InstockDetailServiceI {
	
	/**
	 * 查询明细信息
	 * @param sc
	 */
	public void searchInstockDetails(Scanner sc);
	/**
	 * 根据入库单ID查询明细信息
	 * @param instockId
	 * @return
	 */
	public KInstockDetail[] findInstockDetailsByInstockId(String instockId);
	/**
	 * 添加产品明细
	 * @param sc
	 * @param instockId 入库单主表ID
	 * @param goodsList 产品列表
	 */
	public void addInstockDetail(Scanner sc, String instockId, List<Map> goodsList);
	
	/**
	 * 根据ID查找明细信息
	 * @param id 明细ID
	 * @return
	 */
	public KInstockDetail findInstockDetailsById(String id);
	
	/**
	 * 更新明细信息
	 * @param instockDetail
	 */
	public void updateDetail(KInstockDetail instockDetail);
	/**
	 * 根据明细ID数组，删除明细信息
	 * @param idArr
	 */
	public void deleteDetailsByIds(String[] idArr);
	/**
	 * 根据入库单ID删除明细信息
	 * @param instockId
	 * @return
	 */
	public int deleteDetailsByInstockId(String instockId);
	
}
