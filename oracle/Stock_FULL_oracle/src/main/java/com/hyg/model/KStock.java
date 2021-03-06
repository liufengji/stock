/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.hyg.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class KStock implements Serializable
{
	/** 
	 * This attribute maps to the column ID in the K_STOCK table.
	 */
	protected BigDecimal id;

	/** 
	 * This attribute maps to the column STOCKNO in the K_STOCK table.
	 */
	protected String stockno;

	/** 
	 * This attribute maps to the column STOCKNAME in the K_STOCK table.
	 */
	protected String stockname;

	/** 
	 * This attribute maps to the column PROVINCECODE in the K_STOCK table.
	 */
	protected String province;

	/** 
	 * This attribute maps to the column STOCKADDRESS in the K_STOCK table.
	 */
	protected String stockaddress;

	/** 
	 * This attribute maps to the column STOCKTEL in the K_STOCK table.
	 */
	protected String stocktel;

	/** 
	 * This attribute maps to the column REMARK in the K_STOCK table.
	 */
	protected String remark;

	/**
	 * Method 'KStock'
	 * 
	 */
	public KStock()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	/**
	 * Method 'getStockno'
	 * 
	 * @return String
	 */
	public String getStockno()
	{
		return stockno;
	}

	/**
	 * Method 'setStockno'
	 * 
	 * @param stockno
	 */
	public void setStockno(String stockno)
	{
		this.stockno = stockno;
	}

	/**
	 * Method 'getStockname'
	 * 
	 * @return String
	 */
	public String getStockname()
	{
		return stockname;
	}

	/**
	 * Method 'setStockname'
	 * 
	 * @param stockname
	 */
	public void setStockname(String stockname)
	{
		this.stockname = stockname;
	}

	/**
	 * Method 'getProvince'
	 * 
	 * @return String
	 */
	public String getProvince()
	{
		return province;
	}

	/**
	 * Method 'setProvince'
	 * 
	 * @param province
	 */
	public void setProvince(String province)
	{
		this.province = province;
	}

	/**
	 * Method 'getStockaddress'
	 * 
	 * @return String
	 */
	public String getStockaddress()
	{
		return stockaddress;
	}

	/**
	 * Method 'setStockaddress'
	 * 
	 * @param stockaddress
	 */
	public void setStockaddress(String stockaddress)
	{
		this.stockaddress = stockaddress;
	}

	/**
	 * Method 'getStocktel'
	 * 
	 * @return String
	 */
	public String getStocktel()
	{
		return stocktel;
	}

	/**
	 * Method 'setStocktel'
	 * 
	 * @param stocktel
	 */
	public void setStocktel(String stocktel)
	{
		this.stocktel = stocktel;
	}

	/**
	 * Method 'getRemark'
	 * 
	 * @return String
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * Method 'setRemark'
	 * 
	 * @param remark
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof KStock)) {
			return false;
		}
		
		final KStock _cast = (KStock) _other;
		if (id == null ? _cast.id != id : !id.equals( _cast.id )) {
			return false;
		}
		
		if (stockno == null ? _cast.stockno != stockno : !stockno.equals( _cast.stockno )) {
			return false;
		}
		
		if (stockname == null ? _cast.stockname != stockname : !stockname.equals( _cast.stockname )) {
			return false;
		}
		
		if (province == null ? _cast.province != province : !province.equals( _cast.province )) {
			return false;
		}
		
		if (stockaddress == null ? _cast.stockaddress != stockaddress : !stockaddress.equals( _cast.stockaddress )) {
			return false;
		}
		
		if (stocktel == null ? _cast.stocktel != stocktel : !stocktel.equals( _cast.stocktel )) {
			return false;
		}
		
		if (remark == null ? _cast.remark != remark : !remark.equals( _cast.remark )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (id != null) {
			_hashCode = 29 * _hashCode + id.hashCode();
		}
		
		if (stockno != null) {
			_hashCode = 29 * _hashCode + stockno.hashCode();
		}
		
		if (stockname != null) {
			_hashCode = 29 * _hashCode + stockname.hashCode();
		}
		
		if (province != null) {
			_hashCode = 29 * _hashCode + province.hashCode();
		}
		
		if (stockaddress != null) {
			_hashCode = 29 * _hashCode + stockaddress.hashCode();
		}
		
		if (stocktel != null) {
			_hashCode = 29 * _hashCode + stocktel.hashCode();
		}
		
		if (remark != null) {
			_hashCode = 29 * _hashCode + remark.hashCode();
		}
		
		return _hashCode;
	}


	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.mycompany.myapp.dto.KStock: " );
		ret.append( "id=" + id );
		ret.append( ", stockno=" + stockno );
		ret.append( ", stockname=" + stockname );
		ret.append( ", province=" + province );
		ret.append( ", stockaddress=" + stockaddress );
		ret.append( ", stocktel=" + stocktel );
		ret.append( ", remark=" + remark );
		return ret.toString();
	}

}
