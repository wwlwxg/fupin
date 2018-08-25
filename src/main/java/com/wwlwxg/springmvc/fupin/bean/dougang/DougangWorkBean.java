package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.math.BigDecimal;

public class DougangWorkBean {
	private Integer id;					// 主键id
	private Integer manId;				// 贫困户人员id
	private Integer year;				// 年度
	private Integer workTime;			// 工作了几个月
	private String place;				// 工作地点
	private BigDecimal income;			// 收入
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
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "DougangWorkBean [id=" + id + ", manId=" + manId + ", year=" + year + ", workTime=" + workTime
				+ ", place=" + place + ", income=" + income + ", remarks=" + remarks + "]";
	}
}
