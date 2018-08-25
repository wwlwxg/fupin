package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.util.Date;

/**
 * 陡岗户信息
 * @author jecky
 *
 */
public class DougangHuBean {
	private Integer id;				// 主键id
	private String poorAttri;		// 贫困户属性
	private String poorCause;		// 主要致贫原因
	private String detailCause;		// 纳入时贫困的详细原因
	private String helpStrategy;	// 帮扶措施
	private Date inPoorTime;		// 纳入贫困户时间
	private Date outPoorTime;		// 脱贫时间
	private String cun;				// 村
	private String remarks;			// 备注
	// 扩展字段，在man表户主更新的时候，更新这几个字段
	private String master;			// 户主姓名
	private Integer count;			// 户人数
	private String phone;			// 电话号码
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPoorAttri() {
		return poorAttri;
	}
	public void setPoorAttri(String poorAttri) {
		this.poorAttri = poorAttri;
	}
	public String getPoorCause() {
		return poorCause;
	}
	public void setPoorCause(String poorCause) {
		this.poorCause = poorCause;
	}
	public String getDetailCause() {
		return detailCause;
	}
	public void setDetailCause(String detailCause) {
		this.detailCause = detailCause;
	}
	public String getHelpStrategy() {
		return helpStrategy;
	}
	public void setHelpStrategy(String helpStrategy) {
		this.helpStrategy = helpStrategy;
	}
	public Date getInPoorTime() {
		return inPoorTime;
	}
	public void setInPoorTime(Date inPoorTime) {
		this.inPoorTime = inPoorTime;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getOutPoorTime() {
		return outPoorTime;
	}
	public void setOutPoorTime(Date outPoorTime) {
		this.outPoorTime = outPoorTime;
	}
	public String getCun() {
		return cun;
	}
	public void setCun(String cun) {
		this.cun = cun;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "DougangHuBean [id=" + id + ", poorAttri=" + poorAttri + ", poorCause=" + poorCause + ", detailCause="
				+ detailCause + ", helpStrategy=" + helpStrategy + ", inPoorTime=" + inPoorTime + ", outPoorTime="
				+ outPoorTime + ", cun=" + cun + ", remarks=" + remarks + ", master=" + master + ", count="
				+ count + ", phone=" + phone + "]";
	}
	
}
