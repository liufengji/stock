package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hyg.dao.GoodsDaoI;
import com.hyg.factory.GoodsDaoFactory;
import com.hyg.model.KGoods;
import com.hyg.service.GoodsServiceI;
import com.hyg.util.StringUtils;

public class GoodsServiceImpl implements GoodsServiceI {
	private GoodsDaoI goodsDao;
	@Override
	public void findGoodsList() {
		// TODO Auto-generated method stub
		goodsDao = GoodsDaoFactory.create();
		try {
			KGoods[] goodsList = goodsDao.findAll();
			System.out.println("\t\tID\t\t产品编号\t\t\t\t产品名称\t\t\t\t产品类型\t\t产品规格\t\t计量单位\t\t单价");
			for (KGoods goods : goodsList) {
				System.out.println("\t\t" + goods.getId() + "\t\t" + goods.getProductno() + "\t\t\t\t" 
						+ goods.getProductname() + "\t\t\t\t" + goods.getProducttype()
						+ "\t\t" + goods.getProductstandard() + "\t\t\t\t" + goods.getUnit() 
						+ "\t\t" + goods.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public KGoods findGoodsByID(String id) {
		// TODO Auto-generated method stub
		KGoods goods = null;
		try {
			goods = goodsDao.findByPrimaryKey(new BigDecimal(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	
	@Override
	public void searchGoods(Scanner sc) {
		// TODO Auto-generated method stub
		KGoods[] kGoods = null;
		
		goodsDao = GoodsDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入产品编码：");
				String productNo = sc.nextLine();
				System.out.println("请输入产品名称:");
				String productName = sc.nextLine();
				System.out.println("请输入产品分类");
				String productType = sc.nextLine();
				System.out.println("请输入产品规格");
				String productStandard = sc.nextLine();
				String  whereSql = " 1=1 ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(productNo)) {
					whereSql += " and PRODUCTNO = ?";
					param.add(productNo);
				}
				if (StringUtils.isNotEmpty(productName)) {
					whereSql += " and PRODUCTNAME like ?";
					param.add("%" + productName + "%");
				}
				if (StringUtils.isNotEmpty(productType)) {
					whereSql += " and PRODUCTTYPE = ?";
					param.add(productType);
				}
				if (StringUtils.isNotEmpty(productStandard)) {
					whereSql += " and PRODUCTSTANDARD = ?";
					param.add(productStandard);
				}
				kGoods = goodsDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
				//查询所有产品
				kGoods = goodsDao.findAll();
			}
			System.out.println("\t\tID\t\t产品编号\t\t\t\t产品名称\t\t\t\t产品类型\t\t产品规格\t\t计量单位\t\t单价");
			for (KGoods goods : kGoods) {
				System.out.println("\t\t" + goods.getId() + "\t\t" + goods.getProductno() + "\t\t\t\t" 
						+ goods.getProductname() + "\t\t\t\t" + goods.getProducttype()
						+ "\t\t" + goods.getProductstandard() + "\t\t\t\t" + goods.getUnit() 
						+ "\t\t" + goods.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addGoods(Scanner sc) {
		// TODO Auto-generated method stub
		goodsDao = GoodsDaoFactory.create();
		KGoods kGoods = new KGoods();
		try {
			kGoods.setId(new BigDecimal(goodsDao.getSequences()));
			System.out.println("请输入产品编码：");
			kGoods.setProductno(sc.next());
			System.out.println("请输入产品名称:");
			kGoods.setProductname(sc.next());
			System.out.println("请输入产品分类:");
			kGoods.setProducttype(sc.next());
			System.out.println("请输入产品规格:");
			kGoods.setProductstandard(sc.next());
			System.out.println("请输入计量单位:");
			kGoods.setUnit(sc.next());
			System.out.println("请输入单价:");
			kGoods.setPrice(new BigDecimal(sc.next()));
			System.out.println("请输入备注");
			kGoods.setRemark(sc.nextLine());
			
			int rows = goodsDao.insert(kGoods);
			if (rows > 0) {
				System.out.println("添加产品成功！");
				KGoods goodsDB = goodsDao.findByPrimaryKey(kGoods.getId());
				System.out.println("\t\tID\t\t产品编号\t\t\t\t产品名称\t\t\t\t产品类型\t\t产品规格\t\t计量单位\t\t单价");
				System.out.println("\t\t" + goodsDB.getId() + "\t\t" + goodsDB.getProductno() + "\t\t\t\t" 
						+ goodsDB.getProductname() + "\t\t\t\t" + goodsDB.getProducttype()
						+ "\t\t" + goodsDB.getProductstandard() + "\t\t\t\t" + goodsDB.getUnit() 
						+ "\t\t" + goodsDB.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateGoods(Scanner sc, String id) {
		// TODO Auto-generated method stub

		goodsDao = GoodsDaoFactory.create();
		try {
			KGoods goods = goodsDao.findByPrimaryKey(new BigDecimal(id));
			if (goods == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			System.err.println("请输入新的产品编码:当前为(" + goods.getProductno() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setProductno(temp);
			}
			System.err.println("请输入新的产品名称：当前为(" + goods.getProductname() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setProductname(temp);
			}
			System.err.println("请输入新的产品分类:当前为(" + goods.getProducttype() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setProducttype(temp);
			}
			System.err.println("请输入新的产品规格:当前为(" + goods.getProductstandard() +")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setProductstandard(temp);
			}
			//////////////////
			System.err.println("请输入新的计量单位：当前为(" + goods.getUnit() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setUnit(temp);
			}
			System.err.println("请输入新的单价:当前为(" + goods.getPrice() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setPrice(new BigDecimal(temp));
			}
			/////////////////
			System.err.println("请输入备注:当前为(" + goods.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				goods.setRemark(temp);
			}
			
			int count = goodsDao.update(new BigDecimal(id), goods);
			
			if (count > 0) {
				System.out.println("修改产品成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteGoods(Scanner sc, String id) {
		// TODO Auto-generated method stub

		goodsDao = GoodsDaoFactory.create();
		try {
			goodsDao.delete(new BigDecimal(id));
			System.out.println("删除产品成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
