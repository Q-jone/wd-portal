/**
 * 
 */
package com.wonders.stpt.operationIndicator.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wonders.stpt.indicatorControl.entity.vo.MetroQualityControlVO;
import com.wonders.stpt.indicatorControl.service.MetroQualityControlService;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;
import com.wonders.stpt.metroIndicator.service.MetroQualityService;
import com.wonders.stpt.operationIndicator.entity.vo.ChartDataQuality;
import com.wonders.stpt.operationIndicator.entity.vo.QualityVo;
import com.wonders.stpt.util.DateUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;


/** 
 * @ClassName: OperationIndex 
 * @Description: 运营指标
 * @author ycl
 * @date 2012-4-26 上午09:58:12 
 *  
 */
@SuppressWarnings("serial")
public class QualityIndicatorAction extends BaseAjaxAction{

	private MetroQualityService qualityService;
	private MetroExpressService expressService;
	private MetroQualityControlService qualityControlService;
	
	private static TreeMap<Integer, String> lineMap = new TreeMap<Integer, String>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String daysSpan = "";		//显示几天的数据
	
	public MetroExpressService getExpressService() {
		return expressService;
	}
	
	public MetroQualityControlService getQualityControlService() {
		return qualityControlService;
	}

	public void setQualityControlService(
			MetroQualityControlService qualityControlService) {
		this.qualityControlService = qualityControlService;
	}

	public void setExpressService(MetroExpressService expressService) {
		this.expressService = expressService;
	}
	
	public MetroQualityService getQualityService() {
		return qualityService;
	}

	public void setQualityService(MetroQualityService qualityService) {
		this.qualityService = qualityService;
	}


	/**
	 * 跳转到列表显示页面
	 * @author ycl
	 */
	public String showQualityListPage(){
		List<String> tempLineList = this.expressService.findMetroLineConfig();
		lineMap = new TreeMap<Integer, String>();
		if(tempLineList!=null && tempLineList.size()!=0){
			for(int i=0;i<tempLineList.size();i++){
				String []temp = tempLineList.get(i).split(":");
				lineMap.put(Integer.valueOf(temp[0]),temp[1]);
			}
		}
		servletRequest.setAttribute("lineMap", lineMap);
		List<Object[]> days = qualityService.findFirstAndLastDate();
		if(days!=null && days.size()>0){
			servletRequest.setAttribute("firstDate", days.get(0)[0].toString());
			servletRequest.setAttribute("lastDate",days.get(0)[1].toString());
		}
		return "showQualityListPage";
	}
	
