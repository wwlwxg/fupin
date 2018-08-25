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

import com.wwlwxg.springmvc.common.util.DateUtil;
import com.wwlwxg.springmvc.common.util.JdbcTemplateUtil;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangHuBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangStudyBean;

@Service
public class DougangStudyService {
	
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	public List<DougangStudyBean> findStudyBeanListByManId(int manId) throws Exception {
		String sql = "select id"
						+ ",year"
						+ ",place"
						+ ",grade"
						+ ",is_award"
						+ ",amount"
						+ ",remarks from dougang_study where man_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(manId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangStudyBean>() {
			@Override
			public DougangStudyBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangStudyBean bean = new DougangStudyBean();
				bean.setId(rs.getInt("id"));
				bean.setYear(rs.getInt("year"));
				bean.setPlace(rs.getString("place"));
				bean.setGrade(rs.getString("grade"));
				bean.setAward(rs.getBoolean("is_award"));
				bean.setAmount(rs.getBigDecimal("amount"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(manId);
				
				return bean;
			}
		});
	}
	
	public DougangStudyBean findStudyBeanById(int id) throws Exception {
		String sql = "select man_id"
						+ ",year"
						+ ",place"
						+ ",grade"
						+ ",is_award"
						+ ",amount"
						+ ",remarks from dougang_study where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangStudyBean>() {
			@Override
			public DougangStudyBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangStudyBean bean = new DougangStudyBean();
				bean.setId(id);
				bean.setManId(rs.getInt("man_id"));
				bean.setYear(rs.getInt("year"));
				bean.setPlace(rs.getString("place"));
				bean.setGrade(rs.getString("grade"));
				bean.setAward(rs.getBoolean("is_award"));
				bean.setAmount(rs.getBigDecimal("amount"));
				bean.setRemarks(rs.getString("remarks"));
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertStudyBean(final DougangStudyBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_study"
				+ "(man_id"
				+ ",year"
				+ ",place"
				+ ",grade"
				+ ",is_award"
				+ ",amount"
				+ ",remarks"
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getManId());
	                	ps.setInt(2, bean.getYear());
	                	ps.setString(3, bean.getPlace());
	                	ps.setString(4, bean.getGrade());
	                	ps.setBoolean(5, bean.isAward());
	                	ps.setBigDecimal(6, bean.getAmount());
	                	ps.setString(7, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateStudyBean(final DougangStudyBean bean){
		Integer id = 0;
		final String sql = "update dougang_study set "
				+ "man_id = ?"
				+ ",year = ?"
				+ ",place = ?"
				+ ",grade = ?"
				+ ",is_award = ?"
				+ ",amount = ?"
				+ ",remarks = ? where id = ?";				
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getManId());
		con.add(bean.getYear());
		con.add(bean.getPlace());
		con.add(bean.getGrade());
		con.add(bean.isAward());
		con.add(bean.getAmount());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
