package com.wwlwxg.springmvc.fupin.action.dougang;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wwlwxg.springmvc.common.util.WebUtils;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangSickBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangStudyBean;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangManService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangSickService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangStudyService;

@Controller
public class DougangSickAction {
	
	Logger logger = Logger.getLogger(getClass());

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private DougangSickService sickService;
	
	@Resource
	private DougangManService manService;
	
	@RequestMapping("/sickList.do")
	public String getSickListByManId(@RequestParam(value = "manId") int manId,
			Map<String, Object> map) {
		List<DougangSickBean> list;
		try {
			list = sickService.findSickBeanListByManId(manId);
			DougangManBean bean = manService.findManBeanById(manId);
			map.put("list", list);
			map.put("manId", manId);
			map.put("huId", bean.getHuId());
			map.put("name", bean.getName());
			return "dougang/sick";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/addSick.do",method = RequestMethod.GET)
	public String addSickGet(@RequestParam(value = "id") int id
							, @RequestParam(value = "manId") int manId
							, Map<String, Object> map) {
		try {
			if (id > 0) {
				DougangSickBean bean = sickService.findSickBeanById(id);
				map.put("bean", bean);
			}
			map.put("manId", manId);
			return "dougang/add_sick";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addSick.do", method = RequestMethod.POST)
	public String addStudyPost(HttpServletRequest request, Map<String, Object> map) {
		DougangSickBean bean = WebUtils.request2Bean(request, DougangSickBean.class);
		try {
			if (bean.getId() == 0) {
				sickService.insertSickBean(bean);
			} else {
				sickService.updateSickBean(bean);
			}
			map.put("message", "恭喜!成功了！");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
