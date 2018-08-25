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

import com.wwlwxg.springmvc.common.util.JdbcTemplateUtil;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangStudyBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangWorkBean;

@Service
public class DougangWorkService {
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	public List<DougangWorkBean> findWorkBeanListByManId(int manId) throws Exception {
		String sql = "select id"
						+ ",year"
						+ ",work_time"
						+ ",place"
						+ ",income"
						+ ",remarks from dougang_work where man_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(manId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangWorkBean>() {
			@Override
			public DougangWorkBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangWorkBean bean = new DougangWorkBean();
				bean.setId(rs.getInt("id"));
				bean.setYear(rs.getInt("year"));
				bean.setWorkTime(rs.getInt("work_time"));
				bean.setPlace(rs.getString("place"));
				bean.setIncome(rs.getBigDecimal("income"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(manId);
				
				return bean;
			}
		});
	}
	
	public DougangWorkBean findWorkBeanById(int id) throws Exception {
		String sql = "select man_id"
						+ ",year"
						+ ",work_time"
						+ ",place"
						+ ",income"
						+ ",remarks from dougang_work where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangWorkBean>() {
			@Override
			public DougangWorkBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangWorkBean bean = new DougangWorkBean();
				bean.setManId(rs.getInt("man_id"));
				bean.setYear(rs.getInt("year"));
				bean.setWorkTime(rs.getInt("work_time"));
				bean.setPlace(rs.getString("place"));
				bean.setIncome(rs.getBigDecimal("income"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setId(id);
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertWorkBean(final DougangWorkBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_work"
				+ "(man_id"
				+ ",year"
				+ ",work_time"
				+ ",place"
				+ ",income"
				+ ",remarks)"				
				+ "values"
				+ "(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getManId());
	                	ps.setInt(2, bean.getYear());
	                	ps.setInt(3, bean.getWorkTime());
	                	ps.setString(4, bean.getPlace());
	                	ps.setBigDecimal(5, bean.getIncome());
	                	ps.setString(6, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateWorkBean(final DougangWorkBean bean){
		Integer id = 0;
		final String sql = "update dougang_work set "
				+ "man_id = ?"
				+ ",year = ?"
				+ ",work_time = ?"
				+ ",place = ?"
				+ ",income = ?"
				+ ",remarks = ? where id = ?";				
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getManId());
		con.add(bean.getYear());
		con.add(bean.getWorkTime());
		con.add(bean.getPlace());
		con.add(bean.getIncome());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
