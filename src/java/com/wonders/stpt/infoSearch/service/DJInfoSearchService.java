/**
 * 
 */
package com.wonders.stpt.infoSearch.service;

import java.util.List;

import javax.security.auth.Subject;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.SubjectVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;

/** 
 * @ClassName: InfoSearchService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午06:13:43 
 *  
 */
public interface DJInfoSearchService {
	/**
	 * 法规服务首页显示
	 */
	public List<StfbVo> findLatestStfbNews(String sj_id,String part_id,int num,String parent_id);
	
	/**
	 * 根据parent_id查询子栏目查询
	 */
	public List<SubjectVo> findNewsByType(String  parent_id);
	
	
	/**
	 * 根据sj_id查询所有内容
	 */
	public PageResultSet<InfoDetailVo> findDataByPage(String sj_id,String searchWord,int pageSize,int page);
	
	
	
	
	
	public PageResultSet<InfoDetailVo> queryByPage(String sj_id,String part_id,String searchWord,int pageSize, int page);
	public List<StfbVo> findLatestHeadStfbHeadNews(String sj_id);
	public List<StfbVo> findLatestStfbPicNews(String sj_id,int num,boolean pic_needed);
}
