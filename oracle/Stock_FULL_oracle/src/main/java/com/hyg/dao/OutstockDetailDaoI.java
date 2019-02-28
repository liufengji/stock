package com.hyg.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hyg.model.KOutstock;
import com.hyg.model.KOutstockDetail;

public interface OutstockDetailDaoI {

	/**
	 * 获得序列号
	 * @return
	 * @throws KUserDaoException
	 */
	public int getSequences() throws SQLException;
	/**
	 * 添加出库明细信息
	 * @param dto
	 * @return
	 */
	public int insert(KOutstockDetail dto) throws SQLException;
	/**
	 * 修改明细信息
	 * @param pk
	 * @param dto
	 * @return
	 */
	public int update(BigDecimal pk, KOutstockDetail dto) throws SQLException;
	/**
	 * 删除明细信息
	 * @param pk
	 * @return
	 */
	public int delete(BigDecimal pk) throws SQLException;
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public KOutstockDetail findByPrimaryKey(BigDecimal id) throws SQLException;
	/**
	 * 查询所有的明细
	 * @return
	 */
	public KOutstockDetail[] findAll() throws SQLException;
	/**
	 * 自定义列查询
	 * @param sql
	 * @param sqlParams
	 * @return
	 */
	public KOutstockDetail[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException;
	/**
	 * 根据查询条件查询
	 * @param sql
	 * @param sqlParams
	 * @return
	 */
	public KOutstockDetail[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException;
	/**
	 * 根据出库单查询明细记录
	 * @param outstockId
	 * @return
	 */
	public KOutstockDetail[] findDetailsByOutstockId(BigDecimal outstockId) throws SQLException;

	/**
	 * 将MAP对象转成Object对象
	 * @param list
	 * @return
	 */
	public KOutstockDetail[] MapToObject(List<Map<String, Object>> list);
}
