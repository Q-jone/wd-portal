/**
 * 
 */
package com.wonders.stpt.infoSearch.action;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoSearchVo;
import com.wonders.stpt.infoSearch.entity.vo.MoreStfbVo;
import com.wonders.stpt.infoSearch.service.MoreInfoSearchService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wonders.stpt.core.stfb.ColumnsVo;

/** 
 * @ClassName: InfoSearchAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午06:15:13 
 *  
 */
public class MoreInfoSearchAction extends BaseAjaxAction {
	private MoreInfoSearchService moreInfoSearchService;
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

	public MoreInfoSearchService getMoreInfoSearchService() {
		return moreInfoSearchService;
	}

	public void setMoreInfoSearchService(MoreInfoSearchService moreInfoSearchService) {
		this.moreInfoSearchService = moreInfoSearchService;
	}

	public PageResultSet<InfoDetailVo> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<InfoDetailVo> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
//	public String stfbNewsByPage(){
//		this.pageResultSet = infoSearchService.queryByPage(infoSearchVo.getSj_id(), infoSearchVo.getPart_id(), infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage());
//		//System.out.println("pageResultSet"+pageResultSet.getPageInfo());
//		return SUCCESS;
//	}
	
	public String stfbMoreNewsLatestEvents(){
		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
		int size = 8;
		if(!"".equals(tmpSize)){
			size = Integer.parseInt(tmpSize);
		}
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		String part_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("part_id"));
		List<MoreStfbVo> list = new ArrayList<MoreStfbVo>();
		list = this.moreInfoSearchService.findLatestStfbMoreNews(sj_id, part_id,size);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	public String findNameList(){
		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
		List<ColumnsVo> list = new ArrayList<ColumnsVo>();
		list = moreInfoSearchService.findNameList(sj_id);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
//	public String stfbPicNewsLatestEvents(){
//		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
//		String pic_needed = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("pic_needed"));
//		boolean flag = false;
//		if("1".equals(pic_needed)){
//			flag = true;
//			tmpSize = "5";
//		}
//		int size = 8;
//		if(!"".equals(tmpSize)){
//			size = Integer.parseInt(tmpSize);
//		}
//		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
//		List<StfbVo> list = new ArrayList<StfbVo>();
//		list = this.infoSearchService.findLatestStfbPicNews(sj_id, size,flag);
//		String json = VOUtils.getJsonDataFromCollection(list);
//		createJSonData(json);
//		return AJAX;
//	}
//	
//	public String stfbHeadNewsLatestEvents(){
//		String tmpSize = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("size"));
//		int size = 1;
//		if(!"".equals(tmpSize)){
//			size = Integer.parseInt(tmpSize);
//		}
//		String sj_id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("sj_id"));
//		List<StfbVo> list = new ArrayList<StfbVo>();
//		list = this.infoSearchService.findLatestHeadStfbHeadNews(sj_id);
//		String json = VOUtils.getJsonDataFromCollection(list);
//		createJSonData(json);
//		return AJAX;
//	}

}
