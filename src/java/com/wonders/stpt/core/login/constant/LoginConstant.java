package com.wonders.stpt.core.login.constant;

public class LoginConstant {

	/**
	 * Session中的t_user登录名
	 */
	public static final String STPT_SECURITY_LOGIN_NAME = "t_login_name";

	/**
	 * Session中的t_user登录用户对象
	 */
	public static final String STPT_SECURITY_LOGIN_USER = "t_user";
	
	/**
	 * Session中的t_user登录用户对象 包含部门 和 用户 MAP<organnode,user>
	 */
	public static final String STPT_SECURITY_USER_SELECT = "t_user_select";

	
	
	/**
	 * Session中的cs_user的登录名（工号）

	 */
	public static final String SECURITY_LOGIN_NAME_WITHOUT_DEPTID = "cs_login_name";
		
	/**
	 * Session中的cs_user的loginName（工号+部门ID）

	 */
	public static final String SECURITY_LOGIN_NAME = "loginName";

	/**
	 * Session中的cs_user登录用户ID
	 */
	public static final String SECURITY_USER_ID = "userId";
	
	/**
	 * Session中的登录用户中文名称
	 */
	public static final String SECURITY_USER_NAME = "userName";
	
	
	/**
	 * Session中的cs_user登录用户对象
	 */
	public static final String SECURITY_LOGIN_USER = "user";
	
	/**
	 * Session中的登录用户密码
	 */
	public static final String SECURITY_LOGIN_PASSWORD = "password";

	
	/**
	 * Session中的cs_user登录用户部门ID
	 */
	public static final String USER_DEPT_ID = "deptId";
	
	/**
	 * Session中的cs_user登录用户部门名称
	 */
	public static final String USER_DEPT_NAME = "deptName";
	
	

	/**
	 * Session中的t_user登录用户所有部门VO
	 */
	public static final String USER_DEPT_LIST = "deptList";
	
	/**
	 * Session中的t_user登录用户部门map  部门ID 该部门USER对象
	 */
	public static final String USER_DEPT_MAP = "deptMap";

    /**
     * session t_user 中 是否跟踪记录
     */
    public static final String USER_TRACK_STATUS = "trackStatus";
	
}
