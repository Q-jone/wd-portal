/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.metroIndicator.action;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.infoSearch.service.InfoSearchService;
import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wonders.stpt.metroExpress.entity.vo.MetroExpressVo;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;
import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;
import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;
import com.wonders.stpt.metroIndicator.entity.vo.MetroIndicator;
import com.wonders.stpt.metroIndicator.entity.vo.MetroIndicatorVo;
import com.wonders.stpt.metroIndicator.service.MetroProductionService;
import com.wonders.stpt.metroIndicator.service.MetroQualityService;
import com.wonders.stpt.metroIndicator.service.MetroScaleService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroIndicatorAction extends BaseAjaxAction {
	private MetroProductionService metroProductionService;

	private MetroQualityService metroQualityService;

	private MetroScaleService metroScaleService;

	private MetroExpressService metroExpressService;
	
	private InfoSearchService infoSearchService;
	
	
	public InfoSearchService getInfoSearchService() {
		return infoSearchService;
	}

	public void setInfoSearchService(InfoSearchService infoSearchService) {
		this.infoSearchService = infoSearchService;
	}

	public MetroExpressService getMetroExpressService() {
		return metroExpressService;
	}

	public void setMetroExpressService(MetroExpressService metroExpressService) {
		this.metroExpressService = metroExpressService;
	}

	public MetroScaleService getMetroScaleService() {
		return metroScaleService;
	}

	public void setMetroScaleService(MetroScaleService metroScaleService) {
		this.metroScaleService = metroScaleService;
	}
	
	public MetroQualityService getMetroQualityService() {
		return metroQualityService;
	}

	public void setMetroQualityService(MetroQualityService metroQualityService) {
		this.metroQualityService = metroQualityService;
	}
	
	public MetroProductionService getMetroProductionService() {
		return metroProductionService;
	}

	public void setMetroProductionService(
			MetroProductionService metroProductionService) {
		this.metroProductionService = metroProductionService;
	}
	
	/**
	 * @author ycl
	 * @updateDate 2012-4-10
	 * @description 修改运营速报中的字段“续报”相关操作
	 */
	public String metroExpressLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 4;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		String accidentLine = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("accidentLine"));
		String accidentEmergency = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("accidentEmergency"));
		//System.out.println(accidentEmergency+"--------------------------------");
		List<MetroExpress> list = this.metroExpressService.findLatestMetroExpressEvents(accidentLine,accidentEmergency,size);
		List<MetroExpressVo> voList = new ArrayList<MetroExpressVo>();
		if(list!=null&&list.size()>0){
			for(MetroExpress e: list){
				MetroExpressVo vo = new MetroExpressVo();
				BeanUtils.copyProperties(e, vo);
				if(vo.getAccidentDetail().length()>58) {
					vo.setDetailSimple(vo.getAccidentDetail().substring(0,58)+"...");
				}else{
					vo.setDetailSimple(vo.getAccidentDetail());
				}
				
				//修改“续报”字段显示
				Map<Integer,String> resultMap = new HashMap<Integer, String>();
				if(vo.getAccidentRemark()!=null && !vo.getAccidentRemark().equals("")){
					try {
						Document document = DocumentHelper.parseText(vo.getAccidentRemark());
						int id =-1;
						int maxId = -1;
						String value = null;
						String remarkAll = "";
						List<Element> idList = document.selectNodes("//item/id");
						List<Element> valueList = document.selectNodes("//item/value");
						
						if(idList.size()!=0 && valueList.size()!=0 && valueList.size()==idList.size()){
							maxId = -1;
							for(int i=0; i<idList.size(); i++){
								id = Integer.valueOf(idList.get(i).getText());
								if(id > maxId){
									maxId = id;
								}
								value = valueList.get(i).getText();
								resultMap.put(id, value);
							}
							remarkAll +="<ul>";
							for(int j=valueList.size()-1; j>=0; j--){
								value = valueList.get(j).getText();
								remarkAll += "<li style='width:95%;padding:0;background:none;'>"+value+"</li>";
							}
							remarkAll +="</ul>";
							vo.setAccidentRemarkAll(remarkAll);
						}
						
						if(maxId!=-1){
							vo.setAccidentRemark(resultMap.get(maxId));
						}
						
						if(vo.getAccidentRemark()!=null && !vo.getAccidentRemark().equals("") && vo.getAccidentRemark().length()>58){
							vo.setRemarkSimple(vo.getAccidentRemark().substring(0,58)+"...");
						}else{
							vo.setRemarkSimple(vo.getAccidentRemark());
						}
					} catch (DocumentException de) {
						System.out.println("-----------------该条续报数据为旧版本的时候添加，无法解析！--------------");
						vo.setAccidentRemark("");
					}
				}
				
				voList.add(vo);
			}
		}
		String json = VOUtils.getJsonDataFromCollection(voList);
		//this.servletRequest.setAttribute("lineMap", this.metroExpressService.findMetroLineConfigMap());
		//System.out.println(json+"--------------------------------");
		createJSonData(json);
		return AJAX;
	}
	
	public String metroIndicatorLatestEvents(){
		
		String indicatorLine = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("indicatorLine"));
		String indicatorDate = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("indicatorDate"));
		
		List<MetroProduction> list1 = this.metroProductionService.findLatestMetroProductionEvents(indicatorDate, indicatorLine);
		List<MetroQuality> list2 = this.metroQualityService.findLatestMetroQualityEvents(indicatorDate, indicatorLine);
		List<MetroScale> list3 = this.metroScaleService.findLatestMetroScaleEvents(indicatorDate, indicatorLine);
			
		MetroIndicator m = new MetroIndicator();
		
		if(list1!=null&&list1.size()>0){
			BeanUtils.copyProperties(list1.get(0), m);
		}
		if(list2!=null&&list2.size()>0){
			BeanUtils.copyProperties(list2.get(0), m);
		}
		if(list3!=null&&list3.size()>0){
			BeanUtils.copyProperties(list3.get(0), m);
		}
		
		List<MetroProduction> list4 = this.metroProductionService.findLastMetroProductionEvents(m.getIndicatorDate(), indicatorLine);
		List<MetroQuality> list5 = this.metroQualityService.findLastMetroQualityEvents(m.getIndicatorDate(), indicatorLine);
		List<MetroScale> list6 = this.metroScaleService.findLastMetroScaleEvents(m.getIndicatorDate(), indicatorLine);
		
		if(list4!=null&&list4.size()>0){
			m.setPassengerCapacityDailyLast(list4.get(0).getPassengerCapacityDaily());
			m.setMetroDistanceDailyLast(list4.get(0).getMetroDistanceDaily());
			m.setTicketIncomeDailyLast(list4.get(0).getTicketIncomeDaily());
		}
		if(list5!=null&&list5.size()>0){
			m.setMetroOntimeDailyLast(list5.get(0).getMetroOntimeDaily());
			m.setMetroOnworkDailyLast(list5.get(0).getMetroOnworkDaily());
		}
		if(list6!=null&&list6.size()>0){
			
		}
		
		MetroIndicatorVo vo = new MetroIndicatorVo(m);
		

		
		
		String json = VOUtils.getJsonData(vo);
		this.servletRequest.setAttribute("lineMap", this.metroExpressService.findMetroLineConfigMap());
		//System.out.println(json+"--------------------------------");
		createJSonData(json);
		return AJAX;
	}
	
	public static void main(String[] args){
		JSONObject j = JSONObject.fromObject(null);
		if(j==null){
			System.out.println(j.toString());
		}else{
			System.out.println("21321");
		}
	}
	

}
