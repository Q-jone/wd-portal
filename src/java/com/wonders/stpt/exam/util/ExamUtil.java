package com.wonders.stpt.exam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExamUtil {
	
	public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DF_YYYY = "yyyy";
	public static final String DF_MM = "MM";
	public static final String DF_YYYY_MM = "yyyy-MM";
	public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	//获取当前时间
	public static String getNowTime(){
		return formartDate(new Date(System.currentTimeMillis()),DF_YYYY_MM_DD_HH_MM_SS);
	}
	
	//获取当前日期
	public static String getNowDate(){
		return formartDate(new Date(System.currentTimeMillis()),DF_YYYY_MM_DD);
	}
	
	//获取当前日期
	public static String getformartDate(String dateString){
		if(dateString==null) return null;
		Date d =null;
		try {
			d = formatDateString(dateString,DF_YYYY_MM_DD);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(d==null) return null;
		return formartDate(d,DF_YYYY_MM_DD);
	}
	
	public static String formartDate(Date date,String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static Date formatDateString(String dateString,
			String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(dateString);
	}
	
	public static void main(String[] args) throws ParseException {
		
		
		System.out.println(getformartDate("2013-11-12"));
	}

}
