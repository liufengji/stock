package com.yg.view;

import java.util.List;
import java.util.Scanner;
import com.yg.entity.User;
import com.yg.service.UserService;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class UserView {

	User user = null;
	UserService us = new UserService();

	public User login(Scanner sc) {
		System.out.println("请输入用户名");
		String userNo = sc.next();
		System.out.println("请输入密码:");
		String pwd = sc.next();

		user = us.Login(userNo, pwd);
		if (user != null) {
			System.out.println("登录成功！");
		}
		else {
			System.out.println("登录失败！！！");
		}
		return user;
	}

	public void addUser(Scanner sc) {
		System.out.println("输入学号：");
		String userNo = sc.next();
		System.out.println("输入用户名：");
		String userName = sc.next();
		System.out.println("输入密码：");
		String pwd = sc.next();

		User user = new User();
		user.setUserno(userNo);
		user.setUsername(userName);
		user.setPwd(pwd);

		if(us.AddUser(user)) {
			System.out.println("添加用户成功");
		}
		else {
			System.out.println("添加用户失败");
		}
	}

	public void showUserList() {
		System.out.println("所有用户信息如下：");
		List<User> list = us.GetAllUsers();
		System.out.println("用户ID\t\t用户代码\t\t用户姓名\t\t密码\t\t性别\t\t年龄\t\t职位");

		for (User user : list) {
			System.out.println(user.getId()+"\t\t"+user.getUserno()+"\t\t"+user.getUsername()+"\t\t"+user.getPwd()+"\t\t"+user.getSex()+"\t\t"+user.getAge()+"\t\t"+user.getPosition());
		}
	}

	public void deleteUser(Scanner sc) {
		System.out.println("请输入要删除的用户id：");
		String id = sc.next();
		if (us.DeleteUser(id)) {
			System.out.println("删除成功。");
		}
		else {
			System.out.println("删除失败！！！");
		}
	}

}
