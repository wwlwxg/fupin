package com.wwlwxg.springmvc.fupin.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wwlwxg.springmvc.common.util.JdbcTemplateUtil;
import com.wwlwxg.springmvc.common.util.StringUtil;
import com.wwlwxg.springmvc.fupin.bean.AnalysisAgeBean;
import com.wwlwxg.springmvc.fupin.bean.ManBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceListBean;

@Service
public class ResidenceService {
	
	Logger log = Logger.getLogger(getClass());

	@Resource
	private JdbcTemplateUtil jdbcTemplate;
	
	@Resource
	private ManServiceImpl manServiceImpl;
	
	/**
	 * 获取一个组的户id
	 * @param zu
	 * @return
	 */
	public List<Integer> getIdsByZu(Integer zu) {
		String sql = "select id from residence where zu = ?";
		log.info("sql==>" + sql + "; params ==>zu=" + zu + ", zu=" + zu);
		return jdbcTemplate.queryForList(sql, new Object[]{zu}, Integer.class);
	}
	
	public List<ResidenceBean> findResidenceBeansByZu(Integer zu) {
		List<ResidenceBean> result = new ArrayList<ResidenceBean>();
		List<Integer> list = getIdsByZu(zu);
		for(Integer id : list) {
			ResidenceBean bean = findResidenceBeanById(id);
			result.add(bean);
		}
		return result;
	}
	
	public List<ResidenceListBean> findResidenceListByZu(Integer zu, String name) throws Exception {
		return findResidenceListByZu(zu, name, null, null, null);
	}
	/**
	 * 通过组来获取家庭成员
	 * @return 
	 */
	public List<ResidenceListBean> findResidenceListByZu(Integer zu, String name,Integer belong, String poorCause, String familyType) throws Exception{
		String sql = "SELECT r.id as id,r.master,count(*) as count,r.zu as zu,m.residence_id,r.family_type as familyType,r.cun,if(sum(case m.politics when '党员' then 1 else 0 end)>0,'党员户','')  as rr FROM residence r LEFT JOIN man_information m ON r.id=m.residence_id WHERE 1=1 ";
		String sql2 = " GROUP BY r.zu,r.`master`;";
		List<Object> con = new ArrayList<Object>();
		if(zu != null) {
			sql = sql + " and r.zu=? ";
			con.add(zu);
		}
		if(StringUtil.isNotEmpty(name)) {
			sql = sql + " and m.name=? ";
			con.add(name);
		}
		if(belong != null) {
			sql = sql + " and belong=? ";
			con.add(belong);
		}
		if(StringUtil.isNotEmpty(poorCause)){
			sql = sql + " and poor_cause=? ";
			con.add(poorCause);
		}
		if(StringUtil.isNotEmpty(familyType)) {
			sql = sql + " and family_type = ? ";
			con.add(familyType);
		}
		sql = sql + sql2;
		
		log.info("sql==>" + sql 
				+ "; params ==>zu=" 
				+ zu 
				+ ", name=" 
				+ name 
				+ ", belong=" 
				+ belong 
				+ ", poor_cause=" 
				+ poorCause
				+ ", family_type="
				+ familyType);
		return jdbcTemplate.queryForListBean(sql, con.toArray(), ResidenceListBean.class);
	}
	
