/**
 * 
 */
package com.wonders.stpt.innerWork.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.innerWork.model.bo.TInnerWork;
import com.wonders.stpt.innerWork.model.vo.InnerWorkListVo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: InnerWorkDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:27:05 
 *  
 */
public interface InnerWorkDao {
	public String save(TInnerWork bo);
	public String update(TInnerWork bo);
	public TInnerWork load(String id);
	public PageResultSet<Map<String,Object>> list(InnerWorkListVo vo);
	public PageResultSet<Map<String,Object>> listAll(InnerWorkListVo vo);
	
    public List<TInnerWork> findByStatus();
}
