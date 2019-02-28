package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.UserDaoI;
import com.hyg.dao.impl.UserDaoImpl;
import com.hyg.factory.UserDaoFactory;
import com.hyg.model.KUser;
import com.hyg.service.UserServiceI;
import com.hyg.util.FillEmptyUitl;
import com.hyg.util.StringUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class UserServiceImpl implements UserServiceI {
	private UserDaoI userDao;
	@Override
	public KUser checkUser(Scanner sc) {
		// TODO Auto-generated method stub
		KUser userDB = null;
		//1、输入必要的信息 userNo,pwd,role
		System.out.print("请输入用户名:");
		String userNo = sc.next();
		System.out.print("请输入密码:");
		String pwd = sc.next();
		System.out.print("请输入角色类型:");
		String role = sc.next();
		//2、做一个用户名密码校验,yjx,yjx123,管理员
		//改成动态的从数据库取数据
		userDao = UserDaoFactory.create();
		
		KUser user = new KUser();
		user.setUserno(userNo);
		user.setPwd(pwd);
		user.setRoleno(role);
		
		List<Map<String,Object>> results = userDao.findUserByWhere(user);
		
		//业务逻辑判断
		if (results != null && results.size() > 0) {
			KUser[] users = MapToObject(results);
			userDB = users[0];
		}
		return userDB;
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

	@Override
	public void addUser(Scanner sc) {
		// TODO Auto-generated method stub
		KUser user = new KUser();
		try {
			//user.setId(new BigDecimal(userDao.getSequences()+""));
			
			System.out.println("请输入用户名:");
			user.setUserno(sc.nextLine());
			System.out.println("请输入用户姓名:");
			user.setUsername(sc.nextLine());
			System.out.println("请输入登录密码:");
			user.setPwd(sc.nextLine());
			System.out.println("请输入用户性别(男/女):");
			user.setSex(sc.nextLine());
			//////////////////
			System.out.println("请输入用户年龄(1-200)：");
			user.setAge(sc.nextInt());
			System.out.println("请输入所在部门:");
			user.setDeptno(sc.nextLine());
			System.out.println("请输入用户职位:");
			user.setPosition(sc.nextLine());
			System.out.println("请输入故乡所在地:");
			user.setHometown(sc.nextLine());
			System.out.println("请输入邮箱地址:");
			user.setEmail(sc.nextLine());
			/////////////////
			System.out.println("用户联系方式:");
			user.setTelphone(sc.nextLine());
			System.out.println("请输入用户角色类型(0:管理员 1:库管员):");
			user.setRoleno(sc.nextLine());
			System.out.println("请输入备注:");
			String remark = sc.nextLine();
			if (StringUtils.isEmpty(remark)) {
				user.setRemark("");
			}else {
				user.setRemark(remark);
			}
			userDao = UserDaoFactory.create();
			int rows = userDao.insert(user);
			if (rows > 0) {
				System.out.println("添加用户成功！");
//				KUser userDB = userDao.findByPrimaryKey(user.getId());
//				System.out.println("ID\t\t用户名\t\t用户姓名\t\t密码\t\t性别\t\t年龄"
//						+ "\t\t职位\t\t联系方式\t\t角色\t\t备注");
//				System.out.println(userDB.getId() + "\t\t" + userDB.getUserno() + "\t\t" + userDB.getUsername()+ "\t\t" + userDB.getPwd()
//						+ "\t\t" + userDB.getSex() + "\t\t" + userDB.getAge()
//						+ "\t\t" + userDB.getPosition() + "\t\t" + userDB.getTelphone() 
//						+ "\t\t" + ("0".equals(userDB.getRoleno())?"管理员":"库管员") + "\t\t" + userDB.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(Scanner sc, String id) {
		// TODO Auto-generated method stub
		//1、通过id获取到用户信息
		int count=0;
		try {
			userDao = UserDaoFactory.create();
			//通过userDao中的findByPrimaryKey方法去获取到唯一的用户记录
			KUser userDB = userDao.findByPrimaryKey(new BigDecimal(id));
			//2、先显示数据库中当前userDB中的position这一列的值，然后从控制台数据更新后的值
			System.out.println("当前用户的职位是("+userDB.getPosition()+"),请输入更改后的值: ");
			//获取从控制台数据的值
			String position = sc.next();
			
			//3、到数据库做更新操作，注意的是通过id进行限制做唯一更新
			KUser user = userDB;
			user.setPosition(position);
		
			count = userDao.update(new BigDecimal(id), user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//4、做个判断，如果count > 0 则提示更新成功，否则提示更新失败
		if (count > 0) {
			System.out.println("恭喜你更新成功！");
		}else{
			System.out.println("很抱歉，更新失败!");
		}
	
	
	}

	@Override
	public void deleteUser(Scanner sc, String id) {
		// TODO Auto-generated method stub
		int count=0;
		try {
			userDao = UserDaoFactory.create();
			count = userDao.delete(new BigDecimal(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (count > 0) {
			System.out.println("恭喜你删除成功！");
		}else{
			System.out.println("很抱歉，删除失败!");
		}
	
	}

	@Override
	public void searchUser(Scanner sc) {
		// TODO Auto-generated method stub
		KUser[] users = null;
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			userDao = UserDaoFactory.create();
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入用户名：");
				String userNo = sc.nextLine();
				System.out.println("请输入用户姓名:");
				String userName = sc.nextLine();
				System.out.println("请输入角色类型(0:管理员 1:库管员)");
				String roleNo = sc.nextLine();
				String  whereSql = "";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(userNo)) {
					whereSql += " and userNo = ?";
					param.add(userNo);
				}
				if (StringUtils.isNotEmpty(userName)) {
					whereSql += " and userName like ?";
					param.add("%" + userName + "%");
				}
				if (StringUtils.isNotEmpty(roleNo)) {
					whereSql += " and roleNo = ?";
					param.add(roleNo);
				}
				users = userDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
			//查询所有用户
				users = userDao.findAll();
			}
			System.out.println("ID\t\t用户名\t\t用户姓名\t\t密码\t\t性别\t\t年龄"
					+ "\t\t职位\t\t联系方式\t\t角色\t\t备注");
			for (KUser kUser : users) {
				System.out.println(kUser.getId() + "\t\t" + kUser.getUserno() + "\t\t" 
						+ kUser.getUsername() + "\t\t" + kUser.getPwd()
						+ "\t\t" + kUser.getSex() + "\t\t" + kUser.getAge() 
						+ "\t\t" + kUser.getPosition() + "\t\t" + kUser.getTelphone() 
						+ "\t\t" + ("0".equals(kUser.getRoleno())?"管理员":"库管员") + "\t\t" + kUser.getRemark());
			}
//			String[] columnTitles = {"ID","用户名","用户姓名","密码","性别","年龄","职位","联系方式","角色","备注"};
//			System.out.println(FillEmptyUitl.formatColumn(10,columnTitles));
//			List<String> list = new ArrayList<String>();
//			for (KUser kUser : users) {
//				list.add(kUser.getId() == null ? "" : kUser.getId().toString());
//				list.add(kUser.getUserno() == null ? "" : kUser.getUserno().toString());
//				list.add(kUser.getUsername()  == null ? "" : kUser.getUsername() .toString());
//				list.add(kUser.getPwd() == null ? "" : kUser.getPwd().toString());
//				list.add(kUser.getSex() == null ? "" : kUser.getSex().toString());
//				
//				list.add(kUser.getAge()+"");
//				list.add(kUser.getPosition() == null ? "" : kUser.getPosition().toString());
//				list.add(kUser.getTelphone() == null ? "" : kUser.getTelphone().toString());
//				list.add("0".equals(kUser.getRoleno())?"管理员":"库管员");
//				list.add(kUser.getRemark());
//				String[] columnValues = new String[list.size()];
//				System.out.println(FillEmptyUitl.formatColumn(10,list.toArray(columnValues)));
//				list.clear();
//			}
//			
//			System.out.println(FillEmptyUitl.formatColumn(10,list.toArray(columnValues)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
