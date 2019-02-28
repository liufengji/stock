package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KDept;

public interface DeptDaoI {

	/**
	 * 获得序列号
	 * @return
	 * @
	 */
	public int getSequences() throws SQLException ;
	/** 
	 * 添加部门
	 */
	public int insert(KDept dept) throws SQLException;

	/** 
	 * 修改部门
	 */
	public int update(BigDecimal pk, KDept dept) throws SQLException ;

	/** 
	 * 删除部门
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KDept findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有部门数据
	 */
	public KDept[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KDept[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KDept[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KDept[] MapToObject(List<Map<String, Object>> list);

}
