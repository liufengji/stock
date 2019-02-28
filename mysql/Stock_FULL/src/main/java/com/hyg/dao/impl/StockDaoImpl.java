package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.StockDaoI;
import com.hyg.model.KStock;
import com.hyg.util.DBUtil;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class StockDaoImpl extends DBUtil implements StockDaoI {
	
	private Connection stockConn;
	protected final String SQL_SELECT = "SELECT ID, STOCKNO, STOCKNAME, PROVINCE, STOCKADDRESS, STOCKTEL,  REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, STOCKNO, STOCKNAME, PROVINCE, STOCKADDRESS, STOCKTEL,  REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, STOCKNO = ?, STOCKNAME = ?, PROVINCE = ?, STOCKADDRESS = ?, STOCKTEL = ?,  REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";



	@Override
	public int getSequences() throws SQLException {
		int seq = 0;
		stockConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT STOCK_SEQ.NEXTVAL AS SEQ FROM DUAL";
	
		List<Map<String, Object>> results = this.executeQuery(stockConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new SQLException("执行STOCK_SEQ序列查找下一个序列值语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_STOCK";
	}

	@Override
	public int insert(KStock stock) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		stockConn = this.getConnection();
		params.add(stock.getId() );
		params.add(stock.getStockno() );
		params.add(stock.getStockname() );
		params.add(stock.getProvince() );
		params.add(stock.getStockaddress() );
		params.add(stock.getStocktel() );
		params.add(stock.getRemark() );
		rows = this.executeUpdate(stockConn, SQL_INSERT, params.toArray());
		
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KStock stock) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		stockConn = this.getConnection();
		params.add(stock.getId() );
		params.add(stock.getStockno() );
		params.add(stock.getStockname() );
		params.add(stock.getProvince() );
		params.add(stock.getStockaddress() );
		params.add(stock.getStocktel() );
		params.add(stock.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(stockConn, SQL_UPDATE, params.toArray());
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		stockConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(stockConn, SQL_DELETE, params.toArray());
		
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return rows;
	}

	@Override
	public KStock findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KStock ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KStock[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT +  " ORDER BY ID", new Object[0] );
	}

	@Override
	public KStock[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		stockConn = this.getConnection();
		resultList = this.executeQuery(stockConn, sql, sqlParams);
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KStock[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		stockConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE " + sql;
	
		resultList = this.executeQuery(stockConn, SQL, sqlParams);
		
		if (stockConn != null) {
			this.closeConnection(stockConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KStock[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KStock[] stocks = new KStock[list.size()];
		List<KStock> stockList = new ArrayList<KStock>();
		for (Map<String, Object> map : list) {
			KStock stock = new KStock();
			stock.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			stock.setStockno(map.get("STOCKNO") == null ? null:map.get("STOCKNO").toString());
			stock.setStockname(map.get("STOCKNAME") == null ? null:map.get("STOCKNAME").toString());
			stock.setProvince(map.get("PROVINCE") == null ? null:map.get("PROVINCE").toString());
			stock.setStockaddress(map.get("STOCKADDRESS") == null ? null:map.get("STOCKADDRESS").toString());
			stock.setStocktel(map.get("STOCKTEL") == null ? null:map.get("STOCKTEL").toString());
			stock.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			stockList.add(stock);
		}
		return stockList.toArray(stocks);
	}

}
