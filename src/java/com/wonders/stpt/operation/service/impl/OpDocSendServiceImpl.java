package com.wonders.stpt.operation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.FlowWorkThread;
import com.wonders.stpt.operation.entity.bo.FlowWorkType;
import com.wonders.stpt.operation.entity.bo.FlowWorkUsers;
import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.operation.service.OpDictionaryService;
import com.wonders.stpt.operation.service.OpDocSendService;
import com.wonders.stpt.operation.service.flowwork.FlowWorkProcessService;
import com.wonders.stpt.operation.service.flowwork.FlowWorkThreadService;
import com.wonders.stpt.operation.service.flowwork.FlowWorkTypeService;
import com.wonders.stpt.operation.service.flowwork.FlowWorkUsersService;
import com.wonders.stpt.operation.util.FlowWorkUtils;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

@Repository("opDocSendService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OpDocSendServiceImpl implements OpDocSendService{
	
	private OpTDao<OpDocSend> dao;
	public OpTDao<OpDocSend> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<OpDocSend> dao) {
		this.dao = dao;
	}
	
	private OpTDao<OpDocSend> newDao;
	public OpTDao<OpDocSend> getNewDao() {
		return newDao;
	}
	@Autowired(required=false)
	public void setNewDao(@Qualifier("opTNewDao")OpTDao<OpDocSend> newDao) {
		this.newDao = newDao;
	}

	@Override
	public OpDocSend find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,OpDocSend.class);
	}
	
	@Override
	public OpDocSend findByFlowUid(String flowUid){
		List<OpDocSend> list = this.dao.findByHql("from OpDocSend t where t.flowGroup = ?", new Object[]{flowUid});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Object[]> getDeptLeaders(String deptId){
		return (List<Object[]>)newDao.excuteSQLQuery("select t.login_name,t.name,t.dept_id from v_dept_leaders t where t.dept_id = ? order by t.orders", new Object[]{deptId});
	}
	
	@Override
	public List<Object[]> getUrgentApprovers(){
		return (List<Object[]>)dao.excuteSQLQuery("select t.login_name,t.name from op_urgent_approver t order by t.orders", new Object[]{});
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public int deleteById(String id) {
		dao.excuteHQLUpdate("update OpDocSend set removed = 1 where id = ?", new Object[]{id});
		return 1;
	}

	@Override
	public OpDocSend save(OpDocSend bo,HttpServletRequest request) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			bo = dao.update(bo);
		}else{
			bo.setFileCode(this.genFileCode(bo.getDeptId())); //新增时重新生成编号
			bo = dao.save(bo);
		}
		
		String threadId = request.getParameter("threadId");
		if(StringUtil.isNull(threadId)){
			flowWorkProcessService.startNewFlow(request.getParameter("flowCode"), bo.getId(), "运营发文", request);	
		}else{
			FlowWorkThread flowThread = flowWorkThreadService.find(Long.parseLong(threadId.toString()));//findOne(flowType.getId());
			flowThread.setOperationType(0l);
			
			String now = DateUtil.getNowTime();
			flowThread.setEndTime(now);
			flowThread.setState(2);
			
			if(flowThread.getDefOrderIndex() == 0){
				FlowWorkType flowType = flowWorkTypeService.findByCode(request.getParameter("flowCode"));
				List<FlowWorkUsers> flowWorkUserses = flowWorkUsersService.findByTypeId(flowType.getId());
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("opDocSend", bo);
				for(FlowWorkUsers fu:flowWorkUserses){
					FlowWorkThread f= new FlowWorkThread();
					f.setOrderIndex(fu.getOrderIndex()+flowThread.getOrderIndex());
					f.setDefOrderIndex(fu.getOrderIndex());
					f.setFlowUid(flowThread.getFlowWorkProcess().getFlowUid());
					f.setNodeName(fu.getNodeName());
					f.setUserId(evaluateEL(fu.getUserId(),map));
					f.setUserName(evaluateEL(fu.getUserName(),map));				
					f.setAltAuthCode(fu.getAltAuthCode());
					f.setOperate(fu.getOperate());
					f.setLoopIndex(flowThread.getLoopIndex());
					flowWorkThreadService.save(f);
				}
			}
			
			flowWorkThreadService.saveThreadAndDoNext(flowThread,request);
		}
		
		return bo;
	}
	
	@Override
	public String genFileCode(String deptId){
		String fileCode = "ST/SC";
		
		List<OpDictionary> deptCodes = opDictionaryService.findByCode("DEPT_CODE_"+deptId);
		if(deptCodes != null && deptCodes.size() > 0){
			fileCode += "-"+deptCodes.get(0).getName();
		}
		
		fileCode += "-%-"+(new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()));
		int numOfApplied = dao.countByHql("select count(t.id) from OpDocSend t where t.fileCode like '"+fileCode+"'");
		
		fileCode = fileCode.replaceFirst("%", String.valueOf(numOfApplied+1));
		return fileCode;
	}
	
	@Override
	public int updateAttMain(String id,String contentAttMain){
		return dao.excuteHQLUpdate("update OpDocSend set contentAttMain = ? where id = ?", new Object[]{contentAttMain,id});
	}

    @Override
    public int updateIsLeader(String id,String isLeader){
        return dao.excuteHQLUpdate("update OpDocSend set isLeader = ? where id = ?", new Object[]{isLeader,id});
    }
	
	private FlowWorkProcessService flowWorkProcessService;
	public FlowWorkProcessService getFlowWorkProcessService() {
		return flowWorkProcessService;
	}
	@Autowired(required=false)
	public void setFlowWorkProcessService(@Qualifier("flowWorkProcessService")FlowWorkProcessService flowWorkProcessService) {
		this.flowWorkProcessService = flowWorkProcessService;
	}	
	
	private FlowWorkThreadService flowWorkThreadService;
	public FlowWorkThreadService getFlowWorkThreadService() {
		return flowWorkThreadService;
	}
	@Autowired(required=false)
	public void setFlowWorkThreadService(@Qualifier("flowWorkThreadService")FlowWorkThreadService flowWorkThreadService) {
		this.flowWorkThreadService = flowWorkThreadService;
	}	
	
	private OpDictionaryService opDictionaryService;
	public OpDictionaryService getOpDictionaryService() {
		return opDictionaryService;
	}
	@Autowired(required=false)
	public void setOpDictionaryService(@Qualifier("opDictionaryService")OpDictionaryService opDictionaryService) {
		this.opDictionaryService = opDictionaryService;
	}
	
	FlowWorkUsersService flowWorkUsersService;
	public FlowWorkUsersService getFlowWorkUsersService() {
		return flowWorkUsersService;
	}
	@Autowired(required=false)
	public void setFlowWorkUsersService(@Qualifier("flowWorkUsersService")FlowWorkUsersService flowWorkUsersService) {
		this.flowWorkUsersService = flowWorkUsersService;
	}
	
	FlowWorkTypeService flowWorkTypeService;
	public FlowWorkTypeService getFlowWorkTypeService() {
		return flowWorkTypeService;
	}
	@Autowired(required=false)
	public void setFlowWorkTypeService(@Qualifier("flowWorkTypeService")FlowWorkTypeService flowWorkTypeService) {
		this.flowWorkTypeService = flowWorkTypeService;
	}
	@Override
	public Object[] getReadInfo(String mainId,String deptId){
		List<Object[]> list = dao.excuteSQLQuery("select t.reg_name, t.reg_dept_name, t.reg_time from t_dept_pass t where t.main_id = ? and t.reg_dept_id = ?", new Object[]{mainId,deptId});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
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
}
