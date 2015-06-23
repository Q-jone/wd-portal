/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.organTree.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TOrganRelation实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-5-7
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ORGAN_RELATION")
public class TorganRelation implements Serializable, BusinessObject {

	private String id; // id

	private String description; // description

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private Long nodeId; // nodeId

	private String removed; // removed

	private Long rootNodeId; // rootNodeId

	private String treeCode; // treeCode

	private Long treeId; // treeId

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION", nullable = true, length = 200)
	public String getDescription() {
		return description;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT1", nullable = true, length = 200)
	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT2", nullable = true, length = 200)
	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT3", nullable = true, length = 200)
	public String getExt3() {
		return ext3;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "NODE_ID", precision=19)
	public Long getNodeId() {
		return nodeId;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setRootNodeId(Long rootNodeId) {
		this.rootNodeId = rootNodeId;
	}

	@Column(name = "ROOT_NODE_ID", precision=19)
	public Long getRootNodeId() {
		return rootNodeId;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name = "TREE_CODE", nullable = true, length = 200)
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

	@Column(name = "TREE_ID", precision=19)
	public Long getTreeId() {
		return treeId;
	}

}
