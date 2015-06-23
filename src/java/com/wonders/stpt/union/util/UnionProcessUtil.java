/**
 * 
 */
package com.wonders.stpt.union.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.operation.util.HttpRequestHelper;
import com.wonders.stpt.todoItem.util.ConfigUtil;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.util.ConfigDbUtil;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: ProcessUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-2-17 下午01:45:50 
 *  
 */
public class UnionProcessUtil {
	private static final String domainName = "ST/";
	private static final String urlAddTask = ConfigUtil.getValueByKey("config.properties", "urlAddTask");
	private static final String urlUpdateTask = ConfigUtil.getValueByKey("config.properties", "urlUpdateTask");
	
	public static void prepareFlowInfo(HttpServletRequest request, UnionFlowInfo params){
		
		String loginName = StringUtil.getNotNullValueString(request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME));
		String userName = StringUtil.getNotNullValueString(request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME));
		String deptId = StringUtil.getNotNullValueString(request.getSession().getAttribute(LoginConstant.USER_DEPT_ID));
		String deptName = StringUtil.getNotNullValueString(request.getSession().getAttribute(LoginConstant.USER_DEPT_NAME));
		
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		String choice = StringUtil.getNotNullValueString(request.getParameter("choice"));
		String remark = StringUtil.getNotNullValueString(request.getParameter("remark"));
		
		String status = StringUtil.getNotNullValueString(request.getParameter("status"));
		String checkRole = StringUtil.getNotNullValueString(request.getParameter("checkRole"));
		
		String handlerId = StringUtil.getNotNullValueString(request.getParameter("handlerId"));
		String handlerName = StringUtil.getNotNullValueString(request.getParameter("handlerName"));		
		
		String applyId = StringUtil.getNotNullValueString(request.getParameter("applyId"));
		String themeId = StringUtil.getNotNullValueString(request.getParameter("themeId"));
		
		String applyDeptsId = StringUtil.getNotNullValueString(request.getParameter("applyDeptsId"));
		String applyDeptsName = StringUtil.getNotNullValueString(request.getParameter("applyDeptsName"));
		String applyUsersName = StringUtil.getNotNullValueString(request.getParameter("applyUsersName"));
		String applyLoginsName = StringUtil.getNotNullValueString(request.getParameter("applyLoginsName"));
		
		String personalPrizeIds = StringUtil.getNotNullValueString(request.getParameter("personalPrizeIds"));
		String teamPrizeIds = StringUtil.getNotNullValueString(request.getParameter("teamPrizeIds"));
		String projectPrizeIds = StringUtil.getNotNullValueString(request.getParameter("projectPrizeIds"));
		String achivementPrizeIds = StringUtil.getNotNullValueString(request.getParameter("achivementPrizeIds"));
		
		String prizeId = StringUtil.getNotNullValueString(request.getParameter("prizeId"));
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
		
		params.setId(id);
		params.setLoginName(loginName);
		params.setUserName(userName);
		params.setDeptId(deptId);
		params.setDeptName(deptName);
		params.setStatus(StringUtils.isNotEmpty(status)?Integer.valueOf(status):-1);
		params.setChoice(choice);
		params.setRemark(remark);
		params.setCheckRole(checkRole);
		params.setHandlerId(handlerId);
		params.setHandlerName(handlerName);
		params.setApplyId(applyId);
		params.setApplyDeptsId(applyDeptsId);
		params.setApplyDeptsName(applyDeptsName);
		params.setApplyUsersName(applyUsersName);
		params.setApplyLoginsName(applyLoginsName);
		params.setBasePath(basePath);
		params.setPersonalPrizeIds(personalPrizeIds);
		params.setTeamPrizeIds(teamPrizeIds);
		params.setProjectPrizeIds(projectPrizeIds);
		params.setAchivementPrizeIds(achivementPrizeIds);
		params.setThemeId(themeId);
		params.setPrizeId(prizeId);
	}
	
	public static String addTodo(String loginName, String url, String title, String stepName){
		Map param = new HashMap();
		
		param.put("app", "Union");
		param.put("type", "0");
		param.put("occurTime", DateUtil.getNowTime());
		param.put("title", title+"-"+stepName);
		param.put("loginName", domainName+loginName);
		param.put("status", "0");
		param.put("removed", "0");
		param.put("typename", "工会立功竞赛");
		try {
			param.put("url", URLEncoder.encode(url,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.put("pname", "工会立功竞赛");
		param.put("pincident", "1");
		param.put("cname", "子流程实例号");
		param.put("cincident", "1");
		param.put("stepName", stepName);
		param.put("initiator", domainName+loginName);
		
		String pJson = JSONObject.fromObject(param).toString();
		String result = HttpRequestHelper.portalService(pJson, urlAddTask);
		if("".equals(result) || "error".equals(result)){
			System.out.println("添加工会立功竞赛待办失败!");
		}else{
			sendSMSNotice(loginName.substring(0, 12),"业务待办提醒，摘要:"+title+"-"+stepName);
		}
		return result;
	}
	
	public static void sendSMSNotice(String loginName, String content){
		String queryMobileSql = "select cs.mobile1 from cs_user cs,t_cs_user tcs where cs.id = tcs.id and tcs.msg_notice = '1' and cs.removed=0 and cs.login_name = :loginName";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ConfigDbUtil.getJdbcTemplate("dataSource2"));
		Map<String, Object> paramMap = new HashMap<String, Object>();  
		paramMap.put("loginName", loginName);  
		List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(queryMobileSql, paramMap);
		if(list.size() > 0){
			String mobile = (String)list.get(0).get("mobile1");
			if(StringUtils.isNotEmpty(mobile)){
				String insertSMSSql = "insert into t_short_msg(id,status,mobile,content,insertdate) values(sys_guid(),0,:mobile,:content,TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss'))";
				paramMap.put("mobile", mobile);
				paramMap.put("content", content);
				namedParameterJdbcTemplate.update(insertSMSSql, paramMap);				
			}
		}
	}
	
	public static void clearTodo(String todoId){
		HttpRequestHelper.portalService(todoId, urlUpdateTask);
	}
	
	public static String getStep(int step, int stage){
		String stepName = "";
		if(stage == UnionApproval.ORG_STAGE){
			switch(step){
				case UnionMatch.MATCH_NEW_STATUS:
					stepName = "创建专项";
					break;		
				case UnionMatch.MATCH_REVIEW_STATUS:
					stepName = "专项立项审批";
					break;				
				case UnionMatch.PRIZE_SET_STATUS:
					stepName = "预算填报";
					break;	
				case UnionMatch.PRIZE_SET_DEPT_REVIEW_STATUS:
					stepName = "考评部门领导预算审批";
					break;
				case UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS:
					stepName = "预算一审";
					break;								
				case UnionMatch.PRIZE_SET_APPROVE_STATUS:
					stepName = "竞赛办预算审批";
					break;							
				case UnionMatch.PRIZE_SET_SECOND_REVIEW_STATUS:
					stepName = "预算二审";
					break;					
				case UnionMatch.PRIZE_ASSIGN_STATUS:
					stepName = "奖项分配";
					break;
				case UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS:
					stepName = "参赛单位申报人设置";
					break;			
				case UnionMatch.PRIZE_ASSIGN_DEPT_REVIEW_STATUS:
					stepName = "考评部门领导名额分配审批";
					break;				
				case UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS:
					stepName = "名额分配初审";
					break;
				case UnionMatch.PRIZE_ASSIGN_APPROVE_STATUS:
					stepName = "竞赛办名额分配审批";
					break;
				case UnionMatch.ASSESS_SUM_STATUS:
					stepName = "考评小组预审";
					break;					
				case UnionMatch.ASSESS_SUM_REVIEW_STATUS:
					stepName = "考评小组领导审核";
					break;
				case UnionMatch.LEAD_SUM_STATUS:
					stepName = "领导小组初审";
					break;										
				case UnionMatch.MODIFY_STATUS:
					stepName = "返回修改";
					break;		
			}			
		}else if(stage == UnionApproval.APPLY_STAGE){
			switch(step){
				case UnionApplyMatch.APPLY_STATUS:
					stepName = "申报奖项";
					break;		
				case UnionApplyMatch.DEPT_REVIEW_STATUS:
					stepName = "申报奖项分管领导审核";
					break;						
				case UnionApplyMatch.ASSESS_STATUS:
					stepName = "考评部门奖项初审";
					break;			
				case UnionApplyMatch.MODIFY_STATUS:
					stepName = "返回修改";
					break;							
			}
		}else if(stage == UnionApproval.APPROVE_STAGE){
			switch(step){
			case UnionTheme.SUM_STATUS:
				stepName = "竞赛初审";
				break;		
			case UnionTheme.DEPT_LEADER_APPROVE_STATUS:
				stepName = "部门领导竞赛审核";
				break;						
			case UnionTheme.GROUP_LEADER_APPROVE_STATUS:
				stepName = "集团领导竞赛审核";
				break;								
			}
		}
		return stepName;
	}
	
	public static String getTodoStep(int step, int stage){
		String stepName = "";
		if(stage == UnionApproval.ORG_STAGE){
			switch(step){
				case UnionMatch.MATCH_NEW_STATUS:
					stepName = "创建专项";
					break;		
				case UnionMatch.MATCH_REVIEW_STATUS:
					stepName = "专项立项审批";
					break;				
				case UnionMatch.PRIZE_SET_STATUS:
					stepName = "确认预算";
					break;	
				case UnionMatch.PRIZE_SET_DEPT_REVIEW_STATUS:
					stepName = "确认预算";
					break;
				case UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS:
					stepName = "确认预算";
					break;								
				case UnionMatch.PRIZE_SET_APPROVE_STATUS:
					stepName = "确认预算";
					break;							
				case UnionMatch.PRIZE_SET_SECOND_REVIEW_STATUS:
					stepName = "确认预算";
					break;					
				case UnionMatch.PRIZE_ASSIGN_STATUS:
					stepName = "名额分配";
					break;
				case UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS:
					stepName = "名额分配";
					break;			
				case UnionMatch.PRIZE_ASSIGN_DEPT_REVIEW_STATUS:
					stepName = "名额分配";
					break;				
				case UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS:
					stepName = "名额分配";
					break;
				case UnionMatch.PRIZE_ASSIGN_APPROVE_STATUS:
					stepName = "名额分配";
					break;
				case UnionMatch.ASSESS_SUM_STATUS:
					stepName = "名单初审";
					break;					
				case UnionMatch.ASSESS_SUM_REVIEW_STATUS:
					stepName = "名单初审";
					break;
				case UnionMatch.LEAD_SUM_STATUS:
					stepName = "名单审核";
					break;										
				case UnionMatch.MODIFY_STATUS:
					stepName = "返回修改";
					break;		
			}			
		}else if(stage == UnionApproval.APPLY_STAGE){
			switch(step){
				case UnionApplyMatch.APPLY_STATUS:
					stepName = "申报奖项";
					break;		
				case UnionApplyMatch.DEPT_REVIEW_STATUS:
					stepName = "名单初审";
					break;						
				case UnionApplyMatch.ASSESS_STATUS:
					stepName = "名单初审";
					break;			
				case UnionApplyMatch.MODIFY_STATUS:
					stepName = "返回修改";
					break;							
			}
		}else if(stage == UnionApproval.APPROVE_STAGE){
			switch(step){
			case UnionTheme.SUM_STATUS:
				stepName = "名单审核";
				break;		
			case UnionTheme.DEPT_LEADER_APPROVE_STATUS:
				stepName = "名单审核";
				break;						
			case UnionTheme.GROUP_LEADER_APPROVE_STATUS:
				stepName = "名单审核";
				break;								
			}
		}
		return stepName;
	}
}
