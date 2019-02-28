package com.yg.service;

import java.util.List;

import com.yg.dao.UserDao;
import com.yg.entity.User;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class UserService {
	UserDao dao = new UserDao();
	
	public User Login(String userno,String pwd) {

		return dao.Login(userno, pwd);
	}
	
	public List<User> GetAllUsers() {
		return dao.GetAllUsers();
	}
	
	public Boolean AddUser(User user) {
		return dao.AddUser(user) > 0;
	}
	
	public Boolean DeleteUser(String id) {
		return dao.DeleteUser(id) > 0;
	}
	
	public Boolean EditUser(User user) {
		return dao.EditUser(user) > 0;
	}
}
