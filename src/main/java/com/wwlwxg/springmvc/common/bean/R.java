package com.wwlwxg.springmvc.common.bean;

import java.io.Serializable;


/**
 * 返回数据
 * 
 * @author dyl
 * @email 84829698@qq.com
 * @date 2017年03月06日 下午9:59:27
 */
public class R implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean result;//返回结果
	private String msg;//返回消息
	public R() {
	}
	public R(boolean result,String msg) {
		this.result=result;
		this.msg = msg;
	}
	public R(boolean result) {
		this.result=result;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
