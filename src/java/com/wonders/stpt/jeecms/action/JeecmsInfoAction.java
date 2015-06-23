package com.wonders.stpt.jeecms.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.SubjectVo;
import com.wonders.stpt.infoSearch.service.DJInfoSearchService;
import com.wonders.stpt.jeecms.ShowJeecmsInfo;
import com.wonders.stpt.jeecms.entity.vo.InfoChannelVo;
import com.wonders.stpt.core.stfb.StfbPage;
import com.wonders.stpt.jeecms.entity.vo.InfoDetailVo;
import com.wonders.stpt.jeecms.entity.vo.InfoSearchVo;
import com.wonders.stpt.jeecms.service.JeecmsService;
import com.wonders.stpt.jeecms.service.impl.JeecmsServiceImpl;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

public class JeecmsInfoAction extends BaseAjaxAction{

	private JeecmsService jeecmsService;
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

	public PageResultSet<InfoDetailVo> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<InfoDetailVo> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	
	public JeecmsService getJeecmsService() {
		return jeecmsService;
	}
	public void setJeecmsService(JeecmsService jeecmsService) {
		this.jeecmsService = jeecmsService;
	}
	
	public String getJeecmsInfo(){
		String method= servletRequest.getParameter("method");
		String channelId = servletRequest.getParameter("channelId");
		String hasTitleImg = servletRequest.getParameter("hasTitleImg");
		String typeId = servletRequest.getParameter("typeId");
		String rownum = servletRequest.getParameter("rownum");
		
		
		JSONObject jsonParameter = new JSONObject();
		jsonParameter.accumulate("channelId", channelId);
		jsonParameter.accumulate("hasTitleImg", hasTitleImg);
		jsonParameter.accumulate("typeId", typeId);
		jsonParameter.accumulate("rownum", rownum);
		
		
		ShowJeecmsInfo dwgk=new ShowJeecmsInfo(servletRequest,servletResponse);
		JSONArray jsonResultList = dwgk.getInfoList(method,jsonParameter.toString());
		
		if(jsonResultList!=null && jsonResultList.size()>0){
			createJSonData(jsonResultList.toString());
			return AJAX;
		}else{
			return null;
			//createJSonData("[{'title':'暂无相关信息','contentId':'0'}]");
		}
		
	}
	
public String stfbNewsByPage(){
		 String channelId = servletRequest.getParameter("channelId");
		 String latestDays = servletRequest.getParameter("latestDays");
		 this.servletRequest.setAttribute("latestDays", latestDays);
		this.pageResultSet = jeecmsService.queryByPage(servletRequest,servletResponse,channelId,infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage(),latestDays);
		//System.out.println("pageResultSet"+pageResultSet.getPageInfo());
		return SUCCESS;
	}

/**
 * 显示党纪工团详细页面的子栏目;
 */
public String findByPage(){
	//String parentId = ServletActionContext.getRequest().getParameter("parentId");
	 String parentId = servletRequest.getParameter("parentId");
	 String latestDays = servletRequest.getParameter("latestDays");
	 this.servletRequest.setAttribute("latestDays", latestDays);
	List<InfoChannelVo> channelList =jeecmsService.findNewsByType(servletRequest,servletResponse,parentId,latestDays);	//得到子栏目的list
	servletRequest.setAttribute("channelList", channelList);
	servletRequest.setAttribute("parentId", parentId);
	
	String channelId = servletRequest.getParameter("channelId");
	
	//查询数据
	
	/*if(sj_id!=null && !"".equals(sj_id.trim())){
		infoSearchVo.setSj_id(sj_id);
	}*/
	
	if(channelId!=null && !"".equals(channelId.trim())){
		infoSearchVo.setChannelId(channelId);
	}else{
		if(channelList!=null && channelList.size()>0){
			infoSearchVo.setChannelId(channelList.get(0).getChannelId()+"");
		}
	}
	servletRequest.setAttribute("channelId", infoSearchVo.getChannelId());
	
    this.pageResultSet = jeecmsService.queryByPage(servletRequest,servletResponse,infoSearchVo.getChannelId(),infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage(),latestDays);
	
	return "success";
}


public String findDateByPage(){
	 String channelId = servletRequest.getParameter("channelId");
	 String latestDays = servletRequest.getParameter("latestDays");
	 this.pageResultSet=jeecmsService.queryByPage(servletRequest,servletResponse,channelId,infoSearchVo.getSearchWord(), infoSearchVo.getPageSize(), infoSearchVo.getPage(),latestDays);
	 String jsonData = VOUtils.getJsonData(pageResultSet);
	createJSonData(jsonData);
	return AJAX;
}

public String findIfWsLeader(){
	String loginName = (String)this.servletRequest.getSession().getAttribute("loginName");
	List<Object> list = jeecmsService.findWsLeaders();
	boolean flag = false;
	if(list!=null&&list.size()>0){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(loginName)){
				flag = true;
				break;
			}
		}
	}
	this.createJSonData("{\"ifLeader\":"+flag+"}");
	return AJAX;
}

public String findShortContent() {
	String content="";
	String contentId = servletRequest.getParameter("contentId");
	ShowJeecmsInfo dwgk=new ShowJeecmsInfo(this.servletRequest,this.servletResponse);
	dwgk.getInfoList("JEECMS.ADD_CONTENT_VIEWER","{'contentId':'"+contentId+"'}");
	JSONArray contentList =dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'contentId':'"+contentId+"'}");
	if(contentList!=null && contentList.size()>0){
		JSONObject contentObj=contentList.getJSONObject(0);
		content=contentObj.getString("txt");
	  	if(content!=null && !"".equals(content)){
	  		content = StfbPage.splitAndFilterString(content,150);
	  	}
	}
		
	this.createJSonData("{\"ShortContent\":\"" + content + "\"}");
	return AJAX;
}

}
