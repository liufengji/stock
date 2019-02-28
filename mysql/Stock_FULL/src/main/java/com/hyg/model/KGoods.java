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
import java.util.Date;
/**
 * @author 黑猴子的家
 * @博客 https://www.jianshu.com/u/37fd8e2dff4c
 * @官网 http://www.121ugrow.com/
 */
public class KGoods implements Serializable
{
	/** 
	 * This attribute maps to the column ID in the K_GOODS table.
	 */
	protected BigDecimal id;

	/** 
	 * This attribute maps to the column PRODUCTNO in the K_GOODS table.
	 */
	protected String productno;

	/** 
	 * This attribute maps to the column PRODUCTNAME in the K_GOODS table.
	 */
	protected String productname;

	/** 
	 * This attribute maps to the column PRODUCTTYPE in the K_GOODS table.
	 */
	protected String producttype;

	/** 
	 * This attribute maps to the column PRODUCTSTANDARD in the K_GOODS table.
	 */
	protected String productstandard;

	/** 
	 * This attribute maps to the column UNIT in the K_GOODS table.
	 */
	protected String unit;

	/** 
	 * This attribute maps to the column PRICE in the K_GOODS table.
	 */
	protected BigDecimal price;

	/** 
	 * This attribute maps to the column REMARK in the K_GOODS table.
	 */
	protected String remark;

	/**
	 * Method 'KGoods'
	 * 
	 */
	public KGoods()
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
	 * Method 'getProductno'
	 * 
	 * @return String
	 */
	public String getProductno()
	{
		return productno;
	}

	/**
	 * Method 'setProductno'
	 * 
	 * @param productno
	 */
	public void setProductno(String productno)
	{
		this.productno = productno;
	}

	/**
	 * Method 'getProductname'
	 * 
	 * @return String
	 */
	public String getProductname()
	{
		return productname;
	}

	/**
	 * Method 'setProductname'
	 * 
	 * @param productname
	 */
	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	/**
	 * Method 'getProducttype'
	 * 
	 * @return String
	 */
	public String getProducttype()
	{
		return producttype;
	}

	/**
	 * Method 'setProducttype'
	 * 
	 * @param producttype
	 */
	public void setProducttype(String producttype)
	{
		this.producttype = producttype;
	}

	/**
	 * Method 'getProductstandard'
	 * 
	 * @return String
	 */
	public String getProductstandard()
	{
		return productstandard;
	}

	/**
	 * Method 'setProductstandard'
	 * 
	 * @param productstandard
	 */
	public void setProductstandard(String productstandard)
	{
		this.productstandard = productstandard;
	}


	/**
	 * Method 'getUnit'
	 * 
	 * @return String
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * Method 'setUnit'
	 * 
	 * @param unit
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * Method 'getPrice'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * Method 'setPrice'
	 * 
	 * @param price
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
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
		
		if (!(_other instanceof KGoods)) {
			return false;
		}
		
		final KGoods _cast = (KGoods) _other;
		if (id == null ? _cast.id != id : !id.equals( _cast.id )) {
			return false;
		}
		
		if (productno == null ? _cast.productno != productno : !productno.equals( _cast.productno )) {
			return false;
		}
		
		if (productname == null ? _cast.productname != productname : !productname.equals( _cast.productname )) {
			return false;
		}
		
		if (producttype == null ? _cast.producttype != producttype : !producttype.equals( _cast.producttype )) {
			return false;
		}
		
		if (productstandard == null ? _cast.productstandard != productstandard : !productstandard.equals( _cast.productstandard )) {
			return false;
		}
 
		if (unit == null ? _cast.unit != unit : !unit.equals( _cast.unit )) {
			return false;
		}
		
		if (price == null ? _cast.price != price : !price.equals( _cast.price )) {
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
		
		if (productno != null) {
			_hashCode = 29 * _hashCode + productno.hashCode();
		}
		
		if (productname != null) {
			_hashCode = 29 * _hashCode + productname.hashCode();
		}
		
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		if (productstandard != null) {
			_hashCode = 29 * _hashCode + productstandard.hashCode();
		}
		if (unit != null) {
			_hashCode = 29 * _hashCode + unit.hashCode();
		}
		
		if (price != null) {
			_hashCode = 29 * _hashCode + price.hashCode();
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
		ret.append( "com.mycompany.myapp.dto.KGoods: " );
		ret.append( "id=" + id );
		ret.append( ", productno=" + productno );
		ret.append( ", productname=" + productname );
		ret.append( ", producttype=" + producttype );
		ret.append( ", productstandard=" + productstandard );
		ret.append( ", unit=" + unit );
		ret.append( ", price=" + price );
		ret.append( ", remark=" + remark );
		return ret.toString();
	}

}