package com.wwlwxg.springmvc.common.bean;

/**
 * 由 EasyCode自动生成
 * @author Dyl
 * 2017-06-05 17:33:32
*/

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
	/*字段说明：
	*对应db字段名:id 类型:BIGINT(20) 主键 
	*是否可以为空:是
	*/
	
	private  Long  id;
	
	/*字段说明：
	*对应db字段名:url 类型:VARCHAR(255)  
	*是否可以为空:是
	*/
	
	private  String  url;
	
	/*字段说明：
	*对应db字段名:para 类型:VARCHAR(255)  
	*是否可以为空:是
	*/
	
	private  String  para;
	
	/*字段说明：登陆用户
	*对应db字段名:loginuser 类型:BIGINT(20)  
	*是否可以为空:是
	*/
	
	private  Long  loginuser;
	
	/*字段说明：Ip地址
	*对应db字段名:ip 类型:VARCHAR(255)  
	*是否可以为空:是
	*/
	
	private  String  ip;
	
	public SysLog() {
	}
	public SysLog(String url, String para, Long loginuser, String ip,Date time) {
		super();
		this.url = url;
		this.para = para;
		this.loginuser = loginuser;
		this.ip = ip;
		this.time = time;
	}
	/*字段说明：
	*对应db字段名:time 类型:TIMESTAMP(19)  
	*是否可以为空:是
	*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private  Date  time;
	
	
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPara() {
        return para;
    }
    public void setPara(String para) {
        this.para = para;
    }
    public Long getLoginuser() {
        return loginuser;
    }
    public void setLoginuser(Long loginuser) {
        this.loginuser = loginuser;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
	public String toString() {
		return 
		"id:"+id+"\n"+
		
		"url:"+url+"\n"+
		
		"para:"+para+"\n"+
		
		"loginuser:"+loginuser+"\n"+
		
		"ip:"+ip+"\n"+
		
		"time:"+time+"\n"+
		"";
	}
}
