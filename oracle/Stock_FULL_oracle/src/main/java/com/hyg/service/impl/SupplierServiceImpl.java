package com.hyg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hyg.dao.SupplierDaoI;
import com.hyg.factory.SupplierDaoFactory;
import com.hyg.model.KSupplier;
import com.hyg.service.SupplierServiceI;
import com.hyg.util.StringUtils;

public class SupplierServiceImpl implements SupplierServiceI {
	private SupplierDaoI supplierDao;
	@Override
	public void searchSupplier(Scanner sc) {
		// TODO Auto-generated method stub
		KSupplier[] kSupplier = null;
		
		supplierDao = SupplierDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入供应商编码:");
				String supplierNo = sc.nextLine();
				System.out.println("请输入供应商名称:");
				String supplierName = sc.nextLine();
				System.out.println("请输入所在省份:");
				String province = sc.nextLine();
				System.out.println("请输入负责人姓名:");
				String userName = sc.nextLine();
				String  whereSql = " 1=1 ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(supplierNo)) {
					whereSql += " and SUPPLIERNO = ?";
					param.add(supplierNo);
				}
				if (StringUtils.isNotEmpty(supplierName)) {
					whereSql += " and SUPPLIERNAME like ?";
					param.add("%" + supplierName + "%");
				}
				if (StringUtils.isNotEmpty(province)) {
					whereSql += " and PROVINCECODE = ?";
					param.add(province);
				}
				if (StringUtils.isNotEmpty(userName)) {
					whereSql += " and USERNAME like ?";
					param.add(userName);
				}
				kSupplier = supplierDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
				//查询所有供应商
				kSupplier = supplierDao.findAll();
			}
			System.out.println("\t\tID\t\t供应商编码\t\t\t\t供应商名称\t\t\t\t所在省份\t\t邮箱地址\t\t电话"
					+ "\t\t传真\t\t负责人\t\t联系方式\t\t备注");
			for (KSupplier supplier : kSupplier) {
				System.out.println("\t\t" + supplier.getId() + "\t\t" + supplier.getSupplierno() + "\t\t\t\t" 
						+ supplier.getSuppliername() + "\t\t\t\t" + supplier.getProvincecode()
						+ "\t\t" + supplier.getSupplieremail() + "\t\t\t\t" + supplier.getSuppliertel() 
						+ "\t\t" + supplier.getSuppliertax() + "\t\t\t\t" + supplier.getUsername() 
						+ "\t\t" + supplier.getUsertel() + "\t\t" + supplier.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addSupplier(Scanner sc) {
		// TODO Auto-generated method stub
		supplierDao = SupplierDaoFactory.create();
		KSupplier kSupplier = new KSupplier();
		try {
			kSupplier.setId(new BigDecimal(supplierDao.getSequences()));
			System.out.println("请输入供应商编码：");
			kSupplier.setSupplierno(sc.next());
			System.out.println("请输入供应商名称:");
			kSupplier.setSuppliername(sc.next());
			System.out.println("请输入所在省份:");
			kSupplier.setProvincecode(sc.next());
			System.out.println("请输入详细地址:");
			kSupplier.setSupplieraddress(sc.next());
			System.out.println("请输入邮箱地址:");
			kSupplier.setSupplieremail(sc.next());
			System.out.println("请输入电话:");
			kSupplier.setSuppliertel(sc.next());
			System.out.println("请输入传真:");
			kSupplier.setSuppliertax(sc.next());
			System.out.println("请输入负责人:");
			kSupplier.setUsername(sc.next());
			System.out.println("请输入联系方式:");
			kSupplier.setUsertel(sc.next());
			System.out.println("请输入备注");
			kSupplier.setRemark(sc.nextLine());
			
			int rows = supplierDao.insert(kSupplier);
			if (rows > 0) {
				System.out.println("添加供应商成功！");
				KSupplier supplierDB = supplierDao.findByPrimaryKey(kSupplier.getId());
				System.out.println("\t\tID\t\t供应商编号\t\t\t\t供应商名称\t\t\t\t所在省份\t\t详细地址\t\t邮箱\t\t\t\t电话"
						+ "\t\t传真\t\t负责人\t\t联系方式\t\t备注");
				System.out.println("\t\t" + supplierDB.getId() + "\t\t" + supplierDB.getSupplierno() + "\t\t\t\t" 
						+ supplierDB.getSuppliername() + "\t\t\t\t" + supplierDB.getProvincecode()
						+ "\t\t" + supplierDB.getSupplieraddress() + "\t\t\t\t" + supplierDB.getSupplieremail()
						+ "\t\t" + supplierDB.getSuppliertel() + "\t\t\t\t" + supplierDB.getSuppliertax()
						+ "\t\t" + supplierDB.getUsername() + "\t\t\t\t" + supplierDB.getUsertel()
						+ "\t\t" + supplierDB.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateSupplier(Scanner sc, String id) {
		// TODO Auto-generated method stub
		supplierDao = SupplierDaoFactory.create();
		try {
			KSupplier supplier = supplierDao.findByPrimaryKey(new BigDecimal(id));	
			if (supplier == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			System.err.println("请输入新的供应商编码:当前为(" + supplier.getSupplierno() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSupplierno(temp);
			}
			System.err.println("请输入新的供应商名称：当前为(" + supplier.getSuppliername() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSuppliername(temp);
			}
			System.err.println("请输入新的省份:当前为(" + supplier.getProvincecode() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setProvincecode(temp);
			}
			System.err.println("请输入新的详细地址:当前为(" + supplier.getSupplieraddress() +")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSupplieraddress(temp);
			}
			//////////////////
			System.err.println("请输入新的邮箱：当前为(" + supplier.getSupplieremail() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSupplieremail(temp);
			}
			System.err.println("请输入新的电话:当前为(" + supplier.getSuppliertel() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSuppliertel(temp);
			}
			System.err.println("请输入新的传真:当前为(" + supplier.getSuppliertax() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setSuppliertax(temp);
			}
			System.err.println("请输入新的负责人:当前为(" + supplier.getUsername() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setUsername(temp);
			}
			System.err.println("请输入新的联系方式:当前为(" + supplier.getUsertel() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setUsertel(temp);
			}
			/////////////////
			System.err.println("请输入备注:当前为(" + supplier.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				supplier.setRemark(temp);
			}
			
			int count = supplierDao.update(new BigDecimal(id), supplier);
			
			if (count > 0) {
				System.out.println("修改供应商成功！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSupplier(Scanner sc, String id) {
		// TODO Auto-generated method stub
		supplierDao = SupplierDaoFactory.create();
		try {
			supplierDao.delete(new BigDecimal(id));
			System.out.println("删除供应商成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
