package com.yg.view;

import java.util.Scanner;
import com.yg.entity.User;

/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class Menu {
	private User user = null;
	UserView userView = new UserView();

	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.LoginView();

	}

	private void LoginView() {
		System.out.println("-----------欢迎登录库存管理系统-----------");
		System.out.println("------------1.登录-----------");
		System.out.println("------------2.退出-----------");

		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要进行的操作:");
		int num = sc.nextInt();
		if (num == 1) {
			user = userView.login(sc);
			if(user != null) {
				IndexView(sc);
			}else {
				LoginView();
			}

		} else {
			System.out.println("欢迎下次登录。");
		}

	}

	private void IndexView(Scanner sc) {
		System.out.println("\t\t\t欢迎 [" + user.getUsername() + "]登录本系统!");
		System.out.println("\t\t\t--------1、基库存管理----------");
		System.out.println("\t\t\t-----------1.1 库存查询----------");
		System.out.println("\t\t\t-----------1.2 入库管理----------");
		System.out.println("\t\t\t-----------1.3 出库管理----------");
		System.out.println("\t\t\t--------2、报表管理----------");
		System.out.println("\t\t\t-----------2.1 库存月报----------");
		System.out.println("\t\t\t-----------2.2 物资台账----------");
		System.out.println("\t\t\t--------3、基础信息管理----------");
		System.out.println("\t\t\t-----------3.1 用户管理----------");
		System.out.println("\t\t\t-----------3.2 部门管理----------");
		System.out.println("\t\t\t-----------3.3 仓库管理----------");
		System.out.println("\t\t\t-----------3.4 供应商管理----------");
		System.out.println("\t\t\t-----------3.5 产品管理----------");
		System.out.println("\t\t\t--------4、退出系统----------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		switch (operate) {
			case "3.1":
				showUserView(sc);
				break;

			default:
				break;
		}
	}

	private void showUserView(Scanner sc) {
		System.out.println("\t\t\t------------------欢迎到用户管理界面------------------");
		System.out.println("\t\t\t------------------1、查看用户信息------------------");
		System.out.println("\t\t\t------------------2、添加用户信息------------------");
		System.out.println("\t\t\t------------------3、修改用户信息------------------");
		System.out.println("\t\t\t------------------4、删除用户信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		switch (operate) {
			case "4":
				userView.showUserList();
				userView.deleteUser(sc);
				showUserView(sc);
				break;
			case "1":
				userView.showUserList();
				showUserView(sc);
				break;
			case "2":
				userView.addUser(sc);
				showUserView(sc);
				break;

			default:
				IndexView(sc);
				break;
		}
	}


}
