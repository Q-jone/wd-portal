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

import java.lang.reflect.Field;
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

import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
import com.wonders.stpt.indicatorControl.entity.vo.MetroProductionControlVO;
import com.wonders.stpt.indicatorControl.service.MetroProductionControlService;
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

public class MetroProductionControlAction extends BaseAjaxAction {
	private static final long serialVersionUID = 1605525496209991309L;
	private MetroProductionControlVO metroProductionControlVO = new MetroProductionControlVO();
	private MetroProductionControl metroProductionControl = new MetroProductionControl();
	private MetroProductionControlService metroProductionControlService;
	private MetroExpressService expressService;
	private static TreeMap<Integer, String> lineMap = new TreeMap<Integer, String>();
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final int pageSize = 15; 
	
	public MetroProductionControl getMetroProductionControl() {
		return metroProductionControl;
	}

	public void setMetroProductionControl(
			MetroProductionControl metroProductionControl) {
		this.metroProductionControl = metroProductionControl;
	}
	
	public MetroExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(MetroExpressService expressService) {
		this.expressService = expressService;
	}
	
	public MetroProductionControlVO getMetroProductionControlVO() {
		return metroProductionControlVO;
	}

	public void setMetroProductionControlVO(
			MetroProductionControlVO metroProductionControlVO) {
		this.metroProductionControlVO = metroProductionControlVO;
	}

