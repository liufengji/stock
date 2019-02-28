package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.InstockDaoI;
import com.hyg.model.KInstock;
import com.hyg.util.DBUtil;
import com.hyg.util.DateUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class InstockDaoImpl extends DBUtil implements InstockDaoI {
	private Connection instockConn;
	
	protected final String SQL_SELECT = "SELECT ID, INSTOCKNO, STOCKID, SUPPLIERID, INSTOCKSTATE,  INSTOCKDATE, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, INSTOCKNO, STOCKID, SUPPLIERID, INSTOCKSTATE,  INSTOCKDATE, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, INSTOCKNO = ?, STOCKID = ?, SUPPLIERID = ?, INSTOCKSTATE = ?, INSTOCKDATE = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	@Override
	public int getSequences() throws SQLException {
		int seq = 0;
		try {
			instockConn = this.getConnection();
			// construct the SQL statement
			final String SQL = "SELECT INSTOCK_SEQ.NEXTVAL AS SEQ FROM DUAL";
		
			List<Map<String, Object>> results = this.executeQuery(instockConn, SQL, new Object[0]);
			
			if (results.size() == 0) {
				throw new Exception("执行INSTOCK_SEQ的序列语句错误！");
			}else{
				seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
			}
			
		} catch (Exception _e) {
			_e.printStackTrace();
			
		}finally {
			if (instockConn != null) {
				this.closeConnection(instockConn);
			}
			return seq;
		}
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_INSTOCK";
	}

	@Override
	public int insert(KInstock instock) throws SQLException {
		// TODO Auto-generated method stub
		instockConn = this.getConnection();
		int rows = 0;
		List params = new ArrayList();
		params.add(instock.getId() );
		params.add(instock.getInstockno() );
		params.add(instock.getStockid() );
		params.add(instock.getSupplierid() );
		params.add(instock.getInstockstate() );
		params.add(instock.getInstockdate());
		params.add(instock.getRemark() );
		rows = this.executeUpdate(instockConn, SQL_INSERT, params.toArray());
		this.closeConnection(instockConn);
		return rows;
		
	}

	@Override
	public int update(BigDecimal pk, KInstock instock) throws SQLException {
		// TODO Auto-generated method stub
		instockConn = this.getConnection();
		int rows = 0;
		List params = new ArrayList();
		params.add(instock.getId() );
		params.add(instock.getInstockno() );
		params.add(instock.getStockid() );
		params.add(instock.getSupplierid() );
		params.add(instock.getInstockstate() );
		params.add(instock.getInstockdate());
		params.add(instock.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(instockConn, SQL_UPDATE, params.toArray());
		this.closeConnection(instockConn);
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		// TODO Auto-generated method stub
		instockConn = this.getConnection();
		int rows = 0;
		List params = new ArrayList();
		params.add(pk);
		rows = this.executeUpdate(instockConn, SQL_DELETE, params.toArray());
		this.closeConnection(instockConn);
		return rows;
	}

	@Override
	public KInstock findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KInstock ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KInstock[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", new Object[0]  );
	}

	@Override
	public KInstock[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		instockConn = this.getConnection();
		resultList = this.executeQuery(instockConn, sql, sqlParams);
		this.closeConnection(instockConn);
		return MapToObject(resultList);
	}

	@Override
	public KInstock[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		instockConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE  1=1 " + sql;
		resultList = this.executeQuery(instockConn, SQL, sqlParams);
		this.closeConnection(instockConn);
		return MapToObject(resultList);
	}

	@Override
	public KInstock[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KInstock[] instocks = new KInstock[list.size()];
		List<KInstock> instockList = new ArrayList<KInstock>();
		for (Map<String, Object> map : list) {
			KInstock instock = new KInstock();
			instock.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			instock.setInstockno(map.get("INSTOCKNO") == null ? null:map.get("INSTOCKNO").toString());
			instock.setStockid(map.get("STOCKID") == null ? null:new BigDecimal(map.get("STOCKID").toString()));
			instock.setSupplierid(map.get("SUPPLIERID") == null ? null:new BigDecimal(map.get("SUPPLIERID").toString()));
			instock.setInstockstate(map.get("INSTOCKSTATE") == null ? null:map.get("INSTOCKSTATE").toString());
			instock.setInstockdate(map.get("INSTOCKDATE") == null ? null:DateUtils.toDate("yyyy-MM-dd", map.get("INSTOCKDATE").toString()));
			instock.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			instockList.add(instock);
		}
		return instockList.toArray(instocks);
	}
	
}
