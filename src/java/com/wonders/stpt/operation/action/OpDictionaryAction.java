package com.wonders.stpt.operation.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.operation.service.OpDictionaryService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/**
 * 运营发文 
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/opDic")
@Controller("opDictionaryAction")
@Scope("prototype")
public class OpDictionaryAction extends AbstractParamAction implements ModelDriven<OpDictionary> {

	
	private OpDictionary opDictionary = new OpDictionary();
	
	private PageResultSet<ExamMain> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);

	@Override
	public OpDictionary getModel() {
		return opDictionary;
	}

	public void setOpDictionary(OpDictionary opDictionary) {
		this.opDictionary = opDictionary;
	}

	public PageResultSet<ExamMain> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<ExamMain> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	/**
	 * 获取二级文件类型
	 * @return
	 */
	@Action(value="getChildNodes")
	public String getChildNodes(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		List<OpDictionary> fileTypes = service.findByParentId(id);
		
		JSONArray jso = JSONArray.fromObject(fileTypes);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="getNodesByIds")
	public String getNodesByIds(){
		String ids = StringUtil.getNotNullValueString(this.request.getParameter("ids"));
		List<OpDictionary> nodes = service.findByIds(ids);
		
		JSONArray jso = JSONArray.fromObject(nodes);
		aw.writeAjax(jso.toString());			
		return null;
	}
	
	@Action(value="getNodesByType")
	public String getNodesByType(){
		String typecode = StringUtil.getNotNullValueString(this.request.getParameter("typecode"));
		List<OpDictionary> nodes = service.findByType(typecode);
		
		JSONArray jso = JSONArray.fromObject(nodes);
		aw.writeAjax(jso.toString());			
		return null;
	}
	
	private OpDictionaryService service;
	public OpDictionaryService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("opDictionaryService")OpDictionaryService service) {
		this.service = service;
	}
	

}
