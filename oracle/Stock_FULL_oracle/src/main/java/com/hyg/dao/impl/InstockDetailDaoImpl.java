package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.InstockDetailDaoI;
import com.hyg.model.KInstockDetail;
import com.hyg.util.DBUtil;

public class InstockDetailDaoImpl extends DBUtil implements InstockDetailDaoI{
	private Connection instockDetailConn;
	protected final String SQL_SELECT = "SELECT ID, INSTOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, PRODUCTNUM, UNIT, PRICE, TOTALPRICE, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, INSTOCKID, PRODUCTNO, PRODUCTNAME, PRODUCTSTANDARD, PRODUCTNUM, UNIT, PRICE, TOTALPRICE, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, INSTOCKID = ?, PRODUCTNO = ?, PRODUCTNAME = ?, PRODUCTSTANDARD = ?, PRODUCTNUM = ?, UNIT = ?, PRICE = ?, TOTALPRICE = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";


	@Override
	public KInstockDetail[] findInstockDetailsByInstockID(String instockId) throws SQLException {
		// TODO Auto-generated method stub
		return  findByDynamicSelect( SQL_SELECT + " WHERE INSTOCKID = ?", new Object[] { instockId } );
	}

	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_INSTOCK_DETAIL";
	}

	@Override
	public int getSequences() throws SQLException,Exception {
		// TODO Auto-generated method stub
		int seq = 0;
		instockDetailConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT INSTOCK_DETAIL_SEQ.NEXTVAL AS SEQ FROM DUAL";
		List<Map<String, Object>> results = this.executeQuery(instockDetailConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new Exception("执行INSTOCK_DETAIL的序列语句错误！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
			this.closeConnection(instockDetailConn);
		return seq;
	}

	@Override
	public int insert(KInstockDetail dto) throws SQLException {
		// TODO Auto-generated method stub
		
		int rows = 0;
		instockDetailConn = this.getConnection();
		List params = new ArrayList();
		params.add(dto.getId() );
		params.add(dto.getInstockid() );
		params.add(dto.getProductno() );
		params.add(dto.getProductname() );
		params.add(dto.getProductstandard() );
		params.add(dto.getProductnum() );
		params.add(dto.getUnit() );
		params.add(dto.getPrice() );
		params.add(dto.getTotalprice() );
		params.add(dto.getRemark() );
		rows = this.executeUpdate(instockDetailConn, SQL_INSERT, params.toArray());
		this.closeConnection(instockDetailConn);
		return rows;
	}

	@Override
	public int update(BigDecimal pk, KInstockDetail dto) throws SQLException {
		int rows = 0;
		instockDetailConn = this.getConnection();
		List params = new ArrayList();
		params.add(dto.getId() );
		params.add(dto.getInstockid() );
		params.add(dto.getProductno() );
		params.add(dto.getProductname() );
		params.add(dto.getProductstandard() );
		params.add(dto.getProductnum() );
		params.add(dto.getUnit() );
		params.add(dto.getPrice() );
		params.add(dto.getTotalprice() );
		params.add(dto.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(instockDetailConn, SQL_UPDATE, params.toArray());
		this.closeConnection(instockDetailConn);
		return rows;
	}

	@Override
	public int delete(BigDecimal pk) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		instockDetailConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(instockDetailConn, SQL_DELETE, params.toArray());
		this.closeConnection(instockDetailConn);
		return rows;
	}

	@Override
	public KInstockDetail findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KInstockDetail ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}

	@Override
	public KInstockDetail[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID",  new Object[0]  );
	}

	@Override
	public KInstockDetail[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		instockDetailConn = this.getConnection();
		resultList = this.executeQuery(instockDetailConn, sql, sqlParams);
			this.closeConnection(instockDetailConn);
		return MapToObject(resultList);
	}

	@Override
	public KInstockDetail[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			instockDetailConn = this.getConnection();
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE 1=1 " + sql;
			resultList = this.executeQuery(instockDetailConn, SQL, sqlParams);
			this.closeConnection(instockDetailConn);
			return MapToObject(resultList);
	}

	@Override
	public KInstockDetail[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KInstockDetail[] instocks = new KInstockDetail[list.size()];
		List<KInstockDetail> instockDetailList = new ArrayList<KInstockDetail>();
		for (Map<String, Object> map : list) {
			KInstockDetail instockDetail = new KInstockDetail();
			instockDetail.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			instockDetail.setInstockid(map.get("INSTOCKID") == null ? null:new BigDecimal(map.get("INSTOCKID").toString()));
			instockDetail.setProductno(map.get("PRODUCTNO") == null ? null:map.get("PRODUCTNO").toString());
			instockDetail.setProductname(map.get("PRODUCTNAME") == null ? null:map.get("PRODUCTNAME").toString());
			instockDetail.setProductstandard(map.get("PRODUCTSTANDARD") == null ? null:map.get("PRODUCTSTANDARD").toString());
			instockDetail.setProductnum(map.get("PRODUCTNUM") == null ? null:new BigDecimal(map.get("PRODUCTNUM").toString()));
			instockDetail.setUnit(map.get("UNIT") == null ? null:map.get("UNIT").toString());
			instockDetail.setPrice(map.get("PRICE") == null ? null:new BigDecimal(map.get("PRICE").toString()));
			instockDetail.setTotalprice(map.get("TOTALPRICE") == null ? null:new BigDecimal(map.get("TOTALPRICE").toString()));
			instockDetail.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			instockDetailList.add(instockDetail);
		}
		return instockDetailList.toArray(instocks);
	}

}
