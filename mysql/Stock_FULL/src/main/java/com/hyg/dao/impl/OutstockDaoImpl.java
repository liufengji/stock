package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.OutstockDaoI;
import com.hyg.model.KOutstock;
import com.hyg.util.DBUtil;
import com.hyg.util.DateUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class OutstockDaoImpl extends DBUtil implements OutstockDaoI {

	private Connection outStockConn;
	protected final String SQL_SELECT = "SELECT ID, OUTSTOCKNO, STOCKID, SUPPLIERID, OUTSTOCKSTATE, OUTSTOCKDATE, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, OUTSTOCKNO, STOCKID, SUPPLIERID, OUTSTOCKSTATE, OUTSTOCKDATE, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, OUTSTOCKNO = ?, STOCKID = ?, SUPPLIERID = ?, OUTSTOCKSTATE = ?, OUTSTOCKDATE = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";


	@Override
	public int getSequences() throws SQLException {
		int seq = 0;
		outStockConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT OUTSTOCK_SEQ.NEXTVAL AS SEQ FROM DUAL";
		List<Map<String, Object>> results = this.executeQuery(outStockConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new SQLException("执行OUTSTOCK的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_OUTSTOCK";
	}

	@Override
	public int insert(KOutstock instock) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockConn = this.getConnection();
		
		params.add(instock.getId() );
		params.add(instock.getOutstockno() );
		params.add(instock.getStockid() );
		params.add(instock.getSupplierid() );
		params.add(instock.getOutstockstate() );
		params.add(instock.getOutstockdate());
		params.add(instock.getRemark() );
		rows = this.executeUpdate(outStockConn, SQL_INSERT, params.toArray());
		
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KOutstock instock) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockConn = this.getConnection();
		
		params.add(instock.getId() );
		params.add(instock.getOutstockno() );
		params.add(instock.getStockid() );
		params.add(instock.getSupplierid() );
		params.add(instock.getOutstockstate() );
		params.add(instock.getOutstockdate());
		params.add(instock.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(outStockConn, SQL_UPDATE, params.toArray());
		
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		outStockConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(outStockConn, SQL_DELETE, params.toArray());
		
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return rows;
	}

	@Override
	public KOutstock findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KOutstock ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KOutstock[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT +  " ORDER BY ID", new Object[0] );
	}

	@Override
	public KOutstock[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		outStockConn = this.getConnection();
		resultList = this.executeQuery(outStockConn, sql, sqlParams);
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KOutstock[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		outStockConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE " + sql;
	
		resultList = this.executeQuery(outStockConn, SQL, sqlParams);
		
		if (outStockConn != null) {
			this.closeConnection(outStockConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KOutstock[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KOutstock[] outStocks = new KOutstock[list.size()];
		List<KOutstock> outStockList = new ArrayList<KOutstock>();
		for (Map<String, Object> map : list) {
			KOutstock outStock = new KOutstock();
			outStock.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			outStock.setOutstockno(map.get("OUTSTOCKNO") == null ? null:map.get("OUTSTOCKNO").toString());
			outStock.setStockid(map.get("STOCKID") == null ? null:new BigDecimal(map.get("STOCKID").toString()));
			outStock.setSupplierid(map.get("SUPPLIERID") == null ? null:new BigDecimal(map.get("SUPPLIERID").toString()));
			outStock.setOutstockstate(map.get("OUTSTOCKSTATE") == null ? null:map.get("OUTSTOCKSTATE").toString());
			outStock.setOutstockdate(map.get("OUTSTOCKDATE") == null ? null:DateUtils.toDate("yyyy-MM-dd",map.get("OUTSTOCKDATE").toString()));
			outStock.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			outStockList.add(outStock);
		}
		return outStockList.toArray(outStocks);
	}

}
