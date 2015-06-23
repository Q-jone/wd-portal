/**
 * 
 */
package com.wonders.stpt.jeecms.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.ZTTP;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.jeecms.entity.vo.InfoChannelVo;
import com.wonders.stpt.jeecms.entity.vo.InfoDetailVo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
 * @ClassName: InfoSearchService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author huzhiming
 * @date 2013-01-10 下午16:52:43 
 *  
 */
public interface JeecmsService {
	
	public PageResultSet<InfoDetailVo> queryByPage(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String channelId,String searchWord,int pageSize, int page,String latestDays);
	
	public List<InfoChannelVo> findNewsByType(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String parentId,String latestDays);

	public List<Object> findWsLeaders();
}
