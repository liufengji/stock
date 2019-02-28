package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KGoods;

public interface GoodsDaoI {
	/**
	 * 获得序列号
	 * @return
	 * @throws Exception 
	 * @
	 */
	public int getSequences() throws SQLException, Exception ;
	/** 
	 * 添加产品
	 */
	public int insert(KGoods dto) throws SQLException;

	/** 
	 * 修改产品
	 */
	public int update(BigDecimal pk, KGoods dto) throws SQLException ;

	/** 
	 * 删除产品
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KGoods findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有产品数据
	 */
	public KGoods[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KGoods[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KGoods[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KGoods[] MapToObject(List<Map<String, Object>> list);

}
