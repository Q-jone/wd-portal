/**
 * 
 */
package com.wonders.stpt.metroExpress.service;

import java.util.List;
import java.util.Map;
import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wondersgroup.framework.core.bo.Page;

/** 
 * @ClassName: MetroExpressService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午07:44:44 
 *  
 */
public interface MetroExpressService {

	public void addMetroExpress(MetroExpress metroExpress);
	
	public void updateMetroExpress(MetroExpress metroExpress);
	
	public MetroExpress viewMetroExpress(String id);
	
	public List<MetroExpress> findLatestMetroExpressEvents(String accidentLine, String accidentEmergency, int size);
	
	public List<String> findMetroExpressCode(String codeType_code, String codeInfo_code);
	
	public List<String> findMetroLineConfig();
	
	public Map<String,String> findMetroLineConfigMap();
	
	public Page findMetroExpressByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
}
