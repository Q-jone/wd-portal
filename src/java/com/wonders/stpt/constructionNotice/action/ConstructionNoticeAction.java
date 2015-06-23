package com.wonders.stpt.constructionNotice.action;

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

import java.util.ArrayList;
import java.util.List;


import com.wonders.stpt.constructionNotice.service.ConstructionNoticeService;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;


/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class ConstructionNoticeAction extends BaseAjaxAction {
	private ConstructionNoticeService constructionNoticeService;


	public ConstructionNoticeService getConstructionNoticeService() {
		return constructionNoticeService;
	}


	public void setConstructionNoticeService(
			ConstructionNoticeService constructionNoticeService) {
		this.constructionNoticeService = constructionNoticeService;
	}


	/**
	 * @author ycl
	 * @description 查询各线路总数据条数
	 */
	public String showLineInfo(){
		String startDate = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("startDate"));
		String endDate = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("endDate"));
		String resultString ="";
		List<MetroLine> lineList = constructionNoticeService.findAllMetroLine();
		List<String> result = new ArrayList<String>();
		if(lineList!=null && lineList.size()!=0){
			for(int i=0; i<lineList.size(); i++){
				resultString = lineList.get(i).getLineName() + "("
					+ constructionNoticeService.findCountByLineNo(lineList.get(i).getLineId(),startDate,endDate) + ")";
				result.add(resultString.replace("全网", "跨线"));
			}
		}
		String json = VOUtils.getJsonDataFromCollection(result);
		createJSonData(json); 
		return AJAX;
		
	}
	
	public String showLineInfoCross(){
		String callback = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("jsoncallback"));
		String startDate = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("startDate"));
		String endDate = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("endDate"));
		System.out.println((String)super.getServletRequest().getSession().getAttribute("loginName"));
		String resultString ="";
		List<MetroLine> lineList = constructionNoticeService.findAllMetroLine();
		List<String> result = new ArrayList<String>();
		if(lineList!=null && lineList.size()!=0){
			for(int i=0; i<lineList.size(); i++){
				resultString = lineList.get(i).getLineName() + "("
					+ constructionNoticeService.findCountByLineNo(lineList.get(i).getLineId(),startDate,endDate) + ")";
				result.add(resultString.replace("全网", "跨线"));
			}
		}
		String json = VOUtils.getJsonDataFromCollection(result);
		json = callback+"("+json+")";
		createJSonData(json); 
		return AJAX;
		
	}
	
	
}
