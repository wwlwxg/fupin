package com.wwlwxg.springmvc.fupin.action.dougang;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wwlwxg.springmvc.common.util.WebUtils;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangSickBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangSoldierBean;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangManService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangSickService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangSoldierService;

@Controller
public class DougangSoldierAction {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private DougangSoldierService soldierService;
	
	@Resource
	private DougangManService manService;
	
	@RequestMapping("/soldierList.do")
	public String getSoldierListByManId(@RequestParam(value = "manId") int manId,
			Map<String, Object> map) {
		List<DougangSoldierBean> list;
		try {
			list = soldierService.findSoldierBeanListByManId(manId);
			DougangManBean bean = manService.findManBeanById(manId);
			map.put("list", list);
			map.put("manId", manId);
			map.put("huId", bean.getHuId());
			map.put("name", bean.getName());
			return "dougang/soldier";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/addSoldier.do",method = RequestMethod.GET)
	public String addSickGet(@RequestParam(value = "id") int id
							, @RequestParam(value = "manId") int manId
							, Map<String, Object> map) {
		try {
			if (id > 0) {
				DougangSoldierBean bean = soldierService.findSoldierBeanById(id);
				map.put("bean", bean);
			}
			map.put("manId", manId);
			return "dougang/add_soldier";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addSoldier.do", method = RequestMethod.POST)
	public String addStudyPost(HttpServletRequest request, Map<String, Object> map) {
		DougangSoldierBean bean = WebUtils.request2Bean(request, DougangSoldierBean.class);
		try {
			if (bean.getId() == 0) {
				soldierService.insertSoldierBean(bean);
			} else {
				soldierService.updateSoldierBean(bean);
			}
			map.put("message", "恭喜!成功了！");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