	public void setMetroProductionControlService(
			MetroProductionControlService metroProductionControlService) {
		this.metroProductionControlService = metroProductionControlService;
	}
	
	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}
	
	
	/**
	 * 跳转到list页面
	 */
	@SuppressWarnings("unchecked")
	public String showProductionControlPage(){
		String line = servletRequest.getParameter("line");
		String date = servletRequest.getParameter("date");
		String pageNo = servletRequest.getParameter("pageNo");
		Map<String, Object> filter = new HashMap<String, Object>();
		
		List<String> tempLineList = this.expressService.findMetroLineConfig();
		if(tempLineList!=null && tempLineList.size()!=0){
			for(int i=0;i<tempLineList.size();i++){
				String []temp = tempLineList.get(i).split(":");
				lineMap.put(Integer.valueOf(temp[0]),temp[1]);
			}
		}
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		if(StringUtils.isNotEmpty(line)){
			filter.put("indicatorLine", line);
		}
		if(StringUtils.isNotEmpty(date)){
			filter.put("ext1", date);
		}
		Page page = metroProductionControlService.findMetroProductionControlByPage(filter,Integer.valueOf(pageNo), pageSize);
		List<MetroProductionControlVO> voList = new ArrayList<MetroProductionControlVO>();
		List<MetroProductionControl> mpcList = page.getResult(); 
		if(page!=null && mpcList!=null && mpcList.size()!=0){
			for(int i=0; i<mpcList.size(); i++){
				voList.add(new MetroProductionControlVO(mpcList.get(i)));
			}
		}
		page.setResult(voList);
		servletRequest.setAttribute("lineMap", lineMap);
		servletRequest.setAttribute("line", line);
		servletRequest.setAttribute("date", date);
		servletRequest.setAttribute("page", page);
		return "showProductionControlPage";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String showProductionControlAddPage(){
		servletRequest.setAttribute("lineMap", lineMap);
		return "showProductionControlAddPage";
	}
	
	/**
	 * 保存新增
	 */
	public String saveProductionControl(){
		MetroProductionControl m = metroProductionControlService.findProductionControlByYearAndLine(metroProductionControl.getExt1(),metroProductionControl.getExt2(), metroProductionControl.getIndicatorLine());
		MetroProductionControl mpc = metroProductionControl;
		if(m==null){
			mpc.setRemoved("1");
			mpc.setOperateTime(sdf.format(new Date()));
			metroProductionControlService.saveProductionControl(mpc);
			return "saveProductionControl";
		}else{
			MetroProductionControlVO vo = new MetroProductionControlVO(mpc);
			servletRequest.setAttribute("vo", vo.getVoValueToBoValue(vo));
			servletRequest.setAttribute("lineMap", lineMap);
			return "fail";
		}
	}
	
	/**
	 * 跳转到详细页面
	 */
	public String showProductionControlDetailPage(){
		String id = servletRequest.getParameter("id");
		MetroProductionControl mpc = metroProductionControlService.findMetroProductionControlById(id);
		MetroProductionControlVO vo =null;
		if(mpc!=null){
			vo = new MetroProductionControlVO(mpc);
		}
		servletRequest.setAttribute("vo", vo.getVoValueToBoValue(vo));
		return "showProductionControlDetailPage";
	}

	/**
	 * 跳转到编辑页面
	 */
	public String showProductionControlEditPage(){
		String id = servletRequest.getParameter("id");
		MetroProductionControl mpc = metroProductionControlService.findMetroProductionControlById(id);
		MetroProductionControlVO vo =null;
		if(mpc!=null){
			vo = new MetroProductionControlVO(mpc);
		}
		servletRequest.setAttribute("vo", vo.getVoValueToBoValue(vo));
		servletRequest.setAttribute("mpc", mpc);
		return "showProductionControlEditPage";
	}
	
	/**
	 * 保存更新
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String updateProductionEditPage() throws IllegalAccessException, InvocationTargetException{
		String id = servletRequest.getParameter("id");
		MetroProductionControl mpc = metroProductionControlService.findMetroProductionControlById(id);
	
		if(mpc!=null){
			
			//开行列次
			mpc.setMetroColumnDaily(metroProductionControl.getMetroColumnDaily());
			mpc.setMetroColumnMonth(metroProductionControl.getMetroColumnMonth());
			mpc.setMetroColumnYear(metroProductionControl.getMetroColumnYear());
			mpc.setMetroColumnControl(metroProductionControl.getMetroColumnControl());
			mpc.setMetroColumnDescribe(metroProductionControl.getMetroColumnDescribe());
			
			//开行距离
			mpc.setMetroDistanceDaily(metroProductionControl.getMetroDistanceDaily());
			mpc.setMetroDistanceMonth(metroProductionControl.getMetroDistanceMonth());
			mpc.setMetroDistanceYear(metroProductionControl.getMetroDistanceYear());
			mpc.setMetroDistanceControl(metroProductionControl.getMetroDistanceControl());
			mpc.setMetroDistanceDescribe(metroProductionControl.getMetroDistanceDescribe());
			
			//客流量
			mpc.setPassengerCapacityDaily(metroProductionControl.getPassengerCapacityDaily());
			mpc.setPassengerCapacityMonth(metroProductionControl.getPassengerCapacityMonth());
			mpc.setPassengerCapacityYear(metroProductionControl.getPassengerCapacityYear());
			mpc.setPassengerCapacityControl(metroProductionControl.getPassengerCapacityControl());
			mpc.setPassengerCapacityDescribe(metroProductionControl.getPassengerCapacityDescribe());
			
			//换乘人次 
			mpc.setPassengerTransferDaily(metroProductionControl.getPassengerTransferDaily());
			mpc.setPassengerTransferMonth(metroProductionControl.getPassengerTransferMonth());
			mpc.setPassengerTransferYear(metroProductionControl.getPassengerTransferYear());
			mpc.setPassengerTransferControl(metroProductionControl.getPassengerTransferControl());
			mpc.setPassengerTransferDescribe(metroProductionControl.getPassengerTransferDescribe());
			
			//客运收入
			mpc.setTicketIncomeDaily(metroProductionControl.getTicketIncomeDaily());
			mpc.setTicketIncomeMonth(metroProductionControl.getTicketIncomeMonth());
			mpc.setTicketIncomeYear(metroProductionControl.getTicketIncomeYear());
			mpc.setTicketIncomeControl(metroProductionControl.getTicketIncomeControl());
			mpc.setTicketIncomeDescribe(metroProductionControl.getTicketIncomeDescribe());
			
			mpc.setOperateTime(sdf.format(new Date()));
			
		}
		
		metroProductionControlService.updateMetroProductionControl(mpc);
		
		return "updateProductionEditPage";
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteProductionControl(){
		String id = servletRequest.getParameter("id");
		metroProductionControlService.deleteProductionControl(id);
System.out.println(1111111);		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
