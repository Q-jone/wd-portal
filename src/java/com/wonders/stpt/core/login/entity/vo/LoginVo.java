
package com.wonders.stpt.core.login.entity.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wondersgroup.framework.core.web.vo.impl.DefaultViewModel;

/**
 * 登录对象
 * 
 * @author zhoushun
 * @version 
 */
public class LoginVo{
	/**
	 * 登录名
	 */
	private String loginName;

	private String cloginName;
	/**
	 * 登录密码
	 */
	private String password;

	private String operationTime;
	
	private String validate;
	
	private Tuser tuser;
	
	private String deptId;
	
	
	

	public String getCloginName() {
		return cloginName;
	}

	public void setCloginName(String cloginName) {
		this.cloginName = cloginName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	public LoginVo() {
  		super();
	}
	
	

}
