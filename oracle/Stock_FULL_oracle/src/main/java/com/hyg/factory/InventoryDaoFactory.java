/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.hyg.factory;

import com.hyg.dao.InventoryDaoI;
import com.hyg.dao.impl.InventoryDaoImpl;

public class InventoryDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return InventoryDaoI
	 */
	public static InventoryDaoI create()
	{
		return new InventoryDaoImpl();
	}


}
