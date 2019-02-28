package com.hyg.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface ReportServiceI {
	/**
	 * 库存查询报表视图
	 * @param sc
	 */
	public void searchInventoryReport(Scanner sc);
	/**
	 * 返回期初的入出库信息
	 * @param startDate  期初日期
	 * @param stockId  仓库ID
	 * @param flag  出入库标记
	 * @return
	 */
	public List<Map<String, Object>> findInitTotalNum(String startDate,String stockId,String flag);
	/**
	 * 某时间段内的出入库信息
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param stockId 仓库ID
	 * @param flag 出入库标记
	 * @return
	 */
	public List<Map<String, Object>> findTotalNum(String startDate,String endDate,String stockId,String flag);
}
