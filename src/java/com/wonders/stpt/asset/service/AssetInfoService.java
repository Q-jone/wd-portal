/**
 * 
 */
package com.wonders.stpt.asset.service;

import java.util.List;

import com.wonders.stpt.asset.model.vo.AssetVo;
import com.wonders.stpt.asset.model.vo.InventoryVo;

/** 
 * @ClassName: AssetInfoService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-31 上午11:30:55 
 *  
 */
public interface AssetInfoService {
	/**
	 * type 逗号分割（字段名）
	 * typeCn 逗号分割（显示名）
	 * line 线路
	 * station 车站
	 * flag	标示为（1：线路；2：车站;）
	 * */
	public AssetVo getAssetInfo(
			String line, String station,
			String counttype, String counttypeCn,
			String sumtype, String sumtypeCn,
			String flag);
	
	/**
	 * type 逗号分割（字段名）
	 * typeCn 逗号分割（显示名）
	 * line 线路
	 * owner 车站
	 * flag	标示为（3:所属单位）
	 * */
	public List<InventoryVo> getInventoryInfo(
			String line, String owner,
			String specialtype, String specialtypeCn,
			String sumtype, String sumtypeCn,
			String flag);
}
