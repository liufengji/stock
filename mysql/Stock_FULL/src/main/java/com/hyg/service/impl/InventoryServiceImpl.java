package com.hyg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.InventoryDaoI;
import com.hyg.factory.InventoryDaoFactory;
import com.hyg.model.KInventory;
import com.hyg.service.InventoryServiceI;
import com.hyg.util.StringUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class InventoryServiceImpl implements InventoryServiceI {
	private InventoryDaoI  inventoryDao;
	
	@Override
	public void searchInventory(Scanner sc) {
		// TODO Auto-generated method stub
		inventoryDao = InventoryDaoFactory.create();
		System.out.println("\t\t\t\t\t------------------欢迎到库存查询界面------------------");	
		System.out.println("是否要做条件查询？(Y:是 N:否)");
		List<String> paramList = new ArrayList<String>();
		
		String whereSql = "";
		
		String flag = sc.next();
		
		if ("Y".equals(flag.toUpperCase())) {
			System.out.println("请输入仓库ID：");
			String stockId = sc.nextLine();
			if (StringUtils.isNotEmpty(stockId)) {
				whereSql  += " AND STOCKID=? ";
				paramList.add(stockId);
			}
			System.out.println("请输入产品编码：");
			String productNo = sc.nextLine();
			if (StringUtils.isNotEmpty(productNo)) {
				whereSql += " AND PRODUCTNO=? ";
				paramList.add(productNo);
			}
			System.out.println("请输入产品名称：");
			String productName = sc.nextLine();
			if (StringUtils.isNotEmpty(productName)) {
				whereSql += " AND PRODUCTNAME LIKE ? ";
				paramList.add("'%" + productName + "%'");
			}
		}		
		KInventory[] kInventories = null;
		try {
			kInventories = inventoryDao.findByDynamicWhere(whereSql + " ORDER BY STOCKID", paramList.toArray());
			System.out.println("仓库ID\t\t产品编号\t\t产品名称\t\t产品规格\t\t库存量\t\t库存总金额\t\t最小值预警\t\t最大值预警");
			for (int i = 0; i < kInventories.length; i++) {
				KInventory inventory = kInventories[i];
				System.out.println(inventory.getStockid() + "\t\t" + inventory.getProductno() 
						+ "\t\t" + inventory.getProductname() + "\t\t" + inventory.getProductstandard()
						+ "\t\t" + inventory.getInstocknum() + "\t\t" + inventory.getInventoryprice() 
						+ "\t\t" + inventory.getMinnum() + "\t\t" + inventory.getMaxnum()
						);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveOrUpdate(Map<String, String> map) {
		// TODO Auto-generated method stub
		inventoryDao = InventoryDaoFactory.create();
		String whereSql = " AND STOCKID=? AND PRODUCTNO=? ";
		Object[] params = new Object[2];
		params[0] = map.get("stockId");
		params[1] = map.get("productNo");
		String flag = map.get("flag");
		BigDecimal productNum = new BigDecimal(map.get("productNum"));
		BigDecimal totalPrice = new BigDecimal(map.get("totalPrice"));
		KInventory[] inventory = null;
		KInventory kInventory = null;
		int count = 0;
		try {
			inventory = inventoryDao.findByDynamicWhere(whereSql, params);
			//如果根据仓库ID和产品编码不能查到库存信息，则进行增加操作
			if (inventory.length == 0 || inventory == null) {
				kInventory = new KInventory();
				kInventory.setId(new BigDecimal(inventoryDao.getSequences()));//创建id值
				kInventory.setStockid(new BigDecimal(map.get("stockId")));//仓库ID
				kInventory.setProductno(map.get("productNo"));//产品编码
				kInventory.setProductname(map.get("productName"));//产品名称
				kInventory.setProductstandard(map.get("productStandard"));//产品规格
				kInventory.setInventorynum(productNum);//库存量
				kInventory.setInventoryprice(totalPrice);//库存总金额
				if ("in".equals(flag)) {
					kInventory.setInstocknum(productNum);//入库量
					kInventory.setInstockprice(totalPrice);//入库总金额
					kInventory.setOutstocknum(new BigDecimal(0));//出库量
					kInventory.setOutstockprice(new BigDecimal(0));//出库总金额
				}else {
					kInventory.setInstocknum(new BigDecimal(0));//入库量
					kInventory.setInstockprice(new BigDecimal(0));//入库总金额
					kInventory.setOutstocknum(productNum);//出库量
					kInventory.setOutstockprice(totalPrice);//出库总金额
				}
				kInventory.setMaxnum(kInventory.getInventorynum().multiply(new BigDecimal(1.2)));//最大库存量
				kInventory.setMinnum(kInventory.getInstocknum().multiply(new BigDecimal(0.2)));//最小库存量
				count += inventoryDao.insert(kInventory);
			}else { //否则取出第一个库存明细，进行更新操作
				kInventory = inventory[0];
				if ("in".equals(flag)) {
					kInventory.setInstocknum(kInventory.getInstocknum().add(productNum));//入库总量 = 本次入库量+当前入库总量
					kInventory.setInstockprice(kInventory.getInstockprice().add(totalPrice));//入库总金额 = 本次入库总金额 + 当前入库总金额
				}else {
					kInventory.setOutstocknum(kInventory.getOutstocknum().add(productNum));
					kInventory.setOutstockprice(kInventory.getOutstockprice().add(totalPrice));
				}
				
				kInventory.setInventorynum(kInventory.getInstocknum().subtract(kInventory.getOutstocknum()));//库存量 = 入库总量 -出库总量
				kInventory.setInventoryprice(kInventory.getInventoryprice().subtract(kInventory.getOutstockprice()));//库存金额 = 入库总金额-出库总金额
				count += inventoryDao.update(kInventory.getId(), kInventory);
			}
			
			if (count > 0) {
				System.out.println("恭喜成功保存了[" + count + "]条数据！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showGoodsListByStockId(BigDecimal stockid) {
		// TODO Auto-generated method stub
		inventoryDao = InventoryDaoFactory.create();
		String whereSql  = " AND STOCKID =? ";
		Object[] params = new Object[1];
		params[0] = stockid;
		KInventory[] inventorys = null;
		try {
			inventorys = inventoryDao.findByDynamicWhere(whereSql, params);
			System.out.println("ID\t\t产品编号\t\t产品名称\t\t产品规格\t\t库存量\t\t库存总金额");
			for (int i = 0; i < inventorys.length; i++) {
				KInventory kInventory = inventorys[i];
				System.out.println(kInventory.getId() + "\t\t" + kInventory.getProductno() 
						+ "\t\t" + kInventory.getProductname() + "\t\t" + kInventory.getProductstandard()
						+ "\t\t" + kInventory.getInventorynum() + "\t\t" + kInventory.getInventoryprice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public KInventory findKInventoryById(String id) {
		// TODO Auto-generated method stub
		inventoryDao = InventoryDaoFactory.create();
		KInventory inventory =  null;
		try {
			inventory = inventoryDao.findByPrimaryKey(new BigDecimal(id));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventory;
	}

	
	
}
