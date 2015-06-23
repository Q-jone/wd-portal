/**
 * 
 */
package com.wonders.stpt.infoSearch.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.SubjectVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoSearchVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.infoSearch.service.DJInfoSearchService;
import com.wonders.stpt.infoSearch.service.InfoSearchService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/** 
 * @ClassName: DJInfoSearchAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ycl
 * @date 2012-3-11 下午06:15:13 
 *  
 */
public class DJInfoSearchAction extends BaseAjaxAction {
	private DJInfoSearchService djInfoSearchService;
	private InfoSearchService infoSearchService;
	private InfoSearchVo infoSearchVo = new InfoSearchVo();
	private PageResultSet<InfoDetailVo> pageResultSet;

	
	@Override
	public InfoSearchVo getModel() {
		return infoSearchVo;
	}
	public InfoSearchVo getInfoSearchVo() {
		return infoSearchVo;
	}

	public InfoSearchService getInfoSearchService() {
		return infoSearchService;
	}
	public void setInfoSearchService(InfoSearchService infoSearchService) {
		this.infoSearchService = infoSearchService;
	}
	public void setInfoSearchVo(InfoSearchVo infoSearchVo) {
		this.infoSearchVo = infoSearchVo;
	}

	public DJInfoSearchService getDjInfoSearchService() {
		return djInfoSearchService;
	}
	public void setDjInfoSearchService(DJInfoSearchService djInfoSearchService) {
		this.djInfoSearchService = djInfoSearchService;
	}
	public PageResultSet<InfoDetailVo> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<InfoDetailVo> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	/*
	public String stfbNewsByPage(){
		this.pageResultSet = infoSearchService.queryByPage(infoSearchVo.getSj_id(), infoSearchVo.getPart_id(), infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
		//System.out.println("pageResultSet"+pageResultSet.getPageInfo());
		return SUCCESS;
	}*/
	
	/**
	 * 党纪工团首页显示,法规服务
	 * @return
	 */
	public String djNewsLatestEvents(){
		//每页显示的数量
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 8;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		//栏目id
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		
		String part_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("part_id"));
		String parent_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("parent_id"));
		
		
		List<StfbVo> list = new ArrayList<StfbVo>();
		list = this.djInfoSearchService.findLatestStfbNews(sj_id,part_id,size,parent_id);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	/**
	 * 显示党纪工团详细页面的子栏目;
	 */
	public String findByPage(){
		String parent_id = ServletActionContext.getRequest().getParameter("id");
		List<SubjectVo> subjectList = djInfoSearchService.findNewsByType(parent_id);		//得到子栏目的list
		servletRequest.setAttribute("subjectList", subjectList);
		servletRequest.setAttribute("parent_id", parent_id);
		
		String sj_id = ServletActionContext.getRequest().getParameter("sj_id");
		
		//查询数据
		
		/*if(sj_id!=null && !"".equals(sj_id.trim())){
			infoSearchVo.setSj_id(sj_id);
		}*/
		
		if(sj_id!=null && !"".equals(sj_id.trim())){
			infoSearchVo.setSj_id(sj_id);
		}else{
			if(subjectList!=null && subjectList.size()>0){
				infoSearchVo.setSj_id(subjectList.get(0).getId()+"");
			}
		}
		servletRequest.setAttribute("sj_id", infoSearchVo.getSj_id());
		
		String part_id = servletRequest.getParameter("part_id");
		if(part_id!=null && !"".equals(part_id.trim())){
			this.pageResultSet = infoSearchService.queryByPage(infoSearchVo.getSj_id(), part_id, infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
		}else{
			this.pageResultSet = djInfoSearchService.findDataByPage(infoSearchVo.getSj_id(), infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
		}
		
		
		return "findByPage";
	}
	
	
	/**
	 * ajax查询数据
	 */
	public String findDataByPage(){
		
		this.pageResultSet = djInfoSearchService.findDataByPage(infoSearchVo.getSj_id(), infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
		String jsonData = VOUtils.getJsonData(pageResultSet);
		createJSonData(jsonData);
		return AJAX;
	}
	
	
	
	/*
	public String stfbPicNewsLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		String pic_needed = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("pic_needed"));
		boolean flag = false;
		if("1".equals(pic_needed)){
			flag = true;
			tmpSize = "5";
		}
		int size = 8;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
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
	}*/

}
