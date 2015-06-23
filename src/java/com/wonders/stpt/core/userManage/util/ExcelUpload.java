/**
 * 
 */
package com.wonders.stpt.core.userManage.util;
import java.io.*;   
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.util.StringUtil;

import jxl.*; 
/** 
 * @ClassName: ExcelUpload 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-3 上午11:34:50 
 *  
 */
public class ExcelUpload {
	public static List<StptUser> readExcel(String fileName){
		 List<StptUser> list = new ArrayList<StptUser>();
		 List<String> tmp = new ArrayList<String>();
	        try {   
	            Workbook book = Workbook.getWorkbook(new File(fileName));   
	            //get a Sheet object.    
	            Sheet[] sheets = book.getSheets();
	            //get 1st-Column,1st-Row content.  
	            for(Sheet s:sheets){
	            	//System.out.println(s.getName()+" "+s.getRows()+" "+s.getColumns());
	            	for(int i=1;i<s.getRows();i++){	
	            		StptUser m = new StptUser();
	            		Cell[] cells = s.getRow(i);  
	            		//System.out.println(cells.length);
	            		//System.out.println(cells);
            		if(cells!=null){
            			//System.out.println("------------------------------------------");
            			m.setName(StringUtil.getNotNullValueString(cells[0].getContents()));
            			m.setCompany(StringUtil.getNotNullValueString(cells[1].getContents()));
            			m.setDept(StringUtil.getNotNullValueString(cells[2].getContents()));
            			m.setLoginName(StringUtil.getNotNullValueString(cells[3].getContents()));
            			list.add(m);
	            	}else{
	            		System.out.print("excel格式错误导入数据失败！");
	            		return null;
	            	}
	            }
	            } 
	            book.close(); 
	            return list;
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }   
	        return null;
	  
	}
}
