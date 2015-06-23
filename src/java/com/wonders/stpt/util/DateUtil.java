/**
 * 
 */
package com.wonders.stpt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @ClassName: DateUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-12 下午08:19:19 
 *  
 */
public class DateUtil {
	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间格式
	 */
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static Date getDate(String dateStr,String formatStr){
		Date date = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			date = sdf.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDateStr(Date date,String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}
	
	public static String getNowDate(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String date = sdf.format(new Date());
			return date;
		}catch(Exception e){}
		return null;
	}
	
	public static String getNowTime(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
			String date = sdf.format(new Date());
			return date;
		}catch(Exception e){}
		return null;
	}
	
	public static String getCurrDate(String format){
		return new java.text.SimpleDateFormat(format).format(new java.util.Date());
	}
	public static String getLastYearDay(String date){
		//取得去年数据
		String lastDate = "";
		if(!"".equals(date)){
			//System.out.println("date--"+date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(date);
			Calendar c = Calendar.getInstance();  
			c.setTime(d);
			c.add(Calendar.YEAR, -1);
			d = c.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			lastDate = sdf.format(d);
		}
		return lastDate;
	}
	//取得前5天数据
	public static String getFiveDaysEarlier(String endDate){
		String startDate ="";
		if(!"".equals(endDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date end = null;
			try {
				end = sdf.parse(endDate);
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DATE, -5);
				end = c.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startDate = sdf.format(end);
		}
		return startDate;
	}
	
	//取得天数
	public static String getDayBeforeByParameter(String endDate,int num){
		String startDate ="";
		if(!"".equals(endDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date end = null;
			try {
				end = sdf.parse(endDate);
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DATE, -num+1);
				end = c.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			startDate = sdf.format(end);
		}
		return startDate;
	}
	
	//取得当前月的最后一天
	public static Date getLastDayOfTheMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}
}
