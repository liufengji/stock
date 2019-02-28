package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KOutstock;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface OutstockDaoI {
	/**
	 * 获得序列号
	 * @return
	 * @
	 */
	public int getSequences() throws SQLException ;
	/** 
	 * 添加出库单
	 */
	public int insert(KOutstock instock) throws SQLException;

	/** 
	 * 修改出库单
	 */
	public int update(BigDecimal pk, KOutstock instock) throws SQLException ;

	/** 
	 * 删除出库单
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KOutstock findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有出库单数据
	 */
	public KOutstock[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KOutstock[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KOutstock[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KOutstock[] MapToObject(List<Map<String, Object>> list);
}
