/**
 * 
 */
package com.wonders.stpt.validFile.dao;

import java.util.Map;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.validFile.model.vo.ValidFileListVo;

/** 
 * @ClassName: ValidFileDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 上午11:28:45 
 *  
 */
public interface ValidFileDao {
	public PageResultSet<Map<String,Object>> list(StringBuffer sql ,ValidFileListVo vo);
}
