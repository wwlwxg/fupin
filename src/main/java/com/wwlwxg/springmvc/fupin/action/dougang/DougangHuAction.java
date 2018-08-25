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
import com.wwlwxg.springmvc.fupin.bean.AnalysisAgeBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangHuBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangIncomeBean;
import com.wwlwxg.springmvc.fupin.bean.dougang.DougangManBean;
import com.wwlwxg.springmvc.fupin.service.ResidenceService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangHuService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangIncomeService;
import com.wwlwxg.springmvc.fupin.service.dougang.DougangManService;

@Controller
public class DougangHuAction {

	Logger logger = Logger.getLogger(getClass());

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private DougangHuService huService;

	@Resource
	private DougangManService manService;
	
	@Resource
	private DougangIncomeService incomeService;

	@RequestMapping("/huList.do")
	public String getHuListByCun(@RequestParam(value = "cun", defaultValue = "") String cun,
								@RequestParam(value="name", defaultValue = "") String name,
								Map<String, Object> map) {
		List<DougangHuBean> list;
		try {
			list = huService.findHuBeanListByCunAndName(cun, name);
			map.put("cun", cun);
			map.put("name", name);
			map.put("list", list);
			return "dougang/hu_list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getHu.do")
	public String getHu(@RequestParam(value = "id") int huId, Map<String, Object> map) {
		DougangHuBean bean;
		List<DougangManBean> list;
		List<DougangIncomeBean> listIncome;
		try {
			bean = huService.findHuBeanById(huId);
			list = manService.findManBeanListByHuId(huId);
			listIncome = incomeService.findIncomeBeanListByHuId(huId);
			map.put("bean", bean);
			map.put("list", list);
			map.put("list_income", listIncome);
			return "dougang/hu_show";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/addDougangHu.do", method = RequestMethod.GET)
	public String addDougangHuGet(@RequestParam("id") int id, Map<String, Object> map) {
		try {
			if (id > 0) {
				DougangHuBean bean = huService.findHuBeanById(id);
				map.put("bean", bean);
			}
			return "dougang/add_hu";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/addDougangHu.do", method = RequestMethod.POST)
	public String addDougangHuPost(HttpServletRequest request, Map<String, Object> map) {
		DougangHuBean bean = WebUtils.request2Bean(request, DougangHuBean.class);
		try {
			if (bean.getId() == 0) {
				huService.insertHuBean(bean);
			} else {
				huService.updateHuBean(bean);
			}
			map.put("message", "恭喜!成功了！");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
