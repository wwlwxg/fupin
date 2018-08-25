package com.wwlwxg.springmvc.fupin.service.dougang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.wwlwxg.springmvc.common.util.DateUtil;
import com.wwlwxg.springmvc.common.util.JdbcTemplateUtil;
import com.wwlwxg.springmvc.common.util.StringUtil;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangHuBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;

@Service
public class DougangManService {
	
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	/**
	 * 通过户id获取一家人的信息
	 * 
	 */
	public List<DougangManBean> findManBeanListByHuId(int huId) throws Exception {
		String sql = "select id"
						+ ",name"
						+ ",sex"
						+ ",cer_type"
						+ ",cer_num"
						+ ",relation"
						+ ",is_study"
						+ ",is_work"
						+ ",is_sick"
						+ ",is_empty_nest_old"
						+ ",is_stay_child"
						+ ",is_soldier"
						+ ",health"
						+ ",remarks from dougang_man where hu_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(huId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangManBean>() {
			@Override
			public DougangManBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangManBean bean = new DougangManBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setSex(rs.getString("sex"));
				bean.setCerType(rs.getString("cer_type"));
				bean.setCerNum(rs.getString("cer_num"));
				bean.setRelation(rs.getString("relation"));
				bean.setStudy(rs.getBoolean("is_study"));
				bean.setWork(rs.getBoolean("is_work"));
				bean.setSick(rs.getBoolean("is_sick"));
				bean.setEmptyNestOld(rs.getBoolean("is_empty_nest_old"));
				bean.setStayChild(rs.getBoolean("is_stay_child"));
				bean.setSoldier(rs.getBoolean("is_soldier"));
				bean.setHealth(rs.getString("health"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setHuId(huId);
				
				String cerNum = bean.getCerNum();
				try{
					bean.setAge(StringUtil.idNOToAge(cerNum));
					bean.setBirth(DateUtil.parse(cerNum.substring(6, 14), "yyyyMMdd"));
				}catch(Exception e) {
					bean.setAge(0);
					bean.setBirth(null);
				}
				
				return bean;
			}
		});
	}
	
	public DougangManBean findManBeanById(int id) throws Exception {
		String sql = "select hu_id"
						+ ",name"
						+ ",sex"
						+ ",cer_type"
						+ ",cer_num"
						+ ",relation"
						+ ",is_study"
						+ ",is_work"
						+ ",is_sick"
						+ ",is_empty_nest_old"
						+ ",is_stay_child"
						+ ",is_soldier"
						+ ",health"
						+ ",remarks from dougang_man where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangManBean>() {
			@Override
			public DougangManBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangManBean bean = new DougangManBean();
				bean.setId(id);
				bean.setName(rs.getString("name"));
				bean.setSex(rs.getString("sex"));
				bean.setCerType(rs.getString("cer_type"));
				bean.setCerNum(rs.getString("cer_num"));
				bean.setRelation(rs.getString("relation"));
				bean.setStudy(rs.getBoolean("is_study"));
				bean.setWork(rs.getBoolean("is_work"));
				bean.setSick(rs.getBoolean("is_sick"));
				bean.setEmptyNestOld(rs.getBoolean("is_empty_nest_old"));
				bean.setStayChild(rs.getBoolean("is_stay_child"));
				bean.setSoldier(rs.getBoolean("is_soldier"));
				bean.setHealth(rs.getString("health"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setHuId(rs.getInt("hu_id"));
				
				String cerNum = bean.getCerNum();
				
				try {
					bean.setAge(StringUtil.idNOToAge(cerNum));
					bean.setBirth(DateUtil.parse(cerNum.substring(6, 14), "yyyyMMdd"));
				}catch(Exception e) {
					bean.setAge(0);
					bean.setBirth(null);
				}
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertManBean(final DougangManBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_man"
				+ "(hu_id"					// 1
				+ ",name"					// 2
				+ ",sex"					// 3
				+ ",cer_type"				// 4
				+ ",cer_num"				// 5
				+ ",relation"				// 6
				+ ",is_study"				// 7
				+ ",is_work"				// 8
				+ ",is_sick"				// 9
				+ ",is_empty_nest_old"		// 10
				+ ",is_stay_child"			// 11
				+ ",is_soldier"				// 12
				+ ",health"					// 13
				+ ",remarks"				// 14
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,?"
				+ " ?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getHuId());
	                	ps.setString(2, bean.getName());
	                	ps.setString(3, bean.getSex());
	                	ps.setString(4, bean.getCerType());
	                	ps.setString(5, bean.getCerNum());
	                	ps.setString(6, bean.getRelation());
	                	ps.setBoolean(7, bean.isStudy());
	                	ps.setBoolean(8, bean.isWork());
	                	ps.setBoolean(9, bean.isSick());
	                	ps.setBoolean(10, bean.isEmptyNestOld());
	                	ps.setBoolean(11, bean.isStayChild());
	                	ps.setBoolean(12, bean.isSoldier());
	                	ps.setString(13, bean.getHealth());
	                	ps.setString(14, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateManBean(final DougangManBean bean){
		Integer id = 0;
		final String sql = "update dougang_man set "
				+ "hu_id = ?"					// 1
				+ ",name = ?"					// 2
				+ ",sex = ?"					// 3
				+ ",cer_type = ?"				// 4
				+ ",cer_num = ?"				// 5
				+ ",relation = ?"				// 6
				+ ",is_study = ?"				// 7
				+ ",is_work = ?"				// 8
				+ ",is_sick = ?"				// 9
				+ ",is_empty_nest_old = ?"		// 10
				+ ",is_stay_child = ?"			// 11
				+ ",is_soldier = ?"				// 12
				+ ",health = ?"					// 13
				+ ",remarks = ? where id = ?";	// 14 // 15		
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getHuId());
		con.add(bean.getName());
		con.add(bean.getSex());
		con.add(bean.getCerType());
		con.add(bean.getCerNum());
		con.add(bean.getRelation());
		con.add(bean.isStudy());
		con.add(bean.isWork());
		con.add(bean.isSick());
		con.add(bean.isEmptyNestOld());
		con.add(bean.isStayChild());
		con.add(bean.isSoldier());
		con.add(bean.getHealth());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
