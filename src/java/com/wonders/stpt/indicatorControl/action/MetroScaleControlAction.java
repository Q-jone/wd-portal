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

import java.util.List;

import com.wonders.stpt.indicatorControl.entity.bo.MetroScaleControl;
import com.wonders.stpt.indicatorControl.service.MetroScaleControlService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroScaleControlAction extends BaseAjaxAction {
	
	private static final long serialVersionUID = 1L;
	//private MetroScaleControl metroScaleControl = new MetroScaleControl();
	//private MetroScaleControlVO metroScaleControlVO = new MetroScaleControlVO();
	private MetroScaleControlService metroScaleControlService;
	public MetroScaleControlService getMetroScaleControlService() {
		return metroScaleControlService;
	}
	public void setMetroScaleControlService(
			MetroScaleControlService metroScaleControlService) {
		this.metroScaleControlService = metroScaleControlService;
	}

	/**
	 * 跳转到运营生产指标管控查看页面
	 */
	public String showScaleControlPage(){
		List<MetroScaleControl> mcList = this.metroScaleControlService.findAllScaleControl();
		servletRequest.setAttribute("mcList", mcList);
		return "showScaleControlPage";
	}
}
