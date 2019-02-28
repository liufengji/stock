package com.yg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yg.entity.User;
import com.yg.util.DBUtil;

/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class UserDao {

	/**
	 * 用户登录
	 *
	 * @param user
	 * @param pwd
	 * @return
	 */
	public User Login(String user, String pwd) {
		String sql = "select *from k_user where userno=? And pwd=?";
		Object[] params = { user, pwd };
		ResultSet rs = DBUtil.QuerySql(sql, params);
		User user1 = null;
		try {
			while (rs.next()) {
				user1 = new User();
				user1.setId(rs.getInt("id"));
				user1.setUsername(rs.getString("username"));
				user1.setUserno(rs.getString("userno"));
				user1.setPwd(rs.getString("pwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.CloseAll();
		return user1;
	}

	/**
	 * 获取所有用户
	 *
	 * @return
	 */
	public List<User> GetAllUsers() {
		String sql = "select *from k_user order by id asc";
		ResultSet rs = DBUtil.QuerySql(sql, null);
		List<User> list = new ArrayList<>();
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setUserno(rs.getString("userno"));
				user.setPwd(rs.getString("pwd"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex"));
				user.setPosition(rs.getString("position"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.CloseAll();
		return list;
	}

	public int AddUser(User user) {
		String sql = "insert into k_user(id,username,userno,pwd) values(seq_kuser.nextval,?,?,?)";
		Object[] params = { user.getUsername(), user.getUserno(), user.getPwd() };
		return DBUtil.EditSql(sql, params);
	}

	public int DeleteUser(String id) {
		String sql = "delete from k_user where id=?";
		Object[] params = { id };
		return DBUtil.EditSql(sql, params);
	}

	public int EditUser(User user) {
		String sql = "update k_user set username=?,userno=?,pwd=? where id=?";
		Object[] params = { user.getUserno(), user.getUserno(), user.getPwd(), user.getId() };
		return DBUtil.EditSql(sql, params);
	}

}
