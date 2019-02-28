package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.OutstockDetailDaoI;
import com.hyg.model.KOutstock;
import com.hyg.model.KOutstockDetail;
import com.hyg.util.DBUtil;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class OutstockDetailDaoImpl extends DBUtil implements OutstockDetailDaoI {

	private Connection outStockDetailConn;
	
	protected final String SQL_SELECT = "SELECT ID, OUTSTOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, PRODUCTNUM, UNIT, PRICE, TOTALPRICE, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, OUTSTOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, PRODUCTNUM, UNIT, PRICE, TOTALPRICE, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, OUTSTOCKID = ?, PRODUCTNO = ?, PRODUCTNAME = ?, PRODUCTSTANDARD = ?, PRODUCTNUM = ?, UNIT = ?, PRICE = ?, TOTALPRICE = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";


	@Override
	public int getSequences() throws SQLException {
		// TODO Auto-generated method stub
		int seq = 0;
		outStockDetailConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT OUTSTOCK_DETAIL_SEQ.NEXTVAL AS SEQ FROM DUAL";
		List<Map<String, Object>> results = this.executeQuery(outStockDetailConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new SQLException("执行OUTSTOCK_DETAIL的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_OUTSTOCK_DETAIL";
	}

	@Override
	public int insert(KOutstockDetail dto) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockDetailConn = this.getConnection();
		params.add(dto.getId() );
		params.add(dto.getOutstockid() );
		params.add(dto.getProductno() );
		params.add(dto.getProductname() );
		params.add(dto.getProductstandard() );
		params.add(dto.getProductnum() );
		params.add(dto.getUnit() );
		params.add(dto.getPrice() );
		params.add(dto.getTotalprice() );
		params.add(dto.getRemark() );
		rows = this.executeUpdate(outStockDetailConn, SQL_INSERT, params.toArray());
		
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KOutstockDetail dto) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockDetailConn = this.getConnection();
		params.add(dto.getId() );
		params.add(dto.getOutstockid() );
		params.add(dto.getProductno() );
		params.add(dto.getProductname() );
		params.add(dto.getProductstandard() );
		params.add(dto.getProductnum() );
		params.add(dto.getUnit() );
		params.add(dto.getPrice() );
		params.add(dto.getTotalprice() );
		params.add(dto.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(outStockDetailConn, SQL_UPDATE, params.toArray());
		
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockDetailConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(outStockDetailConn, SQL_DELETE, params.toArray());
		
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return rows;
	}

	@Override
	public KOutstockDetail findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KOutstockDetail ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KOutstockDetail[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT +  " ORDER BY ID", new Object[0] );
	}

	@Override
	public KOutstockDetail[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		outStockDetailConn = this.getConnection();
		resultList = this.executeQuery(outStockDetailConn, sql, sqlParams);
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KOutstockDetail[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		outStockDetailConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE " + sql;
	
		resultList = this.executeQuery(outStockDetailConn, SQL, sqlParams);
		
		if (outStockDetailConn != null) {
			this.closeConnection(outStockDetailConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KOutstockDetail[] findDetailsByOutstockId(BigDecimal outstockId)
			throws SQLException {
		return findByDynamicSelect( SQL_SELECT + " WHERE OUTSTOCKID = ?", new Object[] { outstockId } );
	}

	@Override
	public KOutstockDetail[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KOutstockDetail[] outStockDetails = new KOutstockDetail[list.size()];
		List<KOutstockDetail> outStockDetailList = new ArrayList<KOutstockDetail>();
		for (Map<String, Object> map : list) {
			KOutstockDetail outStockDetail = new KOutstockDetail();
			outStockDetail.setId(map.get("ID") == null ? null: new BigDecimal(map.get("ID").toString()));
			outStockDetail.setOutstockid(map.get("OUTSTOCKID") == null ? null:new BigDecimal(map.get("OUTSTOCKID").toString()));
			outStockDetail.setProductno(map.get("PRODUCTNO") == null ? null:map.get("PRODUCTNO").toString());
			outStockDetail.setProductname(map.get("PRODUCTNAME") == null ? null:map.get("PRODUCTNAME").toString());
			outStockDetail.setProductstandard(map.get("PRODUCTSTANDARD") == null ? null:map.get("PRODUCTSTANDARD").toString());
			outStockDetail.setProductnum(map.get("PRODUCTNUM") == null ? null:new BigDecimal(map.get("PRODUCTNUM").toString()));
			outStockDetail.setUnit(map.get("UNIT") == null ? null:map.get("UNIT").toString());
			outStockDetail.setPrice(map.get("PRICE") == null ? null:new BigDecimal(map.get("PRICE").toString()));
			outStockDetail.setTotalprice(map.get("TOTALPRICE") == null ? null:new BigDecimal(map.get("TOTALPRICE").toString()));
			outStockDetail.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			outStockDetailList.add(outStockDetail);
		}
		return outStockDetailList.toArray(outStockDetails);
	}

	

}
