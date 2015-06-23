/**
 * 
 */
package com.wonders.stpt.infoSearch.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.ZTTP;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoSearchVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.infoSearch.service.InfoSearchService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/** 
 * @ClassName: InfoSearchAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午06:15:13 
 *  
 */
public class InfoSearchAction extends BaseAjaxAction {
	private InfoSearchService infoSearchService;
	private InfoSearchVo infoSearchVo = new InfoSearchVo();
	private PageResultSet<InfoDetailVo> pageResultSet;

	@Override
	public InfoSearchVo getModel() {
		// TODO Auto-generated method stub
		return infoSearchVo;
	}
	public InfoSearchVo getInfoSearchVo() {
		return infoSearchVo;
	}

	public void setInfoSearchVo(InfoSearchVo infoSearchVo) {
		this.infoSearchVo = infoSearchVo;
	}

	public InfoSearchService getInfoSearchService() {
		return infoSearchService;
	}

	public void setInfoSearchService(InfoSearchService infoSearchService) {
		this.infoSearchService = infoSearchService;
	}

	public PageResultSet<InfoDetailVo> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<InfoDetailVo> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	public String stfbNewsByPage(){
		this.pageResultSet = infoSearchService.queryByPage(infoSearchVo.getSj_id(), infoSearchVo.getPart_id(), infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
		//System.out.println("pageResultSet"+pageResultSet.getPageInfo());
		return SUCCESS;
	}
	
	public String stfbNewsLatestEvents(){
		boolean flag = false;
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 8;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		String part_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("part_id"));
		String isContent = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("isContent"));
		if("1".equals(isContent)){
			flag = true;
		}
		List<StfbVo> list = new ArrayList<StfbVo>();
		list = this.infoSearchService.findLatestStfbNews(sj_id, part_id,size,flag);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	public String stfbPicNewsLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		String pic_needed = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("pic_needed"));
		int size = 8;
		boolean flag = false;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		if("1".equals(pic_needed)){
			flag = true;
			if("".equals(tmpSize)){
				tmpSize = "5";
				size = Integer.parseInt(tmpSize);
			}
		}
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		List<StfbVo> list = new ArrayList<StfbVo>();
		list = this.infoSearchService.findLatestStfbPicNews(sj_id, size,flag);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	public String stfbHeadNewsLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 1;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		List<StfbVo> list = new ArrayList<StfbVo>();
		list = this.infoSearchService.findLatestHeadStfbHeadNews(sj_id);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}

	public String ZTTPNewsLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 1;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		String sWhere = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sWhere"));
		List<ZTTP> list = new ArrayList<ZTTP>();
		list = this.infoSearchService.findZTTPNews(sWhere, size);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
}
