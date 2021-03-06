package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KSupplier;

public interface SupplierDaoI {
	/**
	 * 获得序列号
	 * @return
	 * @
	 */
	public int getSequences() throws SQLException ;
	/** 
	 * 添加供应商
	 */
	public int insert(KSupplier supplier) throws SQLException;

	/** 
	 * 修改供应商
	 */
	public int update(BigDecimal pk, KSupplier supplier) throws SQLException ;

	/** 
	 * 删除供应商
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KSupplier findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有供应商数据
	 */
	public KSupplier[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KSupplier[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KSupplier[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KSupplier[] MapToObject(List<Map<String, Object>> list);

}
