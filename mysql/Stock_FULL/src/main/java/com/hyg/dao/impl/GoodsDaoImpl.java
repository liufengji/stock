package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.GoodsDaoI;
import com.hyg.model.KGoods;
import com.hyg.util.DBUtil;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class GoodsDaoImpl extends DBUtil implements GoodsDaoI {
	private Connection goodsConn;
	protected final String SQL_SELECT = "SELECT ID, PRODUCTNO, PRODUCTNAME, PRODUCTTYPE, PRODUCTSTANDARD,  UNIT, PRICE, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, PRODUCTNO, PRODUCTNAME, PRODUCTTYPE, PRODUCTSTANDARD,  UNIT, PRICE, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, PRODUCTNO = ?, PRODUCTNAME = ?, PRODUCTTYPE = ?, PRODUCTSTANDARD = ?, UNIT = ?, PRICE = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	@Override
	public int getSequences() throws SQLException,Exception {
		int seq = 0;
		goodsConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT GOODS_SEQ.NEXTVAL AS SEQ FROM DUAL";
	
		List<Map<String, Object>> results = this.executeQuery(goodsConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new Exception("执行GOODS的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		this.closeConnection(goodsConn);
		return seq;
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_GOODS";
	}

	@Override
	public int insert(KGoods goods) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		goodsConn = this.getConnection();
		params.add(goods.getId() );
		params.add(goods.getProductno() );
		params.add(goods.getProductname() );
		params.add(goods.getProducttype() );
		params.add(goods.getProductstandard() );
		params.add(goods.getUnit() );
		params.add(goods.getPrice() );
		params.add(goods.getRemark() );
		rows = this.executeUpdate(goodsConn, SQL_INSERT, params.toArray());
		
		if (goodsConn != null) {
			this.closeConnection(goodsConn);
		}
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KGoods goods) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		goodsConn = this.getConnection();
		params.add(goods.getId() );
		params.add(goods.getProductno() );
		params.add(goods.getProductname() );
		params.add(goods.getProducttype() );
		params.add(goods.getProductstandard() );
		params.add(goods.getUnit() );
		params.add(goods.getPrice() );
		params.add(goods.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(goodsConn, SQL_UPDATE, params.toArray());
		
		if (goodsConn != null) {
			this.closeConnection(goodsConn);
		}
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		goodsConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(goodsConn, SQL_DELETE, params.toArray());
		if (goodsConn != null) {
			this.closeConnection(goodsConn);
		}
		return rows;
	}

	@Override
	public KGoods findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KGoods ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KGoods[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", new Object[0]  );
	}

	@Override
	public KGoods[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		goodsConn = this.getConnection();
		resultList = this.executeQuery(goodsConn, sql, sqlParams);
		if (goodsConn != null) {
			this.closeConnection(goodsConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KGoods[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		goodsConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE " + sql;
	
		resultList = this.executeQuery(goodsConn, SQL, sqlParams);
		
		if (goodsConn != null) {
			this.closeConnection(goodsConn);
		}
		return MapToObject(resultList);
	}

	@Override
	public KGoods[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KGoods[] goods = new KGoods[list.size()];
		List<KGoods> goodsList = new ArrayList<KGoods>();
		for (Map<String, Object> map : list) {
			KGoods good = new KGoods();
			good.setId(map.get("ID") == null ? null: new BigDecimal(map.get("ID").toString()));
			good.setProductno(map.get("PRODUCTNO") == null ? null:map.get("PRODUCTNO").toString());
			good.setProductname(map.get("PRODUCTNAME") == null ? null:map.get("PRODUCTNAME").toString());
			good.setProducttype(map.get("PRODUCTTYPE") == null ? null:map.get("PRODUCTTYPE").toString());
			good.setProductstandard(map.get("PRODUCTSTANDARD") == null ? null:map.get("PRODUCTSTANDARD").toString());
			good.setUnit(map.get("UNIT") == null ? null:map.get("UNIT").toString());
			good.setPrice(map.get("PRICE") == null ? new BigDecimal(0.0):new BigDecimal(map.get("PRICE").toString()));
			good.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			goodsList.add(good);
		}
		return goodsList.toArray(goods);
	}

}
