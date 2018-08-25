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
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangSickBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangSoldierBean;

@Service
public class DougangSoldierService {

	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	public List<DougangSoldierBean> findSoldierBeanListByManId(int manId) throws Exception {
		String sql = "select id"
						+ ",place"
						+ ",date_join"
						+ ",date_leave"
						+ ",care_situation"
						+ ",remarks from dougang_soldier where man_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(manId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangSoldierBean>() {
			@Override
			public DougangSoldierBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangSoldierBean bean = new DougangSoldierBean();
				bean.setId(rs.getInt("id"));
				bean.setPlace(rs.getString("place"));
				bean.setDateJoin(rs.getDate("date_join"));
				bean.setDateLeave(rs.getDate("date_leave"));
				bean.setCareSituation(rs.getString("care_situation"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(manId);
				
				return bean;
			}
		});
	}
	
	public DougangSoldierBean findSoldierBeanById(int id) throws Exception {
		String sql = "select man_id"
						+ ",place"
						+ ",date_join"
						+ ",date_leave"
						+ ",care_situation"
						+ ",remarks from dougang_soldier where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangSoldierBean>() {
			@Override
			public DougangSoldierBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangSoldierBean bean = new DougangSoldierBean();
				bean.setId(id);
				bean.setPlace(rs.getString("place"));
				bean.setDateJoin(rs.getDate("date_join"));
				bean.setDateLeave(rs.getDate("date_leave"));
				bean.setCareSituation(rs.getString("care_situation"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(rs.getInt("man_id"));
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertSoldierBean(final DougangSoldierBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_soldier"
				+ "(man_id"
				+ ",place"
				+ ",date_join"
				+ ",date_leave"
				+ ",care_situation"
				+ ",remarks"
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getManId());
	                	ps.setString(2, bean.getPlace());
	                	ps.setDate(3, DateUtil.date2date(bean.getDateJoin()));
	                	ps.setDate(4, DateUtil.date2date(bean.getDateLeave()));
	                	ps.setString(5, bean.getCareSituation());
	                	ps.setString(6, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateSoldierBean(final DougangSoldierBean bean){
		Integer id = 0;
		final String sql = "update dougang_soldier set "
				+ "man_id = ?"
				+ ",place = ?"
				+ ",date_join = ?"
				+ ",date_leave = ?"
				+ ",care_situation = ?"
				+ ",remarks = ? where id = ?";				
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getManId());
		con.add(bean.getPlace());
		con.add(bean.getDateJoin());
		con.add(bean.getDateLeave());
		con.add(bean.getCareSituation());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
