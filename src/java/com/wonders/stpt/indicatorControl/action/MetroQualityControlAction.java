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

package com.wonders.stpt.indicatorControl.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wonders.stpt.indicatorControl.entity.vo.MetroQualityControlVO;
import com.wonders.stpt.indicatorControl.service.MetroQualityControlService;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
public class MetroQualityControlAction extends BaseAjaxAction {
	private MetroQualityControlVO metroQualityControlVO = new MetroQualityControlVO();
	private MetroQualityControl metroQualityControl = new MetroQualityControl();
	private MetroQualityControlService metroQualityControlService;
	private MetroExpressService expressService;
	private static TreeMap<Integer, String> lineMap = new TreeMap<Integer, String>();
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final static int pageSize = 15;
	
	public MetroQualityControl getMetroQualityControl() {
		return metroQualityControl;
	}
	public void setMetroQualityControl(MetroQualityControl metroQualityControl) {
		this.metroQualityControl = metroQualityControl;
	}
	public MetroExpressService getExpressService() {
		return expressService;
	}
	public void setExpressService(MetroExpressService expressService) {
		this.expressService = expressService;
	}

	public MetroQualityControlVO getMetroQualityControlVO() {
		return metroQualityControlVO;
	}

	public void setMetroQualityControlVO(MetroQualityControlVO metroQualityControlVO) {
		this.metroQualityControlVO = metroQualityControlVO;
	}

	public void setMetroQualityControlService(
			MetroQualityControlService metroQualityControlService) {
		this.metroQualityControlService = metroQualityControlService;
	}

	/**
	 * 跳转到运营生产指标管控查看页面
	 */
	@SuppressWarnings("unchecked")
	public String showQualityControlPage(){
		List<String> tempLineList = this.expressService.findMetroLineConfig();
		if(tempLineList!=null && tempLineList.size()!=0){
			for(int i=0;i<tempLineList.size();i++){
				String []temp = tempLineList.get(i).split(":");
				lineMap.put(Integer.valueOf(temp[0]),temp[1]);
			}
		}
		servletRequest.setAttribute("lineMap", lineMap);
		String pageNo = servletRequest.getParameter("pageNo");
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		String line = servletRequest.getParameter("line");
		String date = servletRequest.getParameter("date");
		servletRequest.setAttribute("line", line);
		servletRequest.setAttribute("date", date);
		Map<String, Object> filter = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(line)){
			filter.put("indicatorLine", line);
		}
		if(StringUtils.isNotEmpty(date)){
			filter.put("ext1", date);
		}
		Page page = metroQualityControlService.findMetroQualityControlByPage(filter,Integer.valueOf(pageNo),pageSize);
		List<MetroQualityControlVO> voList = new ArrayList<MetroQualityControlVO>();
		List<MetroQualityControl> boList = page.getResult();
		if(page!=null && boList!=null && boList.size()>0){
			for(MetroQualityControl mqc : boList){
				voList.add(new MetroQualityControlVO(mqc));
			}
		}
		page.setResult(voList);
		servletRequest.setAttribute("page", page);
		return "showQualityControlPage";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String showQualityControlAddPage(){
		servletRequest.setAttribute("lineMap", lineMap);
		return "showQualityControlAddPage";
	}
	
	/**
	 * 保存新增
	 */
	public String saveQualityControl(){
		 
		MetroQualityControl mqc = metroQualityControlService.findMQCByLineAndDate(metroQualityControlVO.getIndicatorLine(), metroQualityControlVO.getExt1(),metroQualityControlVO.getExt2());
		if(mqc==null){
			metroQualityControlVO.setRemoved("0");
			metroQualityControlVO.setOperateTime(sdf.format(new Date()));
			metroQualityControlService.saveQualityControl(new MetroQualityControl(metroQualityControlVO));
			return "saveQualityControl";
		}else{
			servletRequest.setAttribute("metroQualityControlVO", metroQualityControlVO);
			servletRequest.setAttribute("lineMap", lineMap);
			return "fail";
		}
	}
	
	/**
	 * 查询
	 */
	public String findQualityControlDetail(){
		String id = servletRequest.getParameter("id");
		MetroQualityControl mqc = metroQualityControlService.findMetroQualityControlById(id);
		MetroQualityControlVO mqcVO = new MetroQualityControlVO(mqc);
		servletRequest.setAttribute("mqc", mqcVO);
		return "findQualityControlDetail";
	}

	/**
	 * 跳转到编辑页面
	 */
	public String showQualityControlDetailPage(){
		String id = servletRequest.getParameter("id");
		MetroQualityControl mqc = metroQualityControlService.findMetroQualityControlById(id);
		MetroQualityControlVO vo = new MetroQualityControlVO(mqc);
		servletRequest.setAttribute("vo", vo);
		return "showQualityControlDetailPage";
	}
	
	/**
	 * 保存更新
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String updateQualityControl() throws IllegalAccessException, InvocationTargetException{
		String id = servletRequest.getParameter("id");
		MetroQualityControl mqc = metroQualityControlService.findMetroQualityControlById(id);
		String line = mqc.getIndicatorLine();
		
		MetroQualityControl tempMQC = new MetroQualityControl(metroQualityControlVO);
		
		mqc.setMetroOntimeDaily(tempMQC.getMetroOntimeDaily());
		mqc.setMetroOntimeMonth(tempMQC.getMetroOntimeMonth());
		mqc.setMetroOntimeYear(tempMQC.getMetroOntimeYear());
		mqc.setOntimeControl(tempMQC.getOntimeControl());
		mqc.setOntimeDescribe(tempMQC.getOntimeDescribe());
		
		mqc.setMetroOnworkDaily(tempMQC.getMetroOnworkDaily());
		mqc.setMetroOnworkMonth(tempMQC.getMetroOnworkMonth());
		mqc.setMetroOnworkYear(tempMQC.getMetroOnworkYear());
		mqc.setOnworkControl(tempMQC.getOnworkControl());
		mqc.setOnworkDescribe(tempMQC.getOnworkDescribe());
		
		mqc.setOperateTime(sdf.format(new Date()));
		
		metroQualityControlService.updateMetroControl(mqc);
		return "updateQualityControl";
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteQualityControl(){
		String id = servletRequest.getParameter("id");
		metroQualityControlService.deleteQualityControlById(id);
		return null;
	}
	
}
