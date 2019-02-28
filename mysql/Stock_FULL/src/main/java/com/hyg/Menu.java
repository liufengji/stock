package com.hyg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KInventory;
import com.hyg.model.KOutstock;
import com.hyg.model.KOutstockDetail;
import com.hyg.model.KUser;
import com.hyg.service.DeptServiceI;
import com.hyg.service.GoodsServiceI;
import com.hyg.service.InstockDetailServiceI;
import com.hyg.service.InstockServiceI;
import com.hyg.service.InventoryServiceI;
import com.hyg.service.OutstockDetailServiceI;
import com.hyg.service.OutstockServiceI;
import com.hyg.service.ReportServiceI;
import com.hyg.service.StockServiceI;
import com.hyg.service.SupplierServiceI;
import com.hyg.service.UserServiceI;
import com.hyg.service.impl.DeptServiceImpl;
import com.hyg.service.impl.GoodsServiceImpl;
import com.hyg.service.impl.InstockDetailServiceImpl;
import com.hyg.service.impl.InstockServiceImpl;
import com.hyg.service.impl.InventoryServiceImpl;
import com.hyg.service.impl.OutstockDetailServiceImpl;
import com.hyg.service.impl.OutstockServiceImpl;
import com.hyg.service.impl.ReportServiceImpl;
import com.hyg.service.impl.StockServiceImpl;
import com.hyg.service.impl.SupplierServiceImpl;
import com.hyg.service.impl.UserServiceImpl;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class Menu {

	private KUser user;
	private UserServiceI userService = new UserServiceImpl();
	private StockServiceI stockService = new StockServiceImpl();
	private DeptServiceI deptService = new DeptServiceImpl();
	private GoodsServiceI goodsService = new GoodsServiceImpl();
	private SupplierServiceI supplierService = new SupplierServiceImpl();
	private InventoryServiceI inventoryService = new InventoryServiceImpl();
	private InstockServiceI instockService = new InstockServiceImpl();
	private InstockDetailServiceI instockDetailService = new InstockDetailServiceImpl();
	private OutstockServiceI outstockService = new OutstockServiceImpl();
	private OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
	private ReportServiceI reportService = new ReportServiceImpl();

	public static void main(String[] args) {
		Menu demo = new Menu();
		demo.loginView();
	}

	/**
	 * 登录界面
	 */
	public void loginView() {
		System.out.println(" ------------------欢迎来到库存管理系统--------------");
		System.out.println("------------------1、登录--------------");
		System.out.println("------------------2、退出--------------");
		// 获取控制台输入流
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要操作的步骤:");
		// 获取控制台输入的内容
		int opflag = sc.nextInt();

		// 进行判断
		if (opflag == 1) {
			System.out.println("进行登录操作");
			// login();
			user = userService.checkUser(sc);
			if (user == null) {
				System.out.println("用户名密码错误");
				loginView();
			} else {
				System.out.println("进入到首页");
				indexView(sc);
			}
		} else {
			System.out.println("本次系统退出，欢迎下次使用!");
		}
	}

	/**
	 * 首页视图
	 */
	private void indexView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("\t\t\t欢迎 [" + user.getUsername() + "]登录本系统!");
		System.out.println("\t\t\t--------请选择要操作的目录----------");
		System.out.println("\t\t\t--------1、库存管理----------");
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
		System.out.print("请选择要操作的功能：");
		String operate = sc.next();
		switch (operate) {
		case "1.1": // 库存查询
			inventoryService.searchInventory(sc);
			indexView(sc);
			break;
		case "1.2":// 入库管理
			showInstockView(sc);
			break;
		case "1.3":// 出库管理
			showOutstockView(sc);
			break;
		case "2.1":// 库存月报
			reportService.searchInventoryReport(sc);
			indexView(sc);
			break;
		case "2.2":// 物资台账
			System.out.println("物资台账查询  未完待续。。。。。");
			indexView(sc);
			break;
		case "3.1":// 用户管理
			showUserView(sc);
			break;
		case "3.2":// 部门管理
			showDeptView(sc);
			break;
		case "3.3":// 仓库管理
			showStockView(sc);
			break;
		case "3.4":// 供应商管理
			showSupplierView(sc);
			break;
		case "3.5":// 产品管理
			showGoodsView(sc);
			break;
		case "4":
			System.out.println("欢迎下次使用系统！");
			break;
		default:
			System.out
					.println("\t\t\t*********************标志位不能识别*****************");
			indexView(sc);
			break;
		}
	}

	/**
	 * 产品视图
	 * 
	 * @param sc
	 */
	private void showGoodsView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到产品管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看产品信息------------------");
		System.out
				.println("\t\t\t------------------2、添加产品信息------------------");
		System.out
				.println("\t\t\t------------------3、修改产品信息------------------");
		System.out
				.println("\t\t\t------------------4、删除产品信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":
			goodsService.searchGoods(sc);
			showGoodsView(sc);
			break;
		case "2":
			goodsService.addGoods(sc);
			showGoodsView(sc);
			break;
		case "3":
			System.out.println("请输入要修改的产品ID：");
			id = sc.next();
			goodsService.updateGoods(sc, id);
			showGoodsView(sc);
			break;
		case "4":
			System.out.println("请输入要删除的产品ID：");
			id = sc.next();
			goodsService.deleteGoods(sc, id);
			showGoodsView(sc);
			break;
		case "5":
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showGoodsView(sc);
			break;
		}
	}

	/**
	 * 供应商视图
	 * 
	 * @param sc
	 */
	private void showSupplierView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到供应商管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看供应商信息------------------");
		System.out
				.println("\t\t\t------------------2、添加供应商信息------------------");
		System.out
				.println("\t\t\t------------------3、修改供应商信息------------------");
		System.out
				.println("\t\t\t------------------4、删除供应商信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":
			supplierService.searchSupplier(sc);
			showSupplierView(sc);
			break;
		case "2":
			supplierService.addSupplier(sc);
			showSupplierView(sc);
			break;
		case "3":
			System.out.println("请输入要修改的供应商ID：");
			id = sc.next();
			supplierService.updateSupplier(sc, id);
			showSupplierView(sc);
			break;
		case "4":
			System.out.println("请输入要删除的供应商ID：");
			id = sc.next();
			supplierService.deleteSupplier(sc, id);
			showSupplierView(sc);
			break;
		case "5":
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showSupplierView(sc);
			break;
		}
	}

	/**
	 * 仓库视图
	 * 
	 * @param sc
	 */
	private void showStockView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到仓库管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看仓库信息------------------");
		System.out
				.println("\t\t\t------------------2、添加仓库信息------------------");
		System.out
				.println("\t\t\t------------------3、修改仓库信息------------------");
		System.out
				.println("\t\t\t------------------4、删除仓库信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":
			stockService.searchStock(sc);
			showStockView(sc);
			break;
		case "2":
			stockService.addStock(sc);
			showStockView(sc);
			break;
		case "3":
			System.out.println("请输入要修改的仓库ID：");
			id = sc.next();
			stockService.updateStock(sc, id);
			showStockView(sc);
			break;
		case "4":
			System.out.println("请输入要删除的仓库ID：");
			id = sc.next();
			stockService.deleteStock(sc, id);
			showStockView(sc);
			break;
		case "5":
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showStockView(sc);
			break;
		}
	}

	/**
	 * 出库视图
	 * 
	 * @param sc
	 */
	private void showOutstockView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到出库管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看出库单信息------------------");
		System.out
				.println("\t\t\t------------------2、添加出库单信息------------------");
		System.out
				.println("\t\t\t------------------3、修改出库单信息------------------");
		System.out
				.println("\t\t\t------------------4、删除出库单信息------------------");
		System.out
				.println("\t\t\t------------------5、添加出库单明细信息------------------");
		System.out
				.println("\t\t\t------------------6、修改出库单明细信息------------------");
		System.out
				.println("\t\t\t------------------7、删除出库单明细信息------------------");
		System.out
				.println("\t\t\t------------------8、执行出库记账操作------------------");
		System.out.println("\t\t\t------------------9、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":// 查看出库单信息
			// 查看主表信息
			outstockService.searchOutstock(sc);
			// 查看明细表信息
			System.out.println("是否要查看明细信息？(Y:是 N:否)");
			String flag = sc.next();
			if ("Y".equals(flag.toUpperCase())) {
				System.out.println("请选择要查看的出库申请单ID：");
				id = sc.next();

				KOutstockDetail[] outstockDetails = outstockDetailService
						.findOutstockDetailsByOutstockId(new BigDecimal(id));
				System.out
						.println("\tID\t出库单ID\t产品编码\t产品名称\t产品规格\t出库数量\t计量单位\t单价\t出库总金额\t备注");
				for (KOutstockDetail outstockDetail : outstockDetails) {
					System.out.println("\t" + outstockDetail.getId() + "\t"
							+ outstockDetail.getOutstockid() + "\t"
							+ outstockDetail.getProductno() + "\t"
							+ outstockDetail.getProductname() + "\t"
							+ outstockDetail.getProductstandard() + "\t"
							+ outstockDetail.getProductnum() + "\t"
							+ outstockDetail.getUnit() + "\t"
							+ outstockDetail.getPrice() + "\t"
							+ outstockDetail.getTotalprice() + "\t"
							+ outstockDetail.getRemark());
				}
			}
			showOutstockView(sc);
			break;
		case "2":// 添加出库单信息
			outstockService.addOutstock(sc);
			showOutstockView(sc);
			break;
		case "3":// 修改出库单信息
			System.out.println("请输入要修改的出库单ID：");
			id = sc.next();
			outstockService.updateOutstock(sc, id);
			showOutstockView(sc);
			break;
		case "4":// 删除出库单信息
			System.out.println("请输入要删除的出库单ID：");
			id = sc.next();
			outstockService.deleteOutstock(sc, id);
			showOutstockView(sc);
			break;
		case "5":// 添加产品明细
			outstockService.addOutstockDetail(sc);
			showOutstockView(sc);
		case "6":// 修改产品明细
			outstockService.updateOutstockDetail(sc);
			showOutstockView(sc);
			break;
		case "7":// 删除产品明细
			outstockService.deleteOutstockDetail(sc);
			showOutstockView(sc);
			break;
		case "8":// 执行出库入账操作
			outstockService.confirmReq(sc);
			showOutstockView(sc);
			break;
		case "9":// 返回上一级
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showOutstockView(sc);
			break;
		}
	}

	/**
	 * 部门管理视图
	 * 
	 * @param sc
	 */
	private void showDeptView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到部门管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看部门信息------------------");
		System.out
				.println("\t\t\t------------------2、添加部门信息------------------");
		System.out
				.println("\t\t\t------------------3、修改部门信息------------------");
		System.out
				.println("\t\t\t------------------4、删除部门信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":
			deptService.searchDept(sc);
			showDeptView(sc);
			break;
		case "2":
			deptService.addDept(sc);
			showDeptView(sc);
			break;
		case "3":
			System.out.println("请输入要修改的部门ID：");
			id = sc.next();
			deptService.updateDept(sc, id);
			showDeptView(sc);
			break;
		case "4":
			System.out.println("请输入要删除的部门ID：");
			id = sc.next();
			deptService.deleteDept(sc, id);
			showDeptView(sc);
			break;
		case "5":
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showDeptView(sc);
			break;
		}
	}

	/**
	 * 入库管理界面视图
	 * 
	 * @param sc
	 */
	private void showInstockView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到入库管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看入库单信息------------------");
		System.out
				.println("\t\t\t------------------2、添加入库单信息------------------");
		System.out
				.println("\t\t\t------------------3、修改入库单信息------------------");
		System.out
				.println("\t\t\t------------------4、删除入库单信息------------------");
		System.out
				.println("\t\t\t------------------5、添加入库单明细信息------------------");
		System.out
				.println("\t\t\t------------------6、修改入库单明细信息------------------");
		System.out
				.println("\t\t\t------------------7、删除入库单明细信息------------------");
		System.out
				.println("\t\t\t------------------8、执行入库记账操作------------------");
		System.out.println("\t\t\t------------------9、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":// 查看入库单信息
			// 查看主表信息
			instockService.searchInstock(sc);
			// // 查看明细表信息
			System.out.println("是否要查看明细信息？(Y:是 N:否)");
			String flag = sc.next();
			if ("Y".equals(flag.toUpperCase())) {
				instockDetailService.searchInstockDetails(sc);
			}
			showInstockView(sc);
			break;
		case "2":// 添加入库单信息
			instockService.addInstock(sc);
			showInstockView(sc);
			break;
		case "3":// 修改入库单信息
			System.out.println("请输入要修改的入库单ID：");
			id = sc.next();
			instockService.updateInstock(sc, id);
			showInstockView(sc);
			break;
		case "4":// 删除入库单信息
			System.out.println("请输入要删除的入库单ID：");
			id = sc.next();
			instockService.deleteInstock(sc, id);
			showInstockView(sc);
			break;
		case "5":// 添加产品明细
			System.out.println("请先输入要添加明细的入库单ID：");
			id = sc.next();
			instockService.addInstockDetails(sc, id);
			showInstockView(sc);
			break;
		case "6":// 修改产品明细
			System.out.println("请先输入要进行明细修改的入库单ID：");
			id = sc.next();
			instockService.updateInstockDetails(sc, id);

			showInstockView(sc);
			break;
		case "7":// 删除产品明细
			System.out.println("请先输入要进行明细删除操作的入库单ID：");
			id = sc.next();
			instockService.deleteInstockDetails(sc, id);
			showInstockView(sc);
			break;
		case "8":// 执行入库入账操作
			// 1、查看主表信息
			instockService.searchInstock(sc);
			instockService.confirmInstock(sc);
			showInstockView(sc);
			break;
		case "9":// 返回上一级
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showInstockView(sc);
			break;
		}
	}

	/**
	 * 用户界面视图
	 * 
	 * @param sc
	 */
	private void showUserView(Scanner sc) {
		// TODO Auto-generated method stub
		System.out
				.println("\t\t\t------------------欢迎到用户管理界面------------------");
		System.out
				.println("\t\t\t------------------1、查看用户信息------------------");
		System.out
				.println("\t\t\t------------------2、添加用户信息------------------");
		System.out
				.println("\t\t\t------------------3、修改用户信息------------------");
		System.out
				.println("\t\t\t------------------4、删除用户信息------------------");
		System.out.println("\t\t\t------------------5、返回上一级------------------");
		System.out.print("请输入要进行的操作:");
		String operate = sc.next();
		String id = "";
		switch (operate) {
		case "1":
			userService.searchUser(sc);
			showUserView(sc);
			break;
		case "2":
			sc = new Scanner(System.in);
			userService.addUser(sc);
			showUserView(sc);
			break;
		case "3":
			System.out.println("请输入要修改的用户ID：");
			id = sc.next();
			userService.updateUser(sc, id);
			showUserView(sc);
			break;
		case "4":
			System.out.println("请输入要删除的用户ID：");
			id = sc.next();
			userService.deleteUser(sc, id);
			showUserView(sc);
			break;
		case "5":
			indexView(sc);
			break;
		default:
			System.out.println("输入的操作标记位不正确，请检查!");
			showUserView(sc);
			break;
		}
	}

}
