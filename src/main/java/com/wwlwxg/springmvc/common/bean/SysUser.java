/**
 * 由 EasyCode自动生成
 * @author Dyl
 * 2017-03-23 20:40:17
*/
package com.wwlwxg.springmvc.common.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
	/*字段说明：
	*对应db字段名:id 类型:NUMBER(22) 主键 
	*是否可以为空:否
	*/
	
	private  BigDecimal  id;
	
	/*字段说明：用户名
	*对应db字段名:username 类型:VARCHAR2(50)  
	*是否可以为空:否
	*/
	
	private  String  username;
	
	/*字段说明：姓名
	*对应db字段名:name 类型:VARCHAR2(50)  
	*是否可以为空:是
	*/
	
	private  String  name;
	
	/*字段说明：密码
	*对应db字段名:password 类型:VARCHAR2(100)  
	*是否可以为空:是
	*/
	
	private  String  password;
	
	/*字段说明：邮箱
	*对应db字段名:email 类型:VARCHAR2(100)  
	*是否可以为空:是
	*/
	
	private  String  email;
	
	/*字段说明：电话
	*对应db字段名:phone 类型:VARCHAR2(100)  
	*是否可以为空:是
	*/
	
	private  String  phone;
	
	/*字段说明：状态0:正常,1禁用
	*对应db字段名:state 类型:CHAR(1)  
	*是否可以为空:是
	*/
	
	private  String  state;
	
	/*字段说明：创建时间
	*对应db字段名:create_time 类型:DATE(7)  
	*是否可以为空:是
	*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private  Date  createTime;
	
	/*字段说明：创建者
	*对应db字段名:creator 类型:NUMBER(22)  
	*是否可以为空:是
	*/
	
	private  BigDecimal  creator;
	
	//额外字段
	private String roleNames;
	//角色集合
	private BigDecimal[] roleIds;
	//菜单权限集合
	private Map<BigDecimal,Set<BigDecimal>> menuAuthMap;
	
	
	public Map<BigDecimal, Set<BigDecimal>> getMenuAuthMap() {
		return menuAuthMap;
	}
	public void setMenuAuthMap(Map<BigDecimal, Set<BigDecimal>> menuAuthMap) {
		this.menuAuthMap = menuAuthMap;
	}
	public BigDecimal[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(BigDecimal[] roleIds) {
		this.roleIds = roleIds;
	}
	private boolean isAdmin = false;
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public BigDecimal getCreator() {
        return creator;
    }
    public void setCreator(BigDecimal creator) {
        this.creator = creator;
    }
	public String toString() {
		return 
		"id:"+id+"\n"+
		
		"username:"+username+"\n"+
		
		"name:"+name+"\n"+
		
		"password:"+password+"\n"+
		
		"email:"+email+"\n"+
		
		"phone:"+phone+"\n"+
		
		"state:"+state+"\n"+
		
		"createTime:"+createTime+"\n"+
		
		"creator:"+creator+"\n"+
		"";
	}
}
