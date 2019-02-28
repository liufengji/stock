package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.SupplierDaoI;
import com.hyg.model.KSupplier;
import com.hyg.util.DBUtil;

public class SupplierDaoImpl extends DBUtil implements SupplierDaoI {
	private Connection supplierConn;
	
	protected final String SQL_SELECT = "SELECT ID, SUPPLIERNO, SUPPLIERNAME, PROVINCECODE, SUPPLIERADDRESS, SUPPLIEREMAIL, SUPPLIERTEL, SUPPLIERTAX, USERNAME, USERTEL, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, SUPPLIERNO, SUPPLIERNAME, PROVINCECODE, SUPPLIERADDRESS, SUPPLIEREMAIL, SUPPLIERTEL, SUPPLIERTAX, USERNAME, USERTEL, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, SUPPLIERNO = ?, SUPPLIERNAME = ?, PROVINCECODE = ?, SUPPLIERADDRESS = ?, SUPPLIEREMAIL = ?, SUPPLIERTEL = ?, SUPPLIERTAX = ?, USERNAME = ?, USERTEL = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	
	@Override
	public int getSequences() throws SQLException {
		int seq = 0;
		supplierConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT SUPPLIER_SEQ.NEXTVAL AS SEQ FROM DUAL";

		List<Map<String, Object>> results = this.executeQuery(supplierConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new SQLException("执行SUPPLIER的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_SUPPLIER";
	}

	@Override
	public int insert(KSupplier supplier) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		supplierConn = this.getConnection();
		params.add(supplier.getId());
		params.add(supplier.getSupplierno() );
		params.add(supplier.getSuppliername() );
		params.add(supplier.getProvincecode() );
		params.add(supplier.getSupplieraddress() );
		params.add(supplier.getSupplieremail() );
		params.add(supplier.getSuppliertel() );
		params.add(supplier.getSuppliertax() );
		params.add(supplier.getUsername() );
		params.add(supplier.getUsertel() );
		params.add(supplier.getRemark() );
		rows = this.executeUpdate(supplierConn, SQL_INSERT, params.toArray());
		
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KSupplier supplier) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		supplierConn = this.getConnection();
		params.add(supplier.getId());
		params.add(supplier.getSupplierno() );
		params.add(supplier.getSuppliername() );
		params.add(supplier.getProvincecode() );
		params.add(supplier.getSupplieraddress() );
		params.add(supplier.getSupplieremail() );
		params.add(supplier.getSuppliertel() );
		params.add(supplier.getSuppliertax() );
		params.add(supplier.getUsername() );
		params.add(supplier.getUsertel() );
		params.add(supplier.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(supplierConn, SQL_UPDATE, params.toArray());
		
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		supplierConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(supplierConn, SQL_DELETE, params.toArray());
		
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return rows;
	}

	@Override
	public KSupplier findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KSupplier ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KSupplier[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT +  " ORDER BY ID", new Object[0] );
	}

	@Override
	public KSupplier[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		supplierConn = this.getConnection();
		resultList = this.executeQuery(supplierConn, sql, sqlParams);
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KSupplier[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		supplierConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE " + sql;
	
		resultList = this.executeQuery(supplierConn, SQL, sqlParams);
		
		if (supplierConn != null) {
			this.closeConnection(supplierConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KSupplier[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KSupplier[] suppliers = new KSupplier[list.size()];
		List<KSupplier> supplierList = new ArrayList<KSupplier>();
		for (Map<String, Object> map : list) {
			KSupplier supplier = new KSupplier();
			supplier.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			supplier.setSupplierno(map.get("SUPPLIERNO") == null ? null:map.get("SUPPLIERNO").toString());
			supplier.setSuppliername(map.get("SUPPLIERNAME") == null ? null:map.get("SUPPLIERNAME").toString());
			supplier.setProvincecode(map.get("PROVINCECODE") == null ? null:map.get("PROVINCECODE").toString());
			supplier.setSupplieraddress(map.get("SUPPLIERADDRESS") == null ? null:map.get("SUPPLIERADDRESS").toString());
			supplier.setSupplieremail(map.get("SUPPLIEREMAIL") == null ? null:map.get("SUPPLIEREMAIL").toString());
			supplier.setSuppliertel(map.get("SUPPLIERTEL") == null ? null:map.get("SUPPLIERTEL").toString());
			supplier.setSuppliertax(map.get("SUPPLIERTAX") == null ? null:map.get("SUPPLIERTAX").toString());
			supplier.setUsername(map.get("USERNAME") == null ? null:map.get("USERNAME").toString());
			supplier.setUsertel(map.get("USERTEL") == null ? null:map.get("USERTEL").toString());
			supplier.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			supplierList.add(supplier);
		}
		return supplierList.toArray(suppliers);
	}

}