	public ResidenceBean findResidenceBeanById(Integer id){
		if(id == null || id==0) {
			return null;
		}
		String sql = "select * from residence where id = ?";
		log.info("sql==>" + sql + "; params ==>id=" + id);
		ResidenceBean residenceBean = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ResidenceBean>() {
			@Override
			public ResidenceBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				ResidenceBean bean = new ResidenceBean();
				bean.setId(rs.getInt("id"));
				bean.setXiangzhen(rs.getString("xiangzhen"));
				bean.setCun(rs.getString("cun"));
				bean.setZu(rs.getInt("zu"));
				bean.setBuild_date(rs.getDate("build_date"));
				bean.setMaster(rs.getString("master"));
				bean.setFamily_type(rs.getString("family_type"));
				bean.setDoor_plate(rs.getString("door_plate"));
				bean.setStar_level(rs.getString("star_level"));
				bean.setHouse_summary(rs.getString("house_summary"));
				bean.setEarth_summary(rs.getString("earth_summary"));
				bean.setIncome(rs.getString("income"));
				bean.setFamily_state(rs.getString("family_state"));
				bean.setWeiwen(rs.getString("weiwen"));
				bean.setSituation_explanation(rs.getString("situation_explanation"));
				return bean;
			}
			
		});
		String sql1 = "select * from man_information where residence_id=?";
		List<ManBean> manList = jdbcTemplate.query(sql1, new Object[]{id},new RowMapper<ManBean>() {

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
		residenceBean.setList(manList);
		return residenceBean;
	}
	/**
	 */
	@Transactional
	public int insertResidenceBean(final ResidenceBean bean) {
		final String sql = "insert into residence"
				+ "(xiangzhen"					// 1
				+ ",cun"						// 2
				+ ",zu"							// 3
				+ ",build_date"					// 4
				+ ",master"						// 5
				+ ",family_type"				// 6
				+ ",door_plate"					// 7
				+ ",star_level"					// 8
				+ ",house_summary"				// 9
				+ ",earth_summary"				// 10
				+ ",income"						// 11
				+ ",family_state"				// 12
				+ ",weiwen"						// 13
				+ ",situation_explanation"		// 14
				+ ") "				
				+ "values"
				+ "(?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?,"
				+ " ?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		log.info("sql==>" + sql + "; params ==>bean=" + bean);
		int returnVal=jdbcTemplate.update(  
	            new PreparedStatementCreator() {  
	                public PreparedStatement createPreparedStatement(Connection con) throws SQLException  
	                {  
	                	PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	                	ps.setString(1, bean.getXiangzhen());
	                	ps.setString(2, bean.getCun());
	                	ps.setInt(3, bean.getZu());
	                	ps.setDate(4, new Date(bean.getBuild_date().getTime()));
	                	ps.setString(5, bean.getMaster());
	                	ps.setString(6, bean.getFamily_type());
	                	ps.setString(7, bean.getDoor_plate());
	                	ps.setString(8, bean.getStar_level());
	                	ps.setString(9, bean.getHouse_summary());
	                	ps.setString(10, bean.getEarth_summary());
	                	ps.setString(11, bean.getIncome());
	                	ps.setString(12, bean.getFamily_state());
	                	ps.setString(13, bean.getWeiwen());
	                	ps.setString(14, bean.getSituation_explanation());
	                    return ps;  
	                }  
	            }, keyHolder); 
		int primaryKey = keyHolder.getKey().intValue();
		if(bean.getList() != null) {
			for(ManBean man : bean.getList()){
				man.setResidence_id(primaryKey);
				manServiceImpl.insertManBean(man);
			}
		}
		return keyHolder.getKey().intValue();
	}
	/**
	 * 说明：根据主键更新表sys_kind中的记录
	 * @return int >0代表操作成功
	 */
	@Transactional
	public int updateResidenceBean(ResidenceBean bean){
		String sql = "update residence set "
				+ "xiangzhen = ?"					// 1
				+ ",cun = ?"						// 2
				+ ",zu = ?"							// 3
				+ ",build_date = ?"					// 4
				+ ",master = ?"						// 5
				+ ",family_type = ?"				// 6
				+ ",door_plate = ?"					// 7
				+ ",star_level = ?"					// 8
				+ ",house_summary = ?"				// 9
				+ ",earth_summary = ?"				// 10
				+ ",income = ?"						// 11
				+ ",family_state = ?"				// 12
				+ ",weiwen = ?"						// 13
				+ ",situation_explanation = ?"		// 14
				+ " where id = ?;";					// 15
		log.info("sql==>" + sql + "; params ==>bean=" + bean);
		List<Object> con = new ArrayList<Object>();
		con.add(bean.getXiangzhen());
		con.add(bean.getCun());
		con.add(bean.getZu());
		con.add(new Date(bean.getBuild_date().getTime()));
		con.add(bean.getMaster());
		con.add(bean.getFamily_type());
		con.add(bean.getDoor_plate());
		con.add(bean.getStar_level());
		con.add(bean.getHouse_summary());
		con.add(bean.getEarth_summary());
		con.add(bean.getIncome());
		con.add(bean.getFamily_state());
		con.add(bean.getWeiwen());
		con.add(bean.getSituation_explanation());
		con.add(bean.getId());
		int returnVal=jdbcTemplate.update(sql,con.toArray());
		return returnVal;
	}
	
	/**
	 * 说明：根据主键删除表sys_kind中的记录
	 * @return int >0代表操作成功
	 */
	@Transactional
	public int deleteResidenceBeanById(int id) throws Exception{
		manServiceImpl.deleteManBeanByResidenceId(id);
		String sql = "delete from residence where id=?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	//////////////////////////////////  统计部分  ////////////////////////////////////////
	public List<AnalysisAgeBean> getAnalysisAge() {
		final List<Map<String, Object>> listMap = getAnalysisHu();
		final List<Map<String, Object>> listEarthMap = getEarthArea();
		String sql = "SELECT zu,"
				   + "sex,"
				   + "sum(1) as ren_sum,"
				   //+ "sum(earth_summary) as earth_sum,"
				   //+ "count(DISTINCT r.id) as hu_sum,"
				   + "sum(if(health_state='残疾',1,0)) as canji,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) < 16, 1,0)) as sixteen,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) <60, 1,0)) as sixty,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) < 65, 1,0)) as six_five,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) < 80, 1,0)) as eighty,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) < 100, 1,0)) as hun,"
				   + "sum(if(year(curdate())-if(length(id_no)=18,SUBSTRING(id_no,7,4),0) >=100, 1,0)) as hun_big"
				   + " FROM man_information m LEFT JOIN residence r ON m.residence_id=r.id GROUP BY zu,sex;";
		log.info("sql==>" + sql + "; params ==>无");
		List<AnalysisAgeBean> list = jdbcTemplate.query(sql, new RowMapper<AnalysisAgeBean>() {
			@Override
			public AnalysisAgeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				AnalysisAgeBean bean = new AnalysisAgeBean();
				bean.setZu(rs.getInt("zu"));
				bean.setSex(rs.getString("sex"));
//				bean.setHuSum(rs.getInt("hu_sum"));
				bean.setRenSum(rs.getInt("ren_sum"));
//				bean.setEarthSum(rs.getDouble("earth_sum"));
				bean.setCanji(rs.getInt("canji"));
				bean.setSixteen(rs.getInt("sixteen"));
				bean.setSixty(rs.getInt("sixty"));
				bean.setSixFive(rs.getInt("six_five"));
				bean.setEighty(rs.getInt("eighty"));
				bean.setHun(rs.getInt("hun"));
				bean.setHunBig(rs.getInt("hun_big"));
				for(Map<String, Object> map : listMap) {
					if(map.get("zu").equals(bean.getZu())) {
						bean.setHuSum(((Long) map.get("c")).intValue());
					}
				}
				for(Map<String, Object> map : listEarthMap) {
					if(map.get("zu").equals(bean.getZu())){
						bean.setEarthSum((Double) map.get("earth_sum"));
					}
				}
				return bean;
			}
		});
		
		return list;
	}
	
	/**
	 * 获取文化分布
	 * @return
	 */
	public List<Map<String, Object>> getAnalysisEducation() {
		String sql= "SELECT zu, sum(if(politics='党员',1,0)) as dang,"
					+ " CASE education "
					+ " WHEN '高中' THEN '2'"
					+ " WHEN '中专' THEN '2'"
					+ " WHEN '技校' THEN '2'"
					+ " WHEN '专科' THEN '3'"
					+ " WHEN '大专' THEN '3'"
					+ " WHEN '高职' THEN '3'"
					+ " WHEN '大学' THEN '3'"
					+ " ELSE '1' END as edu, count(*) as c FROM man_information m "
					+ " LEFT JOIN residence r ON r.id=m.residence_id"
					+ " GROUP BY"
					+ " zu, "
					+ " CASE education "
					+ " WHEN '高中' THEN '2'"
					+ " WHEN '中专' THEN '2'"
					+ " WHEN '技校' THEN '2'"
					+ " WHEN '专科' THEN '3'"
					+ " WHEN '大专' THEN '3'"
					+ " WHEN '高职' THEN '3'"
					+ " WHEN '大学' THEN '3'"
					+ " ELSE '1' END ; ";
		log.info("sql==>" + sql + ";params ==>无");
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 获取贫困分布
	 * @return
	 */
	public List<Map<String, Object>> getAnalysisPoor() {
		String sql = "SELECT "
					 + " family_type,poor_cause,"
					 + " count(DISTINCT r.id) as hu,"
					 + " count(1) as ren "
					 + " FROM "
					 + " residence r "
					 + " LEFT JOIN man_information m ON r.id = m.residence_id "
					 + " WHERE  "
					 + " belong = 1 "
					 + " GROUP BY "
					 + " family_type,poor_cause;" ;
		log.info("sql==>" + sql + "; params ==>无");
		return  jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> getAnalysisHu(){
		String sql1 = "SELECT zu,count(1) as c from residence GROUP BY zu;";
		log.info("sql==>" + sql1 + "; params ==>无");
		return  jdbcTemplate.queryForList(sql1);
	}
	
	public List<Map<String,Object>> getEarthArea() {
		String sql = "SELECT zu,sum(earth_summary) as earth_sum FROM residence group by zu;";
		log.info("sql==>" + sql + ";params ==>无");
		return jdbcTemplate.queryForList(sql);
	}
	
	
	
}
