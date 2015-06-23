/**
 * 
 */
package com.wonders.stpt.infoSearch.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.ZTTP;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;

/** 
 * @ClassName: InfoSearchService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午06:13:43 
 *  
 */
public interface InfoSearchService {
	public PageResultSet<InfoDetailVo> queryByPage(String sj_id,String part_id,String searchWord,int pageSize, int page);
	public List<StfbVo> findLatestStfbNews(String sj_id,String partId,int num ,boolean isContent);
	public List<StfbVo> findLatestHeadStfbHeadNews(String sj_id);
	public List<StfbVo> findLatestStfbPicNews(String sj_id,int num,boolean pic_needed);
	public List<ZTTP> findZTTPNews(String sWhere,int size);
}
