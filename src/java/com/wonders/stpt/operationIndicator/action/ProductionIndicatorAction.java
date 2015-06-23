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

import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
import com.wonders.stpt.indicatorControl.entity.vo.MetroProductionControlVO;
import com.wonders.stpt.indicatorControl.service.MetroProductionControlService;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;
import com.wonders.stpt.metroIndicator.service.MetroProductionService;
import com.wonders.stpt.metroIndicator.service.MetroQualityService;
import com.wonders.stpt.metroIndicator.service.MetroScaleService;
import com.wonders.stpt.operationIndicator.entity.vo.ChartDataProduction;
import com.wonders.stpt.operationIndicator.entity.vo.ProductionVo;
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
public class ProductionIndicatorAction extends BaseAjaxAction{

	private MetroScaleService scaleService;
	private MetroQualityService qualityService;
	private MetroProductionService productionService;
	private MetroExpressService expressService;
	private MetroProductionControlService metroProductionControlService;
	private static TreeMap<Integer, String> lineMap = new TreeMap<Integer, String>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String daysSpan = "";	
	
	public MetroProductionControlService getMetroProductionControlService() {
		return metroProductionControlService;
	}

	public void setMetroProductionControlService(
			MetroProductionControlService metroProductionControlService) {
		this.metroProductionControlService = metroProductionControlService;
	}
	public MetroExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(MetroExpressService expressService) {
		this.expressService = expressService;
	}
	
	public MetroScaleService getScaleService() {
		return scaleService;
	}

	public void setScaleService(MetroScaleService scaleService) {
		this.scaleService = scaleService;
	}

	public MetroQualityService getQualityService() {
		return qualityService;
	}

	public void setQualityService(MetroQualityService qualityService) {
		this.qualityService = qualityService;
	}

	public MetroProductionService getProductionService() {
		return productionService;
	}

	public void setProductionService(MetroProductionService productionService) {
		this.productionService = productionService;
	}

	/**
	 * 跳转到列表显示页面
	 * @author ycl
	 */
	public String showProductionListPage(){
		List<String> tempLineList = this.expressService.findMetroLineConfig();
		lineMap = new TreeMap<Integer, String>();
		if(tempLineList!=null && tempLineList.size()!=0){
			for(int i=0;i<tempLineList.size();i++){
				String []temp = tempLineList.get(i).split(":");
				lineMap.put(Integer.valueOf(temp[0]),temp[1]);
			}
		}
		servletRequest.setAttribute("lineMap", lineMap);
		List<Object[]> days = productionService.findFirstAndLastDate();
		if(days!=null && days.size()>0){
			servletRequest.setAttribute("firstDate", days.get(0)[0].toString());
			servletRequest.setAttribute("lastDate",days.get(0)[1].toString());
		}
		return "showProductionListPage";
	}
	
