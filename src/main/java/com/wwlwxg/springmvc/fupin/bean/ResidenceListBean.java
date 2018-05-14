package com.wwlwxg.springmvc.fupin.bean;

public class ResidenceListBean {
	private Integer id;
	private String master;
	private String count;
	private Integer zu;
	private String familyType;
	private String cun;
	private String rr;
	private String residenceId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Integer getZu() {
		return zu;
	}
	public void setZu(Integer zu) {
		this.zu = zu;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	public String getCun() {
		return cun;
	}
	public void setCun(String cun) {
		this.cun = cun;
	}
	public String getRr() {
		return rr;
	}
	public void setRr(String rr) {
		this.rr = rr;
	}
	public String getResidenceId() {
		return residenceId;
	}
	public void setResidenceId(String residenceId) {
		this.residenceId = residenceId;
	}
	@Override
	public String toString() {
		return "ResidenceListBean [id=" + id + ", master=" + master + ", count=" + count + ", zu=" + zu
				+ ", familyType=" + familyType + ", cun=" + cun + ", rr=" + rr + ", residenceId=" + residenceId + "]";
	}
}
