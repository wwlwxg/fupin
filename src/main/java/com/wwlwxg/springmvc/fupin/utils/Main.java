package com.wwlwxg.springmvc.fupin.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wwlwxg.springmvc.fupin.bean.ResidenceBean;

public class Main {
	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
//		String root = "C:/Users/jecky/Desktop/扶贫/2017-9-14/";
//		List<String> list = Arrays.asList(root.list());
		Main main = new Main();
		List<String> result = main.getFileList(root+"/11");
		System.out.println(result);
        ExcelReaderAndWriter excelReader = new ExcelReaderAndWriter();
        List<ResidenceBean> bean = excelReader.read(result);
        
		
	}
	
	public List<String> filelist = new ArrayList<String>();
	
	public List<String> getFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if(files[i].isHidden()||files[i].getName().contains("~")){
					continue;
				}
				String fileName = files[i].getName();
				if (files[i].isDirectory()) { 
					getFileList(files[i].getAbsolutePath()); 
				} else if (fileName.endsWith("xlsx")) { 
					String strFileName = files[i].getName();
					System.out.println("---nihao:" + files[i].getAbsolutePath());
					String temp = strFileName.substring(0,strFileName.lastIndexOf("."));
					String[] t = temp.split("-");
					filelist.add(files[i].getAbsolutePath());
				} else {
					continue;
				}
			}

		}
		
		return filelist;
	}
}
