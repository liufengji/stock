package com.hyg.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KDept;
import com.hyg.model.KUser;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface DeptServiceI {

	/**
	 * 查询部门
	 * @param sc
	 */
	public void searchDept(Scanner sc);
    /**
     * 添加部门
     * @param sc
     */
	public void addDept(Scanner sc);
    /**
     * 修改部门
     * @param sc
     * @param id
     */
	public void updateDept(Scanner sc, String id);
    /**
     * 删除部门
     * @param sc
     * @param id
     */
	public void deleteDept(Scanner sc, String id);
}
