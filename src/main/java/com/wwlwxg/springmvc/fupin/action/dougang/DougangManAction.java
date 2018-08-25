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
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangHuBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangHuService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangManService;

@Controller
public class DougangManAction {
	
	Logger logger = Logger.getLogger(getClass());

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private DougangHuService huService;

	@Resource
	private DougangManService manService;
	
	@RequestMapping(value="/addDougangMan.do",method=RequestMethod.GET)
	public String addDougangManGet(@RequestParam(value = "id") int id
								,@RequestParam(value = "huId") int huId
								,Map<String, Object> map) {
		DougangManBean bean;
		try {
			if (id > 0) {
				bean = manService.findManBeanById(id);
				map.put("bean", bean);
			}
			map.put("huId", huId);
			return "dougang/add_Man";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addDougangMan.do", method = RequestMethod.POST)
	public String addDougangManPost(HttpServletRequest request, Map<String, Object> map) {
		DougangManBean bean = WebUtils.request2Bean(request, DougangManBean.class);
		try {
			Integer huId = Integer.valueOf(request.getParameter("huId"));
			bean.setHuId(huId);
			if (bean.getId() == 0) {
				manService.insertManBean(bean);
			} else {
				manService.updateManBean(bean);
			}
			map.put("message", "恭喜!成功了！");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
