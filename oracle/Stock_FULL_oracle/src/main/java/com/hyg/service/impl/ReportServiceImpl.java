package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.ReportDaoI;
import com.hyg.dao.impl.ReportDaoImpl;
import com.hyg.factory.ReportDaoFactory;
import com.hyg.service.ReportServiceI;
import com.hyg.util.DateUtils;
import com.hyg.util.StringUtils;

public class ReportServiceImpl implements ReportServiceI {
	private final String INSTOCK_FLAG = "instock";
	private final String OUTSTOCK_FLAG = "outstock";
	
	private ReportDaoI reportDao;
	@Override
	public void searchInventoryReport(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("欢迎来到[库存月报界面]");
		System.out.println("请输入期初日期（yyyy-MM-dd）:");
		String dateStart = sc.next();
		System.out.println("请输入期末日期（yyyy-MM-dd）:");
		String dateEnd = sc.next();
		String stockId = "";
		if (StringUtils.isEmpty(dateStart)) {
			System.out.println("期初日期不能为空！请检查！");
			return;
//		}else {
//			whereSql += " AND S.INSTOCKDATE > TO_DATE(?, 'yyyy-MM-dd') ";
//			params.add(dateStart);
		}
		
		if (StringUtils.isEmpty(dateEnd)) {
			System.out.println("期末日期不能为空！请检查！");
			return;
//		}else {
//			whereSql += " AND S.INSTOCKDATE < TO_DATE(?, 'yyyy-MM-dd') ";
//			params.add(dateEnd);
		}
		
		if (DateUtils.compareDate(dateStart, dateEnd, "yyyy-MM-dd") > 0) {
			System.out.println("期初日期不能大于期末日期，请检查！");
			return;
		}
		System.out.println("是否需要输入仓库ID（Y:是 N:否）？:");
		String flag = sc.next();
		if ("Y".equals(flag.toUpperCase())) {
			System.out.println("请输入仓库ID号：");
			stockId = sc.next();
			if (StringUtils.isEmpty(stockId)) {
				System.out.println("输入的仓库ID不能为空!");
				return ;
			}
		}
		
		//期初入库总量
		List<Map<String, Object>> instockInit = this.findInitTotalNum(dateStart, stockId, INSTOCK_FLAG);
		//期初出库总量
		List<Map<String, Object>> outstockInit = this.findInitTotalNum(dateEnd, stockId, OUTSTOCK_FLAG);
		
		//波段入库总量
		List<Map<String, Object>> instocks = this.findTotalNum(dateStart,dateEnd, stockId, INSTOCK_FLAG);
		//波段出库总量
		List<Map<String, Object>> outstocks = this.findTotalNum(dateStart,dateEnd, stockId, OUTSTOCK_FLAG);
		System.out.println("点检日期\t\t仓库名称\t\t产品编号\t\t产品名称\t\t产品规格\t\t期初库存量\t期间入库量\t期间出库量\t期末库存量");
		String result = "";
		BigDecimal inNum = new BigDecimal(0);  //期初入库量
		BigDecimal outNum = new BigDecimal(0);//期初出库量
		BigDecimal instockNum = new BigDecimal(0); //波段入库总量
		BigDecimal outstockNum = new BigDecimal(0); //波段出库总量
		for (int i = 0; i < instockInit.size(); i++) {
			Map<String, Object> instockMap = instockInit.get(i);
			String stockName = (String) instockMap.get("STOCKNAME");
			String productNo = (String) instockMap.get("PRODUCTNO");
			String productName = (String)instockMap.get("PRODUCTNAME");
			String productStandard = (String) instockMap.get("PRODUCTSTANDARD");
			inNum = new BigDecimal((String) instockMap.get("INNUM"));
			
			result += dateStart + "到" + dateEnd + "\t" + stockName + " \t " + productNo + "\t" 
					+ productName + "\t" + productStandard + "\t";
			for (int j = 0; j < outstockInit.size() ; j++) {
				if (stockName.equals((String)outstockInit.get(j).get("STOCKNAME")) && productNo.equals((String)outstockInit.get(j).get("PRODUCTNO"))) {
					outNum = new BigDecimal((String) outstockInit.get(j).get("OUTNUM"));
					break;
				}else {
					outNum = new BigDecimal(0);
				}
			}
			
			for (int j = 0; j < instocks.size(); j++) {
				if (stockName.equals((String)instocks.get(j).get("STOCKNAME")) && productNo.equals((String)instocks.get(j).get("PRODUCTNO"))) {
					instockNum = new BigDecimal(instocks.get(j).get("INNUM").toString());
					break;
				}else {
					instockNum = new BigDecimal(0);
				}
			}
			
			for (int j = 0; j < outstocks.size(); j++) {
				if (stockName.equals((String)outstocks.get(j).get("STOCKNAME")) && productNo.equals((String)outstocks.get(j).get("PRODUCTNO"))) {
					outstockNum = new BigDecimal(outstocks.get(j).get("OUTNUM").toString());
					break;
				}else {
					outstockNum = new BigDecimal(0);
				}
			}
			
			result += inNum.subtract(outNum) + "\t " + instockNum + "\t " + outstockNum + "\t " + inNum.subtract(outNum).add(instockNum).subtract(outstockNum);
			System.out.println(result);
			result = "";
		}
	}

