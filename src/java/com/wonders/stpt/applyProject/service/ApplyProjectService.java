/**
 * 
 */
package com.wonders.stpt.applyProject.service;

import java.util.Map;

import com.wonders.stpt.applyProject.model.bo.TApplyProject;
import com.wonders.stpt.applyProject.model.vo.ApplyProjectListVo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: ApplyProjectService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:40:56 
 *  
 */
public interface ApplyProjectService {
	public String save(TApplyProject bo);
	public String update(TApplyProject bo);
	public TApplyProject load(String id);
	public PageResultSet<Map<String,Object>> list(ApplyProjectListVo vo);
	public PageResultSet<Map<String,Object>> listAll(ApplyProjectListVo vo);
}
