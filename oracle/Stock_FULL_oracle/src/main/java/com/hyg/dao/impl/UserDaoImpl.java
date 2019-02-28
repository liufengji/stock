package com.hyg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.dao.UserDaoI;
import com.hyg.model.KUser;
import com.hyg.util.DBUtil;
import com.hyg.util.StringUtils;

public class UserDaoImpl extends DBUtil implements UserDaoI {
	protected Connection userConn;
	protected final String SQL_SELECT = "SELECT ID, USERNO, USERNAME, PWD, DEPTNO, SEX, AGE, POSITION, HOMETOWN, TELPHONE, EMAIL, ROLENO, REMARK FROM " + getTableName() + " ";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, USERNO, USERNAME, PWD, DEPTNO, SEX, AGE, POSITION, HOMETOWN, TELPHONE, EMAIL, ROLENO, REMARK ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, USERNO = ?, USERNAME = ?, PWD = ?, DEPTNO = ?, SEX = ?, AGE = ?, POSITION = ?, HOMETOWN = ?, TELPHONE = ?, EMAIL = ?, ROLENO = ?, REMARK = ? WHERE ID = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/**
	 * 获得表名
	 * @return
	 */
	public String getTableName()
	{
		return "K_USER";
	}
	@Override
	public List<Map<String, Object>> findUserByWhere(KUser user) {
		// TODO Auto-generated method stub
		//(1)、获取数据库连接
		userConn = this.getConnection();
		//(2)、编写预编译的sql语句
		String sql = SQL_SELECT + " where 1=1 ";
		//(3)、基于(2)进行判断是否需要添加参数
		List param = new ArrayList();
		
		if (StringUtils.isNotEmpty(user.getUserno())) {
			sql += " and userNo = ? ";
			param.add(user.getUserno());
		}
		
		if (StringUtils.isNotEmpty(user.getPwd())) {
			sql += " and pwd = ? ";
			param.add(user.getPwd());
		}
		
		if (StringUtils.isNotEmpty(user.getRoleno())) {
			sql += " and roleNo = ? ";
			param.add(user.getRoleno());
		}
		List<Map<String, Object>> results = null;
		//(4)、通过DBUtil去执行 executeQuery()方法
		try {
			results = this.executeQuery(userConn, sql, param.toArray());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//(6)、关闭数据库连接
			this.closeConnection(userConn);
		}
		//(5)、进行业务逻辑操作
		return results;
	}

	
	public int getSequences()  {
		int seq = 0;
		try {
			userConn = this.getConnection();
			// construct the SQL statement
			final String SQL = "SELECT USER_SEQ.NEXTVAL AS SEQ FROM DUAL";
		
			List<Map<String, Object>> results = this.executeQuery(userConn, SQL, new Object[0]);
			
			if (results.size() == 0) {
				throw new Exception("执行USER的序列语句错误！");
			}else{
				seq = results.get(0).get("SEQ") == null ? 0:Integer.parseInt(results.get(0).get("SEQ").toString());
			}
			
		} catch (Exception _e) {
			_e.printStackTrace();
			
		}finally {
			if (userConn != null) {
				this.closeConnection(userConn);
			}
			return seq;
		}
	}
	@Override
	public int insert(KUser user) throws SQLException {
		// TODO Auto-generated method stub
		userConn = this.getConnection();
		List params = new ArrayList();
		params.add(user.getId());
		params.add(user.getUserno() );
		params.add(user.getUsername() );
		params.add(user.getPwd() );
		params.add(user.getDeptno() );
		params.add(user.getSex() );
		params.add(user.getAge() );
		params.add(user.getPosition() );
		params.add(user.getHometown() );
		params.add(user.getTelphone() );
		params.add(user.getEmail() );
		params.add(user.getRoleno() );
		params.add(user.getRemark() );
		//注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(userConn, SQL_INSERT, params.toArray());
		this.closeConnection(userConn);
		return rows;
	}
	@Override
	public int update(BigDecimal pk, KUser user) throws SQLException {
		userConn = this.getConnection();
		List params = new ArrayList();
		params.add(user.getId());
		params.add(user.getUserno() );
		params.add(user.getUsername() );
		params.add(user.getPwd() );
		params.add(user.getDeptno() );
		params.add(user.getSex() );
		params.add(user.getAge() );
		params.add(user.getPosition() );
		params.add(user.getHometown() );
		params.add(user.getTelphone() );
		params.add(user.getEmail() );
		params.add(user.getRoleno() );
		params.add(user.getRemark() );
		//加上更新的限制条件
		params.add(pk);
		
		//注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(userConn, SQL_UPDATE, params.toArray());
		this.closeConnection(userConn);
		return rows;
	}
	@Override
	public int delete(BigDecimal pk)  throws SQLException{
		userConn = this.getConnection();
		List params = new ArrayList();
		params.add(pk);
		int rows = this.executeUpdate(userConn, SQL_DELETE, params.toArray());
		this.closeConnection(userConn);
		return rows;
	}
	@Override
	public KUser findByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		KUser user = null;
		//1、获取数据库连接
		userConn = this.getConnection();
		//2、根据主键id = id值 去到数据库查询
		//2.1 先写预编译的sql语句 
		String sql = SQL_SELECT + " where id=? ";
		//2.2 对于有？占位符的sql语句要进行？赋值的定义
		List param = new ArrayList();
		param.add(id);
		//2.3 调用DBUtil类中的executeQuery方法执行查询操作
		try {
			List<Map<String, Object>> result = this.executeQuery(userConn, sql, param.toArray());
			//2.4 将查询出来的List结果转成Object
			KUser[] users = this.MapToObject(result);
			if (users.length > 0) {
				user = users[0];
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//3、关闭数据库连接
			this.closeConnection(userConn);
		}
		return user;
	}
	@Override
	public KUser[] findAll() throws SQLException {
		//如果参数列表不需要，则要定义一个new Object[0] 进行填充params参数，不能用null代替
		return this.findByDynamicSelect( SQL_SELECT + " ORDER BY ID", new Object[0]);
	}
	@Override
	public KUser[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		userConn = this.getConnection();
		resultList = this.executeQuery(userConn, sql, sqlParams);
		if (userConn != null) {
			this.closeConnection(userConn);
		}
		return MapToObject(resultList);
	}
	@Override
	public KUser[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		userConn = this.getConnection();
		// construct the SQL statement
		final String SQL = SQL_SELECT + " WHERE  1=1 " + sql;
	
		resultList = this.executeQuery(userConn, SQL, sqlParams);
		
		if (userConn != null) {
			this.closeConnection(userConn);
		}
		return MapToObject(resultList);
	}
	@Override
	public KUser[] MapToObject(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		KUser[] userArr = new KUser[list.size()];
		List<KUser> users = new ArrayList<KUser>();
		for (Map<String, Object> map : list) {
			KUser temp  = new KUser();
			temp.setUserno(map.get("USERNO") == null ? null: map.get("USERNO").toString());
			temp.setId(map.get("ID") == null ? null:new BigDecimal(map.get("ID").toString()));
			temp.setUserno(map.get("USERNO") == null ? null:map.get("USERNO").toString());
			temp.setUsername(map.get("USERNAME") == null ? null:map.get("USERNAME").toString());
			temp.setPwd(map.get("PWD") == null ? null:map.get("PWD").toString());
			temp.setDeptno(map.get("DEPTNO") == null ? null:map.get("DEPTNO").toString());
			temp.setSex(map.get("SEX") == null ? null:map.get("SEX").toString());
			temp.setAge(map.get("AGE") == null ? 23:Integer.parseInt(map.get("AGE").toString()));
			temp.setPosition(map.get("POSITION") == null ? null:map.get("POSITION").toString());
			temp.setHometown(map.get("HOMETOWN") == null ? null:map.get("HOMETOWN").toString());
			temp.setTelphone(map.get("TELPHONE") == null ? null:map.get("TELPHONE").toString());
			temp.setEmail(map.get("EMAIL") == null ? null:map.get("EMAIL").toString());
			temp.setRoleno(map.get("ROLENO") == null ? null:map.get("ROLENO").toString());
			temp.setRemark(map.get("REMARK") == null ? null:map.get("REMARK").toString());
			
			users.add(temp);
		}
		return users.toArray(userArr);
	}

}
