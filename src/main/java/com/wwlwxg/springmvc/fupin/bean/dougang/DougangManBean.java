package com.wwlwxg.springmvc.fupin.bean.dougang;

import java.util.Date;

/**
 * 陡岗贫困户的人信息
 * @author jecky
 *
 */
public class DougangManBean {
	private Integer id;					// 主键id
	private Integer huId;				// 户id
	private String name;				// 人员姓名
	private String sex;					// 性别
	private String cerType;				// 证件类型
	private String cerNum;				// 证件号码
	private String relation;			// 与户主关系
	private boolean isStudy;			// 是否是学生
	private boolean isWork;				// 是否务工
	private boolean isSick;				// 是否住院
	private boolean isEmptyNestOld;		// 是否空巢老人
	private boolean isStayChild;		// 是否留守儿童
	private boolean isSoldier;			// 是否复员退伍军人
	private String health;				// 健康状况
	private String remarks;				// 备注
	
	private Date birth;
	private int age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCerType() {
		return cerType;
	}
	public void setCerType(String cerType) {
		this.cerType = cerType;
	}
	public String getCerNum() {
		return cerNum;
	}
	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public boolean isStudy() {
		return isStudy;
	}
	public void setStudy(boolean isStudy) {
		this.isStudy = isStudy;
	}
	public boolean isWork() {
		return isWork;
	}
	public void setWork(boolean isWork) {
		this.isWork = isWork;
	}
	public boolean isSick() {
		return isSick;
	}
	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}
	public boolean isEmptyNestOld() {
		return isEmptyNestOld;
	}
	public void setEmptyNestOld(boolean isEmptyNestOld) {
		this.isEmptyNestOld = isEmptyNestOld;
	}
	public boolean isStayChild() {
		return isStayChild;
	}
	public void setStayChild(boolean isStayChild) {
		this.isStayChild = isStayChild;
	}
	public boolean isSoldier() {
		return isSoldier;
	}
	public void setSoldier(boolean isSoldier) {
		this.isSoldier = isSoldier;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Integer getHuId() {
		return huId;
	}
	public void setHuId(Integer huId) {
		this.huId = huId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	@Override
	public String toString() {
		return "DougangManBean [id=" + id + ", huId=" + huId + ", name=" + name + ", sex=" + sex + ", cerType="
				+ cerType + ", cerNum=" + cerNum + ", relation=" + relation + ", isStudy=" + isStudy + ", isWork="
				+ isWork + ", isSick=" + isSick + ", isEmptyNestOld=" + isEmptyNestOld + ", isStayChild=" + isStayChild
				+ ", isSoldier=" + isSoldier + ", health=" + health + ", remarks=" + remarks + ", birth=" + birth
				+ ", age=" + age + "]";
	}
}
