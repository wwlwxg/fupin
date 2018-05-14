package com.wwlwxg.springmvc.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 自定义JdbcTemplate工具
 * @author Dyl
 * 2017-04-05 20:40:17
*/
public class JdbcTemplateUtil extends JdbcTemplate{
	/**
	 * 针对两列的list集合转化成Map
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String,Object> queryforMapByListSql(String sql,Object[] args){
		List<Map<String,Object>>  list =  queryForList(sql,args);
		Map<String,Object> returnMap = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++){
			Map<String,Object> map = list.get(i);
			for(Map.Entry<String,Object> entry : map.entrySet()){
				returnMap.put(entry.getKey(), entry.getValue());
			}
		}
		return returnMap;
	}
	/**
	 * 查询BeanList集合
	 * @param sql 查询语句
	 * @param args 参数数据
	 * @param entryClass 强转bean的类型
	 * @return
	 */
	public <T> List<T> queryForListBean(String sql,Object[] args,Class<?> entryClass){
		return BeanUtil.changeListMapToListBean(queryForList(sql,args),entryClass);
	}
	/**
	 * 查询BeanList集合
	 * @param sql 查询语句
	 * @param entryClass 强转bean的类型
	 * @return
	 */
	public <T> List<T> queryForListBean(String sql,Class<?> entryClass){
		return BeanUtil.changeListMapToListBean(queryForList(sql),entryClass);
	}
	/**
	 * 查询Bean
	 * @param sql 查询语句
	 * @param args 参数
	 * @param entryClass 强转bean的类型
	 * @return
	 */
	public <T> T queryForBean(String sql,Object[] args,Class<?> entryClass){
		return BeanUtil.changeMapToBean(queryForMap(sql, args), entryClass);
	}
	/**
	 * 查询Bean
	 * @param sql 查询语句
	 * @param entryClass 强转bean的类型
	 * @return
	 */
	public <T> T queryForBean(String sql,Class<?> entryClass){
		return BeanUtil.changeMapToBean(queryForMap(sql), entryClass);
	}
	@Override
	public <T> List<T> queryForList(String sql,Object[] args,Class<T> elementType) throws DataAccessException {
		return super.queryForList(sql, args, elementType);
	}
	/**
	 * 方法重写数据查询未空时不抛错直接返回为空
	 */
	@Override
	public Map<String, Object> queryForMap(String sql) throws DataAccessException {
		try {
			return super.queryForMap(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public Map<String, Object> queryForMap(String sql, Object... args)
			throws DataAccessException {
		try {
			return super.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public <T> T queryForObject(String sql, Class<T> requiredType) throws DataAccessException {
		try {
			return super.queryForObject(sql, requiredType);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public <T> T queryForObject(String sql, Class<T> requiredType,Object... args) throws DataAccessException {
		try {
			return super.queryForObject(sql, requiredType, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws DataAccessException {
		try {
			return super.queryForObject(sql, args, requiredType);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
