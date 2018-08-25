package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.util.Date;

public class DougangSoldierBean {
	private Integer id;
	private Integer manId;
	private String place;
	private Date dateJoin;
	private Date dateLeave;
	private String careSituation;
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
	public Date getDateJoin() {
		return dateJoin;
	}
	public void setDateJoin(Date dateJoin) {
		this.dateJoin = dateJoin;
	}
	public Date getDateLeave() {
		return dateLeave;
	}
	public void setDateLeave(Date dateLeave) {
		this.dateLeave = dateLeave;
	}
	public String getCareSituation() {
		return careSituation;
	}
	public void setCareSituation(String careSituation) {
		this.careSituation = careSituation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "DougangSoldierBean [id=" + id + ", manId=" + manId + ", place=" + place + ", dateJoin=" + dateJoin
				+ ", dateLeave=" + dateLeave + ", careSituation=" + careSituation + ", remarks=" + remarks + "]";
	}
}