	/**
	 * 查询数据MetroQuality日视图
	 * @author ycl
	 */
	public String findChartDataQuality(){
		List<String> dateList = new ArrayList<String>();
		List<String> fullDateList = new ArrayList<String>();
		List<Double> onTimeList = new ArrayList<Double>();			//正点率
		List<Double> onWorkList = new ArrayList<Double>();			//兑现率
		ChartDataQuality qualityData = new ChartDataQuality();
		
		daysSpan = servletRequest.getParameter("daysSpan");
		String line = super.servletRequest.getParameter("lineNo");
		String date = super.servletRequest.getParameter("date");
		
		if(StringUtils.isEmpty(daysSpan)){
			daysSpan = "5";
		}
		if(StringUtils.isEmpty(line)){
			line = "0";
		}
		if(StringUtils.isEmpty(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		
		MetroQuality recentlyQu = qualityService.findRecentlyQuality(date, line);
		String recentlyDate ="";
		if(recentlyQu!=null){
			recentlyDate = qualityService.findRecentlyQuality(date, line).getIndicatorDate();
		}
		
		if(StringUtils.isNotEmpty(recentlyDate)){
			List<MetroQuality> qualityList = qualityService.findQualityByDays(DateUtil.getDayBeforeByParameter(recentlyDate, Integer.valueOf(daysSpan)), recentlyDate, line);		
			List<QualityVo> qualityVoList = null;
			if(qualityList!=null && qualityList.size()>0){
				qualityVoList = new ArrayList<QualityVo>();
				QualityVo vo = null;
				for(int i=0; i<qualityList.size(); i++){
					vo = new QualityVo(qualityList.get(i));
					qualityVoList.add(vo);
				}
			}
			MetroQuality mqLast = qualityService.findMetroQualityByDate(DateUtil.getLastYearDay(recentlyDate), line);
			QualityVo lastYearQuality = new QualityVo();
			if(mqLast!=null){
				lastYearQuality = new QualityVo(mqLast);
			}
			qualityData.setLastYearQuality(lastYearQuality);
			if(qualityVoList!=null && qualityVoList.size()!=0){
				for(int i=0;i<qualityVoList.size();i++){
					onTimeList.add(qualityVoList.get(i).getMetroOntimeDaily());
					onWorkList.add(qualityVoList.get(i).getMetroOnworkDaily());
					String dateTemp = qualityVoList.get(i).getIndicatorDate();
					fullDateList.add(dateTemp);
					dateTemp = dateTemp.substring(5,dateTemp.length());
					dateList.add(dateTemp);
				}
				qualityData.setDateList(dateList);
				qualityData.setOnTimeList(onTimeList);
				qualityData.setOnWorkList(onWorkList);
				qualityData.setLastestQuality(qualityVoList.get(qualityVoList.size()-1));
			}
			
			List<MetroQualityControl> mqcList = null; 
			
			if(fullDateList!=null && fullDateList.size()>0){
				mqcList = qualityControlService.findMQCByLineAndDates(line, fullDateList);
			}
			
			List<String> ontimeControlValueList ,onworkControlValueList;
			if(mqcList!=null && mqcList.size()>0){
				ontimeControlValueList = new ArrayList<String>();
				onworkControlValueList = new ArrayList<String>();
				for(int i=0; i<mqcList.size(); i++){
					if(mqcList.get(i)!=null){
						if(mqcList.get(i).getMetroOntimeDaily()!=null){
							ontimeControlValueList.add(mqcList.get(i).getMetroOntimeDaily()*100+"");
						}else{
							ontimeControlValueList.add(null);
						}
						
						if(mqcList.get(i).getMetroOnworkDaily()!=null){
							onworkControlValueList.add(mqcList.get(i).getMetroOnworkDaily()*100+"");
						}else{
							onworkControlValueList.add(null);
						}
					}else{
						ontimeControlValueList.add(null);
						onworkControlValueList.add(null);
					}
					
				}
				qualityData.setOntimeControlValueList(ontimeControlValueList);
				qualityData.setOnworkControlValueList(onworkControlValueList);
				
				if(mqcList.get(mqcList.size()-1)!=null){
					qualityData.setMetroQualityControlVO(new MetroQualityControlVO(mqcList.get(mqcList.size()-1)));
				}else{
					qualityData.setMetroQualityControlVO(null);
				}
				
			}
			
			String jsonData = VOUtils.getJsonData(qualityData);
			createJSonData(jsonData);
		}else{
			createJSonData("");
		}
		return AJAX;
	}
	
	/**
	 * 跳转到详细页面
	 * @author ycl
	 */
	@SuppressWarnings("unchecked")
	public String showQualityDetailPage(){
		List<Object[]> days = qualityService.findFirstAndLastDate();
		if(days!=null && days.size()>0){
			servletRequest.setAttribute("firstDate", days.get(0)[0].toString());
			servletRequest.setAttribute("lastDate",days.get(0)[1].toString());
		}
		String lineNo = servletRequest.getParameter("lineNo");
		String endDate = servletRequest.getParameter("endDate");	
		String chartId = servletRequest.getParameter("chartId");
		String pageNo = servletRequest.getParameter("pageNo");
		String startDate = servletRequest.getParameter("startDate");
		Page page = null;
		int pageSize = 20;
		List<QualityVo> qualitiesVoList= null;
		
		if(pageNo==null ||"".equals(pageNo)){
			pageNo = "1";
		}
		if(StringUtils.isNotEmpty(endDate)){
			if(StringUtils.isEmpty(startDate)){
				startDate = DateUtil.getDayBeforeByParameter(endDate, 15);
			}
		}
		servletRequest.setAttribute("lineNo", lineNo);
		servletRequest.setAttribute("startDate", startDate);
		servletRequest.setAttribute("endDate", endDate);
		servletRequest.setAttribute("chartId", chartId);
		String now = sdf.format(new Date());
		
		String lastDateWithData = qualityService.findRecentlyQuality(now,lineNo).getIndicatorDate();	
		servletRequest.setAttribute("recentlyDate", lastDateWithData);
		if(lineMap!=null&&lineMap.size()>0){
			
		}else{
			List<String> tempLineList = this.expressService.findMetroLineConfig();
			lineMap = new TreeMap<Integer, String>();
			if(tempLineList!=null && tempLineList.size()!=0){
				for(int i=0;i<tempLineList.size();i++){
					String []temp = tempLineList.get(i).split(":");
					lineMap.put(Integer.valueOf(temp[0]),temp[1]);
				}
			}
		}
		servletRequest.setAttribute("lineMap", lineMap);
		
		
		page = qualityService.findMetroQualitiesByPage(Integer.valueOf(pageNo), pageSize,lineNo);
		List<MetroQuality> boList = page.getResult();
		if(boList!=null && boList.size()>0){
			qualitiesVoList = new ArrayList<QualityVo>();
			for(int i=0; i<boList.size(); i++){
				QualityVo vo = new QualityVo(boList.get(i));
				QualityVo last = new QualityVo(qualityService.findMetroQualityByDate(vo.getIndicatorDate(), vo.getIndicatorLine()));
				if(last!=null){
					vo.setLastyearQualityVo(last);
				}
				qualitiesVoList.add(vo);
			}
			page.setResult(qualitiesVoList);
		}
		
		servletRequest.setAttribute("page", page);
		
		switch (Integer.valueOf(chartId)) {
		case 8:
			return "chart8";
		case 9:
			return "chart9";
		}
		
		return "showQualityDetailPage";
	}
	
	/**
	 * 查询MetroQuality月视图
	 * 
	 */
	public String findChartDataQualityByMonth(){
		
		String date = servletRequest.getParameter("date");
		String line = servletRequest.getParameter("line");
		List<String> fullDateList = new ArrayList<String>();
		
		//查询当前月有数据的最后一天
		MetroQuality mq = qualityService.findMetroQualityByLastDay(date.substring(0,7), line);
		if(mq!=null){
			List<MetroQuality> mqList = qualityService.findMetroQualityByMonth(5, mq.getIndicatorDate(), line);
			MetroQuality lastyearMq = qualityService.findMetroQualityByDate(DateUtil.getLastYearDay(mq.getIndicatorDate()), line);
			List<QualityVo> mqVoList = new ArrayList<QualityVo>();
			if(mqList!=null && mqList.size()>0){
				for(MetroQuality temp : mqList){
					mqVoList.add(new QualityVo(temp));
					fullDateList.add(temp.getIndicatorDate());
				}
			}
			if(lastyearMq!=null){
				mqVoList.get(mqVoList.size()-1).setLastyearQualityVo(new QualityVo(lastyearMq));
			}else{
				mqVoList.get(mqVoList.size()-1).setLastyearQualityVo(new QualityVo());
			}
			
			
			List<MetroQualityControl> mqcList = null; 
			
			if(fullDateList!=null && fullDateList.size()>0){
				mqcList = qualityControlService.findMQCByLineAndDates(line, fullDateList);
				if(mqcList!=null && mqVoList!=null && mqcList.size()==mqVoList.size()){
					for(int i=0; i<mqList.size(); i++){
						if(mqcList.get(i)!=null){
							mqVoList.get(i).setMetroQualityControlVO(new MetroQualityControlVO(mqcList.get(i)));
						}else{
							mqVoList.get(i).setMetroQualityControlVO(null);
						}
					}
				}
			}
			
			String jsonData = VOUtils.getJsonDataFromCollection(mqVoList);
			createJSonData(jsonData);
		}else{
			createJSonData("");
		}
		return AJAX;
	}
	
	/**
	 * 查询MetroQuality年视图
	 * 
	 */
	public String findChartDataQualityByYear(){
		String date = servletRequest.getParameter("date");
		String line = servletRequest.getParameter("line");
		List<String> fullDateList = new ArrayList<String>();
		//查询当前月有数据的最后一天
		MetroQuality mp = qualityService.findMetroQualityByLastDay(date.substring(0,4), line);
		if(mp!=null){
			List<MetroQuality> mqList = qualityService.findMetroQualityByYear(5, mp.getIndicatorDate(), line);
			MetroQuality lastyearMq = qualityService.findMetroQualityByDate(DateUtil.getLastYearDay(mp.getIndicatorDate()), line);
			List<QualityVo> mqVoList = new ArrayList<QualityVo>();
			if(mqList!=null && mqList.size()>0){
				for(MetroQuality temp : mqList){
					mqVoList.add(new QualityVo(temp));
					fullDateList.add(temp.getIndicatorDate());
				}
			}
			if(lastyearMq!=null){
				mqVoList.get(mqVoList.size()-1).setLastyearQualityVo(new QualityVo(lastyearMq));
			}else{
				mqVoList.get(mqVoList.size()-1).setLastyearQualityVo(new QualityVo());
			}
			
			List<MetroQualityControl> mqcList = null; 
			if(fullDateList!=null && fullDateList.size()>0){
				//mqcList = qualityControlService.findMQCByLineAndDates(line, fullDateList);
				mqcList = qualityControlService.findMQCByLineAndYear(line, fullDateList);
				if(mqcList!=null && mqVoList!=null && mqcList.size()==mqVoList.size()){
					for(int i=0; i<mqList.size(); i++){
						if(mqcList.get(i)!=null){
							mqVoList.get(i).setMetroQualityControlVO(new MetroQualityControlVO(mqcList.get(i)));
						}else{
							mqVoList.get(i).setMetroQualityControlVO(null);
						}
					}
				}
			}
			
			String jsonData = VOUtils.getJsonDataFromCollection(mqVoList);
			createJSonData(jsonData);
		}else{
			createJSonData("");
		}
		return AJAX;
	}
	
	
	/**
	 * 查询时间段之间的数据Quality
	 * 
	 */
	public String findQualityBetweenDays(){
		String startDate = servletRequest.getParameter("startDate");
		String endDate = servletRequest.getParameter("endDate");
		String line = servletRequest.getParameter("line");
		List<MetroQuality> mqList = qualityService.findQualityByDays(startDate, endDate, line);
		List<QualityVo> voList = null;
		
		List<String> dates ;
		if(mqList!=null && mqList.size()>0){
			dates = new ArrayList<String>(mqList.size());
			voList = new ArrayList<QualityVo>(mqList.size());
			for(int i=0; i<mqList.size(); i++){
				dates.add(mqList.get(i).getIndicatorDate());
				voList.add(new QualityVo(mqList.get(i)));
			}
			
			List<MetroQualityControl> mqcList = qualityControlService.findMQCByLineAndDates(line, dates);
			
			
			if(mqcList!=null && mqList!=null && mqcList.size()==mqList.size()){
				for(int i=0; i<mqList.size(); i++){
					if(mqcList.get(i)!=null){
						voList.get(i).setMetroQualityControlVO(new MetroQualityControlVO(mqcList.get(i)));
					}else{
						voList.get(i).setMetroQualityControlVO(null);
					}
				}
			}
		}
		/*
		List<MetroQualityControl> mqcList = null; 
		
		if (mqList!=null && mqList.size()>0) {
			MetroQualityControl mqc = qualityControlService.findMetroQualityControlByLineAndDate(line, endDate.substring(0,4));
			voList = new ArrayList<QualityVo>();
			for(int i=0; i<mqList.size(); i++){
				voList.add(new QualityVo(mqList.get(i)));
			}
			if(voList!=null && voList.size()>0 && mqc!=null){
				voList.get(voList.size()-1).setMetroQualityControlVO(new MetroQualityControlVO(mqc));
			}
		}
		*/
		
		
		String jsondata = VOUtils.getJsonDataFromCollection(voList);
		createJSonData(jsondata);
		return AJAX;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	











}
