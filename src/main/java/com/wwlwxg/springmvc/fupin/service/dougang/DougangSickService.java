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
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangStudyBean;

@Service
public class DougangSickService {
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	public List<DougangSickBean> findSickBeanListByManId(int manId) throws Exception {
		String sql = "select id"
						+ ",year"
						+ ",place"
						+ ",beginTime"
						+ ",endTime"
						+ ",fei"
						+ ",bao_nong"
						+ ",bao_poor"
						+ ",bao_bigsick"
						+ ",remarks from dougang_sick where man_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(manId);
		return jdbcTemplate.query(sql, con.toArray(), new RowMapper<DougangSickBean>() {
			@Override
			public DougangSickBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangSickBean bean = new DougangSickBean();
				bean.setId(rs.getInt("id"));
				bean.setYear(rs.getInt("year"));
				bean.setPlace(rs.getString("place"));
				bean.setBeginTime(rs.getDate("beginTime"));
				bean.setEndTime(rs.getDate("endTime"));
				bean.setFei(rs.getBigDecimal("fei"));
				bean.setBaoNong(rs.getBigDecimal("bao_nong"));
				bean.setBaoPoor(rs.getBigDecimal("bao_poor"));
				bean.setBaoBigsick(rs.getBigDecimal("bao_bigsick"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(manId);
				
				return bean;
			}
		});
	}
	
	public DougangSickBean findSickBeanById(int id) throws Exception {
		String sql = "select man_id"
						+ ",year"
						+ ",place"
						+ ",beginTime"
						+ ",endTime"
						+ ",fei"
						+ ",bao_nong"
						+ ",bao_poor"
						+ ",bao_bigsick"
						+ ",remarks from dougang_sick where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, con.toArray(), new RowMapper<DougangSickBean>() {
			@Override
			public DougangSickBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DougangSickBean bean = new DougangSickBean();
				bean.setId(id);
				bean.setYear(rs.getInt("year"));
				bean.setPlace(rs.getString("place"));
				bean.setBeginTime(rs.getDate("beginTime"));
				bean.setEndTime(rs.getDate("endTime"));
				bean.setFei(rs.getBigDecimal("fei"));
				bean.setBaoNong(rs.getBigDecimal("bao_nong"));
				bean.setBaoPoor(rs.getBigDecimal("bao_poor"));
				bean.setBaoBigsick(rs.getBigDecimal("bao_bigsick"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setManId(rs.getInt("man_id"));
				
				return bean;
			}
		});
	}
	
	@Transactional
	public int insertSickBean(final DougangSickBean bean){
		Integer id = 0;
		final String sql = "insert into dougang_sick"
				+ "(man_id"
				+ ",year"
				+ ",place"
				+ ",beginTime"
				+ ",endTime"
				+ ",fei"
				+ ",bao_nong"
				+ ",bao_poor"
				+ ",bao_bigsick"
				+ ",remarks"
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,?,"
				+ "?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setInt(1, bean.getManId());
	                	ps.setInt(2, bean.getYear());
	                	ps.setString(3, bean.getPlace());
	                	ps.setDate(4, DateUtil.date2date(bean.getBeginTime()));
	                	ps.setDate(5, DateUtil.date2date(bean.getEndTime()));
	                	ps.setBigDecimal(6, bean.getFei());
	                	ps.setBigDecimal(7, bean.getBaoNong());
	                	ps.setBigDecimal(8, bean.getBaoPoor());
	                	ps.setBigDecimal(9, bean.getBaoBigsick());
	                	ps.setString(10, bean.getRemarks());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	
	@Transactional
	public int updateSickBean(final DougangSickBean bean){
		Integer id = 0;
		final String sql = "update dougang_sick set "
				+ "man_id = ?"
				+ ",year = ?"
				+ ",place = ?"
				+ ",beginTime = ?"
				+ ",endTime = ?"
				+ ",fei = ?"
				+ ",bao_nong = ?"
				+ ",bao_poor = ?"
				+ ",bao_bigsick = ?"
				+ ",remarks = ? where id = ?";				
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getManId());
		con.add(bean.getYear());
		con.add(bean.getPlace());
		con.add(bean.getBeginTime());
		con.add(bean.getEndTime());
		con.add(bean.getFei());
		con.add(bean.getBaoNong());
		con.add(bean.getBaoPoor());
		con.add(bean.getBaoBigsick());
		con.add(bean.getRemarks());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
}
