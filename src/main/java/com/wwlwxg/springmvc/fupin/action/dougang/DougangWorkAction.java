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
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangWorkBean;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangManService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangWorkService;

@Controller
public class DougangWorkAction {
	Logger logger = Logger.getLogger(getClass());

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private DougangWorkService workService;
	
	@Resource
	private DougangManService manService;
	
	@RequestMapping("/workList.do")
	public String getWorkListByManId(@RequestParam(value = "manId") int manId,
			Map<String, Object> map) {
		List<DougangWorkBean> list;
		try {
			list = workService.findWorkBeanListByManId(manId);
			DougangManBean bean = manService.findManBeanById(manId);
			map.put("list", list);
			map.put("manId", manId);
			map.put("huId", bean.getHuId());
			map.put("name", bean.getName());
			return "dougang/work";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/addWork.do",method = RequestMethod.GET)
	public String addStudyGet(@RequestParam(value = "id") int id
							, @RequestParam(value = "manId") int manId
							, Map<String, Object> map) {
		try {
			if (id > 0) {
				DougangWorkBean bean = workService.findWorkBeanById(id);
				map.put("bean", bean);
			}
			map.put("manId", manId);
			return "dougang/add_work";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addWork.do", method = RequestMethod.POST)
	public String addStudyPost(HttpServletRequest request, Map<String, Object> map) {
		DougangWorkBean bean = WebUtils.request2Bean(request, DougangWorkBean.class);
		try {
			if (bean.getId() == 0) {
				workService.insertWorkBean(bean);
			} else {
				workService.updateWorkBean(bean);
			}
			map.put("message", "恭喜!成功了！");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
