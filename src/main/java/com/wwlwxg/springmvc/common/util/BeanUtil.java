package com.wwlwxg.springmvc.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
public class BeanUtil {
	public static Map<String,Field[]> BeanCacheMap = new HashMap<String, Field[]>();
	public static Field[] getentryClassField(Object obj,String key) throws ClassNotFoundException{
		if(BeanCacheMap.get(key)==null){
			Field[] fields = obj.getClass().getDeclaredFields();
			//如果存在父类，则将父类的方法也一起装载进来
			Class<?> clz = obj.getClass().getSuperclass();
			while(!clz.getName().equals("java.lang.Object")){
				fields  =ArrayUtils.addAll(fields, clz.getDeclaredFields());
				clz=Class.forName(clz.getSuperclass().getName());
			}
			BeanCacheMap.put(key, fields);
			return fields;
		}
		return BeanCacheMap.get(key);
	}
	/**
	 * 注意:jdbctemplate 查出什么类型 bean就必须要定义相同的类型才能进行转换
	 * @param list 查询出来的List<Map<String,Object>>集合
	 * @param entryClass object 传入的javaBean
	 * @param oneToOneStr 一对一强制转换（必须是相同类型）例："tb_Id:projectid,PROJECTID:projectid", 冒号前面的为map的key不区分大小写，后面的必须与javabean保持一致。
	 * @param isShowLog 是否输出日志。
	 * @return 新的List集合
	 * @throws Exception
	 * @author dyl 2016.04.27
	 */
	public static <T> T changeMapToBean(Map<String,Object> map,Class<?> entryClass){
		try {
			if(map==null)return null;
			Object obj  = Class.forName(entryClass.getName()).newInstance();//new 一个实例
			//获取当前类中的所有属性
			Field[] fields =  getentryClassField(obj,entryClass.getName());
			for (String key:map.keySet()){
				if(map.get(key)==null)continue;//为空则不进行装载
				setValueInObj(fields, key, map, obj);
			}
			return (T)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void setValueInObj(Field[] fields,String key,Map<String,Object> map,Object obj){
		String keyTemp = key;
		if(key.contains("_"))keyTemp=key.replace("_", "");//去掉table中的_与bean做模糊匹配
		for (int j = 0; j < fields.length; j++){
			if(fields[j].getName().equalsIgnoreCase(keyTemp)){
				//构造方法名称
				String method = "set"+fields[j].getName().substring(0, 1).toUpperCase()+fields[j].getName().substring(1, fields[j].getName().length());
				try {
					//如果jdbcTemplate查询出来的类型与javaBean的类型不一致
					if(fields[j].getType().toString().indexOf(getType(map.get(key)))==-1){
						map.put(key,getNewBean(map.get(key),fields[j].getType().toString()));
					}
					//如果为空，则不塞入javabean中
					if(map.get(key)!=null)MethodUtils.invokeMethod(obj,method,map.get(key));//没有此方法直接跳过
				} catch (Exception e){
				}
				return;
			}
		}
	}
	/**
	 * 
	 * @param list 查询出来的List<Map<String,Object>>集合
	 * @param entryClass object 传入的javaBean
	 * @param oneToOneStr 一对一强制转换（必须是相同类型）例："tb_Id:projectid,PROJECTID:projectid", 冒号前面的为map的key不区分大小写，后面的必须与javabean保持一致。
	 * @param isShowLog 是否输出日志。
	 * @return 新的List集合
	 * @throws Exception
	 * @author dyl 2016.04.27
	 */
	public  static <T> List<T> changeListMapToListBean(List<Map<String,Object>> list,Class<?> entryClass){
		if(list==null||list.size()==0)return (List<T>)list;
		List newList = new LinkedList();
		//获取类中的所有属性
		for (int i = 0; i < list.size(); i++){
			newList.add(changeMapToBean(list.get(i), entryClass));
		}
		return newList;
	}
	/**
	 * 简单类型互转  先将所有的类型转换为BigDecimal再进行转换
	 * @param srcjavaType
	 * @param destJavaType
	 * @return
	 */
	private static Object getNewBean(Object srcjavaType,String destJavaType){
		BigDecimal src = null;
		if (srcjavaType instanceof BigDecimal){
			 src = (BigDecimal)srcjavaType;
		}else if(srcjavaType instanceof Integer){
			 src = new BigDecimal((Integer)srcjavaType);
		}else if(srcjavaType instanceof Double){
			 src = new BigDecimal((Double)srcjavaType);
		}else if(srcjavaType instanceof Float){
			 src = new BigDecimal((Float)srcjavaType);
		}else if(srcjavaType instanceof Long){
			 src = new BigDecimal((Long)srcjavaType);
		}
		if (destJavaType.indexOf("Integer")!=-1) {
			   return  src.intValue();
		} else if (destJavaType.indexOf("String")!=-1) {
			   return String.valueOf(src);
		} else if (destJavaType.indexOf("Double")!=-1) {
			   return src.doubleValue();
		} else if (destJavaType.indexOf("Float")!=-1) {
			   return src.floatValue();
		} else if (destJavaType.indexOf("Long")!=-1) {
			   return src.longValue();
		} 
		return null;
	}
	private static String getType(Object obj){
		   if (obj instanceof Integer) {
			   return "Integer";
		   } else if (obj instanceof String) {
			   return "String";
		   } else if (obj instanceof Double) {
			   return "Double";
		   } else if (obj instanceof Float) {
			   return "Float";
		   } else if (obj instanceof Long) {
			   return "Long";
		   } else if (obj instanceof Boolean) {
			   return "Boolean";
		   } else if (obj instanceof Date) {
			   return "Date";
		   } else if (obj instanceof BigDecimal) {
			   return "BigDecimal";
		   }  
		   return "";
		}
	/**
	 * 说明：将javaBean中的属性按照str规则生成JsonObject 只支持一级分类
	 * @param object 传入的javaBean
	 * @param str 组装字符串 例如 "sysUserId:id,name:username,phone:mobile"
	 * @param isaddAll 是否组装除字符串str之外的属性
	 * @param isNullableShow 属性为空时是否组装到json之中去
	 * @return
	 * @throws Exception
	 * @author dyl  2016.01.27
	 */
	public static JSONObject changeJavaBeanToJsonObject(Object object,String str,boolean isaddAll,boolean isNullableShow) throws Exception{
		JSONObject json = new JSONObject();
		if(str!=null){
			String origins="";
			String[] oneToOne = str.split(",");
			for (int i = 0; i < oneToOne.length; i++) {
				String[] arr = oneToOne[i].split(":");
				String origin=arr[0];
				String target=arr[1];
				String method = "get"+origin.substring(0, 1).toUpperCase()+origin.substring(1, origin.length());
				Object ret = MethodUtils.invokeMethod(object,method,null);//没有此方法直接跳过
				origins+="@"+origin+"@";
				json.put(target, ret);
			}
			if(isaddAll){
				/* Field[] fields =  object.getClass().getDeclaredFields();
				 for (int i = 0; i < fields.length; i++) {
					System.out.println(fields[i].getName());
				 }*/
				Method[] sourceMethods = object.getClass().getDeclaredMethods();//获取到已经声明的方法
				for (int i = 0; i < sourceMethods.length; i++) {
					if(sourceMethods[i].getName().startsWith("get")&&origins.indexOf(sourceMethods[i].getName())==-1){
						String key_ = sourceMethods[i].getName().replace("get", "");
						String key = key_.substring(0, 1).toLowerCase()+key_.substring(1, key_.length());
						Object ret = MethodUtils.invokeMethod(object,sourceMethods[i].getName(),null);//没有此方法直接跳过
						if(ret==null&&isNullableShow)ret="";
						json.put(key, ret);
					}
				}
			}
		}
		return json;
	}
	/**
	 * 说明：该方法是将json对象中的key 转换成所需要的javabean的属性之中去  只支持一级分类
	 * @param jsonObject 传入的Json对象
	 * @param str str 组装字符串 例如  "id:sysUserId,username:name,mobile:phone"
	 * @param entryClass 需要回传的javaBean.class
	 * @param isaddAll 是否组装除字符串str之外的属性
	 * @return
	 * @throws Exception
	 * @author dyl 2016.01.27
	 */
	public static Object changeJsonObjectToJavabean(JSONObject jsonObject,String str,Class<?> entryClass,boolean isaddAll) throws Exception{
		Class<?> demo1=Class.forName(entryClass.getName());
		Object object = demo1.newInstance();
		if(str!=null){
			String origins="";
			String[] oneToOne = str.split(",");
			for (int i = 0; i < oneToOne.length; i++) {
				String[] arr = oneToOne[i].split(":");
				String origin=arr[0];
				String target=arr[1];
				String method = "set"+target.substring(0, 1).toUpperCase()+target.substring(1, target.length());
				if (jsonObject.get(origin)!=null) {
					MethodUtils.invokeMethod(object,method,jsonObject.get(origin));
				}
				origins+="@"+origin+"@";
			}
			if(isaddAll){//
				for(String key : jsonObject.keySet()){
					if(origins.indexOf("@"+key+"@")==-1){
						String method = "set"+key.substring(0, 1).toUpperCase()+key.substring(1, key.length());
						try {
							MethodUtils.invokeMethod(object,method,jsonObject.get(key));//没有此方法直接跳过
						} catch (Exception e) {
						}
					}
				}
			}
		}
		return object;
	}
}
