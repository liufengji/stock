package com.hyg.service;

import java.util.Scanner;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface SupplierServiceI {

	void searchSupplier(Scanner sc);

	void addSupplier(Scanner sc);

	void updateSupplier(Scanner sc, String id);

	void deleteSupplier(Scanner sc, String id);

}
