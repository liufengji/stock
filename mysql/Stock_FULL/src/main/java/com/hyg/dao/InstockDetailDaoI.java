package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KInstockDetail;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface InstockDetailDaoI {
	
	/**
	 * 根据instockId查找明细
	 * @param instockId
	 * @return
	 * @throws SQLException
	 */
	public KInstockDetail[] findInstockDetailsByInstockID(String instockId) throws SQLException;

	/**
	 * 获得序列号
	 * @return
	 * @throws Exception 
	 * 
	 */
	public int getSequences() throws SQLException, Exception ;
	/** 
	 * 添加入库单明细
	 */
	public int insert(KInstockDetail dto) throws SQLException;

	/** 
	 * 修改入库单明细
	 */
	public int update(BigDecimal pk, KInstockDetail dto) throws SQLException ;

	/** 
	 * 删除入库单明细
	 */
	public int delete(BigDecimal pk) throws SQLException ;

	/** 
	 * 根据主键ID列查询
	 */
	public KInstockDetail findByPrimaryKey(BigDecimal id) throws SQLException ;

	/** 
	 *查询所有入库单明细数据
	 */
	public KInstockDetail[] findAll() throws SQLException ;

	/** 
	 * 动态列查询
	 */
	public KInstockDetail[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException ;

	/** 
	 * where条件查询，注意每个查询条件都要以 and 或or 逻辑修饰符开头
	 */
	public KInstockDetail[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException ;

	/**
	 * 将Map对象转成Object对象
	 * @param list
	 * @return
	 */
	public KInstockDetail[] MapToObject(List<Map<String, Object>> list);

}
