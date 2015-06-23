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

import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;
import com.wonders.stpt.metroIndicator.service.MetroScaleService;
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
public class ScaleIndicatorAction extends BaseAjaxAction{

	private MetroScaleService scaleService;
	private MetroExpressService expressService;
	
	private static TreeMap<Integer, String> lineMap = new TreeMap<Integer, String>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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

	

	/**
	 * 跳转到列表显示页面
	 * @author ycl
	 */
	public String showScaleListPage(){
		List<String> tempLineList = this.expressService.findMetroLineConfig();
		lineMap = new TreeMap<Integer, String>();
		if(tempLineList!=null && tempLineList.size()!=0){
			for(int i=0;i<tempLineList.size();i++){
				String []temp = tempLineList.get(i).split(":");
				lineMap.put(Integer.valueOf(temp[0]),temp[1]);
			}
		}
		servletRequest.setAttribute("lineMap", lineMap);
		List<Object[]> days = scaleService.findFirstAndLastDate();
		if(days!=null && days.size()>0){
			servletRequest.setAttribute("firstDate", days.get(0)[0].toString());
			servletRequest.setAttribute("lastDate",days.get(0)[1].toString());
		}
		return "showScaleListPage";
	}
	
	
	/**
	 * 查询数据MetroScale日视图
	 * @author ycl
	 */
	public String findChartDataScale(){
		
		String line = super.servletRequest.getParameter("lineNo");
		String date = super.servletRequest.getParameter("date");
		if(line==null || "".equals(line)){
			line = "0";
		}
		if(date==null || "".equals(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		
		MetroScale scale = scaleService.findScaleWithData(date, line);
		
		if(scale!=null && scale.getLineDistance()!=null && !"".equals(scale.getLineDistance())){
			scale.setLineDistance(scale.getLineDistance()*0.001);
		}
		
		
		if(scale!=null){
			String jsonData = VOUtils.getJsonData(scale);
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
	public String showScaleDetailPage(){
		
		List<Object[]> days = scaleService.findFirstAndLastDate();
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
		List<MetroScale> scaleList = null;
		
		if(pageNo==null ||"".equals(pageNo)){
			pageNo = "1";
		}
		if(StringUtils.isNotEmpty(endDate)){
			if(StringUtils.isEmpty(startDate)){
				startDate = DateUtil.getDayBeforeByParameter(endDate, 14);
			}
		}
		servletRequest.setAttribute("lineNo", lineNo);
		servletRequest.setAttribute("startDate", startDate);
		servletRequest.setAttribute("endDate", endDate);
		servletRequest.setAttribute("chartId", chartId);
		String now = sdf.format(new Date());
		String lastDateWithData = scaleService.findRecentlyScale(now,lineNo).getIndicatorDate();	
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
		
		page = scaleService.findMetroScaleByPage(Integer.valueOf(pageNo), pageSize,lineNo);
		List<MetroScale> mqlist = page.getResult();
		if(mqlist!=null && mqlist.size()>0){
			scaleList = new ArrayList<MetroScale>();
			for(int i=0; i<mqlist.size(); i++){
				MetroScale vo = mqlist.get(i);
				scaleList.add(vo);
			}
			page.setResult(scaleList);
		} 
		servletRequest.setAttribute("page", page);
		
		return "chart12";
	}
	





}
