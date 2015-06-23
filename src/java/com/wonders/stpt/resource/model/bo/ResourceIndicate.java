/**   
* @Title: ResourceIndicate.java 
* @Package com.wonders.stpt.resource.model.bo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午7:18:19 
* @version V1.0   
*/
package com.wonders.stpt.resource.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: ResourceIndicate 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午7:18:19 
 *  
 */

@Entity
@Table(name = "DW_RESOURCE_INDICATE")
public class ResourceIndicate implements java.io.Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 7757312290751599177L;
	private String id ;
	private String indicatorLine;
	private String indicatorDate;
	private Double tractionPowerDaily;
	private Double tractionPowerMonth;
	private Double tractionPowerYear;
	private Double electricPowerDaily;
	private Double electricPowerMonth;
	private Double electricPowerYear;
	private String operateTime;
	private String operatePerson;
	private String removed;
	private String ext1;
	private String ext2;
	private String ext3;
	
	public ResourceIndicate(){
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());	
	}
	
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
	@Column(name = "INDICATOR_LINE", nullable = true, length = 50)
	public String getIndicatorLine() {
		return indicatorLine;
	}
	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}
	
	@Column(name = "INDICATOR_DATE", nullable = true, length = 50)
	public String getIndicatorDate() {
		return indicatorDate;
	}
	public void setIndicatorDate(String indicatorDate) {
		this.indicatorDate = indicatorDate;
	}
	
	@Column(name = "TRACTION_POWER_DAILY", precision=20,scale=4)
	public Double getTractionPowerDaily() {
		return tractionPowerDaily;
	}
	public void setTractionPowerDaily(Double tractionPowerDaily) {
		this.tractionPowerDaily = tractionPowerDaily;
	}
	
	@Column(name = "TRACTION_POWER_MONTH", precision=20,scale=4)
	public Double getTractionPowerMonth() {
		return tractionPowerMonth;
	}
	public void setTractionPowerMonth(Double tractionPowerMonth) {
		this.tractionPowerMonth = tractionPowerMonth;
	}
	
	@Column(name = "TRACTION_POWER_YEAR", precision=20,scale=4)
	public Double getTractionPowerYear() {
		return tractionPowerYear;
	}
	public void setTractionPowerYear(Double tractionPowerYear) {
		this.tractionPowerYear = tractionPowerYear;
	}
	
	@Column(name = "ELECTRIC_POWER_DAILY", precision=20,scale=4)
	public Double getElectricPowerDaily() {
		return electricPowerDaily;
	}
	public void setElectricPowerDaily(Double electricPowerDaily) {
		this.electricPowerDaily = electricPowerDaily;
	}
	
	@Column(name = "ELECTRIC_POWER_MONTH", precision=20,scale=4)
	public Double getElectricPowerMonth() {
		return electricPowerMonth;
	}
	public void setElectricPowerMonth(Double electricPowerMonth) {
		this.electricPowerMonth = electricPowerMonth;
	}
	
	@Column(name = "ELECTRIC_POWER_YEAR", precision=20,scale=4)
	public Double getElectricPowerYear() {
		return electricPowerYear;
	}
	public void setElectricPowerYear(Double electricPowerYear) {
		this.electricPowerYear = electricPowerYear;
	}
	
	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}
	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}
	
	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "EXT1", nullable = true, length = 200)
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Column(name = "EXT2", nullable = true, length = 200)
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	@Column(name = "EXT3", nullable = true, length = 200)
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
	

}
