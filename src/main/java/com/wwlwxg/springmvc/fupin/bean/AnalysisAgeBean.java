package com.wwlwxg.springmvc.fupin.bean;

public class AnalysisAgeBean {
	private Integer zu;				// 组
	private String sex;				// 性别
	private Integer renSum;			// 人员总数
	private Integer huSum;			// 户总数
	private Double  earthSum;		// 土地	单独sql计算
	private Integer canji;			// 残疾人数
	private Integer sixteen;		// 小于16
	private Integer sixty;			// 小于60
	private Integer sixFive;		// 小于65
	private Integer eighty;			// 小于80
	private Integer hun;			// 小于100
	private Integer hunBig;			// 大于100
	public Integer getZu() {
		return zu;
	}
	public void setZu(Integer zu) {
		this.zu = zu;
	}
	public Integer getRenSum() {
		return renSum;
	}
	public void setRenSum(Integer renSum) {
		this.renSum = renSum;
	}
	public Integer getHuSum() {
		return huSum;
	}
	public void setHuSum(Integer huSum) {
		this.huSum = huSum;
	}
	public Double getEarthSum() {
		return earthSum;
	}
	public void setEarthSum(Double earthSum) {
		this.earthSum = earthSum;
	}
	public Integer getCanji() {
		return canji;
	}
	public void setCanji(Integer canji) {
		this.canji = canji;
	}
	public Integer getSixteen() {
		return sixteen;
	}
	public void setSixteen(Integer sixteen) {
		this.sixteen = sixteen;
	}
	public Integer getSixty() {
		return sixty;
	}
	public void setSixty(Integer sixty) {
		this.sixty = sixty;
	}
	public Integer getSixFive() {
		return sixFive;
	}
	public void setSixFive(Integer sixFive) {
		this.sixFive = sixFive;
	}
	public Integer getEighty() {
		return eighty;
	}
	public void setEighty(Integer eighty) {
		this.eighty = eighty;
	}
	public Integer getHun() {
		return hun;
	}
	public void setHun(Integer hun) {
		this.hun = hun;
	}
	public Integer getHunBig() {
		return hunBig;
	}
	public void setHunBig(Integer hunBig) {
		this.hunBig = hunBig;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "AnalysisAgeBean [zu=" + zu + ", sex=" + sex + ", renSum=" + renSum + ", huSum=" + huSum + ", earthSum="
				+ earthSum + ", canji=" + canji + ", sixteen=" + sixteen + ", sixty=" + sixty + ", sixFive=" + sixFive
				+ ", eighty=" + eighty + ", hun=" + hun + ", hunBig=" + hunBig + "]";
	}
}
