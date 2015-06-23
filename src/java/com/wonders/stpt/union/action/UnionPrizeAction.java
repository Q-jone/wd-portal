package com.wonders.stpt.union.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyDepartmentService;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.UnionThemeService;
import com.wonders.stpt.union.service.prize.UnionAchivementPrizeService;
import com.wonders.stpt.union.service.prize.UnionPersonalPrizeService;
import com.wonders.stpt.union.service.prize.UnionProjectPrizeService;
import com.wonders.stpt.union.service.prize.UnionTeamPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/**
 * 立功竞赛
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionPrize")
@Controller("unionPrizeAction")
@Scope("prototype")
public class UnionPrizeAction extends AbstractParamAction implements ModelDriven<UnionPrize> {
	
	private UnionPrize unionPrize = new UnionPrize();
	
	private PageResultSet<UnionPrize> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);
	
	public UnionFlowInfo params = new UnionFlowInfo();

	@Override
	public UnionPrize getModel() {
		return unionPrize;
	}

	public void setUnionPrize(UnionPrize unionPrize) {
		this.unionPrize = unionPrize;
	}

	public PageResultSet<UnionPrize> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionPrize> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	/**
	 * 奖项设置列表
	 * @return
	 */
	@Action(value="list",results={
			@Result(name="success",location="/union/prizeList.jsp"),
			@Result(name="print",location="/union/prizeList_print.jsp"),
			@Result(name="setOperator",location="/union/prizeList_setOperator.jsp"),
			@Result(name="reviewOperator",location="/union/prizeList_reviewOperator.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String matchId = this.request.getParameter("matchId");
		String checkRole = this.request.getParameter("checkRole");
		String op = this.request.getParameter("op");
		
		String pageNoString = this.request.getParameter("page");
		if(!StringUtils.isNotEmpty(pageNoString)){
			pageNoString = "1";
		}
		
		List<UnionPrize> prizes = this.service.findByMatchId(matchId);
		
		UnionMatch match = this.matchService.find(matchId);
		UnionTheme theme = this.themeService.find(match.getThemeId());
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(matchId, UnionApproval.ORG_STAGE);

		double totalBonus = 0;
		for(UnionPrize prize : prizes){
			totalBonus += prize.getBonus()*prize.getQuantity();
		}
		
		this.request.setAttribute("checkRole", checkRole);
		this.request.setAttribute("match", match);
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("prizes", prizes);
		this.request.setAttribute("approvalInfos", approvalInfos);
		this.request.setAttribute("totalBonus", totalBonus);

		Map<String,List<UnionApplyDepartment>> applyDeptMap = new HashMap<String,List<UnionApplyDepartment>>();
		for(UnionPrize prize : prizes){
			List<UnionApplyDepartment> applyDepts = this.applyDepartmentService.findByPrizeId(prize.getId());
			if(applyDepts != null && applyDepts.size() > 0){
				applyDeptMap.put(prize.getId(), applyDepts);
			}
		}
		this.request.setAttribute("applyDeptMap", applyDeptMap);	
		
		if(match.getStatus() >= UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS && match.getStatus() <= UnionMatch.PASS_STATUS){
			List<UnionApplyDepartment> applyDepts = this.applyDepartmentService.applyDeptsOfMatch(matchId);
			this.request.setAttribute("applyDepts", applyDepts);	//所有申报部门

			List<UnionApplyMatch> applyMatches = this.applyMatchService.findByMatchId(matchId);
			//各申报部门下的申报人
			Map<String,List<UnionApplyMatch>> applyMatchesMap = new HashMap<String,List<UnionApplyMatch>>();
			for(UnionApplyMatch applyMatch : applyMatches){
				if(!applyMatchesMap.containsKey(applyMatch.getDeptId())){
					applyMatchesMap.put(applyMatch.getDeptId(), new ArrayList<UnionApplyMatch>());
				}
				applyMatchesMap.get(applyMatch.getDeptId()).add(applyMatch);
			}
			this.request.setAttribute("applyMatchesMap", applyMatchesMap);				
		}
		
		if(StringUtils.isNotEmpty(op)){
			return op;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 奖项申报及审核列表
	 * @return
	 */
	@Action(value="prizeApplyList",results={
			@Result(name="success",location="/union/prizeApplyList.jsp"),
			@Result(name="print",location="/union/prizeApplyList_print.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeApplyList(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		
		String applyMatchId = this.request.getParameter("applyMatchId");
		String op = this.request.getParameter("op");
		UnionApplyMatch applyMatch = applyMatchService.find(applyMatchId);
		String deptId = applyMatch.getDeptId();

		String matchId = applyMatch.getMatchId();
		List<UnionApplyDepartment> deptPrizeList = this.applyDepartmentService.listToApply(matchId,deptId);
		
		UnionMatch match = this.matchService.find(matchId);
		if(match != null){
			UnionTheme theme = this.themeService.find(match.getThemeId());
			this.request.setAttribute("theme", theme);
		}
		
		List<UnionPersonalPrize> appliedPersonalPrizes;
		List<UnionTeamPrize> appliedTeamPrizes;
		List<UnionProjectPrize> appliedProjectPrizes;
		List<UnionAchivementPrize> appliedAchivementPrizes;
		
		if(applyMatch.getStatus()==UnionApplyMatch.DEPT_REVIEW_STATUS || "print".equals(op)){	//部门分管领导按申报人分开审
			appliedPersonalPrizes = this.personalPrizeService.findByApplyId(applyMatchId);
			appliedTeamPrizes = this.teamPrizeService.findByApplyId(applyMatchId);
			appliedProjectPrizes = this.unionProjectPrizeService.findByApplyId(applyMatchId);
			appliedAchivementPrizes = this.unionAchivementPrizeService.findByApplyId(applyMatchId);
		}else{
			appliedPersonalPrizes = this.personalPrizeService.findByDeptOfMatch(matchId, deptId);
			appliedTeamPrizes = this.teamPrizeService.findByDeptOfMatch(matchId, deptId);
			appliedProjectPrizes = this.unionProjectPrizeService.findByDeptOfMatch(matchId, deptId);
			appliedAchivementPrizes = this.unionAchivementPrizeService.findByDeptOfMatch(matchId, deptId);			
		}
		
		List<UnionApproval> approvalInfos = approvalService.findByApplyId(applyMatchId);

		this.request.setAttribute("match", match);
		this.request.setAttribute("deptPrizeList", deptPrizeList);
		this.request.setAttribute("applyMatch", applyMatch);
		this.request.setAttribute("approvalInfos", approvalInfos);
		
		this.request.setAttribute("appliedPersonalPrizes", appliedPersonalPrizes);
		this.request.setAttribute("appliedTeamPrizes", appliedTeamPrizes);
		this.request.setAttribute("appliedProjectPrizes", appliedProjectPrizes);
		this.request.setAttribute("appliedAchivementPrizes", appliedAchivementPrizes);
		
		if(StringUtils.isNotEmpty(op)){
			return op;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 考评部门专项汇总
	 * @return
	 */
	@Action(value="prizeApplySum",results={
			@Result(name="success",location="/union/prizeApplySum.jsp"),
			@Result(name="leader",location="/union/prizeApplySum_leader.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeApplySum(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String matchId = this.request.getParameter("matchId");
		String g = this.request.getParameter("g");
		
		List<UnionPrize> prizeList = this.service.findByMatchId(matchId);
		List<UnionApplyMatch> appliedDepts = applyMatchService.finSubmittedByMatchId(matchId);
		
		UnionMatch match = this.matchService.find(matchId);
		UnionTheme theme = this.themeService.find(match.getThemeId());
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(matchId, UnionApproval.ORG_STAGE);
		
		this.request.setAttribute("match", match);
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("prizeList", prizeList);
		this.request.setAttribute("appliedDepts", appliedDepts);
		this.request.setAttribute("approvalInfos", approvalInfos);
		this.request.setAttribute("allSubmitted", applyMatchService.checkAllSubmitted(matchId));
		
		if(!StringUtils.isEmpty(g)){
			return g;
		}
		
		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/prizeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String themeId = this.request.getParameter("themeId");
		UnionTheme theme = themeService.find(themeId);
		
		this.request.setAttribute("theme", theme);
		return SUCCESS;
	}
	
	@Action(value="showEdit",results={
			@Result(name="success",location="/union/prizeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showEdit(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String id = this.request.getParameter("id");
		UnionPrize prize = service.find(id);
		
		this.request.setAttribute("prize", prize);
		return SUCCESS;
	}
	
	@Action(value="showView",results={
			@Result(name="success",location="/union/prizeView.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showView(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String id = this.request.getParameter("id");
		UnionPrize prize = service.find(id);
		
		this.request.setAttribute("prize", prize);
		return SUCCESS;
	}
	
	@Action(value="showBatchUpload",results={
			@Result(name="success",location="/union/prize/excelUpload.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showBatchUpload(){
		String prizeId = this.request.getParameter("prizeId");
		UnionPrize prize = service.find(prizeId);
		
		this.request.setAttribute("prize", prize);
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);

		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionPrize.getId())){
			this.unionPrize.setcUser(loginName);
			this.unionPrize.setcTime(now);			
		}
		
		this.unionPrize.setuUser(loginName);
		this.unionPrize.setuTime(now);
		unionPrize = this.service.save(unionPrize);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	@Action(value="getApplyDepartments")
	public String getApplyDepartments(){
		
		String prizeId = this.request.getParameter("prizeId");
		
		List<UnionApplyDepartment> applyDepts = this.applyDepartmentService.findByPrizeId(prizeId);
		
		JSONArray jso = JSONArray.fromObject(applyDepts);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="hasQuantityLeft")
	public String hasQuantityLeft(){
		
		String prizeId = this.request.getParameter("prizeId");
		String applyId = this.request.getParameter("applyId");

		UnionApplyMatch applyMatch = this.applyMatchService.find(applyId);
		
		if(this.service.hasQuantityLeft(prizeId, applyMatch.getDeptId())){
			this.aw.writeAjax("1");			
		}else{
			this.aw.writeAjax("0");
		}
		
		return null;
	}
	
	@Action(value="listApplyDepartments",results={
			@Result(name="success",location="/union/applyDeptList.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listApplyDepartments(){
				
		String prizeId = this.request.getParameter("prizeId");
		
		List<UnionApplyDepartment> applyDepts = this.applyDepartmentService.findByPrizeId(prizeId);
		
		List<Map> allDepts = this.service.getDepts();
		
		this.request.setAttribute("applyDepts", applyDepts);
		this.request.setAttribute("allDepts", allDepts);
		this.request.setAttribute("prizeId", prizeId);
		return SUCCESS;
	}
	
/*	@Action(value="saveApplyDept")
	public String saveApplyDept(){
		this.applyDepartmentService.updateQuantity(unionPrize.getApplyDepartmentList());
		
		this.aw.writeAjax("1");
		return null;
	}*/
	
	@Action(value="saveApplyDept")
	public String saveDept(){
		Integer num = Integer.valueOf(StringUtil.getNotNullValueNumber(request.getParameter("num")));
		
		List<UnionApplyDepartment> saveList = new ArrayList<UnionApplyDepartment>();
		for(int i=0;i<num;i++){
			String id = request.getParameter("applyDepartmentList["+i+"].id");
			if(!StringUtil.isNull(id)){
				saveList.add(this.applyDepartmentService.find(id));
			}else{
				saveList.add(new UnionApplyDepartment());
			}
		}
		
		setParams(saveList);
		
		this.applyDepartmentService.saveOrUpdateAll(saveList);
		return null;
	}
	
	@Action(value="delApplyDept")
	public String delApplyDept(){
		String id = StringUtil.getNotNullValueNumber(request.getParameter("id"));

		this.applyDepartmentService.logicDelete(id);
		return null;
	}
	
	@Action(value="rejectByIds")
	public String rejectByIds(){
		UnionProcessUtil.prepareFlowInfo(request, params);
		
		this.service.rejectByIds(params);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	private void setParams(List saveList){
		UnionPrize prize = new UnionPrize();
		prize.setApplyDepartmentList(saveList);
		try {
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String)e.nextElement();
				if(name.startsWith("applyDepartmentList")){
					BeanUtils.setProperty(prize, name, request.getParameter(name));		
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Action(value="showApply",results={
			@Result(name="addPersonalPrize",type = "chain", params= {"namespace","/unionPersonalPrize","actionName","showAdd","method","showAdd"}),
			@Result(name="addTeamPrize",type = "chain", params= {"namespace","/unionTeamPrize","actionName","showAdd","method","showAdd"}),
			@Result(name="addProjectPrize",type = "chain", params= {"namespace","/unionProjectPrize","actionName","showAdd","method","showAdd"}),
			@Result(name="addProjectAchivementPrize",type = "chain", params= {"namespace","/unionAchivementPrize","actionName","showAdd","method","showAdd"}),
			@Result(name="error",location="/404.jsp")
			})
	public String showApply(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String applyId = this.request.getParameter("applyId");
		String prizeId = this.request.getParameter("prizeId");
		UnionPrize prize = service.find(prizeId);
		
        String forward = "";

        if(UnionPrize.PRIZE_TYPE_PERSON.equals(prize.getPrizeType())){
            forward = "addPersonalPrize";
        }else if(UnionPrize.PRIZE_TYPE_TEAM.equals(prize.getPrizeType())){
            forward = "addTeamPrize";
        }else if(UnionPrize.PRIZE_TYPE_PROJECT.equals(prize.getPrizeType())){
            forward = "addProjectPrize";
        }else if(UnionPrize.PRIZE_TYPE_PROJECT_ACHIEVEMENT.equals(prize.getPrizeType())){
            forward = "addProjectAchivementPrize";
        }
        
        this.request.setAttribute("prize", prize);
        this.request.setAttribute("applyId", applyId);
        
        return forward;
	}
	
	@Action(value="prizeAppliedByDept",results={
			@Result(name="success",location="/union/prizeAppliedByDept.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeAppliedByDept(){
		String matchId = this.request.getParameter("matchId");
		String deptId = this.request.getParameter("deptId");
		
		UnionMatch match = this.matchService.find(matchId);
		
		List<UnionApplyDepartment> deptPrizeList = this.applyDepartmentService.listToApply(matchId,deptId);
		
		List<UnionPersonalPrize> appliedPersonalPrizes = this.personalPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionTeamPrize> appliedTeamPrizes = this.teamPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionProjectPrize> appliedProjectPrizes = this.unionProjectPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionAchivementPrize> appliedAchivementPrizes = this.unionAchivementPrizeService.findByDeptOfMatch(matchId, deptId);

		List<UnionApproval> approvalInfos = approvalService.findByMatchAndDeptId(matchId, deptId);
		
		this.request.setAttribute("match", match);
		this.request.setAttribute("deptPrizeList", deptPrizeList);
		this.request.setAttribute("appliedPersonalPrizes", appliedPersonalPrizes);
		this.request.setAttribute("appliedTeamPrizes", appliedTeamPrizes);
		this.request.setAttribute("appliedProjectPrizes", appliedProjectPrizes);
		this.request.setAttribute("appliedAchivementPrizes", appliedAchivementPrizes);
		this.request.setAttribute("approvalInfos", approvalInfos);
		
		return SUCCESS;
	}
	
/*	@Action(value="prizeApplyPrintOfDept",results={
			@Result(name="success",location="/union/prizeApplyPrint_dept.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeApplyPrintOfDept(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String matchId = this.request.getParameter("matchId");
		UnionMatch match = this.matchService.find(matchId);
		UnionTheme theme = themeService.find(match.getThemeId());
		
		List<UnionPersonalPrize> appliedPersonalPrizes = this.personalPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionTeamPrize> appliedTeamPrizes = this.teamPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionProjectPrize> appliedProjectPrizes = this.unionProjectPrizeService.findByDeptOfMatch(matchId, deptId);
		List<UnionAchivementPrize> appliedAchivementPrizes = this.unionAchivementPrizeService.findByDeptOfMatch(matchId, deptId);
		
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("match", match);
		this.request.setAttribute("appliedPersonalPrizes", appliedPersonalPrizes);
		this.request.setAttribute("appliedTeamPrizes", appliedTeamPrizes);
		this.request.setAttribute("appliedProjectPrizes", appliedProjectPrizes);
		this.request.setAttribute("appliedAchivementPrizes", appliedAchivementPrizes);
		
		return SUCCESS;
	}*/
	
	@Action(value="prizeApplyPrintOfMatch",results={
			@Result(name="success",location="/union/prizeApplyPrint_match.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeApplyPrintOfMatch(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String matchId = this.request.getParameter("matchId");
		UnionMatch match = this.matchService.find(matchId);
		UnionTheme theme = themeService.find(match.getThemeId());
		
		List<UnionPersonalPrize> appliedPersonalPrizes = this.personalPrizeService.findByMatchId(matchId);
		List<UnionTeamPrize> appliedTeamPrizes = this.teamPrizeService.findByMatchId(matchId);
		List<UnionProjectPrize> appliedProjectPrizes = this.unionProjectPrizeService.findByMatchId(matchId);
		List<UnionAchivementPrize> appliedAchivementPrizes = this.unionAchivementPrizeService.findByMatchId(matchId);
		
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("match", match);
		this.request.setAttribute("appliedPersonalPrizes", appliedPersonalPrizes);
		this.request.setAttribute("appliedTeamPrizes", appliedTeamPrizes);
		this.request.setAttribute("appliedProjectPrizes", appliedProjectPrizes);
		this.request.setAttribute("appliedAchivementPrizes", appliedAchivementPrizes);
		
		return SUCCESS;
	}
	
	@Action(value="prizeApplyPrintOfTheme",results={
			@Result(name="success",location="/union/prizeApplyPrint_theme.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String prizeApplyPrintOfTheme(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String themeId = this.request.getParameter("themeId");
		UnionTheme theme = themeService.find(themeId);
		
		List<UnionPersonalPrize> appliedPersonalPrizes = this.personalPrizeService.findByThemeId(themeId);
		List<UnionTeamPrize> appliedTeamPrizes = this.teamPrizeService.findByThemeId(themeId);
		List<UnionProjectPrize> appliedProjectPrizes = this.unionProjectPrizeService.findByThemeId(themeId);
		List<UnionAchivementPrize> appliedAchivementPrizes = this.unionAchivementPrizeService.findByThemeId(themeId);
		
		Map<String,Map<String,List>> matchMap = new HashMap<String,Map<String,List>>();
		for(UnionPersonalPrize personalPrize : appliedPersonalPrizes){
			if(!matchMap.containsKey(personalPrize.getPrize().getMatch().getId())){
				matchMap.put(personalPrize.getPrize().getMatch().getId(), new HashMap<String,List>());
			}
			Map<String,List> prizeMap = matchMap.get(personalPrize.getPrize().getMatch().getId());
			if(!prizeMap.containsKey("appliedPersonalPrizes")){
				prizeMap.put("appliedPersonalPrizes", new ArrayList<UnionPersonalPrize>());
			}
			prizeMap.get("appliedPersonalPrizes").add(personalPrize);
		}
		for(UnionTeamPrize teamPrize : appliedTeamPrizes){
			if(!matchMap.containsKey(teamPrize.getPrize().getMatch().getId())){
				matchMap.put(teamPrize.getPrize().getMatch().getId(), new HashMap<String,List>());
			}
			Map<String,List> prizeMap = matchMap.get(teamPrize.getPrize().getMatch().getId());
			if(!prizeMap.containsKey("appliedTeamPrizes")){
				prizeMap.put("appliedTeamPrizes", new ArrayList<UnionTeamPrize>());
			}
			prizeMap.get("appliedTeamPrizes").add(teamPrize);
		}
		for(UnionProjectPrize projectPrize : appliedProjectPrizes){
			if(!matchMap.containsKey(projectPrize.getPrize().getMatch().getId())){
				matchMap.put(projectPrize.getPrize().getMatch().getId(), new HashMap<String,List>());
			}
			Map<String,List> prizeMap = matchMap.get(projectPrize.getPrize().getMatch().getId());
			if(!prizeMap.containsKey("appliedProjectPrizes")){
				prizeMap.put("appliedProjectPrizes", new ArrayList<UnionProjectPrize>());
			}
			prizeMap.get("appliedProjectPrizes").add(projectPrize);
		}
		for(UnionAchivementPrize achivementPrize : appliedAchivementPrizes){
			if(!matchMap.containsKey(achivementPrize.getPrize().getMatch().getId())){
				matchMap.put(achivementPrize.getPrize().getMatch().getId(), new HashMap<String,List>());
			}
			Map<String,List> prizeMap = matchMap.get(achivementPrize.getPrize().getMatch().getId());
			if(!prizeMap.containsKey("appliedAchivementPrizes")){
				prizeMap.put("appliedAchivementPrizes", new ArrayList<UnionAchivementPrize>());
			}
			prizeMap.get("appliedAchivementPrizes").add(achivementPrize);
		}
		List<UnionMatch> matchList = this.matchService.findByThemeId(themeId);
		this.request.setAttribute("matchMap", matchMap);
		this.request.setAttribute("matchList", matchList);
		
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("appliedPersonalPrizes", appliedPersonalPrizes);
		this.request.setAttribute("appliedTeamPrizes", appliedTeamPrizes);
		this.request.setAttribute("appliedProjectPrizes", appliedProjectPrizes);
		this.request.setAttribute("appliedAchivementPrizes", appliedAchivementPrizes);
		
		return SUCCESS;
	}
	
	@Action(value="doDel")
	public String doDel(){
		String id = StringUtil.getNotNullValueNumber(request.getParameter("id"));
		this.service.logicDelete(id);
		return null;
	}
	
	/**
	 * 下载模板
	 * @throws IOException 
	 */
	@Action(value="downloadTemplete")
	public void downloadTemplete() throws IOException {
		String fileName = request.getParameter("fileName");
		String fullPath = request.getSession().getServletContext().getRealPath("/union/prize/template")+ File.separator + fileName;
		InputStream is = null;
		try {
			is = new FileInputStream(fullPath);

			int len = 0;
			byte[] buffers = new byte[1024];
			this.response.setCharacterEncoding("utf-8");
			this.response.reset();
			this.response.setContentType("application/x-msdownload");

			this.response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream os = this.response.getOutputStream();
			// 把文件内容通过输出流打印到页面上供下载
			while ((len = is.read(buffers)) != -1) {
				os.write(buffers, 0, len);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private UnionPrizeService service;
	public UnionPrizeService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionPrizeService")UnionPrizeService service) {
		this.service = service;
	}
	
	private UnionThemeService themeService;
	public UnionThemeService getThemeService() {
		return themeService;
	}
	@Autowired(required=false)
	public void setThemeService(@Qualifier("unionThemeService")UnionThemeService themeService) {
		this.themeService = themeService;
	}

	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}

	private UnionApplyDepartmentService applyDepartmentService;
	public UnionApplyDepartmentService getApplyDepartmentService() {
		return applyDepartmentService;
	}
	@Autowired(required=false)
	public void setApplyDepartmentService(@Qualifier("unionApplyDepartmentService")UnionApplyDepartmentService applyDepartmentService) {
		this.applyDepartmentService = applyDepartmentService;
	}
	
	private UnionPersonalPrizeService personalPrizeService;
	public UnionPersonalPrizeService getPersonalPrizeService() {
		return personalPrizeService;
	}
	@Autowired(required=false)
	public void setPersonalPrizeService(@Qualifier("unionPersonalPrizeService")UnionPersonalPrizeService personalPrizeService) {
		this.personalPrizeService = personalPrizeService;
	}
	
	private UnionTeamPrizeService teamPrizeService;
	public UnionTeamPrizeService getTeamPrizeService() {
		return teamPrizeService;
	}
	@Autowired(required=false)
	public void setTeamPrizeService(@Qualifier("unionTeamPrizeService")UnionTeamPrizeService teamPrizeService) {
		this.teamPrizeService = teamPrizeService;
	}

	public UnionPrize getUnionPrize() {
		return unionPrize;
	}

	private UnionApplyMatchService applyMatchService;
	public UnionApplyMatchService getApplyMatchService() {
		return applyMatchService;
	}
	@Autowired(required=false)
	public void setApplyMatchService(@Qualifier("unionApplyMatchService")UnionApplyMatchService applyMatchService) {
		this.applyMatchService = applyMatchService;
	}
	
	private UnionApprovalService approvalService;
	public UnionApprovalService getApprovalService() {
		return approvalService;
	}
	@Autowired(required=false)
	public void setApprovalService(@Qualifier("unionApprovalService")UnionApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	private UnionProjectPrizeService unionProjectPrizeService;
	public UnionProjectPrizeService getUnionProjectPrizeService() {
		return unionProjectPrizeService;
	}
	@Autowired(required=false)
	public void setUnionProjectPrizeService(@Qualifier("unionProjectPrizeService")UnionProjectPrizeService unionProjectPrizeService) {
		this.unionProjectPrizeService = unionProjectPrizeService;
	}
	
	private UnionAchivementPrizeService unionAchivementPrizeService;
	public UnionAchivementPrizeService getUnionAchivementPrizeService() {
		return unionAchivementPrizeService;
	}
	@Autowired(required=false)
	public void setUnionAchivementPrizeService(@Qualifier("unionAchivementPrizeService")UnionAchivementPrizeService unionAchivementPrizeService) {
		this.unionAchivementPrizeService = unionAchivementPrizeService;
	}
	
	
}
