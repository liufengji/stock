package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KInstock;

public interface InstockDaoI {
	/**
	 * 获得序列号
	 * @return
	 * @
	 */
	public int getSequences() throws SQLException ;
	/** 
	 * 添加入库单
	 */
	public int insert(KInstock instock) throws SQLException;

	/** 
	 * 修改入库单
	 */
	public int update(BigDecimal pk, KInstock instock) throws SQLException ;

	/** 
	 * 删除入库单
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KInstock findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有入库单数据
	 */
	public KInstock[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KInstock[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KInstock[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KInstock[] MapToObject(List<Map<String, Object>> list);

}
