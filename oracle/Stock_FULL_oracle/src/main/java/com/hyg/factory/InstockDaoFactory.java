/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.hyg.factory;

import com.hyg.dao.InstockDaoI;
import com.hyg.dao.impl.InstockDaoImpl;

public class InstockDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return InstockDaoI
	 */
	public static InstockDaoI create()
	{
		return new InstockDaoImpl();
	}
}
