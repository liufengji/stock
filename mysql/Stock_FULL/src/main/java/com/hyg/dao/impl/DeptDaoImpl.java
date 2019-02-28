package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.DeptDaoI;
import com.hyg.model.KDept;
import com.hyg.util.DBUtil;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class DeptDaoImpl extends DBUtil implements DeptDaoI {
	private Connection deptConn = null;
	
	protected final String SQL_SELECT = "SELECT ID, DEPTNO, DEPTNAME, DEPTLEADER, DEPTTEL, PARENTDEPTNO, DEPTDESC, REMARK FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, DEPTNO, DEPTNAME, DEPTLEADER, DEPTTEL, PARENTDEPTNO, DEPTDESC, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, DEPTNO = ?, DEPTNAME = ?, DEPTLEADER = ?, DEPTTEL = ?, PARENTDEPTNO = ?, DEPTDESC = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	
	public DeptDaoImpl(Connection conn) {
		// TODO Auto-generated constructor stub
		deptConn = conn;
	}
	
	public DeptDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private String getTableName() {
		// TODO Auto-generated method stub
		return "K_DEPT";
	}
	
	
	@Override
	public int getSequences() throws SQLException {
		// TODO Auto-generated method stub
		int seq = 0;
		deptConn = this.getConnection();
		// construct the SQL statement
		final String SQL = "SELECT DEPT_SEQ.NEXTVAL AS SEQ FROM DUAL";
	
		List<Map<String, Object>> results = this.executeQuery(deptConn, SQL, new Object[0]);
		
		if (results.size() == 0) {
			throw new SQLException("查询DEPT_SEQ序列下一个值得时候，发生错误，请检查！");
		}else{
			seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
		}
		
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return seq;
	}
	@Override
	public int insert(KDept dept) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		deptConn = this.getConnection();
		params.add(dept.getId() );
		params.add(dept.getDeptno() );
		params.add(dept.getDeptname() );
		params.add(dept.getDeptleader() );
		params.add(dept.getDepttel() );
		params.add(dept.getParentdeptno() );
		params.add(dept.getDeptdesc() );
		params.add(dept.getRemark() );
		rows = this.executeUpdate(deptConn,SQL_INSERT,params.toArray());
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return rows;
	}
	@Override
	public int update(BigDecimal pk, KDept dept) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		int rows = 0;
		deptConn = this.getConnection();
		params.add(dept.getId() );
		params.add(dept.getDeptno() );
		params.add(dept.getDeptname() );
		params.add(dept.getDeptleader() );
		params.add(dept.getDepttel() );
		params.add(dept.getParentdeptno() );
		params.add(dept.getDeptdesc() );
		params.add(dept.getRemark() );
		params.add(pk);
		rows = this.executeUpdate(deptConn,SQL_UPDATE,params.toArray());
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return rows;
	}
	@Override
	public int delete(BigDecimal pk) throws SQLException {
		List params = new ArrayList();
		int rows = 0;
		deptConn = this.getConnection();
		params.add(pk);
		rows = this.executeUpdate(deptConn,SQL_DELETE,params.toArray());
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return rows;
	}
	@Override
	public KDept findByPrimaryKey(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		KDept ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] { id } );
		return ret.length==0 ? null : ret[0];
	}
	@Override
	public KDept[] findAll() throws SQLException {
		// TODO Auto-generated method stub
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", new Object[0] );
	}
	@Override
	public KDept[] findByDynamicSelect(String sql, Object[] sqlParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		deptConn = this.getConnection();
		resultList = this.executeQuery(deptConn, sql, sqlParams);
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return MapToObject(resultList);
	}
	@Override
	public KDept[] findByDynamicWhere(String sql, Object[] sqlParams)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		deptConn = this.getConnection();
		final String SQL = SQL_SELECT + " WHERE " + sql;
		resultList = this.executeQuery(deptConn, SQL, sqlParams);
		if (deptConn != null) {
			this.closeConnection(deptConn);
		}
		return MapToObject(resultList);
	}
	@Override
	public KDept[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KDept[] depts = new KDept[list.size()];
		List<KDept> deptList = new ArrayList<KDept>();
		for (Map<String, Object> map : list) {
			KDept dept = new KDept();
			dept.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			dept.setDeptno(map.get("DEPTNO") == null ? null:map.get("DEPTNO").toString());
			dept.setDeptname(map.get("DEPTNAME") == null ? null:map.get("DEPTNAME").toString());
			dept.setDeptleader(map.get("DEPTLEADER") == null ? null:map.get("DEPTLEADER").toString());
			dept.setDepttel(map.get("DEPTTEL") == null ? null:map.get("DEPTTEL").toString());
			dept.setDeptdesc(map.get("DEPTDESC") == null ? null:map.get("DEPTDESC").toString());
			dept.setParentdeptno(map.get("PARENTDEPTNO") == null ? null:map.get("PARENTDEPTNO").toString());
			dept.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());

			deptList.add(dept);
		}
		return deptList.toArray(depts);
	}

}
