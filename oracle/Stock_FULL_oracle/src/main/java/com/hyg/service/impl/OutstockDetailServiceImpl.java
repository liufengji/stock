package com.hyg.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.OutstockDetailDaoI;
import com.hyg.factory.OutstockDetailDaoFactory;
import com.hyg.model.KInventory;
import com.hyg.model.KOutstockDetail;
import com.hyg.service.OutstockDetailServiceI;

public class OutstockDetailServiceImpl implements OutstockDetailServiceI {
	private OutstockDetailDaoI outstockDetailDao;
	@Override
	public KOutstockDetail[] findOutstockDetailsByOutstockId(BigDecimal id) {
		// TODO Auto-generated method stub
		outstockDetailDao = OutstockDetailDaoFactory.create();
		KOutstockDetail[] outstockDetails = null;
		try {
			outstockDetails = outstockDetailDao.findDetailsByOutstockId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outstockDetails;
	}

	@Override
	public void addOutstockDetail(String id, List<Map> inventoryList) {
		// TODO Auto-generated method stub
		outstockDetailDao = OutstockDetailDaoFactory.create();
		int count = 0;
		//获取拼接明细信息
		try {
		
			for (int i = 0; i < inventoryList.size(); i++) {
				KInventory inventory = (KInventory) inventoryList.get(i).get("inventory");
				KOutstockDetail outstockDetail = new KOutstockDetail();
				outstockDetail.setId(new BigDecimal(outstockDetailDao.getSequences()));
				outstockDetail.setOutstockid(new BigDecimal(id));
				outstockDetail.setProductno(inventory.getProductno());
				outstockDetail.setProductname(inventory.getProductname());
				outstockDetail.setProductstandard(inventory.getProductstandard());
				outstockDetail.setProductnum(new BigDecimal(inventoryList.get(i).get("num").toString()));
				outstockDetail.setPrice(inventory.getInventoryprice().divide(inventory.getInstocknum(),BigDecimal.ROUND_UP ));
				outstockDetail.setTotalprice(outstockDetail.getProductnum().multiply(outstockDetail.getPrice()));
				count += outstockDetailDao.insert(outstockDetail);
				
			}
			
			if (count > 0) {
				System.out.println("保存出库单明细成功，一共保存了[" + count + "]条明细!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public KOutstockDetail findOutstockDetailsById(String id) {
		// TODO Auto-generated method stub
		outstockDetailDao = OutstockDetailDaoFactory.create();
		KOutstockDetail outstockDetail = null;
		try {
			outstockDetail = outstockDetailDao.findByPrimaryKey(new BigDecimal(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outstockDetail;
	}

	@Override
	public void updateDetail(KOutstockDetail outstockDetail) {
		// TODO Auto-generated method stub
		outstockDetailDao = OutstockDetailDaoFactory.create();
		int count = 0;
		try {
			count = outstockDetailDao.update(outstockDetail.getId(), outstockDetail);
			if (count > 0) {
				System.err.println("更新记录成功!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDetailsByIds(String[] idArr) {
		// TODO Auto-generated method stub
		outstockDetailDao = OutstockDetailDaoFactory.create();
		//删除明细信息
		int count = 0;
		try {
			for (int i = 0; i < idArr.length; i++) {
				count += outstockDetailDao.delete(new BigDecimal(idArr[i]));
			}
			if (count > 0 ) {
				System.err.println("删除明细成功,一共删除了[" + count + "]条明细信息!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDetailById(BigDecimal id) {
		// TODO Auto-generated method stub
		//删除明细信息
		int count = 0;
		try {
			count = outstockDetailDao.delete(id);
			if (count > 0 ) {
				System.err.println("删除明细成功,删除了[" + count + "]条明细信息!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
