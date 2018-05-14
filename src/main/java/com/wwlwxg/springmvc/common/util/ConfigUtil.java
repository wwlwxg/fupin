package com.wwlwxg.springmvc.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/** 
* <DL>
* <DT><B> Title: 读取配置文件 </B></DT>
* <p>
* <DD> Description:  配置文件工具类</DD>
* </DL>
* <p>
* Copyright: Copyright (c) 2011 Company:northking
* 
* @author tc
* @version 1.00, 2011-6-22 15:47:55
*/
public class ConfigUtil {
	private static Properties props = new Properties();
	static {
		InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(
				"config/config.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key,value设置常量值
	 * 
	 * @param key     
	 * @param value
	 */
	/*public static void setValue(String key, String value) {
		Map<String, String> map = DictUtils.getSharkConfigMap();
		map.put(key, value);
	}*/
	
	/**
	 * 根据key获取配置值
	 * 
	 * @param key     
	 * @return
	 */
	public static String getValue(String key) {
		//return DictUtils.getSharkConfigMap().get(key);
		return props.getProperty(key);
	}
	
	/**
	 * 根据key获取常量值
	 * 
	 * @param key     
	 * @return
	 */
	/*public static String getConstantValue(String key) {
		return DictUtils.getSharkConstantMap().get(key);
	}*/
	
	/**
	 * 根据配置的规则项返回内容
	 * 
	 * @param key        规则的名称(同配置参数名)
	 * @param param		 入口参数(传入的字段，不定长)
	 * @return   Object  返回规则生成结果
	 * @throws Exception
	 */
	public static Object getValueByRule(String key,Object...param) throws Exception{
		ScriptEngineManager maneger = new ScriptEngineManager();
		ScriptEngine engine = maneger.getEngineByName("JavaScript");
		//String configValue = ConfigUtil.getConstantValue(key);
		String configValue =  props.getProperty(key);
		engine.eval(configValue);
        Invocable invocable = (Invocable) engine;
		Object result = invocable.invokeFunction(key, param);
		return result;
	}
	
}
