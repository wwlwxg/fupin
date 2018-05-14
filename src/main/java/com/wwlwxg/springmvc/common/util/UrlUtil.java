package com.wwlwxg.springmvc.common.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class UrlUtil {
	public static String getRealUrl(HttpServletRequest request){
		String url = "";
		url = request.getScheme() +"://" + request.getServerName()  
	                    + ":" +request.getServerPort() 
	                    + request.getServletPath();
		if (request.getQueryString() != null){//获取get参数
			url += "?" + request.getQueryString();
		}
		//获取post参数
		Map<String,String[]>  map =  request.getParameterMap();
		int n = 0 ;
		for (Entry<String,String[]>  entry : map.entrySet()) {
			String v = StringUtils.join(entry.getValue(),",");
			if(url.indexOf("?")==-1&&n==0)url+="?"+entry.getKey()+"="+v;
			else url+="&"+entry.getKey()+"="+v;
			n++;
		}
		return url;
	}
	public static String getUrl(HttpServletRequest request){
		String url = request.getServletPath();
		return url;
	}
	public static String getUrlPara(HttpServletRequest request){
		String urlPara = "";
		if (request.getQueryString() != null){//获取get参数
			urlPara += "?" + request.getQueryString();
		}
		//获取post参数
		Map<String,String[]>  map =  request.getParameterMap();
		int n = 0 ;
		for (Entry<String,String[]>  entry : map.entrySet()) {
			String v = StringUtils.join(entry.getValue(),",");
			if(urlPara.indexOf("?")==-1&&n==0)urlPara+="?"+entry.getKey()+"="+v;
			else urlPara+="&"+entry.getKey()+"="+v;
			n++;
		}
		return urlPara;
	}
}
