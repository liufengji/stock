package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.OutstockDaoI;
import com.hyg.factory.OutstockDaoFactory;
import com.hyg.model.KInventory;
import com.hyg.model.KOutstock;
import com.hyg.model.KOutstockDetail;
import com.hyg.service.InventoryServiceI;
import com.hyg.service.OutstockDetailServiceI;
import com.hyg.service.OutstockServiceI;
import com.hyg.util.DateUtils;
import com.hyg.util.StringUtils;

public class OutstockServiceImpl implements OutstockServiceI {
	private OutstockDaoI outStockDao;

	@Override
	public void searchOutstock(Scanner sc) {
		// TODO Auto-generated method stub
		KOutstock[] outStocks = null;
		outStockDao = OutstockDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入出库申请单编号：");
				String oustockNo = sc.nextLine();
				System.out.println("请输入仓库ID:");
				String stockId = sc.nextLine();
				System.out.println("请输入出库日期");
				String oustockDate = sc.nextLine();
				String  whereSql = " ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(oustockNo)) {
					whereSql += " and OUTSTOCKNO like ?";
					param.add( "%" + oustockNo + "%");
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " and STOCKID like ?";
					param.add(stockId);
				}
				if (StringUtils.isNotEmpty(oustockDate)) {
					whereSql += " and OUTSTOCKDATE = ?";
					param.add(oustockDate);
				}
				outStocks = outStockDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
				//查询所有出库单信息
				outStocks = outStockDao.findAll();
			}
			System.out.println("\t\tID\t\t出库单编号\t\t\t\t出库仓库ID\t\t供应商ID\t\t出库单状态"
					+ "\t\t出库日期\t\t\t\t备注");
			for (KOutstock outStock : outStocks) {
				String instockState = outStock.getOutstockstate();
				if ("00".equals(instockState)) {
					instockState = "创建";
				}else if ("02".equals(instockState)) {
					instockState = "记账";
				}
				System.out.println("\t\t" + outStock.getId()+ "\t\t" + outStock.getOutstockno()+ "\t\t" + outStock.getStockid()
						+ "\t\t" + outStock.getSupplierid() + "\t\t" + instockState
						+ "\t\t" + DateUtils.formatDate("yyyy-MM-dd",outStock.getOutstockdate()) + "\t\t" + outStock.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addOutstock(Scanner sc) {
		// TODO Auto-generated method stub
		outStockDao = OutstockDaoFactory.create();
		KOutstock outStock = new KOutstock();
		try {
			
			outStock.setId(new BigDecimal(outStockDao.getSequences()));
			outStock.setOutstockno("RK" + DateUtils.getDate14());
			outStock.setOutstockstate("00");
			System.out.println("请输入出库的仓库ID:");
			outStock.setStockid(new BigDecimal(sc.next()));
			System.out.println("请输入出库的供应商ID:");
			outStock.setSupplierid(new BigDecimal(sc.next()));
			System.out.println("请输入出库日期格式为(yyyy-MM-dd):");
			String date = sc.next();
			outStock.setOutstockdate(DateUtils.toDate("yyyy-MM-dd", date));
			System.out.println("请输入备注:");
			outStock.setRemark(sc.nextLine());
			int rows = outStockDao.insert(outStock);
			if (rows > 0 ) {
				System.out.println("出库单主信息保存成功!");
				KOutstock outStockDB = outStockDao.findByPrimaryKey(outStock.getId());
				String instockState = outStockDB.getOutstockstate();
				if ("00".equals(instockState)) {
					instockState = "创建";
				}else if ("01".equals(instockState)) {
					instockState = "记账";
				}
				System.out.println("\t\tID\t\t出库单编号\t\t\t\t出库仓库ID\t\t供应商ID\t\t出库单状态"
						+ "\t\t出库日期\t\t\t\t备注");
				System.out.println("\t\t" + outStockDB.getId()+ "\t\t" + outStockDB.getOutstockno()+ "\t\t" + outStockDB.getStockid()
						+ "\t\t" + outStockDB.getSupplierid() + "\t\t" + instockState
						+ "\t\t" + DateUtils.formatDate("yyyy-MM-dd",outStockDB.getOutstockdate()) + "\t\t" + outStockDB.getRemark());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateOutstock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		outStockDao = OutstockDaoFactory.create();
		try {
			KOutstock outStock = outStockDao.findByPrimaryKey(new BigDecimal(id));
			if (outStock == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			
			if (!"00".equals(outStock.getOutstockstate())) {
				throw new Exception("ID为[" + id +"]的记录出库单状态不是【创建】无法进行修改操作，请检查!");
			}
			System.err.println("请输入新的仓库ID:当前为(" + outStock.getStockid() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				outStock.setStockid(new BigDecimal(temp));
			}
			System.err.println("请输入新的仓供应商ID:当前为(" + outStock.getSupplierid() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				outStock.setSupplierid(new BigDecimal(temp));
			}
			System.err.println("请输入新的出库日期（yyyy-MM-dd）:当前为(" + outStock.getOutstockdate() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				outStock.setOutstockdate(DateUtils.toDate("yyyy-MM-dd", temp));
			}
			System.err.println("请输入新的备注:当前为(" + outStock.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				outStock.setRemark(temp);
			}
			int rows  = outStockDao.update(new BigDecimal(id), outStock);
			if (rows > 0 ) {
				System.out.println("出库单主信息修改成功!");
				KOutstock outStockDB = outStockDao.findByPrimaryKey(outStock.getId());
				String instockState = outStockDB.getOutstockstate();
				if ("00".equals(instockState)) {
					instockState = "创建";
				}else if ("02".equals(instockState)) {
					instockState = "记账";
				}
				System.out.println("\t\tID\t\t出库单编号\t\t\t\t出库仓库ID\t\t供应商ID\t\t出库单状态"
						+ "\t\t出库日期\t\t\t\t备注");
				System.out.println("\t\t" + outStockDB.getId()+ "\t\t" + outStockDB.getOutstockno()+ "\t\t" + outStockDB.getStockid()
						+ "\t\t" + outStockDB.getSupplierid() + "\t\t" + instockState
						+ "\t\t" + DateUtils.formatDate("yyyy-MM-dd",outStockDB.getOutstockdate()) + "\t\t" + outStockDB.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOutstock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		outStockDao = OutstockDaoFactory.create();
		try {
//			outStockDetailService 
//			outStockDetail[] outstockDetail    ;
			KOutstock outstock = outStockDao.findByPrimaryKey(new BigDecimal(id));
			
			if (outstock == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			
			if (!"00".equals(outstock.getOutstockstate())) {
				throw new Exception("ID为[" + id +"]的记录出库单状态不是【创建】无法进行修删除操作，请检查!");
			}
			
			//删除明细信息
			OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
			KOutstockDetail[] outstockDetails = outstockDetailService
					.findOutstockDetailsByOutstockId(outstock.getId());
			
			for (int i = 0; i < outstockDetails.length; i++) {
				outstockDetailService.deleteDetailById(outstockDetails[i].getId());
			}
			
			//删除主单信息
			int rows = outStockDao.delete(new BigDecimal(id));
			if (rows > 0 ) {
				System.out.println("出库单主信息删除成功!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public KOutstock findOutstockById(String id) {
		// TODO Auto-generated method stub
		outStockDao = OutstockDaoFactory.create();
		KOutstock outStock = null;
		try {
			outStock = outStockDao.findByPrimaryKey(new BigDecimal(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outStock;
	}

	@Override
	public void confirmReq(Scanner sc) {
		// 1、查看主表信息
		this.searchOutstock(sc);

		System.out.println("请输入要进行【记账】操作的出库单ID：");
		String outstockId = sc.next();
		// 2校验出库单ID是否存在
		KOutstock outStock;
		try {
			outStock = outStockDao.findByPrimaryKey(new BigDecimal(outstockId));
		
			if (outStock == null) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单不存在，请检查！");
				return;
	
			} else if ("02".equals(outStock.getOutstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单状态不是【新建】，请检查！");
				return;
			}
			// 3执行记账操作
			//1、更新库存表中对应的产品库存量
			//3.1 根据仓库ID和产品明细编号，到库存表中查询是否已经存在库存记录，如果存在记录，则进行更新操作，否则添加一条记录
			OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
			InventoryServiceI inventoryService = new InventoryServiceImpl();
			Map<String,String> map = new HashMap<String,String>();
	
			KOutstockDetail[] outstockDetails = outstockDetailService.findOutstockDetailsByOutstockId(outStock.getId());
			if (outstockDetails.length <= 0) {
				System.err.println("申请单编号为[" + outStock.getOutstockno() + "]的记录没有任何产品明细记录，该申请单无效，请检查！");
				return;
			}
			BigDecimal stockId = outStock.getStockid();//获取仓库ID
			for (int i = 0; i < outstockDetails.length; i++) {
				KOutstockDetail outstockDetail = outstockDetails[i];
				map.put("stockId", stockId.toString());
				map.put("productNo", outstockDetail.getProductno());
				map.put("productName", outstockDetail.getProductname());
				map.put("productStandard", outstockDetail.getProductstandard());
				map.put("productNum", outstockDetail.getProductnum().toString());
				map.put("totalPrice", outstockDetail.getTotalprice().toString());
				map.put("flag", "out");
				inventoryService.saveOrUpdate(map);
			}
			//3.2、改变出库申请单的状态 创建-->记账
			
			outStock.setOutstockstate("02");
			int count = outStockDao.update(outStock.getId(), outStock);
			if (count > 0 ) {
				System.out.println("恭喜您，【记账】操作成功!");
			}
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addOutstockDetail(Scanner sc) {
		// TODO Auto-generated method stub
		
		System.out.println("请先输入要添加明细的出库单ID：");
		String outstockId = sc.next();
		// 校验ID库入单是否存在
		KOutstock kOutstock;
		try {
			kOutstock = outStockDao.findByPrimaryKey(new BigDecimal(outstockId));
		
			if (kOutstock == null) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单不存在，请检查！");
				return;
			} else if ("02".equals(kOutstock.getOutstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单状态不是【新建】，请检查！");
				return;
			}
			System.out.println("可用产品列表如下：");
			// 显示可用产品列表
			InventoryServiceI inventoryService = new InventoryServiceImpl();
			inventoryService.showGoodsListByStockId(kOutstock.getStockid());
			System.out.println("请输入要添加的库存ID及数量，以ID1-NUM1,ID2-NUM2的格式输入：");
			String goods = sc.next();// 数据要添加的产品列表
			List<Map> inventoryList = new ArrayList<Map>();
			// 获取产品列表
			String[] goodsArr = goods.split(",");
			for (int i = 0; i < goodsArr.length; i++) {
				String[] goodInfo = goodsArr[i].split("-");
				KInventory kInventory = inventoryService
						.findKInventoryById(goodInfo[0]);
				if (kInventory == null) {
					System.err.println("您输入的库存ID[" + goodInfo[0] + "]无效！请检查!");
					return;
				} else {
					if (kInventory.getInstocknum().compareTo(
							new BigDecimal(goodInfo[1])) < 0) {
						System.err.println("您输入的库存ID[" + goodInfo[0]
								+ "]的出库量大于库存量无效！请检查!");
						return;
					}
					Map map = new HashMap();
					map.put("inventory", kInventory);// 库存产品信息
					map.put("num", goodInfo[1]);// 出库数量
					inventoryList.add(map);
				}
			}
			OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
			outstockDetailService.addOutstockDetail(outstockId, inventoryList);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateOutstockDetail(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("请先输入要进行明细修改的出库单ID：");
		String outstockId = sc.next();
		// 校验入单库ID是否存在
		KOutstock kOutstockDB;
		try {
			kOutstockDB = outStockDao.findByPrimaryKey(new BigDecimal(outstockId));
		
			if (kOutstockDB == null) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单不存在，请检查！");
				return;
			} else if ("02".equals(kOutstockDB.getOutstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单状态不是【新建】，请检查！");
				return;
			}
			// 根据出库单ID到出库明细表中查询产品信息
			OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
			KOutstockDetail[] outstockDetails = outstockDetailService
					.findOutstockDetailsByOutstockId(kOutstockDB.getId());
			System.out
					.println("\tID\t出库单ID\t产品编码\t产品名称\t产品规格\t出库数量\t单价\t出库总金额");
			for (KOutstockDetail outstockDetail : outstockDetails) {
				System.out.println("\t" + outstockDetail.getId() + "\t"
						+ outstockDetail.getOutstockid() + "\t"
						+ outstockDetail.getProductno() + "\t"
						+ outstockDetail.getProductname() + "\t"
						+ outstockDetail.getProductstandard() + "\t"
						+ outstockDetail.getProductnum() + "\t"
						+ outstockDetail.getPrice() + "\t"
						+ outstockDetail.getTotalprice());
			}
			System.out.println("请选择需要修改的产品明细ID：");
			String detailId = sc.next();
			KOutstockDetail outstockDetail = outstockDetailService
					.findOutstockDetailsById(detailId);
			if (outstockDetail == null) {
				System.out.println("产品明细ID为[" + outstockDetail + "]的记录不存在，请检查！");
				return;
			}
	
			System.out.println("请输入修改后的出库数量:");
			String num = sc.next();
			// 更新出库数量
			outstockDetail.setProductnum(new BigDecimal(num));
			// 更新出库总金额
			outstockDetail.setTotalprice(outstockDetail.getProductnum()
					.multiply(outstockDetail.getPrice()));
			// 执行修改操作
			outstockDetailService.updateDetail(outstockDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOutstockDetail(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("请先输入要进行明细删除操作的出库单ID：");
		String outstockId = sc.next();
		// 校验入单库ID是否存在
		KOutstock outstock;
		try {
			outstock = outStockDao.findByPrimaryKey(new BigDecimal(outstockId));
		
			if (outstock == null) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单不存在，请检查！");
				return;
			} else if ("02".equals(outstock.getOutstockstate())) {
				System.out.println("\t\t\t-------------------ID号为[" + outstockId
						+ "]的出库单状态不是【新建】，请检查！");
				return;
			}
			// 根据出库单ID到出库明细表中查询产品信息
			OutstockDetailServiceI outstockDetailService = new OutstockDetailServiceImpl();
			KOutstockDetail[] outstockDetailsDB = outstockDetailService
					.findOutstockDetailsByOutstockId(outstock.getId());
			System.out
					.println("\tID\t出库单ID\t产品编码\t产品名称\t产品规格\t出库数量\t单价\t出库总金额");
			for (KOutstockDetail outstockDetail1 : outstockDetailsDB) {
				System.out.println("\t" + outstockDetail1.getId() + "\t"
						+ outstockDetail1.getOutstockid() + "\t"
						+ outstockDetail1.getProductno() + "\t"
						+ outstockDetail1.getProductname() + "\t"
						+ outstockDetail1.getProductstandard() + "\t"
						+ outstockDetail1.getProductnum() + "\t"
						+ outstockDetail1.getPrice() + "\t"
						+ outstockDetail1.getTotalprice());
			}
			// 输入要进行删除的产品ID
			System.out.println("请输入要删除的产品明细ID(以,割开)：");
			String ids = sc.next();
			String[] idArr = ids.split(",");
			outstockDetailService.deleteDetailsByIds(idArr);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
