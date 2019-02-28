package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KInventory;

public interface InventoryDaoI {
	
	/**
	 * 获得序列号
	 * @return
	 * @throws Exception 
	 * @
	 */
	public int getSequences() throws SQLException, Exception ;
	/** 
	 * 添加用户
	 */
	public int insert(KInventory inventory) throws SQLException;

	/** 
	 * 修改用户
	 */
	public int update(BigDecimal pk, KInventory inventory) throws SQLException ;

	/** 
	 * 删除用户
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KInventory findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有用户数据
	 */
	public KInventory[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KInventory[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KInventory[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KInventory[] MapToObject(List<Map<String, Object>> list);

}
