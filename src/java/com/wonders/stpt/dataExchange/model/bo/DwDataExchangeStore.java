package com.wonders.stpt.dataExchange.model.bo;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DwDataExchangeStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DW_DATA_EXCHANGE_STORE")
public class DwDataExchangeStore implements java.io.Serializable {

	// Fields

	private String id;
	private String dataDate;
	private String dataType;
	private String dataFormat;	
	private String storePath;	
	private String content;
	private Timestamp createTime;
	private Timestamp operateTime;	
	private Long valid;
	// Constructors

	/** default constructor */
	public DwDataExchangeStore() {
	}

	/** full constructor */
	public DwDataExchangeStore(String dataDate, String dataType, String dataFormat, String storePath, 
			String content,Timestamp createTime, Long valid) {
		this.dataDate = dataDate;
		this.dataType = dataType;		
		this.dataFormat = dataFormat;
		this.storePath = storePath;
		this.content = content;
		this.createTime = createTime;
		this.valid = valid;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DATA_DATE", length = 20)
	public String getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	
	@Column(name = "DATA_TYPE", length = 20)
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name = "DATA_FORMAT", length = 10)
	public String getDataFormat() {
		return this.dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	@Column(name = "STORE_Path", length = 100)
	public String getStorePath() {
		return this.storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}	
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "CONTENT",columnDefinition = "CLOB")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "CREATE_TIME", length = 11)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "OPERATE_TIME", length = 11)
	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "VALID")
	public Long getValid() {
		return this.valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}
}