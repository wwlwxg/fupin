package com.wwlwxg.springmvc.fupin.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wwlwxg.springmvc.common.util.DateUtil;
import com.wwlwxg.springmvc.common.util.StringUtil;
import com.wwlwxg.springmvc.fupin.bean.ManBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceBean;
import com.wwlwxg.springmvc.fupin.bean.ResidenceListBean;

public class ExcelReaderAndWriter {
    
	Logger logger = Logger.getLogger(getClass());
	
	public List<ResidenceBean> read(List<String> datas){
		List<ResidenceBean> list = new ArrayList<ResidenceBean>();
		for(String s : datas) {
			ResidenceBean bean;
			try {
				bean = read(s);
				list.add(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return list;
	}
	
	public ResidenceBean read(String data) throws IOException {
		ResidenceBean bean = new ResidenceBean();
		List<ManBean> list = new ArrayList<ManBean>();
		bean.setList(list);
		InputStream is = new FileInputStream(data);
		System.out.println(data);
		Workbook wb = new XSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(wb.getNumberOfSheets() - 1);
		String title = sheet.getRow(1).getCell(0).getStringCellValue();
		String[] tt = title.split("；");
		String xiangzhen = tt[0].split("：")[1].trim();
		String cun = tt[1].split("：")[1].trim();
		String zu = tt[2].split("：")[1].trim();
		String builddate = tt[3].split("：")[1].trim();
		
		bean.setXiangzhen(xiangzhen);
		bean.setCun(cun);
		bean.setZu(Integer.valueOf(zu));
		bean.setBuild_date(DateUtil.parse(builddate, "yyyy.MM.dd"));
		
		for(int i = 3; ;i++){
			
			Row row = sheet.getRow(i);
			
			if("家庭类型".equals(row.getCell(1).getStringCellValue().trim())){
				Row row1 = sheet.getRow(i+1);
				Cell cell1 = row1.getCell(1);
				String family_type = getStringCellValue(cell1).trim();
				Cell cell23 = row1.getCell(2);
				String door_plate = getStringCellValue(cell23).trim();
				Cell cell45 = row1.getCell(4);
				String star_level = getStringCellValue(cell45).trim();
				Cell cell67 = row1.getCell(6);
				String house_summary = getStringCellValue(cell67).trim();
				Cell cell8 = row1.getCell(8);
				String earth_summary = getStringCellValue(cell8).trim();
				Cell cell90 = row1.getCell(9);
				String income = getStringCellValue(cell90).trim();
				Cell cell11 = row1.getCell(11);
				String family_state = getStringCellValue(cell11).trim();
				Row row2 = sheet.getRow(i+2);
				Cell cell22 = row2.getCell(2);
				String weiwen = getStringCellValue(cell22).trim();
				Row row3 = sheet.getRow(i+3);
				Cell cell32 = row3.getCell(9);
				String situation_explanation = getStringCellValue(cell32).trim();
				
				bean.setFamily_type(family_type);
				bean.setDoor_plate(door_plate);
				bean.setStar_level(star_level);
				bean.setHouse_summary(house_summary);
				bean.setEarth_summary(earth_summary);
				bean.setIncome(income);
				bean.setFamily_state(family_state);
				bean.setWeiwen(weiwen);
				bean.setSituation_explanation(situation_explanation);
				
				break;
			}
			
			Cell cell1 = row.getCell(1);
			String relation = getStringCellValue(cell1).trim();
			Cell cell2 = row.getCell(2);
			String name = getStringCellValue(cell2).trim();
			Cell cell3 = row.getCell(3);
			String sex = getStringCellValue(cell3).trim();
			Cell cell4 = row.getCell(4);
			String education = getStringCellValue(cell4).trim();
			Cell cell5 = row.getCell(5);
			String politics = getStringCellValue(cell5).trim();
			Cell cell6 = row.getCell(6);
			String registrationNature = getStringCellValue(cell6).trim();
			Cell cell7 = row.getCell(7);
			String healthState = getStringCellValue(cell7).trim();
			Cell cell8 = row.getCell(8);
			String idNo = getStringCellValue(cell8).trim();
			Cell cell9 = row.getCell(9);
			String address = getStringCellValue(cell9).trim();
			
			Cell cell10 = row.getCell(10);
			String phone = getStringCellValue(cell10).trim();
			Cell cell11 = row.getCell(11);
			String remarks = getStringCellValue(cell11).trim();
			
			if(StringUtil.isEmpty(relation)
			  &&StringUtil.isEmpty(name)
			  &&StringUtil.isEmpty(sex)
			  &&StringUtil.isEmpty(education)
			  &&StringUtil.isEmpty(politics)
			  &&StringUtil.isEmpty(registrationNature)
			  &&StringUtil.isEmpty(healthState)
			  &&StringUtil.isEmpty(idNo)
			  &&StringUtil.isEmpty(address)
			  &&StringUtil.isEmpty(phone)
			  &&StringUtil.isEmpty(remarks)) {
				continue;
			}
			
			if(!StringUtil.isEmpty(relation) && "户主".equals(relation.trim())) {
				bean.setMaster(name);
			}
			
			ManBean manBean = new ManBean();
			manBean.setRelation(relation);
			manBean.setName(name);
			manBean.setSex(sex);
			manBean.setEducation(education);
			manBean.setPolitics(politics);
			manBean.setRegistration_nature(registrationNature);
			manBean.setHealth_state(healthState);
			manBean.setId_no(idNo);
			manBean.setAddress(address);
			manBean.setPhone(phone);
			manBean.setRemarks(remarks);
			
			list.add(manBean);
			
		}
		
		return bean;
		
	}
	
	
    public void write(String filePath,String descPath,List<ResidenceBean> datas) throws IOException{
        InputStream is = new FileInputStream(filePath);
        HSSFWorkbook wb = new HSSFWorkbook(is);
        
        HSSFSheet sheet = wb.getSheetAt(0);
        for(int i = 0; i <  datas.size(); i++){
        }
        Cell cell0 = sheet.getRow(0).getCell(2);
        cell0.setCellType(CellType.FORMULA);
        cell0.setCellFormula("C3");
//    	File desktopDir = FileSystemView.getFileSystemView()
//    			.getHomeDirectory();
        FileOutputStream os = new FileOutputStream(descPath+"/结果输出.xls");
        try {
            //д��
            wb.write(os);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
        	if(os !=null) {
        		os.close();
        	}
        	if(wb!=null){
        		wb.close();
        	}
        }
        
    }
    
    public boolean exportZipExcelForResidenceBeans(POIFSFileSystem pofs, ResidenceBean bean, ZipOutputStream out) throws Exception {
    	HSSFWorkbook workbook = new HSSFWorkbook(pofs);
    	buildWorkBook(workbook, bean);
    	String familyType = bean.getFamily_type();
    	if(familyType == null || "".equals(familyType)){
    		familyType = "无";
    	}
    	List<ManBean> list = bean.getList();
    	String fileName = bean.getZu() + "-"
    					+ familyType
    					+ "-" + bean.getMaster() 
    					+ "-" + list.size() + "-" 
    					+ bean.getCun();
		for(ManBean man : list) {
			if("党员".equals(man.getPolitics().trim())){
				fileName = fileName + "-" + "党员";
				break;
			}
		}
		fileName = fileName + ".xls";
		//String master = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		out.putNextEntry(new ZipEntry(fileName));
		workbook.write(out);
		out.closeEntry();
		return true;
    }
    
    public boolean exportZipExcelForResidenceListBeans(POIFSFileSystem pofs,Integer zu, List<ResidenceListBean> list, ZipOutputStream out) throws Exception {
    	HSSFWorkbook workbook = new HSSFWorkbook(pofs);
    	buildWorkBookForResidenceList(workbook, list,zu);
		String fileName = zu + "组统计表.xls";
		//String master = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		out.putNextEntry(new ZipEntry(fileName));
		workbook.write(out);
		out.closeEntry();
		return true;
    }
    
    public Workbook buildWorkBookForResidenceList(HSSFWorkbook workbook, List<ResidenceListBean> list,Integer zu) throws Exception {
    	Sheet sheet = workbook.getSheetAt(0);
    	sheet.getRow(0).getCell(0).setCellValue("一户一档分组("+zu+"组)统计表");
    	int i = 0;
    	for(; i < list.size(); i++) {
    		ResidenceListBean bean =  list.get(i);
	    	Row row = sheet.createRow(i+2);
	    	Cell indexCell = row.createCell(0);
	    	indexCell.setCellStyle(getCellStyle(workbook));
	    	indexCell.setCellValue(i+1);
	    	
	    	Cell masterCell = row.createCell(1);
	    	String familyType = bean.getFamilyType();
	    	familyType = StringUtil.isEmpty(familyType) ? "无" : familyType;
	    	String remarks = bean.getRr();
	    	remarks = "党员户".equals(remarks) ? "-党员" : "";
	    	String linkName = zu + "-" + familyType + "-" + bean.getMaster() + "-" + bean.getCount()+"-"+bean.getCun()+remarks+".xls";
	    	linkName = new String(linkName.getBytes("gb2312"), "ISO8859-1");
	    	CreationHelper createHelper = workbook.getCreationHelper();
	    	HSSFHyperlink  hyperLink = (HSSFHyperlink) createHelper.createHyperlink(HyperlinkType.FILE); 
	    	hyperLink.setAddress(linkName);
	    	masterCell.setHyperlink(hyperLink);  
	    	CellStyle style = getCellStyle(workbook);
	    	HSSFFont hlinkfont = workbook.createFont();  
	        hlinkfont.setUnderline(HSSFFont.U_SINGLE);  
	        hlinkfont.setFontHeightInPoints((short)14);
	        hlinkfont.setColor(HSSFColorPredefined.BLUE.getIndex());  
	        style.setFont(hlinkfont); 
	    	masterCell.setCellStyle(style);
	    	masterCell.setCellValue(bean.getMaster());
	    	
	    	
	    	Cell countCell = row.createCell(2);
	    	countCell.setCellStyle(getCellStyle(workbook));
	    	countCell.setCellValue(Integer.valueOf(bean.getCount()));
	    	Cell zuCell = row.createCell(3);
	    	zuCell.setCellStyle(getCellStyle(workbook));
	    	zuCell.setCellValue(bean.getZu());
	    	Cell familyTypeCell = row.createCell(4);
	    	familyTypeCell.setCellStyle(getCellStyle(workbook));
	    	familyTypeCell.setCellValue(bean.getFamilyType());
	    	Cell cunCell = row.createCell(5);
	    	cunCell.setCellStyle(getCellStyle(workbook));
	    	cunCell.setCellValue(bean.getCun());
	    	Cell remarksCell = row.createCell(6);
	    	remarksCell.setCellStyle(getCellStyle(workbook));
	    	remarksCell.setCellValue(bean.getRr());
    	}
    	// 统计行
    	Row analysisRow = sheet.createRow(i+2);
    	for(int j = 0; j < 7; j++){
    		Cell c = analysisRow.createCell(j);
    		c.setCellStyle(getCellStyle(workbook));
    	}
    	CellRangeAddress range = new CellRangeAddress(i+2, i+2, 0, 1);
    	sheet.addMergedRegion(range);
    	setRangeStyle(range,sheet);
    	Cell hejiCell = analysisRow.getCell(0);
    	hejiCell.setCellValue("合计 ");
    	// 公式计数
    	Cell sumCell = analysisRow.getCell(2);
    	sumCell.setCellFormula("sum(C3:C"+(list.size()+2)+")");
    	// 计算个数
    	Cell countCell = analysisRow.getCell(3);
    	countCell.setCellValue(list.size());
    	// 
    	
    	return workbook;
    }
    
    private void setRangeStyle(CellRangeAddress range, Sheet sheet) {
    	RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
    	RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
    	RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
    	RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
    }
    
    public boolean exportExcel(HSSFWorkbook workbook, ResidenceBean bean, HttpServletResponse response) throws Exception {
    	if(bean == null) {
    		return false;
    	} 
    	
    	buildWorkBook(workbook, bean);
    	
    	List<ManBean> list = bean.getList();
    	
    	response.reset();
		response.setContentType("application/x-msdownload");
		String fileName = bean.getZu() + "-" + bean.getFamily_type() + "-" + bean.getMaster() + "-" + list.size() + "-" + bean.getCun();
		for(ManBean man : list) {
			if("党员".equals(man.getPolitics().trim())){
				fileName = fileName + "-" + "党员";
			}
		}
		String master = new String(fileName.getBytes("gb2312"), "ISO8859-1" );
		response.addHeader("Content-Disposition", "attachment; filename=\"" + master + ".xls\"");
		
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		ServletOutputStream servletOS = response.getOutputStream();
		workbook.write(ostream);
		servletOS.write(ostream.toByteArray());
		servletOS.flush();
		servletOS.close();
		return true;
    }
    
    
    public Workbook buildWorkBook(HSSFWorkbook workbook, ResidenceBean bean) {
    	Sheet sheet = workbook.getSheetAt(0);

    	
    	// xx乡镇xx村xx湾xx建档/更新日期xx
    	Cell titleCell = sheet.getRow(1).getCell(0); 
    	String title = getStringCellValue(titleCell);//xx乡镇xx村xx湾xx建档/更新日期xx
    	String[] titleSplite = title.split("；");//长度为4
    	String xiangzhen = titleSplite[0].split("：")[0] + "：" + bean.getXiangzhen();
    	String cun = titleSplite[1].split("：")[0] + "：" + bean.getCun();
    	String zu = titleSplite[2].split("：")[0] + "：" + bean.getZu();
    	String buildDate = titleSplite[3].split("：")[0] + "：" + bean.getBuild_date();
    	String newTitle = xiangzhen + "；" + cun + "；" + zu + "；" + buildDate;
    	titleCell.setCellValue(newTitle);
    	
    	// man
    	int manSize = bean.getList().size();
    	if(manSize > 10) {
    		for(int i = 0; i < manSize - 10; i++){
    			sheet.shiftRows(4, sheet.getLastRowNum(), 1);
    			sheet.createRow(4);
    		}
    	}
    	
    	List<ManBean> list = bean.getList();
    	for(int i = 0; i < list.size(); i++) {
	    	Row row = sheet.getRow(i+3);
	    	ManBean data= list.get(i);
	    	Cell relationCell = row.createCell(1);
	    	relationCell.setCellStyle(getCellStyle(workbook));
	    	relationCell.setCellValue(data.getRelation());
	    	Cell nameCell = row.createCell(2);
	    	nameCell.setCellStyle(getCellStyle(workbook));
	    	nameCell.setCellValue(data.getName());
	    	Cell sexCell = row.createCell(3);
	    	sexCell.setCellStyle(getCellStyle(workbook));
	    	sexCell.setCellValue(data.getSex());
	    	Cell educationCell = row.createCell(4);
	    	educationCell.setCellStyle(getCellStyle(workbook));
	    	educationCell.setCellValue(data.getEducation());
	    	Cell politicsCell = row.createCell(5);
	    	politicsCell.setCellStyle(getCellStyle(workbook));
	    	politicsCell.setCellValue(data.getPolitics());
	    	Cell registrationNatureCell = row.createCell(6);
	    	registrationNatureCell.setCellStyle(getCellStyle(workbook));
	    	registrationNatureCell.setCellValue(data.getRegistration_nature());
	    	Cell healthStateCell = row.createCell(7);
	    	healthStateCell.setCellStyle(getCellStyle(workbook));
	    	healthStateCell.setCellValue(data.getHealth_state());
	    	Cell idNoCell = row.createCell(8);
	    	idNoCell.setCellStyle(getCellStyle(workbook));
	    	idNoCell.setCellValue(data.getId_no());
	    	Cell addressCell = row.createCell(9);
	    	addressCell.setCellStyle(getCellStyle(workbook));
	    	addressCell.setCellValue(data.getAddress());
	    	Cell phoneCell = row.createCell(10);
	    	phoneCell.setCellStyle(getCellStyle(workbook));
	    	phoneCell.setCellValue(data.getPhone());
	    	Cell remarksCell = row.createCell(11);
	    	remarksCell.setCellStyle(getCellStyle(workbook));
	    	remarksCell.setCellValue(data.getRemarks());
    	}
    	
    	Row row = sheet.getRow(14+Math.max(0, manSize-10));
    	Cell familyTypeCell = row.createCell(1);
    	familyTypeCell.setCellStyle(getCellStyle(workbook));
    	familyTypeCell.setCellValue(bean.getFamily_type());
    	Cell doorFlateCell = row.createCell(2);
    	doorFlateCell.setCellStyle(getCellStyle(workbook));
    	doorFlateCell.setCellValue(bean.getDoor_plate());
    	Cell starLevelCell = row.createCell(4);
    	starLevelCell.setCellStyle(getCellStyle(workbook));
    	starLevelCell.setCellValue(bean.getStar_level());
    	Cell houseSummaryCell = row.createCell(6);
    	houseSummaryCell.setCellStyle(getCellStyle(workbook));
    	houseSummaryCell.setCellValue(bean.getHouse_summary());
    	Cell earthSummaryCell = row.createCell(8);
    	earthSummaryCell.setCellStyle(getCellStyle(workbook));
    	earthSummaryCell.setCellValue(bean.getEarth_summary());
    	Cell incomeCell = row.createCell(9);
    	incomeCell.setCellStyle(getCellStyle(workbook));
    	incomeCell.setCellValue(bean.getIncome());
    	Cell familyStateCell = row.createCell(11);
    	familyStateCell.setCellStyle(getCellStyle(workbook));
    	familyStateCell.setCellValue(bean.getFamily_state());
    	
    	return workbook;
    }
    
    private CellStyle getCellStyle(Workbook wb){
    	CellStyle style = wb.createCellStyle();
    	style.setBorderBottom(BorderStyle.THIN);   
    	style.setBorderTop(BorderStyle.THIN);   
    	style.setBorderLeft(BorderStyle.THIN);   
    	style.setBorderRight(BorderStyle.THIN);
    	
    	style.setAlignment(HorizontalAlignment.CENTER);
    	style.setVerticalAlignment(VerticalAlignment.CENTER); 
    	
    	style.setWrapText(true);
    	
    	Font font = wb.createFont();
    	font.setFontName("仿宋");
    	font.setFontHeightInPoints((short)14);
    	
    	style.setFont(font);
    	
    	return style;
    }
    
    
    private String getStringCellValue(Cell cell) {
    	if(cell == null) {
    		return "";
    	}
        String strCell = "";
        switch (cell.getCellTypeEnum()) {
        case STRING:
            strCell = cell.getStringCellValue();
            break;
        case NUMERIC:
        	cell.setCellType(CellType.STRING);
            strCell = cell.getStringCellValue();
            break;
        case BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

}
