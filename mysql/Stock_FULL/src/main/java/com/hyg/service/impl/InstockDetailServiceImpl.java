package com.hyg.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hyg.dao.InstockDetailDaoI;
import com.hyg.dao.impl.InstockDetailDaoImpl;
import com.hyg.factory.InstockDetailDaoFactory;
import com.hyg.model.KGoods;
import com.hyg.model.KInstockDetail;
import com.hyg.service.InstockDetailServiceI;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class InstockDetailServiceImpl implements InstockDetailServiceI {
	private InstockDetailDaoI instockDetailDao;
	
	@Override
	public void searchInstockDetails(Scanner sc) {
		// TODO Auto-generated method stub
		instockDetailDao = InstockDetailDaoFactory.create();
		System.out.println("请选择要查看的入库申请单ID：");
		String instockId = sc.next();
		
		KInstockDetail[] instockDetails;
		try {
			instockDetails = instockDetailDao.findInstockDetailsByInstockID(instockId);
			System.out.println("\tID\t入库单ID\t产品编码\t产品名称\t产品规格\t入库数量\t计量单位\t单价\t入库总金额\t备注");
			for (KInstockDetail kInstockDetail : instockDetails) {
				System.out.println("\t" + kInstockDetail.getId() + "\t"
				 + kInstockDetail.getInstockid() + "\t"
				 + kInstockDetail.getProductno() + "\t"
				 + kInstockDetail.getProductname() + "\t"
				 + kInstockDetail.getProductstandard() + "\t"
				 + kInstockDetail.getProductnum() + "\t"
				 + kInstockDetail.getUnit() + "\t"
				 + kInstockDetail.getPrice() + "\t"
				 + kInstockDetail.getTotalprice() + "\t"
				 + kInstockDetail.getRemark());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public KInstockDetail[] findInstockDetailsByInstockId(String instockId) {
		// TODO Auto-generated method stub
		instockDetailDao = InstockDetailDaoFactory.create();
		KInstockDetail[] instockDetails = null;
		try {
			instockDetails = instockDetailDao.findInstockDetailsByInstockID(instockId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instockDetails;
	}

	@Override
	public int deleteDetailsByInstockId(String instockId) {
		// TODO Auto-generated method stub
		int rows = 0;
		try {
			instockDetailDao = InstockDetailDaoFactory.create();
			KInstockDetail[] details = this.findInstockDetailsByInstockId(instockId);
			
			for (int i = 0; i < details.length; i++) {
				rows += instockDetailDao.delete(details[i].getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public void addInstockDetail(Scanner sc, String instockId, List<Map> goodsList) {
		// TODO Auto-generated method stub
		instockDetailDao = InstockDetailDaoFactory.create();
		int count = 0;
		//获取拼接明细信息
		try {
		
			for (int i = 0; i < goodsList.size(); i++) {
				KGoods goods = (KGoods) goodsList.get(i).get("goods");
				KInstockDetail instockDetail = new KInstockDetail();
				instockDetail.setId(new BigDecimal(instockDetailDao.getSequences()));
				instockDetail.setInstockid(new BigDecimal(instockId));
				instockDetail.setProductno(goods.getProductno());
				instockDetail.setProductname(goods.getProductname());
				instockDetail.setProductstandard(goods.getProductstandard());
				instockDetail.setProductnum(new BigDecimal(goodsList.get(i).get("num").toString()));
				instockDetail.setUnit(goods.getUnit());
				//入库单价 = 采购单价*1.1
				instockDetail.setPrice(goods.getPrice().multiply(new BigDecimal("1.1")));
				//采购总价
				instockDetail.setTotalprice(instockDetail.getProductnum().multiply(instockDetail.getPrice()));
				instockDetail.setRemark(goodsList.get(i).get("remark").toString());
				count += instockDetailDao.insert(instockDetail);
			}
			
			if (count > 0) {
				System.out.println("保存入库单明细成功，一共保存了[" + count + "]条明细!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public KInstockDetail findInstockDetailsById(String id) {
		// TODO Auto-generated method stub
		KInstockDetail instockDetail = null;
		try {
			instockDetail = instockDetailDao.findByPrimaryKey(new BigDecimal(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instockDetail;
	}

	@Override
	public void updateDetail(KInstockDetail instockDetail) {
		// TODO Auto-generated method stub
		try {
			int rows = instockDetailDao.update(instockDetail.getId(), instockDetail);
			if (rows > 0) {
				System.out.println("一共更新了[" + rows + "]条入库明细记录！");
			}else{
				System.out.println("更新失败，更新了[" + rows +"]条入库明细记录！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteDetailsByIds(String[] idArr) {
		// TODO Auto-generated method stub
		instockDetailDao = InstockDetailDaoFactory.create();
		//删除明细信息
		int count = 0;
		try {
			for (int i = 0; i < idArr.length; i++) {
				count += instockDetailDao.delete(new BigDecimal(idArr[i]));
			}
			if (count > 0 ) {
				System.err.println("删除明细成功,一共删除了[" + count + "]条明细信息!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
