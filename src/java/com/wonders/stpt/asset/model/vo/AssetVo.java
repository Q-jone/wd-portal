/**
 * 
 */
package com.wonders.stpt.asset.model.vo;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: AssetChartVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-2-1 上午11:46:34 
 *  
 */
public class AssetVo {
	public String line = "";
	public String station = "";
	public String flag = "";
	public List<String> counttype = new ArrayList<String>();
	public List<String> counttypeCn = new ArrayList<String>();
	public List<String> sumtype = new ArrayList<String>();
	public List<String> sumtypeCn = new ArrayList<String>();
	public List<Long> count = new ArrayList<Long>();
	public List<Double> sum = new ArrayList<Double>();
}
 