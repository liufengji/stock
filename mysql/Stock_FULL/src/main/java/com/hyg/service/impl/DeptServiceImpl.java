package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hyg.dao.DeptDaoI;
import com.hyg.factory.DeptDaoFactory;
import com.hyg.model.KDept;
import com.hyg.service.DeptServiceI;
import com.hyg.util.DBUtil;
import com.hyg.util.StringUtils;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class DeptServiceImpl implements DeptServiceI {

	private DeptDaoI deptDao;
	@Override
	public void searchDept(Scanner sc) {
		// TODO Auto-generated method stub
		KDept[] users = null;
		deptDao = DeptDaoFactory.create();
		System.out.println("是否要带条件查询(Y:是 N：否)?请选择:");
		String yesOrNo = sc.next();
		try {
			if ("Y".equals(yesOrNo.toUpperCase())) {
				System.out.println("请输入部门编号：");
				String deptNo = sc.nextLine();
				System.out.println("请输入部门名称:");
				String deptName = sc.nextLine();
				System.out.println("请输入部门领导：");
				String leader = sc.nextLine();
				String  whereSql = " 1=1 ";
				List<String> param = new ArrayList<String>();
				if (StringUtils.isNotEmpty(deptNo)) {
					whereSql += " and DEPTNO = ?";
					param.add(deptNo);
				}
				if (StringUtils.isNotEmpty(deptName)) {
					whereSql += " and DEPTNAME like ?";
					param.add("%" + deptName + "%");
				}
				if (StringUtils.isNotEmpty(leader)) {
					whereSql += " and DEPTLEADER = ?";
					param.add(leader);
				}
				users = deptDao.findByDynamicWhere(whereSql, param.toArray());
			}else {
			//查询所有部门
				users = deptDao.findAll();
			}
			System.out.println("\t\tID\t\t部门编号\t\t部门名称\t\t部门领导\t\t联系方式\t\t上级部门\t\t部门描述\t\t备注");
			for (KDept kDept : users) {
				System.out.println("\t\t" + kDept.getId() + "\t\t" + kDept.getDeptno() 
						+ "\t\t" + kDept.getDeptname() + "\t\t" + kDept.getDeptleader()
						+ "\t\t" + kDept.getDepttel() + "\t\t" + kDept.getParentdeptno() 
						+ "\t\t" + kDept.getDeptdesc() + "\t\t" + kDept.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addDept(Scanner sc) {
		// TODO Auto-generated method stub
		deptDao = DeptDaoFactory.create();
		KDept user = new KDept();
		try {
			user.setId(new BigDecimal(deptDao.getSequences()));
			System.out.println("请输入部门编号：");
			user.setDeptno(sc.next());
			System.out.println("请输入部门名称:");
			user.setDeptname(sc.next());
			System.out.println("请输入部门领导:");
			user.setDeptleader(sc.next());
			System.out.println("请输入联系方式:");
			user.setDepttel(sc.next());
			//////////////////
			System.out.println("请输入上级部门：");
			user.setParentdeptno(sc.next());
			System.out.println("请输入部门描述:");
			user.setDeptdesc(sc.next());
			/////////////////
			System.out.println("请输入备注:");
			user.setRemark(sc.nextLine());	
			int rows = deptDao.insert(user);
			if (rows > 0) {
				System.out.println("添加部门成功！");
				KDept deptDB = deptDao.findByPrimaryKey(user.getId());
				System.out.println("\t\tID\t\t部门编号\t\t部门名称\t\t部门领导\t\t联系方式\t\t上级部门\t\t部门描述\t\t备注");
				System.out.println("\t\t" + deptDB.getId() + "\t\t" + deptDB.getDeptno() 
						+ "\t\t" + deptDB.getDeptname() + "\t\t" + deptDB.getDeptleader()
						+ "\t\t" + deptDB.getDepttel() + "\t\t" + deptDB.getParentdeptno() 
						+ "\t\t" + deptDB.getDeptdesc() + "\t\t" + deptDB.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateDept(Scanner sc, String id) {
		// TODO Auto-generated method stub
		deptDao = DeptDaoFactory.create();
		try {
			KDept dept = deptDao.findByPrimaryKey(new BigDecimal(id));
			if (dept == null) {
				throw new Exception("ID为[" + id +"]的记录不存在，请检查!");
			}
			System.err.println("请输入新的部门编号:当前为(" + dept.getDeptno() + ")");
			String temp = sc.next();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setDeptno(temp);
			}
			System.err.println("请输入新的部门名称：当前为(" + dept.getDeptname() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setDeptname(temp);
			}
			System.err.println("请输入新的部门领导:当前为(" + dept.getDeptleader() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setDeptleader(temp);
			}
			System.err.println("请输入新的联系方式:当前为(" + dept.getDepttel()+")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setDepttel(temp);
			}
			//////////////////
			System.err.println("请输入上级部门编号：当前为(" + dept.getParentdeptno() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setParentdeptno(temp);
			}
			System.err.println("请输入部门描述:当前为(" + dept.getDeptdesc() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setDeptdesc(temp);
			}
			/////////////////
			System.err.println("请输入备注:当前为(" + dept.getRemark() + ")");
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				dept.setRemark(temp);
			}
			deptDao.update(new BigDecimal(id), dept);
			System.out.println("修改部门成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDept(Scanner sc, String id) {
		// TODO Auto-generated method stub
		deptDao = DeptDaoFactory.create();
		try {
			deptDao.delete(new BigDecimal(id));
			System.out.println("删除部门成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
