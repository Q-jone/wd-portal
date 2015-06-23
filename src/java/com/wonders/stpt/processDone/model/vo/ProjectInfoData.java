package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Datas")
public class ProjectInfoData {

	@SuppressWarnings("unused")
	@XmlElement(name = "BasicData")
	private ProjectVo projectVo;

	public ProjectInfoData(ProjectVo projectVo) {
		this.projectVo = projectVo;
	}

	public ProjectInfoData() {

	}
}
