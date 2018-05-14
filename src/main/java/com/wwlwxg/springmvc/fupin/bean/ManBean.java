package com.wwlwxg.springmvc.fupin.bean;

public class ManBean {
	private int id;
	private String relation; 				// 户主和与户主关系
	private String name;					// 姓名
	private String sex;						// 性别
	private String education;				// 文化程度
	private String politics;				// 政治面貌
	private String registration_nature;		// 户籍性质
	private String health_state;			// 健康状态
	private String id_no;					// 身份证号码
	private String address;					// 从业及地址
	private String phone;					// 联系电话
	private String remarks;					// 备注
	private int residence_id;				// 家庭表的外检
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPolitics() {
		return politics;
	}
	public void setPolitics(String politics) {
		this.politics = politics;
	}
	public String getRegistration_nature() {
		return registration_nature;
	}
	public void setRegistration_nature(String registration_nature) {
		this.registration_nature = registration_nature;
	}
	public String getHealth_state() {
		return health_state;
	}
	public void setHealth_state(String health_state) {
		this.health_state = health_state;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getResidence_id() {
		return residence_id;
	}
	public void setResidence_id(int residence_id) {
		this.residence_id = residence_id;
	}
	@Override
	public String toString() {
		return "ManInformation [id=" + id + ", relation=" + relation + ", name=" + name + ", sex=" + sex
				+ ", education=" + education + ", politics=" + politics + ", registration_nature=" + registration_nature
				+ ", health_state=" + health_state + ", id_no=" + id_no + ", address=" + address + ", phone=" + phone
				+ ", remarks=" + remarks + ", residence_id=" + residence_id + "]";
	}
	
}
