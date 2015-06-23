/**
 * 
 */
package com.wonders.stpt.asset.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.asset.model.vo.AssetVo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: AssetInfoAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-31 上午11:30:19 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/assetInfo")
@Controller("assetInfoAction")
@Scope("prototype")
public class AssetInfoAction extends AbstractParamAction{

	private static final long serialVersionUID = 1L;
	//private ActionWriter aw = new ActionWriter(this.servletResponse);
	//private Gson gson = new Gson();
	private AssetInfoService assetInfoService;
	public AssetInfoService getAssetInfoService() {
		return assetInfoService;
	}
	@Autowired(required=false)
	public void setAssetInfoService(@Qualifier(value="assetInfoService") AssetInfoService assetInfoService) {
		this.assetInfoService = assetInfoService;
	}
	
	
	@Action(value="getAssetInfo")
	public String getAssetInfo(){
		String line = StringUtil.getNotNullValueString(this.servletRequest.getParameter("line"));
		String station = StringUtil.getNotNullValueString(this.servletRequest.getParameter("station"));
		String counttype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("counttype"));
		String counttypeCn = StringUtil.getNotNullValueString(this.servletRequest.getParameter("counttypeCn"));
		String sumtype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("sumtype"));
		String sumtypeCn = StringUtil.getNotNullValueString(this.servletRequest.getParameter("sumtypeCn"));
		String flag = StringUtil.getNotNullValueString(this.servletRequest.getParameter("flag"));
		ActionWriter aw = new ActionWriter(this.servletResponse);
		AssetVo vo = this.assetInfoService.getAssetInfo(line, station,counttype, counttypeCn, sumtype, sumtypeCn,  flag);
		aw.writeJson(vo);
		return null;
	}
	
	@Action(value="getInventoryInfo")
	public String getInventoryInfo(){
		String line = StringUtil.getNotNullValueString(this.servletRequest.getParameter("line"));
		String owner = StringUtil.getNotNullValueString(this.servletRequest.getParameter("owner"));
		String specialtype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("specialtype"));
		String specialtypeCn = StringUtil.getNotNullValueString(this.servletRequest.getParameter("specialtypeCn"));
		String sumtype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("sumtype"));
		String sumtypeCn = StringUtil.getNotNullValueString(this.servletRequest.getParameter("sumtypeCn"));
		String flag = StringUtil.getNotNullValueString(this.servletRequest.getParameter("flag"));
		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeJson(this.assetInfoService.getInventoryInfo(line,owner, specialtype,specialtypeCn, sumtype, sumtypeCn,flag));
		return null;
	}
	

	
	
}