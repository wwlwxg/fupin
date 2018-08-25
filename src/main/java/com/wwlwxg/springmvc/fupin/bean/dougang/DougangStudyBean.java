package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.math.BigDecimal;

public class DougangStudyBean {
	private Integer id;
	private Integer manId;
	private Integer year;
	private String place;
	private String grade;
	private boolean isAward;
	private BigDecimal amount;
	private String remarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getManId() {
		return manId;
	}
	public void setManId(Integer manId) {
		this.manId = manId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isAward() {
		return isAward;
	}
	public void setAward(boolean isAward) {
		this.isAward = isAward;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "DougangStudyBean [id=" + id + ", manId=" + manId + ", year=" + year + ", place=" + place + ", grade="
				+ grade + ", isAward=" + isAward + ", amount=" + amount + ", remarks=" + remarks + "]";
	}
}
