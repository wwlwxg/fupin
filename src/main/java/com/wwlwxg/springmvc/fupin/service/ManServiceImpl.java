package com.wwlwxg.springmvc.fupin.service;

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
import com.wwlwxg.springmvc.fupin.bean.ManBean;

/**

 * @author Dyl
 * 2017-04-14 17:37:34
 */
@Service
public class ManServiceImpl {
	
	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	/**
	 * 通过组来获取家庭成员
	 * @return List<SysAuthKind
	 */
	public List<ManBean> findManBeanListByResidenceId(int residence_id) throws Exception{
		String sql = "select * from man_information where residence_id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(residence_id);
		return jdbcTemplate.query(sql, new Object[]{residence_id},new RowMapper<ManBean>() {
			@Override
			public ManBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				ManBean bean = new ManBean();
				bean.setId(rs.getInt("id"));
				bean.setRelation(rs.getString("relation"));
				bean.setName(rs.getString("name"));
				bean.setSex(rs.getString("sex"));
				bean.setEducation(rs.getString("education"));
				bean.setPolitics(rs.getString("politics"));
				bean.setRegistration_nature(rs.getString("registration_nature"));
				bean.setHealth_state(rs.getString("health_state"));
				bean.setId_no(rs.getString("id_no"));
				bean.setAddress(rs.getString("address"));
				bean.setPhone(rs.getString("phone"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setResidence_id(rs.getInt("residence_id"));
				return bean;
			}
		});
	
		}
	
	public ManBean findManBeanById(int id) throws Exception {
		String sql = "select * from man_information where id = ?";
		List<Object> con = new ArrayList<Object>();
		con.add(id);
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ManBean>() {

			@Override
			public ManBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				ManBean bean = new ManBean();
				bean.setId(rs.getInt("id"));
				bean.setRelation(rs.getString("relation"));
				bean.setName(rs.getString("name"));
				bean.setSex(rs.getString("sex"));
				bean.setEducation(rs.getString("education"));
				bean.setPolitics(rs.getString("politics"));
				bean.setRegistration_nature(rs.getString("registration_nature"));
				bean.setHealth_state(rs.getString("health_state"));
				bean.setId_no(rs.getString("id_no"));
				bean.setAddress(rs.getString("address"));
				bean.setPhone(rs.getString("phone"));
				bean.setRemarks(rs.getString("remarks"));
				bean.setResidence_id(rs.getInt("residence_id"));
				return bean;
			}
			
		});
	}
	/**
	 * 说明：往表sys_kind中插入一条记录
	 * @return int >0代表操作成功
	 */
	@Transactional
	public int insertManBean(final ManBean manBean){
		Integer id = 0;
		final String sql = "insert into man_information"
				+ "(relation"					// 1
				+ ",name"						// 2
				+ ",sex"						// 3
				+ ",education"					// 4
				+ ",politics"					// 5
				+ ",registration_nature"		// 6
				+ ",health_state"				// 7
				+ ",id_no"						// 8
				+ ",address"					// 9
				+ ",phone"						// 10
				+ ",remarks"					// 11
				+ ",residence_id"				// 12
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setString(1, manBean.getRelation());
	                	ps.setString(2, manBean.getName());
	                	ps.setString(3, manBean.getSex());
	                	ps.setString(4, manBean.getEducation());
	                	ps.setString(5, manBean.getPolitics());
	                	ps.setString(6, manBean.getRegistration_nature());
	                	ps.setString(7, manBean.getHealth_state());
	                	ps.setString(8, manBean.getId_no());
	                	ps.setString(9, manBean.getAddress());
	                	ps.setString(10, manBean.getPhone());
	                	ps.setString(11, manBean.getRemarks());
	                	ps.setInt(12, manBean.getResidence_id());
	                    return ps;  
	                }  
	            }, keyHolder); 
		return keyHolder.getKey().intValue();
	}
	/**
	 * 说明：根据主键更新表sys_kind中的记录
	 * @return int >0代表操作成功
	 */
	@Transactional
	public int updateManBean(ManBean manBean) {
		String sql = "update man_information set "
				+ "relation = ?"				// 1
				+ ",name = ?"						// 2
				+ ",sex = ?"						// 3
				+ ",education = ?"					// 4
				+ ",politics = ?"					// 5
				+ ",registration_nature = ?"		// 6
				+ ",health_state = ?"				// 7
				+ ",id_no = ?"						// 8
				+ ",address = ?"					// 9
				+ ",phone = ?"						// 10
				+ ",remarks = ?"					// 11
				+ ",residence_id = ?"				// 12
				+ " where id = ?;";					// 13
		List<Object> con = new ArrayList<Object>();
		con.add(manBean.getRelation());				//1
		con.add(manBean.getName());					//2
		con.add(manBean.getSex());					//3
		con.add(manBean.getEducation());			//4
		con.add(manBean.getPolitics());				//5
		con.add(manBean.getRegistration_nature());	//6
		con.add(manBean.getHealth_state());			//7
		con.add(manBean.getId_no());				//8
		con.add(manBean.getAddress());				//9
		con.add(manBean.getPhone());				//10
		con.add(manBean.getRemarks());				//11
		con.add(manBean.getResidence_id());			//12
		con.add(manBean.getId());					//13
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
	/**
	 * 说明：根据主键删除表sys_kind中的记录
	 * @return int >0代表操作成功
	 */
	@Transactional
	public int deleteManBeanById(String id) throws Exception{
		String sql = "delete from man_information where id=?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	@Transactional
	public int deleteManBeanByResidenceId(int residence_id) {
		String sql = "delete from man_information where residence_id=?";
		return jdbcTemplate.update(sql,new Object[]{residence_id});
	}
	
}
