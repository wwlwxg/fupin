package com.wwlwxg.springmvc.fupin.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 家庭信息
 * @author coco
 *
 */
public class ResidenceBean {
	
	private Integer	id;
	private String	xiangzhen;			 		// 乡镇
	private String 	cun;						// 村
	private Integer	zu;							// 组
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date 	build_date;					// 建档/更新日期
	private String 	master;						// 户主
	private String 	family_type;				// 家庭类型
	private String 	door_plate;					// 门牌
	private String  star_level;					// 农户星级
	private String 	house_summary;				// 家庭住房情况
	private String 	earth_summary;				// 承包土地情况
	private String 	income;						// 主要收入来源
	private String 	family_state;				// 户情
	private String 	weiwen;						// 各级走访慰问情况
	private String 	situation_explanation;		// 有关情况说明
	
	private List<ManBean> list;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXiangzhen() {
		return xiangzhen;
	}
	public void setXiangzhen(String xiangzhen) {
		this.xiangzhen = xiangzhen;
	}
	public String getCun() {
		return cun;
	}
	public void setCun(String cun) {
		this.cun = cun;
	}
	public int getZu() {
		return zu;
	}
	public void setZu(Integer zu) {
		this.zu = zu;
	}
	public Date getBuild_date() {
		return build_date;
	}
	public void setBuild_date(Date build_date) {
		this.build_date = build_date;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getFamily_type() {
		return family_type;
	}
	public void setFamily_type(String family_type) {
		this.family_type = family_type;
	}
	public String getDoor_plate() {
		return door_plate;
	}
	public void setDoor_plate(String door_plate) {
		this.door_plate = door_plate;
	}
	public String getStar_level() {
		return star_level;
	}
	public void setStar_level(String star_level) {
		this.star_level = star_level;
	}
	public String getHouse_summary() {
		return house_summary;
	}
	public void setHouse_summary(String house_summary) {
		this.house_summary = house_summary;
	}
	public String getEarth_summary() {
		return earth_summary;
	}
	public void setEarth_summary(String earth_summary) {
		this.earth_summary = earth_summary;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getFamily_state() {
		return family_state;
	}
	public void setFamily_state(String family_state) {
		this.family_state = family_state;
	}
	public String getWeiwen() {
		return weiwen;
	}
	public void setWeiwen(String weiwen) {
		this.weiwen = weiwen;
	}
	public String getSituation_explanation() {
		return situation_explanation;
	}
	public void setSituation_explanation(String situation_explanation) {
		this.situation_explanation = situation_explanation;
	}
	
	public List<ManBean> getList() {
		return list;
	}
	public void setList(List<ManBean> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ResidenceBean [id=" + id + ", xiangzhen=" + xiangzhen + ", cun=" + cun + ", zu=" + zu + ", build_date="
				+ build_date + ", master=" + master + ", family_type=" + family_type + ", door_plate=" + door_plate
				+ ", star_level=" + star_level + ", house_summary=" + house_summary + ", earth_summary=" + earth_summary
				+ ", income=" + income + ", family_state=" + family_state + ", weiwen=" + weiwen
				+ ", situation_explanation=" + situation_explanation + ", list=" + list + "]";
	}
}
