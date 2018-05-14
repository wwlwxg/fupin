package com.wwlwxg.springmvc.common.util;

import java.math.BigDecimal;
/**
 * 此类用于特定数据库中特定的sql写法
 * @author Administrator
 *
 */
public  class DylSqlUtil {
	public static boolean isMYSQL = false;
	public static boolean isORACLE = false;
	/**
	 * 用于查询多少条记录
	 * @param num
	 * @return
	 */
	public static String getTotalNum(int num){
		if(isORACLE)return " and rownum<="+num;
		else if(isMYSQL)return  " limit "+num;
		return null;
	}
	/**
	 * 将oracle的to_char(*****)转换为对应数据库的格式
	 * @param str
	 * @return
	 */
	public static String dateToStr(String str){
		if(isORACLE)return str;
		else if(isMYSQL){
			str = str.replace("to_char", "DATE_FORMAT");
			str = str.replace("yyyy", "%Y");
			str = str.replace("MM", "%m");
			str = str.replace("mm", "%m");
			str = str.replace("dd", "%d");
			str = str.replace("HH24", "%H");
			str = str.replace("hh24", "%H");
			str = str.replace("mi", "%i");
			str = str.replace("ss", "%s");
			return str ; 
		}
		return null;
	}
	/**
	 * 将oracle的to_date(*****)转换为对应数据库的格式
	 * @param str
	 * @return
	 */
	public static String strToDate(String str){
		if(isORACLE)return str;
		else if(isMYSQL){
			str = str.replace("to_date", "str_to_date");
			str = str.replace("yyyy", "%Y");
			str = str.replace("MM", "%m");
			str = str.replace("mm", "%m");
			str = str.replace("dd", "%d");
			str = str.replace("HH24", "%H");
			str = str.replace("hh24", "%H");
			str = str.replace("mi", "%i");
			str = str.replace("ss", "%s");
			return str ; 
		}
		return null;
	}
	/**
	 * 获取行转列的函数
	 * @return
	 */
	public static String getRowToLine(){
		if(isORACLE)return "wm_concat";
		else if(isMYSQL)return  "group_concat";
		return null;
	}
	public static String getnextSeqNextVal(){
		if(isORACLE)return "seq_id.nextval";
		else if(isMYSQL)return  "func_nextval('seq_id')";
		return null;
	}
	/**
	 * 获取数据库当前系统时间
	 * @return
	 */
	public static String getDbNowDate(){
		if(isORACLE)return "sysdate";
		else if(isMYSQL)return  "now()";
		return null;
	}
	/**
	 * 将oracle的decode转换为标准的 case when格式
	 * @param str
	 * @return
	 */
	public static String decodeToCaseWhen(String str){
		if(isORACLE)return str;
		else if(isMYSQL){
			String newStr="";
			if(str.indexOf("decode")!=-1){
				newStr = " (case ";
				str = str.substring(str.indexOf("(")+1,str.lastIndexOf(")"));
				String arr[] = str.split(",");
				if(arr.length%2==1){//奇数则是正常的1对1
					for (int i = 1; i < arr.length; i=i+2) {
						if(arr[i].equalsIgnoreCase("null"))newStr+=" when "+arr[0]+" is null or "+arr[0]+"='' then "+arr[i+1];//mysql中需要判断null和空字符串
						else newStr+=" when "+arr[0]+"="+arr[i]+" then "+arr[i+1];
					}
					newStr+=" end) ";
				}else{//偶数位最后一个是else
					for (int i = 1; i < arr.length-1; i=i+2) {
						if(arr[i].equalsIgnoreCase("null"))newStr+=" when "+arr[0]+" is null or "+arr[0]+"='' then "+arr[i+1];//mysql中需要判断null和空字符串
						else newStr+=" when "+arr[0]+"="+arr[i]+" then "+arr[i+1];
					}
					newStr+=" else "+arr[arr.length-1];
					newStr+=" end) ";
				}
			}else{
				newStr=str;
			}
			return  newStr;
		}
		return null;
	}
}
