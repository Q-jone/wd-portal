/**
 * 
 */
package com.wonders.stpt.innerWork.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nl.justobjects.pushlet.util.Sys;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.innerWork.model.bo.TInnerWork;
import com.wonders.stpt.innerWork.service.CaRestAPIService;
import com.wonders.stpt.innerWork.service.InnerWorkService;
import com.wonders.stpt.innerWork.service.TShortMsgService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: InnerWorkAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:51:41 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/innerWork")
@Controller("innerWorkAction")
@Scope("prototype")
public class InnerWorkAction extends AbstractParamAction implements ModelDriven<TInnerWork>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8744535044414148667L;
	private TInnerWork bo = new TInnerWork();
	private ActionWriter aw = new ActionWriter(response);
	private InnerWorkService service;
	
	@Resource
	private TShortMsgService tShortMsgService;
	
	
	public TInnerWork getBo() {
		return bo;
	}
	public void setBo(TInnerWork bo) {
		this.bo = bo;
	}
	public InnerWorkService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("innerWorkService")InnerWorkService service) {
		this.service = service;
	}

	@Override
	public TInnerWork getModel() {
		// TODO Auto-generated method stub
		return bo;
	}
	
	@Action(value="edit",results={
			@Result(name="success",location="/innerWork/edit.jsp")
			})
	public String edit(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="view",results={
			@Result(name="success",location="/innerWork/view.jsp")
			})
	public String view(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="add")
	public String add(){
		//获取，并解析责任人、分管领导字符串
		String loginNamesRPeople=request.getParameter("loginNamesRPeople");
		String loginNamesRLeader=request.getParameter("loginNamesRLeader");
		String[] rPeople=loginNamesRPeople.split(",");
		String[] rLeader=loginNamesRLeader.split(",");
		
		//将负责人、分光领导loginName设置到实体
		bo.setrPeopleLoginName(loginNamesRPeople);
		bo.setrLeaderLoginName(loginNamesRLeader);
		
		//存放发送人loginName
		List<String> sendToPeople=new ArrayList<String>();
		
		String rPeopleStr="";
		for(int i=0;i<rPeople.length;i++){
			if(i==rPeople.length-1){
				rPeopleStr+=rPeople[i];
			}else{
				rPeopleStr+=rPeople[i]+",";
			}
			sendToPeople.add(rPeople[i]);
		}
		String rLeaderStr="";
		for(int j=0;j<rLeader.length;j++){
			if(j==rLeader.length-1){
				rLeaderStr+=rLeader[j];
			}else{
				rLeaderStr+=rLeader[j]+",";
			}
			sendToPeople.add(rLeader[j]);
		}
		
		String content="您好！关于\""+bo.getJobName()+"\"已经创建重点工作跟踪,将于:"
				       +bo.getbTime()+"开始，预计完成时间:"+bo.getPfTime()+","
				       +"相关责任人:"+rPeopleStr+","
				       +"分管领导："+rLeaderStr+"。请关注！";
		for(String loginName:sendToPeople){
			tShortMsgService.sendNewShortMsg(loginName, content);
		}
		aw.writeAjax(this.service.save(bo));
		return null;
	}
	
	@Action(value="update")
	public String update(){
		aw.writeAjax(this.service.update(bo));
		return null;
	}
	
	
	/**
	 * @author taoweiwei
	 * 测试获取部门用户，异步方式
	 * @return
	 */
	@Action(value="getDeptUserByLoginUser")
	public String getDeptUserByLoginUser(){
		Map<String,Object> returnData=CaRestAPIService.getCurrentLoginInfoFromCa(request, response);
		aw.writeJson(returnData);
		return null;
	}
	
	/**
	 * @author taoweiwei
	 * 作用：短信定时提醒功能
	 * @return
	 */
	@Action(value="sendMsgNotice")
	public String sendMsgNotice(){
		//获取满足条件的数据记录
		List<TInnerWork> tInnerWorks=this.service.findByStatus();
		if(tInnerWorks==null||tInnerWorks.size()==0){
			return null;
		}
		
		//遍历数据记录，给每一条数据的负责人发送短信提醒
		for (TInnerWork tInnerWork : tInnerWorks) {
			List<String> loginNames=new ArrayList<String>();
			if(tInnerWork.getrPeopleLoginName()!=null && !"".equals(tInnerWork.getrPeopleLoginName())){
				String[] rPeoleLoginName=tInnerWork.getrPeopleLoginName().split(",");
				for(String s:rPeoleLoginName){
					loginNames.add(s);
				}
				
			}
			if(tInnerWork.getrLeaderLoginName()!=null && !"".equals(tInnerWork.getrLeaderLoginName())){
				String[] rLeaderLoginName=tInnerWork.getrLeaderLoginName().split(",");
				for(String ss:rLeaderLoginName){
					loginNames.add(ss);
				}
				
			}
			if(loginNames.size()==0){
				continue;
			}
			
             String rPeopleStr="";
             if(tInnerWork.getrPeople()==null||"".equals(tInnerWork.getrPeople())){
            	 rPeopleStr="无";
             }else{
            	 String[] rPeople=tInnerWork.getrPeople().split(",");
            	 for(int i=0;i<rPeople.length;i++){
              		rPeopleStr+=rPeople[i]+",";
                 }
                rPeopleStr.substring(0, rPeopleStr.length()-1);
             }
            
             String rLeaderStr="";
             if(tInnerWork.getrLeader()==null||"".equals(tInnerWork.getrLeader())){
             	rLeaderStr="无";
             }else{
             	 String[] rLeader=tInnerWork.getrLeader().split(",");
                  for(int j=0;j<rLeader.length;j++){
                  		rLeaderStr+=rLeader[j]+",";
                  }
                  rLeaderStr.substring(0, rLeaderStr.length()-1);
             }
            
			String content="您好！关于\""+tInnerWork.getJobName()+"\"剩余完成时间不足一周！请尽快督促!相关信息如下"
						   +"("
						   +"开始时间:"+tInnerWork.getbTime()+","
						   +"预计完成时间:"+tInnerWork.getPfTime()+","
						   +"当前状态:"+tInnerWork.getStatus()+","
						   +"相关责任人:"+rPeopleStr+","
						   +"分管领导："+rLeaderStr
						   +")。";  
			for(String loginName:loginNames){
				tShortMsgService.sendNewShortMsg(loginName, content);
			}
		}
		return null;
	}
	
}
