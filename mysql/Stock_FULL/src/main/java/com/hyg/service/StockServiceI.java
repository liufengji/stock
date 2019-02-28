package com.hyg.service;

import java.util.Scanner;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface StockServiceI {

	void searchStock(Scanner sc);

	void addStock(Scanner sc);

	void updateStock(Scanner sc, String id);

	void deleteStock(Scanner sc, String id);

}
