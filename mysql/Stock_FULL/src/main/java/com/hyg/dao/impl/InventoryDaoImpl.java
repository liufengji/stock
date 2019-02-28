package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.InventoryDaoI;
import com.hyg.model.KInventory;
import com.hyg.util.DBUtil;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class InventoryDaoImpl extends DBUtil implements InventoryDaoI {
	private Connection inventoryConn;
	protected final String SQL_SELECT = "SELECT ID, STOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, INVENTORYNUM, INVENTORYPRICE, INSTOCKNUM, INSTOCKPRICE, OUTSTOCKNUM, OUTSTOCKPRICE, MAXNUM, MINNUM, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, STOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, INVENTORYNUM, INVENTORYPRICE, INSTOCKNUM, INSTOCKPRICE, OUTSTOCKNUM, OUTSTOCKPRICE, MAXNUM, MINNUM, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, STOCKID = ?, PRODUCTNO = ?, PRODUCTNAME = ?, PRODUCTSTANDARD = ?, INVENTORYNUM = ?, INVENTORYPRICE = ?, INSTOCKNUM = ?, INSTOCKPRICE = ?, OUTSTOCKNUM = ?, OUTSTOCKPRICE = ?, MAXNUM = ?, MINNUM = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	@Override
	public int getSequences() throws SQLException,Exception {
		int seq = 0;
		inventoryConn = this.getConnection();
		final String SQL = "SELECT INVENTORY_SEQ.NEXTVAL AS SEQ FROM DUAL";
		List<Map<String, Object>> results = this.executeQuery(inventoryConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new Exception("执行INVENTORY_SEQ的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		this.closeConnection(inventoryConn);
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_INVENTORY";
	}

	@Override
	public int insert(KInventory inventory) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		inventoryConn = this.getConnection();
		params.add(inventory.getId() );
		params.add(inventory.getStockid() );
		params.add(inventory.getProductno() );
		params.add(inventory.getProductname() );
		params.add(inventory.getProductstandard() );
		params.add(inventory.getInventorynum() );
		params.add(inventory.getInventoryprice() );
		params.add(inventory.getInstocknum() );
		params.add(inventory.getInstockprice() );
		params.add(inventory.getOutstocknum() );
		params.add(inventory.getOutstockprice() );
		params.add(inventory.getMaxnum() );
		params.add(inventory.getMinnum() );
		params.add(inventory.getRemark() );
		rows = this.executeUpdate(inventoryConn, SQL_INSERT, params.toArray());
		this.closeConnection(inventoryConn);
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KInventory inventory) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		inventoryConn = this.getConnection();
		params.add(inventory.getId() );
		params.add(inventory.getStockid() );
		params.add(inventory.getProductno() );
		params.add(inventory.getProductname() );
		params.add(inventory.getProductstandard() );
		params.add(inventory.getInventorynum() );
		params.add(inventory.getInventoryprice() );
		params.add(inventory.getInstocknum() );
		params.add(inventory.getInstockprice() );
		params.add(inventory.getOutstocknum() );
		params.add(inventory.getOutstockprice() );
		params.add(inventory.getMaxnum() );
		params.add(inventory.getMinnum() );
		params.add(inventory.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(inventoryConn, SQL_UPDATE, params.toArray());
		this.closeConnection(inventoryConn);
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		inventoryConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(inventoryConn, SQL_DELETE, params.toArray());
		this.closeConnection(inventoryConn);
		return rows;
	}

	@Override
	public KInventory findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KInventory ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KInventory[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT +  " ORDER BY ID", new Object[0] );
	}

	@Override
	public KInventory[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		inventoryConn = this.getConnection();
		resultList = this.executeQuery(inventoryConn, sql, sqlParams);
		this.closeConnection(inventoryConn);
		return MapToObject(resultList);
	}

	@Override
	public KInventory[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		inventoryConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE 1=1 " + sql;
		resultList = this.executeQuery(inventoryConn, SQL, sqlParams);
		this.closeConnection(inventoryConn);
		return MapToObject(resultList);
	}

	@Override
	public KInventory[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KInventory[] inventories = new KInventory[list.size()];
		List<KInventory> inventoryList = new ArrayList<KInventory>();
		for (Map<String, Object> map : list) {
			KInventory inventory = new KInventory();
			inventory.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			inventory.setStockid(map.get("STOCKID") == null ? null:new BigDecimal(map.get("STOCKID").toString()));
			inventory.setProductno(map.get("PRODUCTNO") == null ? null:map.get("PRODUCTNO").toString());
			inventory.setProductname(map.get("PRODUCTNAME") == null ? null:map.get("PRODUCTNAME").toString());
			inventory.setProductstandard(map.get("PRODUCTSTANDARD") == null ? null:map.get("PRODUCTSTANDARD").toString());
			inventory.setInventorynum(map.get("INVENTORYNUM") == null ? null:new BigDecimal(map.get("INVENTORYNUM").toString()));
			inventory.setInventoryprice(map.get("INVENTORYPRICE") == null ? null:new BigDecimal(map.get("INVENTORYPRICE").toString()));
			inventory.setInstocknum(map.get("INSTOCKNUM") == null ? null:new BigDecimal(map.get("INSTOCKNUM").toString()));
			inventory.setInstockprice(map.get("INSTOCKPRICE") == null ? null:new BigDecimal(map.get("INSTOCKPRICE").toString()));
			inventory.setOutstocknum(map.get("OUTSTOCKNUM") == null ? null:new BigDecimal(map.get("OUTSTOCKNUM").toString()));
			inventory.setOutstockprice(map.get("OUTSTOCKPRICE") == null ? null:new BigDecimal(map.get("OUTSTOCKPRICE").toString()));
			inventory.setMinnum(map.get("MINNUM") == null ? null:new BigDecimal(map.get("MINNUM").toString()));
			inventory.setMaxnum(map.get("MAXNUM") == null ? null:new BigDecimal(map.get("MAXNUM").toString()));
			inventory.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			inventoryList.add(inventory);
		}
		return inventoryList.toArray(inventories);
	
	}

}
