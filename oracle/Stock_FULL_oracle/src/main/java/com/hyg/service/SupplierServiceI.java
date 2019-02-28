package com.hyg.service;

import java.util.Scanner;

public interface SupplierServiceI {

	void searchSupplier(Scanner sc);

	void addSupplier(Scanner sc);

	void updateSupplier(Scanner sc, String id);

	void deleteSupplier(Scanner sc, String id);

}
