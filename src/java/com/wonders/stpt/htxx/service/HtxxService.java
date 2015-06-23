/**
 * 
 */
package com.wonders.stpt.htxx.service;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: HtxxService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-5-15 下午2:12:50 
 *  
 */
public interface HtxxService {

	public List<Map<String,Object>> executeSQl(String sql);
	
}
