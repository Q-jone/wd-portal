package com.wonders.stpt.project.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TWorkEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_WORK_EVENT", schema = "STPTDEMO")
public class WorkEvent implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 * 单位名称
	 */
	private String companyName;
	/**
	 * 报告时间
	 */
	private Date telTime;
	/**
	 * 事件发生时间
	 */
	private Date beginTime;
	/**
	 * 信息来源
	 */
	private String messageSource;
	/**
	 * 业务分类
	 */
	private String classification;
	/**
	 * 事件类型
	 */
	private String eventType;
	/**
	 * 涉及网络或系统
	 */
	private String networkSystem;
	/**
	 * 事件描述
	 */
	private String descriptions;
	/**
	 * 事件等级
	 */
	private String ranks;
	/**
	 * 事件原因分析
	 */
	private String reasons;
	/**
	 * 处理过程
	 */
	private String process;
	/**
	 * 处理结果
	 */
	private String results;
	/**
	 * 事件报告人
	 */
	private String reporter;
	/**
	 * 联系方式
	 */
	private String telphone;
	/**
	 * 备注
	 */
	private String memo;
	private Date createTime;
	private String creator;
	private Date updateTime;
	private String updator;
	private String removed;

	// Constructors

	/** default constructor */
	public WorkEvent() {
		removed="0";
		createTime=new Date();
		updateTime=new Date();
	}

	/** minimal constructor */
	public WorkEvent(String id) {
		this.id = id;
	}

	/** full constructor */
	public WorkEvent(String id, String companyName, Date telTime,
			Date beginTime, String messageSource, String classification,
			String eventType, String networkSystem, String descriptions,
			String ranks, String reasons, String process, String results,
			String reporter, String telphone, String memo, Date createTime,
			String creator, Date updateTime, String updator, String removed) {
		this.id = id;
		this.companyName = companyName;
		this.telTime = telTime;
		this.beginTime = beginTime;
		this.messageSource = messageSource;
		this.classification = classification;
		this.eventType = eventType;
		this.networkSystem = networkSystem;
		this.descriptions = descriptions;
		this.ranks = ranks;
		this.reasons = reasons;
		this.process = process;
		this.results = results;
		this.reporter = reporter;
		this.telphone = telphone;
		this.memo = memo;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.removed = removed;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "COMPANY_NAME", length = 100)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TEL_TIME")
	public Date getTelTime() {
		return this.telTime;
	}

	public void setTelTime(Date telTime) {
		this.telTime = telTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_TIME")
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "MESSAGE_SOURCE", length = 100)
	public String getMessageSource() {
		return this.messageSource;
	}

	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}

	@Column(name = "CLASSIFICATION", length = 100)
	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Column(name = "EVENT_TYPE", length = 100)
	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Column(name = "NETWORK_SYSTEM", length = 100)
	public String getNetworkSystem() {
		return this.networkSystem;
	}

	public void setNetworkSystem(String networkSystem) {
		this.networkSystem = networkSystem;
	}

	@Column(name = "DESCRIPTIONS", length = 600)
	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	@Column(name = "RANKS", length = 100)
	public String getRanks() {
		return this.ranks;
	}

	public void setRanks(String ranks) {
		this.ranks = ranks;
	}

	@Column(name = "REASONS", length = 100)
	public String getReasons() {
		return this.reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	@Column(name = "PROCESS", length = 100)
	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Column(name = "RESULTS", length = 600)
	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Column(name = "REPORTER", length = 100)
	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Column(name = "TELPHONE", length = 15)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "MEMO", length = 600)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATOR", length = 100)
	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

}