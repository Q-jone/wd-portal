package com.wonders.stpt.ThemeDatabase.model.bo;

// Generated 2013-11-26 13:24:44 by Hibernate Tools 3.4.0.CR1

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * GreataProjectLine generated by hbm2java
 */
@Entity
@Table(name = "GREATA_PROJECT_LINE")
@XmlRootElement(name="ProjectLine")
@XmlAccessorType(XmlAccessType.NONE)
public class GreataProjectLine implements java.io.Serializable {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	/** */
	/**
	 */
	private String id;
	/** */
	/**
	 */
	@XmlElement(name="PlatformID")
	private String oaId;
	/** */
	/**
	 */
	@XmlElement(name="ProjectLineCode")
	private String lineCode;
	/** */
	/**
	 */
	@XmlElement(name="ProjectLineName")
	private String lineName;
	/** */
	/**
	 */
	private Integer removed = 0;
	/** */
	/**
	 */
	@XmlElement(name="ProjectLineID")
	private String lineId;
	/** */
	/**
	 */
	private String createTime = df.format(new Date());

	public GreataProjectLine() {
	}

	public GreataProjectLine(String id, String lineId) {
		this.id = id;
		this.lineId = lineId;
	}

	public GreataProjectLine(String id, String oaId, String lineCode,
			String lineName, Integer removed, String lineId, String createTime) {
		this.id = id;
		this.oaId = oaId;
		this.lineCode = lineCode;
		this.lineName = lineName;
		this.removed = removed;
		this.lineId = lineId;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "OA_ID", length = 50)
	public String getOaId() {
		return this.oaId;
	}

	public void setOaId(String oaId) {
		this.oaId = oaId;
	}

	@Column(name = "LINE_CODE", length = 200)
	public String getLineCode() {
		return this.lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	@Column(name = "LINE_NAME", length = 200)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "REMOVED", precision = 5, scale = 0)
	public Integer getRemoved() {
		return this.removed;
	}

	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

	@Column(name = "LINE_ID", nullable = false, length = 50)
	public String getLineId() {
		return this.lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	@Column(name = "CREATE_TIME", length = 100)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}