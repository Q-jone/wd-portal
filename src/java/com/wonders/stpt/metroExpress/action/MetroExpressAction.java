/**
 * 
 */
package com.wonders.stpt.metroExpress.action;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import net.sf.json.JSONObject;

import com.wonders.stpt.core.publicFun.util.PublicFunction;
import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/** 
 * @ClassName: MetroExpressAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午06:55:22 
 *  
 */
public class MetroExpressAction extends BaseAjaxAction {
	private MetroExpressService metroExpressService;
	private MetroExpress metroExpress = new MetroExpress();
	
	public MetroExpress getMetroExpress() {
		return metroExpress;
	}

	public void setMetroExpress(MetroExpress metroExpress) {
		this.metroExpress = metroExpress;
	}

	@Override
	public MetroExpress getModel() {
		// TODO Auto-generated method stub
		return metroExpress;
	}

	public MetroExpressService getMetroExpressService() {
		return metroExpressService;
	}

	public void setMetroExpressService(MetroExpressService metroExpressService) {
		this.metroExpressService = metroExpressService;
	}
	
	
	/** 
	* @Title: getQyDl 
	* @Description: 获取牵引电量 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getQyDl(){
		Map<String,Double> map = PublicFunction.getQyDl();
		String json = VOUtils.getJsonData(map);
		createJSonData(json);
		return AJAX;
	}
	
	/**
	 * @author ycl
	 * @updateDate 2012/4/9
	 * @description 修改运营速报中“续报”字段的存贮方式
	 */
	public String metroExpressAdd(){
		String hideRemarkParam = StringUtil.getNotNullValueString(servletRequest.getParameter("hideRemarkParam"));
		
		if(hideRemarkParam!=null && !hideRemarkParam.equals("")){
			String []remarkArray = hideRemarkParam.split("——"); 
			Document document = DocumentHelper.createDocument();
			Element expressElem = document.addElement("express");
			if(remarkArray.length>0){
				for(int i=0; i<remarkArray.length; i++){
					Element itemElem = expressElem.addElement("item");
					Element idElement = itemElem.addElement("id");
						idElement.setText(String.valueOf(i+1));
					Element valueElement = itemElem.addElement("value");
						valueElement.setText(remarkArray[i]);
				}
			}
			metroExpress.setAccidentRemark(document.asXML());
		}
		this.metroExpressService.addMetroExpress(metroExpress);
		
		return SUCCESS;
	}
	
	/**
	 * @author ycl
	 * @updateDate 2012-4-10
	 * @description 修改“续报”字段的保存 
	 */
	public String metroExpressUpdate(){
		
		String hideRemarkParam = StringUtil.getNotNullValueString(servletRequest.getParameter("hideRemarkParam"));
		if(hideRemarkParam!=null && !hideRemarkParam.equals("")){
			String []remarkArray = hideRemarkParam.split("——"); 
			Document document = DocumentHelper.createDocument();
			Element expressElem = document.addElement("express");
			if(remarkArray.length>0){
				for(int i=0; i<remarkArray.length; i++){
					Element itemElem = expressElem.addElement("item");
					Element idElement = itemElem.addElement("id");
						idElement.setText(String.valueOf(i+1));
					Element valueElement = itemElem.addElement("value");
						valueElement.setText(remarkArray[i]);
				}
			}
			metroExpress.setAccidentRemark(document.asXML());
		}
		this.metroExpressService.updateMetroExpress(metroExpress);
		
		//createJSonData("{\"success\":true}");
		//this.getServletRequest().setAttribute("metroExpressId", this.getServletRequest().getParameter("metroExpressId"));
		return "metroExpressEdited";
	}
	
