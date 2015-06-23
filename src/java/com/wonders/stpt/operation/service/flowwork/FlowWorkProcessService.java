package com.wonders.stpt.operation.service.flowwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.FlowWorkProcess;
import com.wonders.stpt.operation.entity.bo.FlowWorkThread;
import com.wonders.stpt.operation.entity.bo.FlowWorkType;
import com.wonders.stpt.operation.entity.bo.FlowWorkUsers;
import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.operation.service.OpDocSendService;
import com.wonders.stpt.operation.util.FlowWorkContents;
import com.wonders.stpt.operation.util.FlowWorkUtils;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

@Repository("flowWorkProcessService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class FlowWorkProcessService {

	private OpTDao<FlowWorkProcess> dao;
	public OpTDao<FlowWorkProcess> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<FlowWorkProcess> dao) {
		this.dao = dao;
	}
	
	FlowWorkThreadService flowWorkThreadService;
	FlowWorkTypeService flowWorkTypeService;
	FlowWorkUsersService flowWorkUsersService;

	
	/**
	 * 新增发起流程
	 */
	@Transactional(readOnly=false)
	public Map<String,Object> startNewFlow(String flowCode,String objectId,String objectName,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		String accoutid = (String) session.getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String accName = (String) session.getAttribute(LoginConstant.SECURITY_USER_NAME);
		//String loginName = (String) session.getAttribute("loginName");
		
		FlowWorkType flowType = flowWorkTypeService.findByCode(flowCode);
		FlowWorkProcess flowProcess = new FlowWorkProcess();
		try {
			if(objectId!=null&&!"".equals(objectId)&&flowType.getObjectClass()!=null&&!"".equals(flowType.getObjectClass())){
				String hql = " update "+flowType.getObjectClass()+" a set a.flowGroup ='"+flowProcess.getFlowUid()+"' where a.id = '"+objectId+"' and ( a.flowGroup is null or a.flowGroup='' ) " ;
				int upnum = flowWorkTypeService.doHql(hql,new Object[]{});
				if(upnum==0){
					return map;
				}
			}
        } catch(java.lang.Exception e) {
            System.out.println("in main, catch Exception: " + e);
            return map;
        }
		String now = DateUtil.getNowTime();

		flowProcess.setFlowName(objectName);
		
		flowProcess.setFlowTypeCode(flowType.getFlowTypeCode());
		flowProcess.setFlowTypeId(flowType.getId());
		flowProcess.setFlowTypeName(flowType.getFlowTypeName());
		flowProcess.setFlowDescription(flowType.getFlowDescription());
		flowProcess.setEndToDoHql(flowType.getEndToDoHql());
		flowProcess.setObjectClass(flowType.getObjectClass());
		flowProcess.setLinkStr(flowType.getLinkStr());
		flowProcess.setObjectMethod(flowType.getObjectMethod());
		flowProcess.setParamVals(flowType.getParamVals());
		flowProcess.setObjectMethodClass(flowType.getObjectMethodClass());
		
		flowProcess.setObjectId(objectId);
		flowProcess.setUserId(accoutid);
		flowProcess.setStartTime(now);
		flowProcess.setUserName(accName);
		flowProcess.setUpdateTime(now);
		this.save(flowProcess);
		
		List<FlowWorkUsers> flowWorkUserses = flowWorkUsersService.findByTypeId(flowType.getId());
		List<FlowWorkThread> flowThreads = new ArrayList<FlowWorkThread>();
		
		FlowWorkThread starThread = new FlowWorkThread();//发起人设置
		starThread.setOrderIndex(0);
		starThread.setDefOrderIndex(0);
		starThread.setUserId(accoutid);
		starThread.setUserName(accName);
		starThread.setState(1);
		starThread.setStartTime(now);
		starThread.setNodeName(FlowWorkContents.FLOW_NODE_SPONSOR);
		starThread.setFlowUid(flowProcess.getFlowUid());
		starThread = flowWorkThreadService.save(starThread);

		OpDocSend opDocSend = opDocSendService.find(objectId);
		Map<String,Object> repoMap = new HashMap<String,Object>();
		repoMap.put("opDocSend", opDocSend);
		for(FlowWorkUsers fu:flowWorkUserses){
			FlowWorkThread f= new FlowWorkThread();
			f.setOrderIndex(fu.getOrderIndex());
			f.setDefOrderIndex(fu.getOrderIndex());
			f.setFlowUid(flowProcess.getFlowUid());
			f.setNodeName(fu.getNodeName());
			f.setUserId(evaluateEL(fu.getUserId(),repoMap));
			f.setUserName(evaluateEL(fu.getUserName(),repoMap));				
			f.setAltAuthCode(fu.getAltAuthCode());
			f.setOperate(fu.getOperate());
			flowWorkThreadService.save(f);
			flowThreads.add(f);
		}
		
		starThread.setState(2);
		starThread.setEndTime(now);
		
		flowWorkThreadService.saveThreadAndDoNext(starThread,request);
		
		map.put("flowProcess", flowProcess);
		map.put("flowThreads", flowThreads);
		map.put("starThread", starThread);
		
		return map;
	}
	
	public FlowWorkProcess save(FlowWorkProcess bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	} 
	
	public FlowWorkProcess findFlowProcessByFlowUid(String flowUid){
		List<FlowWorkProcess> list = dao.findByHql(" from FlowWorkProcess f where f.removed = 0 and f.flowUid=? ", new Object[]{flowUid});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public int updateProcess(String flowUid, String hander, String stage, int state, String updateTime){
		return this.dao.excuteHQLUpdate(" update FlowWorkProcess f set f.flowHandler = ? , f.state=?, f.flowStage = ?, f.updateTime = ? where f.flowUid =? ", new Object[]{hander,state,stage,updateTime,flowUid});
	}
	
	/**
	 * 将flowUser中的${*}替换为对应的值
	 * @param flowThreads
	 * @param map
	 */
	public String evaluateEL(String field, Map<String,Object> map){
		if(!StringUtil.isNull(field) && field.contains("$")){
			field = FlowWorkUtils.evaluateEL(field, map);
		}
		return field;
	}
	
	/**
	 * 完结流程
	 * @return
	 */
	public int updateProcess(String flowUid, String hander,int state,String endtime, String updateTime){
		return this.dao.excuteHQLUpdate(" update FlowWorkProcess f set f.flowHandler = ? , f.state=?, f.endTime=?, f.updateTime = ? where f.flowUid =? ",new Object[]{hander,state,endtime,updateTime,flowUid});
	}

	public FlowWorkTypeService getFlowWorkTypeService() {
		return flowWorkTypeService;
	}
	@Autowired(required=false)
	public void setFlowWorkTypeService(@Qualifier("flowWorkTypeService")FlowWorkTypeService flowWorkTypeService) {
		this.flowWorkTypeService = flowWorkTypeService;
	}
	public FlowWorkUsersService getFlowWorkUsersService() {
		return flowWorkUsersService;
	}
	@Autowired(required=false)
	public void setFlowWorkUsersService(@Qualifier("flowWorkUsersService")FlowWorkUsersService flowWorkUsersService) {
		this.flowWorkUsersService = flowWorkUsersService;
	}
	public FlowWorkThreadService getFlowWorkThreadService() {
		return flowWorkThreadService;
	}
	@Autowired(required=false)
	public void setFlowWorkThreadService(@Qualifier("flowWorkThreadService")FlowWorkThreadService flowWorkThreadService) {
		this.flowWorkThreadService = flowWorkThreadService;
	}	
	
	private OpDocSendService opDocSendService;
	public OpDocSendService getOpDocSendService() {
		return opDocSendService;
	}
	@Autowired(required=false)
	public void setOpDocSendService(@Qualifier("opDocSendService")OpDocSendService opDocSendService) {
		this.opDocSendService = opDocSendService;
	}
}
