package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.math.BigDecimal;
import java.util.Date;

public class DougangSickBean {
	private Integer id;
	private Integer manId;
	private Integer year;
	private String place;
	private Date beginTime;
	private Date endTime;
	private BigDecimal fei;
	private BigDecimal baoNong;
	private BigDecimal baoPoor;
	private BigDecimal baoBigsick;
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getFei() {
		return fei;
	}
	public void setFei(BigDecimal fei) {
		this.fei = fei;
	}
	public BigDecimal getBaoNong() {
		return baoNong;
	}
	public void setBaoNong(BigDecimal baoNong) {
		this.baoNong = baoNong;
	}
	public BigDecimal getBaoPoor() {
		return baoPoor;
	}
	public void setBaoPoor(BigDecimal baoPoor) {
		this.baoPoor = baoPoor;
	}
	public BigDecimal getBaoBigsick() {
		return baoBigsick;
	}
	public void setBaoBigsick(BigDecimal baoBigsick) {
		this.baoBigsick = baoBigsick;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "DougangSickBean [id=" + id + ", manId=" + manId + ", year=" + year + ", place=" + place + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", fei=" + fei + ", baoNong=" + baoNong + ", baoPoor="
				+ baoPoor + ", baoBigsick=" + baoBigsick + ", remarks=" + remarks + "]";
	}
}
