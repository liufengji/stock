package com.hyg.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.model.KOutstockDetail;

public interface OutstockDetailServiceI {

	public KOutstockDetail[] findOutstockDetailsByOutstockId(BigDecimal id);

	public void addOutstockDetail(String id, List<Map> inventoryList);

	public KOutstockDetail findOutstockDetailsById(String id);

	public void updateDetail(KOutstockDetail outstockDetail);

	public void deleteDetailsByIds(String[] idArr);

	public void deleteDetailById(BigDecimal id);

}
