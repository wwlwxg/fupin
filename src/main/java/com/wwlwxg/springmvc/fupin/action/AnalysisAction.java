package com.wwlwxg.springmvc.fupin.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wwlwxg.springmvc.fupin.bean.AnalysisAgeBean;
import com.wwlwxg.springmvc.fupin.constant.Constant;
import com.wwlwxg.springmvc.fupin.service.ManServiceImpl;
import com.wwlwxg.springmvc.fupin.service.ResidenceService;

@Controller
public class AnalysisAction {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private ResidenceService residenceService;
	
	@RequestMapping("/analysisAge.do")
	public String analysisAge(Map<String,Object> map){
		List<AnalysisAgeBean> list = residenceService.getAnalysisAge();
		Map<String, AnalysisAgeBean[]> result = parseData(list);
		map.put("map", result);
		return "analysis_list";
	}
	
	@RequestMapping("/analysisEducation.do")
	public String analysisEducation(Map<String, Object> map) {
		List<Map<String, Object>> maplist = residenceService.getAnalysisEducation();
		Map<String, Long[]> list = parseEducation(maplist);
		map.put("list", list);
		return "analysis_education";
	}
	
	@RequestMapping("/analysisPoor.do")
	public String analysisPoor(Map<String, Object> map) {
		List<Map<String, Object>> maplist = residenceService.getAnalysisPoor();
		Map<String, Long[]> list = parsePoor(maplist);
		map.put("list", list);
		return "analysis_poor";
	}
	
	private Map<String, AnalysisAgeBean[]> parseData(List<AnalysisAgeBean> list) {
		Map<String, AnalysisAgeBean[]> result = new HashMap<String, AnalysisAgeBean[]>();
		for(AnalysisAgeBean bean : list) {
			String key = bean.getZu()+"";
			if(result.get(key) == null) {
				AnalysisAgeBean[] arr =  new AnalysisAgeBean[2];
				result.put(key, arr);
			}
			if("男".equals(bean.getSex())){
				result.get(key)[0] = bean;
			} else if("女".equals(bean.getSex())){
				result.get(key)[1] = bean;
			} else {
				logger.error("性别填写的不对!!!"+bean);
			}
		}
		return result;
	}
	
	private Map<String, Long[]> parseEducation(List<Map<String, Object>> list) {
		Map<String, Long[]> result = new HashMap<String, Long[]>();
		for(Map<String, Object> map : list){
			String zu = ((Integer) map.get("zu")).toString();
			int edu = Integer.valueOf(((String) map.get("edu"))).intValue();
			Long c = (Long) map.get("c");
			Long dang = ((BigDecimal) map.get("dang")).longValue();
			Long[] catr = result.get(zu);
			if(catr == null) {
				catr = new Long[]{0L,0L,0L,0L};
				result.put(zu, catr);
			}
			catr[edu - 1] = c;
			catr[3] += dang;
		}
		return result;
	}
	
	private Map<String, Long[]> parsePoor(List<Map<String, Object>> list) {
		Map<String, Long[]> result = new HashMap<String, Long[]>();
		for(Map<String, Object> map : list){
			String familyType = (String) map.get("family_type");
			String poorCause = (String) map.get("poor_cause");
			Long hu = (Long) map.get("hu");
			Long ren = (Long) map.get("ren");
			Long[] fatr = result.get(familyType);
			if(fatr == null) {
				fatr = new Long[]{0L,0L,0L,0L,0L,0L,0L,0L};
				result.put(familyType, fatr);
			}
			switch (poorCause) {
			case Constant.POOR_CAUSE_BING:
				fatr[0] = hu;
				fatr[1] = ren;
				break;
			case Constant.POOR_CAUSE_CAN:
				fatr[2] = hu;
				fatr[3] = ren;
				break;
			case Constant.POOR_CAUSE_XUE:
				fatr[4] = hu;
				fatr[5] = ren;
				break;
			case Constant.POORT_CAUSE_JISHU:
				fatr[6] = hu;
				fatr[7] = ren;
				break;
			default:
				break;
			}
		}
		
		return result;
	}
	
}
