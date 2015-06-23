package com.wonders.stpt.doneItem.action;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.doneItem.model.vo.DoneStatInfo;
import com.wonders.stpt.doneItem.service.DoneItemService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParentPackage("struts-default")
@Namespace(value="/doneNew")
@Controller("newDoneItemAction")
@Scope("prototype")
public class NewDoneItemAction extends AbstractParamAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3319656901996338661L;
	@Autowired
	private DoneItemService service;
	
	@SuppressWarnings({ "unchecked"})
	@Action(value="doneItem")
	public String done(){
		String trackStatus = StringUtil.getNotNullValueString(this.servletRequest.getParameter("trackStatus"));
		String processStatus = StringUtil.getNotNullValueString(this.servletRequest.getParameter("processStatus"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		List<String> source = new ArrayList<String>();
		source.add(ologinName);
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			source.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
		ActionWriter aw = new ActionWriter(this.servletResponse);
		DoneStatInfo doneStatInfo = this.service.getDoneInfo(source,processStatus,trackStatus);
		aw.writeJson(doneStatInfo);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="doneTrack",results={
			@Result(name="success",location="/center/wdsw/newVersion/track.jsp")
			})
	public String doneTrack(){
		String trackStatus = "0";
		String processStatus = "1";
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		List<String> source = new ArrayList<String>();
		source.add(ologinName);
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			source.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
        this.servletRequest.setAttribute("processStatus","1");
		this.servletRequest.setAttribute("type", this.service.getDoneType(source, processStatus, trackStatus));
		this.servletRequest.setAttribute("result", this.service.getDoneResult(source, processStatus, trackStatus));
		return "success";
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value="unDoneTrack",results={
			@Result(name="success",location="/center/wdsw/newVersion/track.jsp")
			})
	public String unDoneTrack(){
		String trackStatus = "0";
		String processStatus = "0";
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		List<String> source = new ArrayList<String>();
		source.add(ologinName);
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			source.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
        this.servletRequest.setAttribute("processStatus","0");
		this.servletRequest.setAttribute("type", this.service.getDoneType(source, processStatus, trackStatus));
		this.servletRequest.setAttribute("result", this.service.getDoneResult(source, processStatus, trackStatus));
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="track")
	public String track(){
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		List<String> source = new ArrayList<String>();
		source.add(ologinName);
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			source.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
		String id = StringUtil.getNotNullValueString(this.servletRequest.getParameter("id"));	
		String trackStatus = StringUtil.getNotNullValueString(this.servletRequest.getParameter("trackStatus"));
		ActionWriter aw = new ActionWriter(this.servletResponse);
		Map<String,Boolean> result = new HashMap<String,Boolean>();
        result.put("success",this.service.track(source, id, trackStatus));
        aw.writeJson(result);
		return null;
	}

    @SuppressWarnings("unchecked")
    @Action(value="trackBatch")
    public String trackBatch(){
        String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
        List<String> source = new ArrayList<String>();
        source.add(ologinName);
        Map<String, TaskUserVo> userMap =
                (Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
        for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
            source.add(StringUtil.getNotNullValueString(entry.getKey()));
        }
        String type = StringUtil.getNotNullValueString(this.servletRequest.getParameter("type"));
        String trackStatus = StringUtil.getNotNullValueString(this.servletRequest.getParameter("trackStatus"));
        ActionWriter aw = new ActionWriter(this.servletResponse);
        Map<String,Boolean> result = new HashMap<String,Boolean>();
        result.put("success",this.service.trackBatch(source, type, trackStatus));
        aw.writeJson(result);
        return null;
    }
}
