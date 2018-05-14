package com.wwlwxg.springmvc.fupin.action;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wwlwxg.springmvc.fupin.bean.ManBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceListBean;
import com.wwlwxg.springmvc.fupin.service.ManServiceImpl;
import com.wwlwxg.springmvc.fupin.service.ResidenceService;
import com.wwlwxg.springmvc.fupin.utils.ExcelReaderAndWriter;
import com.wwlwxg.springmvc.fupin.utils.Main;

@Controller
public class IndexAction {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private ResidenceService residenceService;
	
	@Resource
	private ManServiceImpl manService;
	
	@RequestMapping("/")
	public String index(Map<String,Object> map){
		map.put("zu", 1);
		map.put("name", null);
		return "redirect:/getResidenceList.do";
	}
	
    @RequestMapping(value = "/getResidenceList.do")
	public String index(@RequestParam(value="zu") Integer zu, @RequestParam("name") String name,Map<String, Object> map) {
    	try {
			List<ResidenceListBean> list = residenceService.findResidenceListByZu(zu, name);
    		map.put("list", list);
    		map.put("zu", zu);
    		map.put("name", name);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/residence_list_show";
	}
    
    @RequestMapping(value = "/getPoorResidenceList.do")
	public String getPoorResidenceList(@RequestParam(value="belong") Integer belong
									, @RequestParam(value="poor_cause",required=false) String poorCause
									, @RequestParam(value="family_type",required=false) String familyType
									,Map<String, Object> map) {
    	try {
			List<ResidenceListBean> list = residenceService.findResidenceListByZu(null, null, belong, poorCause, familyType);
    		map.put("list", list);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/residence_poor_list_show";
	}
    
    
    @RequestMapping("/getResidence.do")
    public String getResidence(@RequestParam("residenceId") Integer residenceId,Map<String,Object> map) {
    	try {
    		ResidenceBean bean = residenceService.findResidenceBeanById(residenceId);
    		map.put("bean", bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "/residence_show";
    }
    
    @RequestMapping(value = "/loadResidence.do")
   	public String main(@RequestParam("id")Integer residenceId,Map<String, Object> map) {
    	try {
    		ResidenceBean bean = residenceService.findResidenceBeanById(residenceId);
    		if(bean != null) {
    			map.put("bean", bean);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		return "/add_residence";
   	}
    
    @RequestMapping("/loadMan.do")
    public String loadMan(@RequestParam("id") Integer id,@RequestParam("residence_id") Integer residence_id, Map<String, Object> map) {
    	if(id != 0) {
    		try {
				ManBean bean = manService.findManBeanById(id);
				map.put("bean", bean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		ManBean bean = new ManBean();
    		bean.setId(0);
    		bean.setResidence_id(residence_id);
    		map.put("bean", bean);
    	}
    	return "/add_man";
    }
    
    @RequestMapping("/addMan.do")
    public String addMan(ManBean bean,Map<String,Object> map) {
    	System.out.println(bean);
    	if(bean.getId() == 0) {
    		if("户主".equals(bean.getRelation())){
    			ResidenceBean rBean = residenceService.findResidenceBeanById(bean.getResidence_id());
    			rBean.setMaster(bean.getName());
    			residenceService.updateResidenceBean(rBean);
    		}
    		manService.insertManBean(bean);
    	} else {
    		if("户主".equals(bean.getRelation())){
    			ResidenceBean rBean = residenceService.findResidenceBeanById(bean.getResidence_id());
    			rBean.setMaster(bean.getName());
    			residenceService.updateResidenceBean(rBean);
    		}
			manService.updateManBean(bean);
    	}
    	map.put("issuccess", true);
    	return "home";
    }
    
    @RequestMapping("/addResidence.do")
    public String addResidence(ResidenceBean bean,RedirectAttributes  attr){
    	Integer id = bean.getId();
    	if(id != null && id > 0) {
    		id = bean.getId();
    		residenceService.updateResidenceBean(bean);
    	} else {
    		id=residenceService.insertResidenceBean(bean);
    	}
    	attr.addAttribute("id", id);
    	return "redirect:/loadResidence.do";
    }
    
    @RequestMapping("/exportExcel.do")
    public String exportExcel(@RequestParam("id") Integer id, HttpServletResponse response,Map<String, Object> map) throws Exception {
    	boolean isSuccess = true;
    	String root = System.getProperty("evan.webapp");
    	ExcelReaderAndWriter excelWriter = new ExcelReaderAndWriter();
    	POIFSFileSystem pofs = new POIFSFileSystem(new File(root+"/22/moban.xls"));
    	HSSFWorkbook workbook = new HSSFWorkbook(pofs);
    	ResidenceBean bean = residenceService.findResidenceBeanById(id);
    	if(bean == null) {
    		map.put("issuccess", "没有找到数据!");
    		isSuccess = false;
    	}
    	if(isSuccess) {
    		excelWriter.exportExcel(workbook, bean, response); 
    	}
		return "forward:/getResidenceList.do";
    }
    
    @RequestMapping("/export2Zip.do")
    public String excel2Zip(@RequestParam("zu") Integer zu
    						,Map<String, Object> map
    						,HttpServletResponse response) throws Exception {
    	String root = System.getProperty("evan.webapp");
    	ExcelReaderAndWriter excelWriter = new ExcelReaderAndWriter();
    	POIFSFileSystem pofs = new POIFSFileSystem(new File(root+"/22/moban.xls"));
    	List<ResidenceBean> list = residenceService.findResidenceBeansByZu(zu);
    	List<ResidenceListBean> rList = residenceService.findResidenceListByZu(zu, null);
    	OutputStream out = response.getOutputStream();
    	ZipOutputStream zip = new ZipOutputStream(out);
    	
    	String zipFileName = zu+"组数据打包.zip";
    	response.setContentType("application/octet-stream");
    	response.setHeader("Connection", "close"); // 表示不能用浏览器直接打开
    	response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传多线程连接下载
    	response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(zipFileName.getBytes("GB2312"), "ISO8859-1"));
    	response.setCharacterEncoding("UTF-8");
    	
    	POIFSFileSystem pofsForResidenceList = new POIFSFileSystem(new File(root+"/22/residenceListTemplate.xls"));
    	for(ResidenceBean bean : list) {
    		excelWriter.exportZipExcelForResidenceBeans(pofs, bean, zip);
    	}
    	
    	excelWriter.exportZipExcelForResidenceListBeans(pofsForResidenceList,zu, rList, zip);
    	
    	zip.flush();
    	zip.close();

    	return "forward:/getResidenceList.do";
    }
    
    @RequestMapping("/importData.do")
    public String importData(){
    	String root = System.getProperty("evan.webapp");
    	Main main = new Main();
    	List<String> result = main.getFileList(root+"/11");
		System.out.println(result);
        ExcelReaderAndWriter excelReader = new ExcelReaderAndWriter();
        List<ResidenceBean> beanList = excelReader.read(result);
        /**
        for(ResidenceBean bean : beanList) {
        	residenceService.insertResidenceBean(bean);
        }
        */
        return "home";
    }
}
