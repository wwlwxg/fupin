package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.math.BigDecimal;

public class DougangIncomeBean {
	private Integer id;
	private Integer huId;
	private Integer year;
	private BigDecimal jinOld;
	private BigDecimal jinLow;
	private BigDecimal jinWu;
	private BigDecimal jinGao;
	private BigDecimal earthPower;
	private BigDecimal earthTrans;
	private BigDecimal product;
	private BigDecimal canbu;
	private BigDecimal birthPlan;
	private String remarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHuId() {
		return huId;
	}
	public void setHuId(Integer huId) {
		this.huId = huId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public BigDecimal getJinOld() {
		return jinOld;
	}
	public void setJinOld(BigDecimal jinOld) {
		this.jinOld = jinOld;
	}
	public BigDecimal getJinLow() {
		return jinLow;
	}
	public void setJinLow(BigDecimal jinLow) {
		this.jinLow = jinLow;
	}
	public BigDecimal getJinWu() {
		return jinWu;
	}
	public void setJinWu(BigDecimal jinWu) {
		this.jinWu = jinWu;
	}
	public BigDecimal getJinGao() {
		return jinGao;
	}
	public void setJinGao(BigDecimal jinGao) {
		this.jinGao = jinGao;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public BigDecimal getEarthPower() {
		return earthPower;
	}
	public void setEarthPower(BigDecimal earthPower) {
		this.earthPower = earthPower;
	}
	public BigDecimal getEarthTrans() {
		return earthTrans;
	}
	public void setEarthTrans(BigDecimal earthTrans) {
		this.earthTrans = earthTrans;
	}
	public BigDecimal getProduct() {
		return product;
	}
	public void setProduct(BigDecimal product) {
		this.product = product;
	}
	public BigDecimal getCanbu() {
		return canbu;
	}
	public void setCanbu(BigDecimal canbu) {
		this.canbu = canbu;
	}
	public BigDecimal getBirthPlan() {
		return birthPlan;
	}
	public void setBirthPlan(BigDecimal birthPlan) {
		this.birthPlan = birthPlan;
	}
	@Override
	public String toString() {
		return "DougangIncomeBean [id=" + id + ", huId=" + huId + ", year=" + year + ", jinOld=" + jinOld + ", jinLow="
				+ jinLow + ", jinWu=" + jinWu + ", jinGao=" + jinGao + ", earthPower=" + earthPower + ", earthTrans="
				+ earthTrans + ", product=" + product + ", canbu=" + canbu + ", birthPlan=" + birthPlan + ", remarks="
				+ remarks + "]";
	}
}
