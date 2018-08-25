package com.wwlwxg.springmvc.common.util;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.log4j.Logger;

/**
 * @author gacl
 * 把request对象中的请求参数封装到bean中
 */
public class WebUtils {

	static Logger logger = Logger.getLogger(WebUtils.class);
	
	/**
	 * 将request对象转换成T对象
	 * @param request 
	 * @param clazz
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
		DateConverter dateConverter = new DateConverter(null);
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy/MM/dd"});
		ConvertUtils.register(dateConverter, java.util.Date.class); 
		try{
			T bean = clazz.newInstance();
			Enumeration<String> e = request.getParameterNames(); 
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				try{
					BeanUtils.setProperty(bean, name, value);
				} catch(Exception e1) {
					logger.error(e1);
				}
			}
			return bean;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String makeId(){
		return UUID.randomUUID().toString();
	}
	
}
