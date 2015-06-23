/**
 * 
 */
package com.wonders.stpt.union.entity.vo;

/** 
 * @ClassName: UnionFlowInfo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-22 下午02:26:39 
 *  
 */
public class UnionFlowInfo {
	
	private String loginName;//工号
	private String userName;//姓名
	private String deptId;//部门ID
	private String deptName;//部门
	private String handlerId;//下一步处理人ID
	private String handlerName;//下一步处理人姓名
	
	private String id;//MATCH ID
	private String choice;//同意/不同意
	private String remark;//意见
	private int status;//流程当前状态
	private String checkRole;//操作人角色
	
	private String applyId;
	private String applyDeptsId;
	private String applyDeptsName;
	private String applyUsersName;
	private String applyLoginsName;
	
	private String themeId;
	
	private String personalPrizeIds;
	private String teamPrizeIds;
	private String projectPrizeIds;
	private String achivementPrizeIds;
	
	private String basePath;
	
	private String prizeId;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCheckRole() {
		return checkRole;
	}
	public void setCheckRole(String checkRole) {
		this.checkRole = checkRole;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getHandlerId() {
		return handlerId;
	}
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getApplyDeptsId() {
		return applyDeptsId;
	}
	public void setApplyDeptsId(String applyDeptsId) {
		this.applyDeptsId = applyDeptsId;
	}
	public String getApplyDeptsName() {
		return applyDeptsName;
	}
	public void setApplyDeptsName(String applyDeptsName) {
		this.applyDeptsName = applyDeptsName;
	}
	public String getApplyUsersName() {
		return applyUsersName;
	}
	public void setApplyUsersName(String applyUsersName) {
		this.applyUsersName = applyUsersName;
	}
	public String getApplyLoginsName() {
		return applyLoginsName;
	}
	public void setApplyLoginsName(String applyLoginsName) {
		this.applyLoginsName = applyLoginsName;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getPersonalPrizeIds() {
		return personalPrizeIds;
	}
	public void setPersonalPrizeIds(String personalPrizeIds) {
		this.personalPrizeIds = personalPrizeIds;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getTeamPrizeIds() {
		return teamPrizeIds;
	}
	public void setTeamPrizeIds(String teamPrizeIds) {
		this.teamPrizeIds = teamPrizeIds;
	}
	public String getProjectPrizeIds() {
		return projectPrizeIds;
	}
	public void setProjectPrizeIds(String projectPrizeIds) {
		this.projectPrizeIds = projectPrizeIds;
	}
	public String getAchivementPrizeIds() {
		return achivementPrizeIds;
	}
	public void setAchivementPrizeIds(String achivementPrizeIds) {
		this.achivementPrizeIds = achivementPrizeIds;
	}
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

}