	public String metroExpressDelete(){
		String id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("metroExpressId"));
		this.metroExpress = this.metroExpressService.viewMetroExpress(id);
		metroExpress.setRemoved("1");
		this.metroExpressService.updateMetroExpress(metroExpress);
		createJSonData("{\"success\":true}");
		return AJAX;
	}
	
	/**
	 * @author ycl
	 * @updateDate 2012-4-10
	 * @description 修改“续报”字段显示
	 */
	public String metroExpressEdit(){
		//this.metroExpress.setRemoved("1");
		Map<Integer,String> resultMap = new TreeMap<Integer, String>();
		
		String metroExpressId = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("metroExpressId"));
		this.metroExpress = this.metroExpressService.viewMetroExpress(metroExpressId);
		
		if(metroExpress.getAccidentRemark()!=null && !metroExpress.getAccidentRemark().equals("")){
			try {
				Document document = DocumentHelper.parseText(metroExpress.getAccidentRemark());
				int id =-1;
				String value = null;
				List<Element> idList = document.selectNodes("//item/id");
				List<Element> valueList = document.selectNodes("//item/value");
				
				if(idList.size()!=0 && valueList.size()!=0 && valueList.size()==idList.size()){
					for(int i=0; i<idList.size(); i++){
						id = Integer.valueOf(idList.get(i).getText());
						value = valueList.get(i).getText();
						resultMap.put(id, value);
					}
				}
			} catch (DocumentException e) {
				//e.printStackTrace();
			}
		}
		this.servletRequest.setAttribute("resultMap", resultMap);
		
		return SUCCESS;
	}
	
	/**
	 * @author ycl
	 * @updateDate 2012-4-10
	 * @descripting 修改“续报”字段的显示
	 * @return
	 */
	public String metroExpressView(){
		//this.metroExpress.setRemoved("1");
		String metroExpressId = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("metroExpressId"));
		this.metroExpress = this.metroExpressService.viewMetroExpress(metroExpressId);
		this.servletRequest.setAttribute("lineMap", this.metroExpressService.findMetroLineConfigMap());
		
		Map<Integer,String> resultMap = new TreeMap<Integer, String>();
		
		if(metroExpress.getAccidentRemark()!=null && !metroExpress.getAccidentRemark().equals("")){
			try {
				Document document = DocumentHelper.parseText(metroExpress.getAccidentRemark());
				
				int id =-1;
				String value = null;
				List<Element> idList = document.selectNodes("//item/id");
				List<Element> valueList = document.selectNodes("//item/value");
				
				if(idList.size()!=0 && valueList.size()!=0 && valueList.size()==idList.size()){
					for(int i=0; i<idList.size(); i++){
						id = Integer.valueOf(idList.get(i).getText());
						value = valueList.get(i).getText();
						resultMap.put(id, value);
					}
				}
			} catch (DocumentException e) {
				//e.printStackTrace();
			}
		}
		this.servletRequest.setAttribute("resultMap", resultMap);
		
		return SUCCESS;
	}
	
	
	
	public String metroExpressCode(){
		String codeType_code = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("codeType_code"));
		String codeInfo_code = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("codeInfo_code"));
		List<String> list = this.metroExpressService.findMetroExpressCode(codeType_code, codeInfo_code);
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	public String metroLineConfig(){
		List<String> list = this.metroExpressService.findMetroLineConfig();
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
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
	
	public String metroExpresseByPage() {
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.metroExpress);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.metroExpress,
						key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if(currentPage==0){
			page = metroExpressService.findMetroExpressByPage(
					filter, start / size + 1, size);
		}else{
			page = metroExpressService.findMetroExpressByPage(
					filter,currentPage, size);
		}
		
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("lineMap", this.metroExpressService.findMetroLineConfigMap());
		return SUCCESS;
}
	
	public String metroExpressManageByPage() {
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.metroExpress);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.metroExpress,
						key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if(currentPage==0){
			page = metroExpressService.findMetroExpressByPage(
					filter, start / size + 1, size);
		}else{
			page = metroExpressService.findMetroExpressByPage(
					filter,currentPage, size);
		}
		
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("lineMap", this.metroExpressService.findMetroLineConfigMap());
		return SUCCESS;
}
}
