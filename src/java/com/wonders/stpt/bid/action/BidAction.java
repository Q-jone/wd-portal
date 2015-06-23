/**
 * 
 */
package com.wonders.stpt.bid.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.bid.model.bo.TBidPlan;
import com.wonders.stpt.bid.service.BidService;
import com.wonders.stpt.bid.util.BidUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: BidAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:51:41 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/bid")
@Controller("bidAction")
@Scope("prototype")
public class BidAction extends AbstractParamAction implements ModelDriven<TBidPlan>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1381002596285221877L;
	private TBidPlan bo = new TBidPlan();
	private ActionWriter aw = new ActionWriter(response);
	private BidService service;
	
	
	public TBidPlan getBo() {
		return bo;
	}
	public void setBo(TBidPlan bo) {
		this.bo = bo;
	}
	public BidService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("bidService")BidService service) {
		this.service = service;
	}

	@Override
	public TBidPlan getModel() {
		// TODO Auto-generated method stub
		return bo;
	}
	
	@Action(value="getBidType")
	public String getBidType(){
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		List<Map<String,Object>> list = BidUtil.getBidType(flag);
		aw.writeJson(list);
		return null;			
	}
	
	@Action(value="addBidType")
	public String addBidType(){
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		String typeName = StringUtil.getNotNullValueString(request.getParameter("typeName"));
		String result = "0";
		int count = BidUtil.addBidType(typeName,flag);
		if(count > 0){
			result = "1";
		}
		aw.writeAjax(result);
		return null;			
	}
	
	@Action(value="edit",results={
			@Result(name="success",location="/bid/edit.jsp")
			})
	public String edit(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="add")
	public String add(){
		aw.writeAjax(this.service.save(bo));
		return null;
	}
	
	@Action(value="update")
	public String update(){
		aw.writeAjax(this.service.update(bo));
		return null;
	}
	
}
