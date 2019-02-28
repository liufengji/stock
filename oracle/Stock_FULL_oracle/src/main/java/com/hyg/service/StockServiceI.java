package com.hyg.service;

import java.util.Scanner;

public interface StockServiceI {

	void searchStock(Scanner sc);

	void addStock(Scanner sc);

	void updateStock(Scanner sc, String id);

	void deleteStock(Scanner sc, String id);

}