	/**
	 * 查询MetroProduction日视图
	 * @author ycl
	 */
	public String findChartDataProduction(){
		
		List<String> dateList = new ArrayList<String>();
		List<Integer> columnList = new ArrayList<Integer>();
		List<Double> distanceList = new ArrayList<Double>();
		List<Double> passengerCapacityList = new ArrayList<Double>();
		List<Double> passengerTransferList = new ArrayList<Double>();
		List<Double> ticketIncomeList = new ArrayList<Double>();
		List<Long> onlineSectionList = new ArrayList<Long>();
		List<Long> backupSectionList = new ArrayList<Long>();
		
		
		ChartDataProduction data = new ChartDataProduction();		
		
		daysSpan = servletRequest.getParameter("daysSpan");
		String line = servletRequest.getParameter("lineNo");
		String date = servletRequest.getParameter("date");
		
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
		MetroProduction recentlyMP = productionService.findRecentlyProduction(date, line);
		String recentlyDate ="";
		if(recentlyMP!=null){
			recentlyDate = productionService.findRecentlyProduction(date, line).getIndicatorDate();
		}
		
		if(StringUtils.isNotEmpty(recentlyDate)){
			//list页面，取前5天
			List<MetroProduction> productionList = productionService.findProductionByDays(DateUtil.getDayBeforeByParameter(recentlyDate, Integer.valueOf(daysSpan)), recentlyDate, line);
			List<ProductionVo> productionVoList = null;
			if(productionList!=null && productionList.size()>0){
				productionVoList = new ArrayList<ProductionVo>();
				ProductionVo vo = null;
				for(int i=0; i<productionList.size(); i++){
					vo = new ProductionVo(productionList.get(i));
					productionVoList.add(vo);
				}
			}
			MetroProduction lastyearProduction = productionService.findMetroProductionByDate(DateUtil.getLastYearDay(recentlyDate), line);
			
			if(lastyearProduction!=null){
				data.setLastYearProduction(new ProductionVo(lastyearProduction));
			}else{
				data.setLastYearProduction(new ProductionVo());
			}
			if(productionVoList!=null && productionVoList.size()>0){
				
				for(int i=0;i<productionVoList.size();i++){
					dateList.add(productionVoList.get(i).getIndicatorDate().substring(5,productionVoList.get(i).getIndicatorDate().length()));
					columnList.add(productionVoList.get(i).getMetroColumnDaily());
					distanceList.add(productionVoList.get(i).getMetroDistanceDaily());
					passengerCapacityList.add(productionVoList.get(i).getPassengerCapacityDaily());
					passengerTransferList.add(productionVoList.get(i).getPassengerTransferDaily());
					ticketIncomeList.add(productionVoList.get(i).getTicketIncomeDaily());
					
					
				}
				if(productionVoList.size()>1){
					data.setLastestProduction(productionVoList.get(productionVoList.size()-1));		//最近一天有数据的
				}else{
					data.setLastestProduction(productionVoList.get(0));		//最近一天有数据的
				}
				
				if(data.getLastestProduction()!=null){
					onlineSectionList.add(data.getLastestProduction().getOnlineThreeSection());
					onlineSectionList.add(data.getLastestProduction().getOnlineFourSection());
					onlineSectionList.add(data.getLastestProduction().getOnlineSixSection());
					onlineSectionList.add(data.getLastestProduction().getOnlineSevenSection());
					onlineSectionList.add(data.getLastestProduction().getOnlineEightSection());
					
					backupSectionList.add(data.getLastestProduction().getBackupThreeSection());
					backupSectionList.add(data.getLastestProduction().getBackupFourSection());
					backupSectionList.add(data.getLastestProduction().getBackupSixSection());
					backupSectionList.add(data.getLastestProduction().getBackupSevenSection());
					backupSectionList.add(data.getLastestProduction().getBackupEightSection());
				}
				data.setOnlineSectionList(onlineSectionList);
				data.setBackupSectionList(backupSectionList);
			}

			data.setDateList(dateList);
			data.setColumnList(columnList);
			data.setDistanceList(distanceList);
			data.setPassengerCapacityList(passengerCapacityList);
			data.setPassengerTransferList(passengerTransferList);
			data.setTicketIncomeList(ticketIncomeList);
			
			List<MetroProductionControl> mpcList = metroProductionControlService.findProductionControlByDatesAndLine(DateUtil.getDayBeforeByParameter(date, Integer.valueOf(daysSpan)),date, line);
			
			
			
			
			
			if(mpcList!=null && mpcList.size()>0){
				
				List<String> metroControlValueList = new ArrayList<String>();	//开行列次,管控值
				List<String> distanceControlValueList = new ArrayList<String>();	//运营里程,管控值
				List<String> passengerCapacityControlValueList = new ArrayList<String>();	//客运量,管控值
				List<String> passengerTransferControlValueList = new ArrayList<String>();	//换乘人次,管控值
				List<String> ticketIncomeControlValueList = new ArrayList<String>();	//运营收入,管控值
				
				for(int i=0; i<mpcList.size(); i++){
					
					MetroProductionControl tempControl = mpcList.get(i);
					
					if(tempControl!=null){
						//设置管控值
						metroControlValueList.add(tempControl.getMetroColumnDaily()+"");
						distanceControlValueList.add(tempControl.getMetroDistanceDaily()+"");
						passengerCapacityControlValueList.add(tempControl.getPassengerCapacityDaily()+"");
						passengerTransferControlValueList.add(tempControl.getPassengerTransferDaily()+"");
						ticketIncomeControlValueList.add(tempControl.getTicketIncomeDaily()+"");
						
						/*metroControlValueList.add("4000");
						distanceControlValueList.add("4000");
						passengerCapacityControlValueList.add("5000");
						passengerTransferControlValueList.add("4000");
						ticketIncomeControlValueList.add("5000");*/
						
					}else{
						
						
						//添加管控值
						metroControlValueList.add(null);
						distanceControlValueList.add(null);
						passengerCapacityControlValueList.add(null);
						passengerTransferControlValueList.add(null);
						ticketIncomeControlValueList.add(null);
					}
				}
				
				
				data.setColumnControlValue(metroControlValueList);
				data.setDistanceControlValue(distanceControlValueList);
				data.setCapacityControlValue(passengerCapacityControlValueList);
				data.setTransferControlValue(passengerTransferControlValueList);
				data.setTicketControlValue(ticketIncomeControlValueList);
			
				if(mpcList.get(mpcList.size()-1)!=null){
					data.setMetroProductionControlVO(new MetroProductionControlVO(mpcList.get(mpcList.size()-1)));
				}
			}
			
			
			
			
			
			servletRequest.setAttribute("data", data);
			String jsondata = VOUtils.getJsonData(data);
			createJSonData(jsondata);
		}else{
			createJSonData("");
		}
		
		return AJAX;
	}
	
