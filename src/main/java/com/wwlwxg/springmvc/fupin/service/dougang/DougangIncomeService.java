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
import com.wwlwxg.springmvc.common.util.StringUtil;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangIncomeBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;

@Service
public class DougangIncomeService {
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	public List<DougangIncomeBean> findIncomeBeanListByHuId(int huId) throws Exception {
		String sql = "select id"
						+ ",year"
						+ ",jin_old"
						+ ",jin_low"
						+ ",jin_wu"
						+ ",jin_gao"
						+ ",earth_power"
						+ ",earth_trans"
						+ ",product"
						+ ",canbu"
						+ ",birth_plan"
						+ ",remarks from dougang_income where hu_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(huId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangIncomeBean>() {
			@Override
			public DougangIncomeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangIncomeBean bean = new DougangIncomeBean();
				bean.setId(rs.getInt("id"));
				bean.setYear(rs.getInt("year"));
				bean.setJinOld(rs.getBigDecimal("jin_old"));
				bean.setJinLow(rs.getBigDecimal("jin_low"));
				bean.setJinWu(rs.getBigDecimal("jin_wu"));
				bean.setJinGao(rs.getBigDecimal("jin_gao"));
				bean.setEarthPower(rs.getBigDecimal("earth_power"));
				bean.setEarthTrans(rs.getBigDecimal("earth_trans"));
				bean.setProduct(rs.getBigDecimal("product"));
				bean.setCanbu(rs.getBigDecimal("canbu"));
				bean.setBirthPlan(rs.getBigDecimal("birth_plan"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setHuId(huId);
				
				return bean;
			}
		});
	}
	
	public DougangIncomeBean findIncomeBeanById(int id) throws Exception {
		String sql = "select hu_id"
						+ ",year"
						+ ",jin_old"
						+ ",jin_low"
						+ ",jin_wu"
						+ ",jin_gao"
						+ ",earth_power"
						+ ",earth_trans"
						+ ",product"
						+ ",canbu"
						+ ",birth_plan"
						+ ",remarks from dougang_income where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangIncomeBean>() {
			@Override
			public DougangIncomeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangIncomeBean bean = new DougangIncomeBean();
				bean.setId(id);
				bean.setYear(rs.getInt("year"));
				bean.setJinOld(rs.getBigDecimal("jin_old"));
				bean.setJinLow(rs.getBigDecimal("jin_low"));
				bean.setJinWu(rs.getBigDecimal("jin_wu"));
				bean.setJinGao(rs.getBigDecimal("jin_gao"));
				bean.setEarthPower(rs.getBigDecimal("earth_power"));
				bean.setEarthTrans(rs.getBigDecimal("earth_trans"));
				bean.setProduct(rs.getBigDecimal("product"));
				bean.setCanbu(rs.getBigDecimal("canbu"));
				bean.setBirthPlan(rs.getBigDecimal("birth_plan"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setHuId(rs.getInt("hu_id"));
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertIncomeBean(final DougangIncomeBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_income"
				+ "(hu_id"
				+ ",year"
				+ ",jin_old"
				+ ",jin_low"
				+ ",jin_wu"
				+ ",jin_gao"
				+ ",earth_power"
				+ ",earth_trans"
				+ ",product"
				+ ",canbu"
				+ ",birth_plan"
				+ ",remarks"				
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,?"
				+ ",?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getHuId());
	                	ps.setInt(2, bean.getYear());
	                	ps.setBigDecimal(3, bean.getJinOld());
	                	ps.setBigDecimal(4, bean.getJinLow());
	                	ps.setBigDecimal(5, bean.getJinWu());
	                	ps.setBigDecimal(6, bean.getJinGao());
	                	ps.setBigDecimal(7, bean.getEarthPower());
	                	ps.setBigDecimal(8, bean.getEarthTrans());
	                	ps.setBigDecimal(9, bean.getProduct());
	                	ps.setBigDecimal(10, bean.getCanbu());
	                	ps.setBigDecimal(11, bean.getBirthPlan());
	                	ps.setString(12, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateIncomeBean(final DougangIncomeBean bean){
		Integer id = 0;
		final String sql = "update dougang_income set "
				+ "hu_id = ?"
				+ ",year = ?"
				+ ",jin_old = ?"
				+ ",jin_low = ?"
				+ ",jin_wu = ?"
				+ ",jin_gao = ?"
				+ ",earth_power = ?"
				+ ",earth_trans = ?"
				+ ",product = ?"
				+ ",canbu = ?"
				+ ",birth_plan = ?"
				+ ",remarks = ? where id = ?";		
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getHuId());
		con.add(bean.getYear());
		con.add(bean.getJinOld());
		con.add(bean.getJinLow());
		con.add(bean.getJinWu());
		con.add(bean.getJinGao());
		con.add(bean.getEarthPower());
		con.add(bean.getEarthTrans());
		con.add(bean.getProduct());
		con.add(bean.getCanbu());
		con.add(bean.getBirthPlan());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
