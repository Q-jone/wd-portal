/**
 * 
 */
package com.wonders.stpt.marquee.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: MarqueeConfigBo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 上午11:13:57 
 *  
 */

@Entity
@Table(name = "T_MARQUEE_CONFIG")
public class MarqueeConfigBo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -172516233155591554L;
	private String id;
	private String count;
	private String limit;
	private String speed;
	private String removed;
	
	
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
	
	@Column(name = "COUNT", length = 200)
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	@Column(name = "LIMIT", length = 200)
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	@Column(name = "SPEED", length = 200)
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	
}
