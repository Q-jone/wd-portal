/**
 * 
 */
package com.wonders.stpt.asset.model.vo;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: InventoryVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-2-1 下午03:02:38 
 *  
 */
public class InventoryVo {
	public List<String> specialtype = new ArrayList<String>();
	public List<String> specialtypeCn = new ArrayList<String>();
	public List<String> special = new ArrayList<String>();
	public List<String> sumtype = new ArrayList<String>();
	public List<String> sumtypeCn = new ArrayList<String>();
	public List<Double> value = new ArrayList<Double>();
	public String flag = "";
}
