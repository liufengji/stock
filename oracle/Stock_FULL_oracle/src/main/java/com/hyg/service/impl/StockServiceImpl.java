package com.hyg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hyg.dao.StockDaoI;
import com.hyg.factory.StockDaoFactory;
import com.hyg.model.KStock;
import com.hyg.service.StockServiceI;
import com.hyg.util.StringUtils;

public class StockServiceImpl implements StockServiceI {
	private StockDaoI stockDao;
	@Override
	public void searchStock(Scanner sc) {
		// TODO Auto-generated method stub
		KStock[] kStock = null;
		
		stockDao = StockDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {	
				System.out.println("请输入仓库编码：");
				String stockNo = sc.nextLine();
				System.out.println("请输入仓库名称:");
				String stockName = sc.nextLine();
				System.out.println("请输入仓库所在地区");
				String province = sc.nextLine();
			
				String  whereSql = " 1=1 ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(stockNo)) {
					whereSql += " and STOCKNO = ?";
					param.add(stockNo);
				}
				if (StringUtils.isNotEmpty(stockName)) {
					whereSql += " and STOCKNAME like ?";
					param.add("%" + stockName + "%");
				}
				if (StringUtils.isNotEmpty(province)) {
					whereSql += " and PROVINCE = ?";
					param.add(province);
				}
				kStock = stockDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
				//查询所有仓库
				kStock = stockDao.findAll();
			}
			System.out.println("\t\tID\t\t仓库编号\t\t\t\t仓库名称\t\t\t\t所在地区\t\t联系方式\t\t详细地址\t\t\t\t备注");
			for (KStock stock : kStock) {
				System.out.println("\t\t" + stock.getId() + "\t\t" + stock.getStockno() + "\t\t\t\t" 
						+ stock.getStockname()+ "\t\t\t\t" + stock.getProvince()
						+ "\t\t" + stock.getStocktel() + "\t\t" + stock.getStockaddress() 
						+ "\t\t" + stock.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addStock(Scanner sc) {
		// TODO Auto-generated method stub
		stockDao = StockDaoFactory.create();
		KStock kStock = new KStock();
		try {
			kStock.setId(new BigDecimal(stockDao.getSequences()));
			System.out.println("请输入仓库编码：");
			kStock.setStockno(sc.next());
			System.out.println("请输入仓库名称:");
			kStock.setStockname(sc.next());
			System.out.println("请输入仓库所在省份:");
			kStock.setProvince(sc.next());
			System.out.println("请输入仓库联系方式:");
			kStock.setStocktel(sc.next());
			System.out.println("请输入仓库详细地址:");
			kStock.setStockaddress(sc.next());
			System.out.println("请输入备注");
			kStock.setRemark(sc.nextLine());
			
			int rows = stockDao.insert(kStock);
			if (rows > 0) {
				System.out.println("添加仓库成功！");
				KStock stockDB = stockDao.findByPrimaryKey(kStock.getId());
				System.out.println("\t\tID\t\t仓库编号\t\t\t\t仓库名称\t\t\t\t所在地区\t\t联系方式\t\t详细地址\t\t\t\t备注");
				System.out.println("\t\t" + stockDB.getId() + "\t\t" + stockDB.getStockno() + "\t\t\t\t" 
						+ stockDB.getStockname()+ "\t\t\t\t" + stockDB.getProvince()
						+ "\t\t" + stockDB.getStocktel() + "\t\t" + stockDB.getStockaddress() 
						+ "\t\t" + stockDB.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateStock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		stockDao = StockDaoFactory.create();
		try {
			KStock stock = stockDao.findByPrimaryKey(new BigDecimal(id));
			if (stock == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			System.err.println("请输入新的仓库编码:当前为(" + stock.getStockno() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setStockno(temp);
			}
			System.err.println("请输入新的仓库名称：当前为(" + stock.getStockname() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setStockname(temp);
			}
			System.err.println("请输入新的仓库所在省份:当前为(" + stock.getProvince() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setProvince(temp);
			}
			System.err.println("请输入新的联系方式:当前为(" + stock.getStocktel() +")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setStocktel(temp);
			}
			//////////////////
			System.err.println("请输入新的详细地址：当前为(" + stock.getStockaddress() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setStockaddress(temp);
			}
			/////////////////
			System.err.println("请输入备注:当前为(" + stock.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				stock.setRemark(temp);
			}
			
			int count = stockDao.update(new BigDecimal(id), stock);
			
			if (count > 0) {
				System.out.println("修改仓库成功！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStock(Scanner sc, String id) {
		// TODO Auto-generated method stub
		stockDao = StockDaoFactory.create();
		try {
			stockDao.delete(new BigDecimal(id));
			System.out.println("删除仓库成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
