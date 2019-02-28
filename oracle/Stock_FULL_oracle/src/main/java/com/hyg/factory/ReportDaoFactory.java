package com.hyg.factory;

import com.hyg.dao.ReportDaoI;
import com.hyg.dao.impl.ReportDaoImpl;

public class ReportDaoFactory {

	public static ReportDaoI create()
	{
		return new ReportDaoImpl();
	}
}