	/**
	 * 查询MetroProduction月视图
	 * 
	 */
	public String findChartDataProductionByMonth(){
		
		String date = servletRequest.getParameter("date");
		String line = servletRequest.getParameter("line");
		//查询当前月有数据的最后一天
		MetroProduction mp = productionService.findMetroProductionByLastDay(date.substring(0,7), line);
		if(mp!=null){
			List<MetroProduction> mpList = productionService.findMetroProductionByMonth(5, mp.getIndicatorDate(), line);
			MetroProduction lastyearMp = productionService.findMetroProductionByDate(DateUtil.getLastYearDay(mp.getIndicatorDate()), line);
			List<ProductionVo> mpVoList = new ArrayList<ProductionVo>();
			if(mpList!=null && mpList.size()>0){
				String[] dates = new String[mpList.size()];
//				for(MetroProduction temp : mpList){
				for(int i=0; i<mpList.size(); i++){
					mpVoList.add(new ProductionVo(mpList.get(i)));
					dates[i] = mpList.get(i).getIndicatorDate();
				}
				if(lastyearMp!=null){
					mpVoList.get(mpVoList.size()-1).setLastyearMetroProduction(new ProductionVo(lastyearMp));
				}else{
					mpVoList.get(mpVoList.size()-1).setLastyearMetroProduction(new ProductionVo());
				}
				
				//查询管控值
				List<MetroProductionControl> mpcList = metroProductionControlService.findMPCByDatesAndLine(dates, line);

				
				if(mpcList!=null && mpcList.size()>0 && mpVoList.size()==mpcList.size()){
					for(int i=0; i<mpcList.size(); i++){
						if(mpcList.get(i)!=null){
							mpVoList.get(i).setMetroProductionControlVO(new MetroProductionControlVO(mpcList.get(i)));
						}else{
							mpVoList.get(i).setMetroProductionControlVO(null);
						}
					}
				}
			}
			
			String jsonData = VOUtils.getJsonDataFromCollection(mpVoList);
			createJSonData(jsonData);
		}else{
			List<ProductionVo> mpVoList = null;
			String jsonData = VOUtils.getJsonDataFromCollection(mpVoList);
			createJSonData(jsonData);
		}
		return AJAX;
	}
	
	/**
	 * 查询MetroProduction年视图
	 */
	public String findChartDataProductionByYear(){
		String date = servletRequest.getParameter("date");
		String line = servletRequest.getParameter("line");
		//查询当前月有数据的最后一天
		MetroProduction mp = productionService.findMetroProductionByLastDay(date.substring(0,4), line);
		if(mp!=null){
			List<MetroProduction> mpList = productionService.findMetroProductionByYear(5, mp.getIndicatorDate(), line);
			MetroProduction lastyearMp = productionService.findMetroProductionByDate(DateUtil.getLastYearDay(mp.getIndicatorDate()), line);
			List<ProductionVo> mpVoList = new ArrayList<ProductionVo>();
			if(mpList!=null && mpList.size()>0){
				for(MetroProduction temp : mpList){
					mpVoList.add(new ProductionVo(temp));
				}
			}
			if(lastyearMp!=null){
				mpVoList.get(mpVoList.size()-1).setLastyearMetroProduction(new ProductionVo(lastyearMp));
			}else{
				mpVoList.get(mpVoList.size()-1).setLastyearMetroProduction(new ProductionVo());
			}
			
			String[] years;
			List<MetroProductionControl> mpcList = new ArrayList<MetroProductionControl>();
			if(mpVoList!=null && mpVoList.size()>0){
				years = new String[mpVoList.size()];
				for(int i=0; i<mpVoList.size(); i++){
					try {
						years[i] = mpVoList.get(i).getIndicatorDate().substring(0,4);
					} catch (Exception e) {
						years[i] = null;
					}
				}
				mpcList = metroProductionControlService.findMPCByYearsAndLine(years, line);
			}
			if(mpcList!=null && mpcList.size()>0 && mpVoList.size()==mpcList.size()){
				for(int i=0; i<mpcList.size(); i++){
					if(mpcList.get(i)!=null){
						mpVoList.get(i).setMetroProductionControlVO(new MetroProductionControlVO(mpcList.get(i)));
					}else{
						mpVoList.get(i).setMetroProductionControlVO(null);
					}
				}
			}
			String jsonData = VOUtils.getJsonDataFromCollection(mpVoList);
			createJSonData(jsonData);
		}else{
			List<ProductionVo> mpVoList = null;
			String jsonData = VOUtils.getJsonDataFromCollection(mpVoList);
			createJSonData(jsonData);
		}
		return AJAX;
	}
	
