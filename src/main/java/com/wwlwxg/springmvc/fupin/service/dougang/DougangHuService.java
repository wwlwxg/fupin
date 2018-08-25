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
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangHuBean;

@Service
public class DougangHuService {
	
	@Resource
	private JdbcTemplateUtil jdbcTemplate;

	public List<DougangHuBean> findHuBeanListByCunAndName(String cun,String name) throws Exception {
		String sql = "select hu.id as id"
						+ ",hu.cun"
						+ ",hu.poor_attri as poor_attri"
						+ ",hu.poor_cause as poor_cause"
						+ ",hu.detail_cause as detail_cause"
						+ ",hu.help_strategy as help_strategy"
						+ ",hu.inpoor_time as inpoor_time"
						+ ",hu.outpoor_time as outpoor_time"
						+ ",hu.remarks as remarks"
						+ ",hu.master as master"
						+ ",hu.count as count"
						+ ",hu.phone as phone from dougang_hu hu LEFT JOIN dougang_man man on hu.id = man.hu_id WHERE 1=1 ";
		List<Object> con = new ArrayList<Object>();
		if(!StringUtil.isEmpty(cun)) {
			sql += " and hu.cun = ?";
			con.add(cun);
		} 
		if(!StringUtil.isEmpty(name)) {
			sql += " and man.name = ?";
			con.add(name);
		}
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangHuBean>() {
			@Override
			public DougangHuBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangHuBean bean = new DougangHuBean();
				bean.setId(rs.getInt("id"));
				bean.setPoorAttri(rs.getString("poor_attri"));
				bean.setPoorCause(rs.getString("poor_cause"));
				bean.setDetailCause(rs.getString("detail_cause"));
				bean.setHelpStrategy(rs.getString("help_strategy"));
				bean.setInPoorTime(rs.getDate("inpoor_time"));
				bean.setOutPoorTime(rs.getDate("outpoor_time"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setCun(rs.getString("cun"));
				
				bean.setMaster(rs.getString("master"));
				bean.setCount(rs.getInt("count"));
				bean.setPhone(rs.getString("phone"));
				return bean;
			}
		});
	}
	
	/**
	 * 通过组来获取家庭成员
	 * 
	 * @return List<SysAuthKind
	 */
	public List<DougangHuBean> findHuBeanListByCun(String cun) throws Exception {
		String sql = "select id"
						+ ",poor_attri"
						+ ",poor_cause"
						+ ",detail_cause"
						+ ",help_strategy"
						+ ",inpoor_time"
						+ ",outpoor_time"
						+ ",remarks"
						+ ",master"
						+ ",count"
						+ ",phone from dougang_hu where cun = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(cun);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangHuBean>() {
			@Override
			public DougangHuBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangHuBean bean = new DougangHuBean();
				bean.setId(rs.getInt("id"));
				bean.setPoorAttri(rs.getString("poor_attri"));
				bean.setPoorCause(rs.getString("poor_cause"));
				bean.setDetailCause(rs.getString("detail_cause"));
				bean.setHelpStrategy(rs.getString("help_strategy"));
				bean.setInPoorTime(rs.getDate("inpoor_time"));
				bean.setOutPoorTime(rs.getDate("outpoor_time"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setCun(cun);
				
				bean.setMaster(rs.getString("master"));
				bean.setCount(rs.getInt("count"));
				bean.setPhone(rs.getString("phone"));
				return bean;
			}
		});
	}
	
	/**
	 * 通过Huid来获取户基本信息
	 * 
	 * @return List<SysAuthKind
	 */
	public DougangHuBean findHuBeanById(int id) throws Exception {
		String sql = "select cun"
						+ ",poor_attri"
						+ ",poor_cause"
						+ ",detail_cause"
						+ ",help_strategy"
						+ ",inpoor_time"
						+ ",outpoor_time"
						+ ",remarks"
						+ ",master"
						+ ",count"
						+ ",phone from dougang_hu where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangHuBean>() {
			@Override
			public DougangHuBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangHuBean bean = new DougangHuBean();
				bean.setId(id);
				bean.setPoorAttri(rs.getString("poor_attri"));
				bean.setPoorCause(rs.getString("poor_cause"));
				bean.setDetailCause(rs.getString("detail_cause"));
				bean.setHelpStrategy(rs.getString("help_strategy"));
				bean.setInPoorTime(rs.getDate("inpoor_time"));
				bean.setOutPoorTime(rs.getDate("outpoor_time"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setCun(rs.getString("cun"));
				
				bean.setMaster(rs.getString("master"));
				bean.setCount(rs.getInt("count"));
				bean.setPhone(rs.getString("phone"));
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertHuBean(final DougangHuBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_hu"
				+ "(cun"
				+ ",poor_attri"
				+ ",poor_cause"
				+ ",detail_cause"
				+ ",help_strategy"
				+ ",inpoor_time"
				+ ",outpoor_time"
				+ ",remarks"
				+ ",master"
				+ ",count"
				+ ",phone"
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,"
				+ " ?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setString(1, bean.getCun());
	                	ps.setString(2, bean.getPoorAttri());
	                	ps.setString(3, bean.getPoorCause());
	                	ps.setString(4, bean.getDetailCause());
	                	ps.setString(5, bean.getHelpStrategy());
	                	ps.setDate(6, DateUtil.date2date(bean.getInPoorTime()));
	                	ps.setDate(7, DateUtil.date2date(bean.getOutPoorTime()));
	                	ps.setString(8, bean.getRemarks());
	                	ps.setString(9, bean.getMaster());
	                	ps.setInt(10, bean.getCount());
	                	ps.setString(11, bean.getPhone());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateHuBean(final DougangHuBean bean){
		Integer id = 0;
		final String sql = "update dougang_hu set "
				+ "cun = ?"
				+ ",poor_attri = ?"
				+ ",poor_cause = ?"
				+ ",detail_cause = ?"
				+ ",help_strategy = ?"
				+ ",inpoor_time = ?"
				+ ",outpoor_time = ?"
				+ ",remarks = ?"
				+ ",master = ?"
				+ ",count = ?"
				+ ",phone = ? where id = ?";				
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getCun());
		con.add(bean.getPoorAttri());
		con.add(bean.getPoorCause());
		con.add(bean.getDetailCause());
		con.add(bean.getHelpStrategy());
		con.add(DateUtil.date2date(bean.getInPoorTime()));
		con.add(DateUtil.date2date(bean.getOutPoorTime()));
		con.add(bean.getRemarks());
		con.add(bean.getMaster());
		con.add(bean.getCount());
		con.add(bean.getPhone());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
	
}
