package com.hyg.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KOutstockDetail;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public interface OutstockDetailServiceI {

	public KOutstockDetail[] findOutstockDetailsByOutstockId(BigDecimal id);

	public void addOutstockDetail(String id, List<Map> inventoryList);

	public KOutstockDetail findOutstockDetailsById(String id);

	public void updateDetail(KOutstockDetail outstockDetail);

	public void deleteDetailsByIds(String[] idArr);

	public void deleteDetailById(BigDecimal id);

}
