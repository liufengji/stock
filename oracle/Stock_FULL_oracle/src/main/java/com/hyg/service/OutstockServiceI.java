package com.hyg.service;

import java.util.Scanner;

import com.hyg.model.KOutstock;

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
