package com.wonders.stpt.exam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Test {

	public static void main(String[] args) {

		//File f = new File("E:\Projects\02ShenTong\02EXAM\SQL");
		
		String xlsFile="E://Projects//02ShenTong//02EXAM//SQL//SQL.xls";
		readExcel(xlsFile);
		
		//List<String> names = tree(f);
		//exExcel(names,xlsFile);
	}

	// 显示目录的方法
	public static List<String> tree(File f) {
		List<String> list = new ArrayList<String>();
		// 判断传入对象是否为一个文件夹对象
		if (!f.isDirectory()) {
			System.out.println("你输入的不是一个文件夹，请检查路径是否有误！！");
		} else {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				// 判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
				if (t[i].isDirectory()) {
					list.add(t[i].getName());
					System.out.println(t[i].getName() + "\tttdir");
					// tree(t[i]);// 递归 输入子目录下信息
				} else {
					// System.out.println(t[i].getName()+"tFile");
					// //输出文件夹下面的文件名字
				}
			}
		}
		return list;
	}
	
	
	public static void exExcel(List<String> list,String xlsFile){
		
		try
		 {
		 HSSFWorkbook workbook = new HSSFWorkbook(); //产生工作簿对象
		 HSSFSheet sheet = workbook.createSheet(); //产生工作表对象
		 //设置第一个工作表的名称为firstSheet
		 //为了工作表能支持中文，设置字符编码为UTF_16
		 workbook.setSheetName(0,"firstSheet");
		 
		 
		 for(int i=0;i<list.size();i++){
			 //产生一行
			 HSSFRow row = sheet.createRow((short)i);
			 
			 //产生第一个单元格
			 HSSFCell cell = row.createCell((short) 0);
			 //设置单元格内容为字符串型
			 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			 //为了能在单元格中写入中文，设置字符编码为UTF_16。
//			 cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			 //往第一个单元格中写入信息
			 cell.setCellValue(list.get(i));
		 }
		
		 FileOutputStream fOut = new FileOutputStream(xlsFile);
		 workbook.write(fOut);
		 fOut.flush();
		 fOut.close();
		 System.out.println("文件生成...");
		 
		 
		 
		 //以下语句读取生成的Excel文件内容
		 FileInputStream fIn=new FileInputStream(xlsFile);
		 HSSFWorkbook readWorkBook= new HSSFWorkbook(fIn);
		 HSSFSheet readSheet= readWorkBook.getSheet("firstSheet");
		 HSSFRow readRow =readSheet.getRow(0);
		 HSSFCell readCell = readRow.getCell((short)0);
		 System.out.println("第一个单元是：" + readCell.getStringCellValue()); 
		 }
		 catch(Exception e) 
		 {
		 System.out.println(e);
		 }
	}
	public static void readExcel(String xlsFile){
		try{
//			System.out.println("dddddddddddddd");
		 //以下语句读取生成的Excel文件内容
		 FileInputStream fIn=new FileInputStream(xlsFile);
		 HSSFWorkbook readWorkBook= new HSSFWorkbook(fIn);
		 HSSFSheet readSheet= readWorkBook.getSheetAt(4);//.getSheet("firstSheet");
		 
		 String getsettem="// %s \r\n"
		 		+ "@Column(name = \"%s\", nullable = true, length =%s )\r\n"
		 		+ "public %s get%s() {\r\n"
		 		+ "return %s;\r\n"
		 		+ "}\r\n"
		 		+ "public void set%s(%s %s) {\r\n"
		 		+ "this.%s = %s;\r\n"
		 		+ "}\r\n";
		 
		 String java = "";
		 String top="";
		 String getset="";
		 	for(int rnum=0;rnum<readSheet.getLastRowNum()+1;rnum++){
				 HSSFRow readRow =readSheet.getRow(rnum);
				 
				 String com = readRow.getCell((short)0).getStringCellValue();
				 String typeAll = readRow.getCell((short)1).getStringCellValue();
				 String info = readRow.getCell((short)2).getStringCellValue();
				 
				 String type = "";
				 String  len = "";
				 
				 String comJ = com.toLowerCase();
				 if(comJ.indexOf("_")>0){
					 String[] c = comJ.split("_");
					 String c1 = c[1].substring(0,1).toUpperCase()+c[1].substring(1);
					 
					 comJ=c[0]+c1;
				 }
						 
				 if(typeAll.startsWith("NUM")){
					 type="long ";
					 len ="9";
				 }else{
					 type="String ";
					 len =typeAll.substring(9, typeAll.length()-1);//(typeAll.split("(")[1]).split(")")[0];
				 }
			 
				 top+="private "+type+comJ+";    //"+info+"\r\n\r\n";
				 
				 
				/*// SDDD
				 * @Column(name = "title", nullable = true, length = 500)
					public String getTitle() {
						return title;
					}
					public void setTitle(String title) {
						this.title = title;
					}*/
//				 str = string.format(getsettem,str,int);
				 
				 String comb = comJ.substring(0,1).toUpperCase()+comJ.substring(1);
				 getset +=String.format(getsettem,info,com,len,type,comb,comJ,comb,type,comJ,comJ,comJ);
			 }
		 	java = top;
		 System.out.println("第一个单元是：" + java); 
		 
		 System.out.println("第一个单元是：" + getset); 
		 }
		 
		
		 
		 
		
		 catch(Exception e) 
		 {
		 System.out.println(e);
		 }
	}

}
