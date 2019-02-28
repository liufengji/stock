package com.hyg.factory;

import com.hyg.dao.ReportDaoI;
import com.hyg.dao.impl.ReportDaoImpl;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class ReportDaoFactory {

	public static ReportDaoI create()
	{
		return new ReportDaoImpl();
	}
}
