package com.hyg.service;

import java.util.Scanner;

import com.hyg.model.KOutstock;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface OutstockServiceI {

	public void searchOutstock(Scanner sc);

	public void addOutstock(Scanner sc);

	public void updateOutstock(Scanner sc, String id);

	public void deleteOutstock(Scanner sc, String id);
	
	public void addOutstockDetail(Scanner sc);
	
	public void updateOutstockDetail(Scanner sc);
	
	public void deleteOutstockDetail(Scanner sc);

	public KOutstock findOutstockById(String id);

	public void confirmReq(Scanner sc);

}
