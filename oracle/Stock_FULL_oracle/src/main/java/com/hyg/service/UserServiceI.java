package com.hyg.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KUser;

public interface UserServiceI {
	/**
	 * 校验用户是否有效
	 * @param sc 控制台输入流
	 * @return 如果有效则返回用户信息，否则返回NULL
	 */
	public KUser checkUser(Scanner sc);
	
	/**
	 * 将List集合中的Map对象转成Object对象
	 * @param list 
	 * @return Object[]
	 */
	public KUser[] MapToObject(List<Map<String,Object>> list);

	/**
	 * 添加用户信息
	 * @param sc
	 */
	public void addUser(Scanner sc);
	
	/**
	 * 根据ID修改用户
	 * @param sc
	 * @param id
	 */
	public void updateUser(Scanner sc,String id);
	/**
	 * 根据ID删除用户
	 * @param sc
	 * @param id
	 */
	public void deleteUser(Scanner sc, String id);
	
	/**
	 * 查询用户
	 * @param sc
	 */
	public void searchUser(Scanner sc);
	
}
