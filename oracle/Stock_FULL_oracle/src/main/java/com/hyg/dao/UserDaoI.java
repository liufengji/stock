package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KUser;

public interface UserDaoI {
	/**
	 * 通过查询条件去查询用户数据
	 * @param user  带有查询条件
	 * @return
	 */
	public List<Map<String,Object>> findUserByWhere(KUser user);
	
	
	/**
	 * 获得序列号
	 * @return
	 * @
	 */
	public int getSequences() throws SQLException ;
	/** 
	 * 添加用户
	 */
	public int insert(KUser dto) throws SQLException;

	/** 
	 * 修改用户
	 */
	public int update(BigDecimal pk, KUser dto) throws SQLException ;

	/** 
	 * 删除用户
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KUser findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有用户数据
	 */
	public KUser[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KUser[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KUser[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KUser[] MapToObject(List<Map<String, Object>> list);
}
