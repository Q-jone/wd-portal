package com.wonders.stpt.union.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "U_THEME")
public class UnionTheme implements Serializable{
	
    public static final int NEW_STATUS = 0; // 工会新建
    public static final int SUM_STATUS = 1; // 工会汇总
    public static final int DEPT_LEADER_APPROVE_STATUS = 2; // 待工会领导审核
    public static final int GROUP_LEADER_APPROVE_STATUS = 3; // 待集团领导审核
    public static final int PASS_STATUS = 4; // 通过

    private String id;
    private Integer year;
    private String themeName;
    private long removed= 0l;
    private int status = 0;
    
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
    
    private String todoId;
    private String handlerId;
    private String handlerName;
    
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "YEAR", nullable = true, length = 4)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "THEME_NAME", nullable = true, length = 200)
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    
    @Column(name = "removed", nullable = true, length = 1)
	public long getRemoved() {
		return removed;
	}
	public void setRemoved(long removed) {
		this.removed = removed;
	}
    
	@Column(name = "c_user", nullable = true, length = 40)
	public String getcUser() {
		return cUser;
	}
	public void setcUser(String cUser) {
		this.cUser = cUser;
	}
	@Column(name = "c_time", nullable = true, length = 40)
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	@Column(name = "u_user", nullable = true, length = 40)
	public String getuUser() {
		return uUser;
	}
	public void setuUser(String uUser) {
		this.uUser = uUser;
	}
	@Column(name = "u_time", nullable = true, length = 40)
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	
	@Column(name = "STATUS", nullable = true, length = 1)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "TODO_ID", nullable = true, length = 40)
	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	@Column(name = "HANDLER_ID", nullable = true, length = 40)
	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	@Column(name = "HANDLER_NAME", nullable = true, length = 40)
	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
}