	/**
	 * 跳转到详细页面
	 * @author ycl
	 */
	@SuppressWarnings("unchecked")
	public String showProductionDetailPage(){
		
		List<Object[]> days = productionService.findFirstAndLastDate();
		if(days!=null && days.size()>0){
			servletRequest.setAttribute("firstDate", days.get(0)[0].toString());
			servletRequest.setAttribute("lastDate",days.get(0)[1].toString());
		}
		String lineNo = servletRequest.getParameter("lineNo");
		String endDate = servletRequest.getParameter("endDate");	
		String chartId = servletRequest.getParameter("chartId");
		String pageNo = servletRequest.getParameter("pageNo");
		
		String sameChart = servletRequest.getParameter("sameChart");	//显示的是否是同一张图表
		
		if(sameChart==null || "".equals(sameChart) || "false".equals(sameChart)){	//如果不是
			servletRequest.setAttribute("searchArea", "hide");
			servletRequest.setAttribute("dataArea", "hide");
		}else{		//同一个图表
System.out.println(servletRequest.getParameter("searchArea"));
System.out.println(servletRequest.getParameter("dataArea"));
			servletRequest.setAttribute("searchArea", servletRequest.getParameter("searchArea"));
			servletRequest.setAttribute("dataArea", servletRequest.getParameter("dataArea"));
		}
		
		
		String startDate = servletRequest.getParameter("startDate");
		Page page = null;
		int pageSize = 20;
		List<ProductionVo> productionVoList= null;
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
		String lastDateWithData = productionService.findRecentlyProduction(now,lineNo).getIndicatorDate();	
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
		page = productionService.findMetroProductionByPage(Integer.valueOf(pageNo), pageSize,lineNo);
		List<MetroProduction> boList = page.getResult();
		if(boList!=null && boList.size()>0){
			productionVoList = new ArrayList<ProductionVo>();
			for(int i=0; i<boList.size(); i++){
				ProductionVo vo = new ProductionVo(boList.get(i));
				MetroProduction mp = productionService.findMetroProductionByDate(vo.getIndicatorDate(), vo.getIndicatorLine());
				if(mp!=null){
					vo.setLastyearMetroProduction(new ProductionVo(mp));
				}
				productionVoList.add(vo);
			}
			page.setResult(productionVoList);
		}
		servletRequest.setAttribute("page", page);
		switch (Integer.valueOf(chartId)) {
		case 1:
			return "chart1";
		case 2:
			return "chart2";
		case 3:
			return "chart3";
		case 4:
			return "chart4";
		case 5:
			return "chart5";
		case 6:
			return "chart6";
		case 7:
			return "chart7";
		default : 
			return null;
		}
	}
	
	/**
	 * 查询时间段之间的数据production
	 * 
	 */
	public String findProductionBetweenDays(){
		String startDate = servletRequest.getParameter("startDate");
		String endDate = servletRequest.getParameter("endDate");
		String line = servletRequest.getParameter("line");
		List<MetroProduction> mpList = productionService.findProductionByDays(startDate, endDate, line);
		List<ProductionVo> voList = null;
		
		String[] dates ;
		if(mpList!=null && mpList.size()>0){
			dates = new String[mpList.size()];
			for(int i=0; i<mpList.size(); i++){
				dates[i] = mpList.get(i).getIndicatorDate();
			}
			//查询管控值
			List<MetroProductionControl> mpcList = metroProductionControlService.findMPCByDatesAndLine(dates, line);
			
			if(mpList!=null && mpcList.size() == mpList.size()){
				voList = new ArrayList<ProductionVo>();
				for(int i=0; i<mpList.size(); i++){
					//MetroProduction vo = new ProductionVo(mpList.get(i));
					ProductionVo vo = new ProductionVo(mpList.get(i));
					if(mpcList.get(i)!=null){
						vo.setMetroProductionControlVO(new MetroProductionControlVO(mpcList.get(i)));
					}else{
						vo.setMetroProductionControlVO(null);
					}
					
					voList.add(vo);
				}
				
			}
		}
		
		/*if (mpList!=null && mpList.size()>0) {
			//查询最后一天时间的管控值
			MetroProductionControl mpc = metroProductionControlService.findProductionControlByYearAndLine(endDate.substring(0,4), line);
			voList = new ArrayList<ProductionVo>();
			for(int i=0; i<mpList.size(); i++){
				voList.add(new ProductionVo(mpList.get(i)));
			}
			if(voList!=null && voList.size()>0 && mpc!=null){
				voList.get(voList.size()-1).setMetroProductionControlVO(new MetroProductionControlVO(mpc));
			}
		}*/
		
		String jsondata = VOUtils.getJsonDataFromCollection(voList);
		createJSonData(jsondata);
		return AJAX;
	}
	
	
	
	


}
