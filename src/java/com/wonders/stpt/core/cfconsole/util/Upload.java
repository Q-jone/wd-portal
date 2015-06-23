/**
 * 
 */
package com.wonders.stpt.core.cfconsole.util;
import java.io.*;   
import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.core.cfconsole.entity.vo.ConsoleVo;
import com.wonders.stpt.util.StringUtil;

import jxl.*; 
/** 
 * @ClassName: ExcelUpload 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-3 上午11:34:50 
 *  
 */
public class Upload {
	public static List<ConsoleVo> readExcel(String fileName){
		 List<ConsoleVo> list = new ArrayList<ConsoleVo>();
	        try {   
	            Workbook book = Workbook.getWorkbook(new File(fileName));   
	            Sheet[] sheets = book.getSheets();
	            for(Sheet s:sheets){
	            	for(int i=1;i<s.getRows();i++){	
	            		ConsoleVo m = new ConsoleVo();
	            		Cell[] cells = s.getRow(i);  
            		if(cells!=null){
            			System.out.println(cells.length);
            			m.setLoginName(StringUtil.getNotNullValueString(cells[0].getContents()));
            			m.setUserName(StringUtil.getNotNullValueString(cells[1].getContents()));
            			m.setDeptId(StringUtil.getNotNullValueString(cells[2].getContents()));
            			m.setOrders(StringUtil.getNotNullValueString(cells[3].getContents()));
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
