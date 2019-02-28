package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.InstockDaoI;
import com.hyg.dao.impl.InstockDaoImpl;
import com.hyg.factory.InstockDaoFactory;
import com.hyg.model.KGoods;
import com.hyg.model.KInstock;
import com.hyg.model.KInstockDetail;
import com.hyg.service.GoodsServiceI;
import com.hyg.service.InstockDetailServiceI;
import com.hyg.service.InstockServiceI;
import com.hyg.service.InventoryServiceI;
import com.hyg.util.DateUtils;
import com.hyg.util.StringUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class InstockServiceImpl implements InstockServiceI {
	private InstockDaoI instockDao;
	@Override
	public void searchInstock(Scanner sc) {
		// TODO Auto-generated method stub
		//先查询主表中的数据
		KInstock[] kInstocks = null;
		instockDao = InstockDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入入库申请单编号：");
				String instockNo = sc.nextLine();
				System.out.println("请输入仓库ID:");
				String stockId = sc.nextLine();
				System.out.println("请输入入库日期");
				String instockDate = sc.nextLine();
				String  whereSql = " 1=1 ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(instockNo)) {
					whereSql += " and INSTOCKNO like ?";
					param.add( "%" + instockNo + "%");
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " and STOCKID like ?";
					param.add(stockId);
				}
				if (StringUtils.isNotEmpty(instockDate)) {
					whereSql += " and INSTOCKDATE = ?";
					param.add(instockDate);
				}
				kInstocks = instockDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
				//查询所有入库单信息
				kInstocks = instockDao.findAll();
			}
			System.out.println("\tID\t入库单编号\t\t入库仓库ID\t供应商ID\t入库单状态"
					+ "\t入库日期\t\t备注");
			for (KInstock kInstock : kInstocks) {
				String instockState = kInstock.getInstockstate();
				if ("00".equals(instockState)) {
					instockState = "创建";
				}else if ("02".equals(instockState)) {
					instockState = "记账";
				}
				System.out.println("\t" + kInstock.getId()+ "\t" + kInstock.getInstockno()+ "\t" + kInstock.getStockid()
						+ "\t" + kInstock.getSupplierid() + "\t" + instockState
						+ "\t" + DateUtils.formatDate("yyyy-MM-dd",kInstock.getInstockdate()) + "\t" + kInstock.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//然后根据提示查询明细表中的数据
	}

	@Override 	
	public void addInstock(Scanner sc) {
		//初始化instockDao
		instockDao = InstockDaoFactory.create();
		System.out.println("请输入入库的仓库ID:");
		String stockId = sc.next();
		System.out.println("请输入入库的供应商ID:");
		String supplierId = sc.next();
		System.out.println("请输入入库日期格式为(yyyy-MM-dd):");
		String date = sc.next();
		System.out.println("请输入备注:");
		String remark = sc.nextLine();
		
		KInstock instock = new KInstock();
		try {
			instock.setId(new BigDecimal(instockDao.getSequences()));
			instock.setInstockno("RK" + DateUtils.getDate14());
			instock.setStockid(new BigDecimal(stockId));
			instock.setSupplierid(new BigDecimal(supplierId));
			//设置入库单状态为新建
			instock.setInstockstate("00");
			instock.setInstockdate(DateUtils.toDate("yyyy-MM-dd", date));
			instock.setRemark(remark);
			int rows = instockDao.insert(instock);
			if (rows == 0) {
				System.out.println("对不起，保存入库单主单信息失败!");
			}else{
				System.out.println("恭喜你，保存入库单主单信息成功！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateInstock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		instockDao = InstockDaoFactory.create();
		try {
			KInstock kInstock = instockDao.findByPrimaryKey(new BigDecimal(id));
			if (kInstock == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			
			if (!"00".equals(kInstock.getInstockstate())) {
				throw new Exception("ID为[" + id +"]的记录入库单状态不是【创建】无法进行修改操作，请检查!");
			}
			System.err.println("请输入新的仓库ID:当前为(" + kInstock.getStockid() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				kInstock.setStockid(new BigDecimal(temp));
			}
			System.err.println("请输入新的仓供应商ID:当前为(" + kInstock.getSupplierid() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				kInstock.setSupplierid(new BigDecimal(temp));
			}
			System.err.println("请输入新的入库日期（yyyy-MM-dd）:当前为(" + kInstock.getInstockdate() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				kInstock.setInstockdate(DateUtils.toDate("yyyy-MM-dd", temp));
			}
			System.err.println("请输入新的备注:当前为(" + kInstock.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				kInstock.setRemark(temp);
			}
			int rows  = instockDao.update(new BigDecimal(id), kInstock);
			if (rows > 0 ) {
				System.out.println("入库单主信息修改成功!");
				KInstock instock = instockDao.findByPrimaryKey(kInstock.getId());
				String instockState = instock.getInstockstate();
				if ("00".equals(instockState)) {
					instockState = "创建";
				}else if ("01".equals(instockState)) {
					instockState = "记账";
				}
				System.out.println("\tID\t入库单编号\t\t入库仓库ID\t供应商ID\t入库单状态"
						+ "\t入库日期\t\t备注");
				System.out.println("\t" + instock.getId()+ "\t" + instock.getInstockno()+ "\t" + instock.getStockid()
						+ "\t" + instock.getSupplierid() + "\t" + instockState
						+ "\t" + DateUtils.formatDate("yyyy-MM-dd",instock.getInstockdate()) + "\t" + instock.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInstock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		instockDao = InstockDaoFactory.create();
		try {
			//判断ID为XX的记录是否合法
			KInstock kInstock = instockDao.findByPrimaryKey(new BigDecimal(id));
			if (kInstock == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			if (!"00".equals(kInstock.getInstockstate())) {
				throw new Exception("ID为[" + id +"]的记录入库单状态不是【创建】无法进行修改操作，请检查!");
			}
			//先删除明细表中的数据
			InstockDetailServiceI instockDetailServiceI = new InstockDetailServiceImpl();
			int detailRows = instockDetailServiceI.deleteDetailsByInstockId(id);
			System.out.println("一共删除了[" + detailRows +"]条明细信息！");
			//然后再删除主表中的数据
			int rows = instockDao.delete(new BigDecimal(id));
			System.out.println("一共删除了["+rows+"]条主单信息！");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 记账操作：
	 *	1、根据明细信息到库存表中进行查询，
	 *  (1) 看看库存ID为XX，产品编号为YY的记录是否存在
	 *  (2) 如果记录存在，则在此记录库存数量的基础上进行累加
	 *  (3) 如果记录不存在，则去创建一条记录，并保存到库存表中
	 *  2、将主表的状态从创建-->记账
	 */
	public void confirmInstock(Scanner sc) {
		// TODO Auto-generated method stub
		InstockDetailServiceI instockDetailService = new InstockDetailServiceImpl();
		InventoryServiceI inventoryService = new InventoryServiceImpl();
		Map<String,String> map = new HashMap<String,String>();

		System.out.println("请输入要进行【记账】操作的入库单ID：");
		String id = sc.next();
		// 2校验入单库ID是否存在
		KInstock instock = null;
		try {
			instock = instockDao.findByPrimaryKey(new BigDecimal(id));
		
			if (instock == null) {
				System.out.println("\t\t\t-------------------ID号为[" + id
						+ "]的入库单不存在，请检查！");
				return;
	
			} else if ("02".equals(instock.getInstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + id
						+ "]的入库单状态不是【新建】，请检查！");
				return;
			}
			
			KInstockDetail[] instockDetails = instockDetailService.findInstockDetailsByInstockId(instock.getId().toString());
			if (instockDetails.length <= 0) {
				System.err.println("申请单编号为[" + instock.getInstockno() + "]的记录没有任何产品明细记录，该申请单无效，请检查！");
				return;
			}
			BigDecimal stockId = instock.getStockid();//获取仓库ID
			for (int i = 0; i < instockDetails.length; i++) {
				KInstockDetail instockDetail = instockDetails[i];
				map.put("stockId", stockId.toString());
				map.put("productNo", instockDetail.getProductno());
				map.put("productName", instockDetail.getProductname());
				map.put("productStandard", instockDetail.getProductstandard());
				map.put("productNum", instockDetail.getProductnum().toString());
				map.put("totalPrice", instockDetail.getTotalprice().toString());
				map.put("flag", "in");
				inventoryService.saveOrUpdate(map);
			}
			//2、改变入库申请单的状态 创建-->记账
		
			instock.setInstockstate("02");
			int count = instockDao.update(instock.getId(), instock);
			if (count > 0 ) {
				System.out.println("恭喜您，【记账】操作成功!");
			}
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addInstockDetails(Scanner sc, String id) {
		// TODO Auto-generated method stub
		instockDao = InstockDaoFactory.create();
		//1.校验输入的入库单是否有效
		// 校验ID库入单是否存在
		KInstock kInstock;
		try {
			kInstock = instockDao.findByPrimaryKey(new BigDecimal(id));
			if (kInstock == null) {
				System.out.println("\t\t\t-------------------ID号为[" + id
						+ "]的入库单不存在，请检查！");
				return;
			} else if ("02".equals(kInstock.getInstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + id
						+ "]的入库单状态不是【新建】，请检查！");
				return;
			}
			//2、显示可以进行入库操作的产品列表
			GoodsServiceI goodsService = new GoodsServiceImpl();
			System.out.println("可用产品列表如下：");
			// 显示可用产品列表
			goodsService.findGoodsList();
			System.out.println("请输入要添加的产品ID及数量，以ID1-NUM1[-REMARK1],ID2-NUM2[-REMARK2]的格式输入：");
			String goods = sc.next();// 数据要添加的产品列表
			List<Map> goodsList = new ArrayList<Map>();
			// 获取产品列表
			String[] goodsArr = goods.split(",");
			for (int i = 0; i < goodsArr.length; i++) {
				String[] goodInfo = goodsArr[i].split("-");
				KGoods good = goodsService.findGoodsByID(goodInfo[0]);
				if (good == null) {
					System.err.println("您输入的产品ID[" + goodInfo[0] + "]无效！请检查!");
					return;
				} else {
					Map map = new HashMap();
					map.put("goods", good);// 产品信息
					map.put("num", goodInfo[1]);// 入库数量
					map.put("remark", goodInfo.length == 3 ? goodInfo[2] : "");// 备注
					goodsList.add(map);
				}
			}
			//3.调用明细的服务类，进行添加操作
			InstockDetailServiceI instockDetailService = new InstockDetailServiceImpl();
			instockDetailService.addInstockDetail(sc, id, goodsList);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateInstockDetails(Scanner sc, String id) {
		// TODO Auto-generated method stub
		instockDao = InstockDaoFactory.create();
		// 1.校验入单库ID是否存在
		 KInstock instock;
		try {
			instock = instockDao.findByPrimaryKey(new BigDecimal(id));
			 if (instock == null) {
			 System.out.println("\t\t\t-------------------ID号为[" + id
			 + "]的入库单不存在，请检查！");
			 return;
			 } else if ("02".equals(instock.getInstockstate())) {
			 System.out.println("\t\t\t-------------------ID号为[" + id
			 + "]的入库单状态不是【新建】，请检查！");
			 return;
			 }
			 // 2.根据入库单ID到入库明细表中查询产品信息
			 InstockDetailServiceI instockDetailService = new InstockDetailServiceImpl();
			 KInstockDetail[] instockDetails = instockDetailService.findInstockDetailsByInstockId(instock.getId().toString());
			 System.out.println("\tID\t入库单ID\t产品编码\t产品名称\t产品规格\t入库数量\t计量单位\t单价\t入库总金额\t备注");
			 for (KInstockDetail kInstockDetail : instockDetails) {
			 System.out.println("\t" + kInstockDetail.getId() + "\t"
			 + kInstockDetail.getInstockid() + "\t"
			 + kInstockDetail.getProductno() + "\t"
			 + kInstockDetail.getProductname() + "\t"
			 + kInstockDetail.getProductstandard() + "\t"
			 + kInstockDetail.getProductnum() + "\t"
			 + kInstockDetail.getUnit() + "\t"
			 + kInstockDetail.getPrice() + "\t"
			 + kInstockDetail.getTotalprice() + "\t"
			 + kInstockDetail.getRemark());
			 }
			 System.out.println("请选择需要修改的产品明细ID：");
			 id = sc.next();
			 KInstockDetail instockDetail = instockDetailService.findInstockDetailsById(id);
			 if (instockDetail == null) {
			 System.out.println("产品明细ID为[" + instockDetail + "]的记录不存在，请检查！");
			 return;
			 }
			//3.更新入库明细的数量，金额等信息
			 System.out.println("请输入修改后的入库数量:");
			 String num = sc.next();
			 // 更新入库数量
			 instockDetail.setProductnum(new BigDecimal(num));
			 // 更新入库总金额
			 instockDetail.setTotalprice(instockDetail.getProductnum().multiply(
			 instockDetail.getPrice()));
			 // 执行修改操作
			 instockDetailService.updateDetail(instockDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInstockDetails(Scanner sc, String id) {
		// TODO Auto-generated method stub
		instockDao = InstockDaoFactory.create();
		//1.校验入单库ID是否存在
		 KInstock instock;
		try {
			instock = instockDao.findByPrimaryKey(new BigDecimal(id));
			 if (instock == null) {
			 System.out.println("\t\t\t-------------------ID号为[" + id
			 + "]的入库单不存在，请检查！");
			 return;
			 } else if ("02".equals(instock.getInstockstate())) {
			 System.out.println("\t\t\t-------------------ID号为[" + id
			 + "]的入库单状态不是【新建】，请检查！");
			 return;
			 }
			 //2.根据入库单ID到入库明细表中查询产品信息
			 InstockDetailServiceI instockDetailService = new InstockDetailServiceImpl();
			 KInstockDetail[] instockDetailsDB = instockDetailService.findInstockDetailsByInstockId(instock.getId().toString());
			 System.out.println("\tID\t入库单ID\t产品编码\t产品名称\t产品规格\t入库数量\t计量单位\t单价\t入库总金额\t备注");
			 for (KInstockDetail kInstockDetail : instockDetailsDB) {
			 System.out.println("\t" + kInstockDetail.getId() + "\t"
			 + kInstockDetail.getInstockid() + "\t"
			 + kInstockDetail.getProductno() + "\t"
			 + kInstockDetail.getProductname() + "\t"
			 + kInstockDetail.getProductstandard() + "\t"
			 + kInstockDetail.getProductnum() + "\t"
			 + kInstockDetail.getUnit() + "\t"
			 + kInstockDetail.getPrice() + "\t"
			 + kInstockDetail.getTotalprice() + "\t"
			 + kInstockDetail.getRemark());
			 }
			 // 输入要进行删除的产品ID
			 System.out.println("请输入要删除的产品明细ID(以,割开)：");
			 String ids = sc.next();
			 String[] idArr = ids.split(",");
			 instockDetailService.deleteDetailsByIds(idArr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

}