	@Override
	public List<Map<String, Object>> findInitTotalNum(String startDate,
			String stockId, String flag) {
		String whereSql = "";
		List param = new ArrayList();
		
		reportDao = ReportDaoFactory.create();
		List<Map<String, Object>> results = null;
		try {
			if (INSTOCK_FLAG.equals(flag)) {
				if (StringUtils.isNotEmpty(startDate)) {
					whereSql += " AND S.INSTOCKDATE < TO_DATE(?,'YYYY-MM-DD') ";
					param.add(startDate);
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " AND S.STOCKID = ? ";
					param.add(stockId);
				}
				results = reportDao.findMonthInstock(whereSql, param.toArray());
			}else if (OUTSTOCK_FLAG.equals(flag)) {
				if (StringUtils.isNotEmpty(startDate)) {
					whereSql += " AND S.OUTSTOCKDATE < TO_DATE(?,'YYYY-MM-DD') ";
					param.add(startDate);
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " AND S.STOCKID = ? ";
					param.add(stockId);
				}
				results = reportDao.findMonthOutstock(whereSql, param.toArray());
			}else {
				System.out.println("出入库标志位不能识别，请检查！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;	
	}

	@Override
	public List<Map<String, Object>> findTotalNum(String startDate,
			String endDate, String stockId, String flag) {
		String whereSql = "";
		List param = new ArrayList();
		
		reportDao = ReportDaoFactory.create();
		List<Map<String, Object>> results = null;
		try {
			if (INSTOCK_FLAG.equals(flag)) {
				if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
					whereSql += " AND S.INSTOCKDATE >= TO_DATE(?,'YYYY-MM-DD')  AND S.INSTOCKDATE <= TO_DATE(?,'YYYY-MM-DD')";
					param.add(startDate);
					param.add(endDate);
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " AND S.STOCKID = ? ";
					param.add(stockId);
				}
				results = reportDao.findMonthInstock(whereSql, param.toArray());
			}else if (OUTSTOCK_FLAG.equals(flag)) {
				if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
					whereSql += " AND S.OUTSTOCKDATE >= TO_DATE(?,'YYYY-MM-DD') AND S.OUTSTOCKDATE <= TO_DATE(?,'YYYY-MM-DD')";
					param.add(startDate);
					param.add(endDate);
				}
				if (StringUtils.isNotEmpty(stockId)) {
					whereSql += " AND S.STOCKID = ? ";
					param.add(stockId);
				}
				results = reportDao.findMonthOutstock(whereSql, param.toArray());
			}else {
				System.out.println("出入库标志位不能识别，请检查！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;	
	}

}
