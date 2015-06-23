package com.wonders.stpt.train.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.train.entity.TrainData;
import com.wonders.stpt.train.entity.TrainDept;
import com.wonders.stpt.train.entity.TrainLevel;
import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.train.entity.TrainMonth;
import com.wonders.stpt.train.entity.TrainProfile;
import com.wonders.stpt.train.service.TrainDeptService;
import com.wonders.stpt.train.service.TrainLevelService;
import com.wonders.stpt.train.service.TrainMainService;
import com.wonders.stpt.train.service.TrainMonthService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/**
 * 在线答题 
 * @author ICESUGAR
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/train")
@Controller("trainAction")
@Scope("prototype")
public class TrainAction extends AbstractParamAction implements ModelDriven<TrainMain> {

	
	private TrainMain trainMain = new TrainMain();
	
	private PageResultSet<TrainMain> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);

	@Override
	public TrainMain getModel() {
		// TODO Auto-generated method stub
		return trainMain;
	}

	public TrainMain getTrainMain() {
		return trainMain;
	}

	public void setTrainMain(TrainMain trainMain) {
		this.trainMain = trainMain;
	}

	public PageResultSet<TrainMain> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<TrainMain> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/center/jypx/list_main.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String list(){
		String year = request.getParameter("year");
		if(StringUtil.isNull(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());			
		}
		
		List<TrainMain> mains = service.findByYear(year);
		TrainProfile profile = service.findProfileByYear(year);
		this.request.setAttribute("mains", mains);
		this.request.setAttribute("thisYear", year);
		this.request.setAttribute("profile", profile);
	
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		Integer num = Integer.valueOf(StringUtil.getNotNullValueNumber(request.getParameter("num")));
		String thisYear = request.getParameter("thisYear");
		String deadline = request.getParameter("deadline");
		
		List<TrainMain> saveList = new ArrayList<TrainMain>();
		for(int i=0;i<num;i++){
			String id = request.getParameter("datas["+i+"].id");
			if(!StringUtil.isNull(id)){
				saveList.add(service.find(id));
			}else{
				saveList.add(new TrainMain());
			}
		}
		
		setParams(saveList);

		this.service.saveAll(saveList,thisYear,deadline);
		return null;
	}
	
	@Action(value="listMonth",results={
			@Result(name="success",location="/center/jypx/list_month.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listMonth(){
		String mainId = request.getParameter("mainId");
		List<TrainMonth> months = trainMonthService.findByMainId(mainId);
		this.request.setAttribute("months", months);
		
		TrainMain main = service.find(mainId);
		this.request.setAttribute("main", main);
	
		return SUCCESS;
	}	
	
	@Action(value="saveMonth")
	public String saveMonth(){
		Integer num = Integer.valueOf(StringUtil.getNotNullValueNumber(request.getParameter("num")));
		
		List<TrainMonth> saveList = new ArrayList<TrainMonth>();
		for(int i=0;i<num;i++){
			String id = request.getParameter("datas["+i+"].id");
			if(!StringUtil.isNull(id)){
				saveList.add(trainMonthService.find(id));
			}else{
				saveList.add(new TrainMonth());
			}
		}
		
		setParams(saveList);
		
		this.trainMonthService.saveOrUpdateAll(saveList);
		return null;
	}
	
	@Action(value="delMonth")
	public String delMonth(){
		String id = request.getParameter("id");
		if(!StringUtil.isNull(id)){
			this.trainMonthService.deleteById(id);			
		}
		
		return null;
	}
	
	@Action(value="listLevel",results={
			@Result(name="success",location="/center/jypx/list_level.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listLevel(){
		String mainId = request.getParameter("mainId");
		List<TrainLevel> levels = trainLevelService.findByMainId(mainId);
		this.request.setAttribute("levels", levels);
		
		TrainMain main = service.find(mainId);
		this.request.setAttribute("main", main);
	
		return SUCCESS;
	}	
	
	@Action(value="saveLevel")
	public String saveLevel(){
		Integer num = Integer.valueOf(StringUtil.getNotNullValueNumber(request.getParameter("num")));
		
		List<TrainLevel> saveList = new ArrayList<TrainLevel>();
		for(int i=0;i<num;i++){
			String id = request.getParameter("datas["+i+"].id");
			if(!StringUtil.isNull(id)){
				saveList.add(trainLevelService.find(id));
			}else{
				saveList.add(new TrainLevel());
			}
		}
		
		setParams(saveList);
		
		this.trainLevelService.saveOrUpdateAll(saveList);
		return null;
	}
	
	@Action(value="delLevel")
	public String delLevel(){
		String id = request.getParameter("id");
		if(!StringUtil.isNull(id)){
			this.trainLevelService.deleteById(id);			
		}
		
		return null;
	}
	
	@Action(value="listDept",results={
			@Result(name="success",location="/center/jypx/list_dept.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listDept(){
		String mainId = request.getParameter("mainId");
		List<TrainDept> depts = trainDeptService.findByMainId(mainId);
		this.request.setAttribute("depts", depts);
		
		TrainMain main = service.find(mainId);
		this.request.setAttribute("main", main);
	
		return SUCCESS;
	}	
	
	@Action(value="saveDept")
	public String saveDept(){
		Integer num = Integer.valueOf(StringUtil.getNotNullValueNumber(request.getParameter("num")));
		
		List<TrainDept> saveList = new ArrayList<TrainDept>();
		for(int i=0;i<num;i++){
			String id = request.getParameter("datas["+i+"].id");
			if(!StringUtil.isNull(id)){
				saveList.add(trainDeptService.find(id));
			}else{
				saveList.add(new TrainDept());
			}
		}
		
		setParams(saveList);
		
		this.trainDeptService.saveOrUpdateAll(saveList);
		return null;
	}
	
	@Action(value="delDept")
	public String delDept(){
		String id = request.getParameter("id");
		if(!StringUtil.isNull(id)){
			this.trainDeptService.deleteById(id);			
		}
		
		return null;
	}
	
	private TrainMainService service;
	public TrainMainService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("trainMainService")TrainMainService service) {
		this.service = service;
	}
	
	private TrainMonthService trainMonthService;
	public TrainMonthService getTrainMonthService() {
		return trainMonthService;
	}
	@Autowired(required=false)
	public void setTrainMonthService(@Qualifier("trainMonthService")TrainMonthService trainMonthService) {
		this.trainMonthService = trainMonthService;
	}
	
	private TrainLevelService trainLevelService;
	public TrainLevelService getTrainLevelService() {
		return trainLevelService;
	}
	@Autowired(required=false)
	public void setTrainLevelService(@Qualifier("trainLevelService")TrainLevelService trainLevelService) {
		this.trainLevelService = trainLevelService;
	}
	
	private TrainDeptService trainDeptService;
	public TrainDeptService getTrainDeptService() {
		return trainDeptService;
	}
	@Autowired(required=false)
	public void setTrainDeptService(@Qualifier("trainDeptService")TrainDeptService trainDeptService) {
		this.trainDeptService = trainDeptService;
	}
	
	private void setParams(List saveList){
		TrainData trainData = new TrainData();
		trainData.setDatas(saveList);
		try {
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String)e.nextElement();
				if(name.startsWith("datas")){
					BeanUtils.setProperty(trainData, name, request.getParameter(name));		
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
