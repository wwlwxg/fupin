package com.wwlwxg.springmvc.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * <b>类说明:分页对象</b><br>
 *  
 * @author Administrator
 * @version 1.0
 * Date: 2012-7-23
 */
public class Page implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	/** 每页显示页数,默认显示20条 */
	private Integer pageSize = 12;
	/** 总条数 */
	private Integer totalCount = 0;
	/** 总页数 */
	private Integer totalPage = 0;
	/** 当前页 */
	private Integer currentPage  = 1;
	/** 起始索引 */
	private Integer startIndex = 0;
	/** 分页条件 */
	private String condition;
	/** 查询的返回值 */
	private List<?> objList;
	/** 排序 */
	private String orderBy;
	/** 查询的对象名称或表名/或查询语句 */
	private String objName;
	/** 需要查询的字段 */
	private String queryField;
	
	/** 通过JDBC查询的SQL语句 */
	private String sql;
	
	public Page(Integer totalCount,List<?> objList, Integer currentPage) {
		super();
		this.totalCount = totalCount;
		this.objList = objList;
		this.currentPage = currentPage;
	}
	
	public Page(Integer pageSize, String condition, List<?> objList,  
            String orderBy, String objName) {
		super();
		this.pageSize = pageSize;
		this.condition = condition;
		this.objList = objList;
		this.orderBy = orderBy;
		this.objName = objName;
	}
	public Page(Integer pageSize, String objName) {
		super();  
        this.pageSize = pageSize;  
        this.objName = objName;
	}
	
	public Page() {
	}
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize)
				: (totalCount / pageSize + 1);
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		if(currentPage <= 0)
			currentPage = 1;
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartIndex() {
		if(this.currentPage == null ){
			this.currentPage  = 1;
		}
		startIndex = this.pageSize * (this.currentPage - 1);
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<?> getObjList() {
		return objList;
	}

	public void setObjList(List<?> objList) {
		this.objList = objList;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
