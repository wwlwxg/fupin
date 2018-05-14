package com.wwlwxg.springmvc.fupin.utils;

public class FileBean {
	private String master;//����
	private int count;
	private String group;
	private String familyType;
	private String community;
	
	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileBean(String group, String familyType, String master, int count, String community) {
		super();
		this.master = master;
		this.count = count;
		this.group = group;
		this.familyType = familyType;
		this.community = community;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	@Override
	public String toString() {
		return "FileBean [master=" + master + ", count=" + count + ", group=" + group + ", familyType=" + familyType
				+ ", community=" + community + "]";
	}
	
}
