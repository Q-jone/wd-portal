/**
 * 
 */
package com.wonders.stpt.asset.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.asset.model.vo.AssetVo;
import com.wonders.stpt.asset.model.vo.InventoryVo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: AssetInfoServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-31 上午11:30:48 
 *  
 */
@Transactional(value = "txManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("assetInfoService")
public class AssetInfoServiceImpl implements AssetInfoService{
	private JdbcTemplate jt;
	/**
	 * 轨道：1
	 * 车站：2
	 * 通信：5
	 * 车辆：4
	 * flag 1 线路 2车站 3公司
	 * */
	
	public AssetVo getAssetInfo(String line, String station,String counttype, String counttypeCn,String sumtype, String sumtypeCn,  String flag){
		AssetVo vo = new AssetVo();
		List<String> counttypelist = convertStringToList(counttype,",");
		List<String> counttypeCnlist = convertStringToList(counttypeCn,",");
		List<String> sumtypelist = convertStringToList(sumtype,",");
		List<String> sumtypeCnlist = convertStringToList(sumtypeCn,",");
		vo.line = line;
		vo.station = station;
		vo.flag = flag;
		
		String sql = "select "+ counttype +"," + sumtype + " from DW_ASSET_TYPE t " +
				" where t.REMOVED='0' and t.TYPE = '"+flag+"' and LINE='"+line+"' ";
		if(station!=null && !"".equals(station)){
			sql += " and t.STATION = '"+station+"'";
		}
		System.out.println(sql);
		Map<String,Object> map = jt.queryForMap(sql);
		if(map!=null && map.size()>0){
			System.out.println("map in ----------------------------------");
			for(int i=counttypelist.size()-1;i>=0;i--){
				
				System.out.println("list in ======================="+i);
				Long count = convertToLong(map.get(counttypelist.get(i)));
				
				if(count == 0){
					System.out.println("count in ======================="+i);
					counttypelist.remove(i);
					counttypeCnlist.remove(i);
				}else{
					vo.count.add(count);
				}
				vo.counttype = counttypelist;
				vo.counttypeCn = counttypeCnlist;
			}
			
			for(int i=sumtypelist.size()-1;i>=0;i--){
				
				System.out.println("list in ======================="+i);
				Double sum = convertToDouble(map.get(sumtypelist.get(i)));
				if(sum == 0.0){
					System.out.println("sum in ======================="+i);
					sumtypelist.remove(i);
					sumtypeCnlist.remove(i);
				}else{
					vo.sum.add(sum);
				}

				vo.sumtype = sumtypelist;
				vo.sumtypeCn = sumtypeCnlist;
			}
			System.out.println("----------------------------------");
		}
		
		return vo;
	}
	
	
	
	

	@Override
	public List<InventoryVo> getInventoryInfo(String line, String owner,
			String specialtype, String specialtypeCn,
			String sumtype, String sumtypeCn,
			String flag) {
		List<InventoryVo> list = new ArrayList<InventoryVo>();
		List<String> specialtypelist = convertStringToList(specialtype,",");
		List<String> specialtypeCnlist = convertStringToList(specialtypeCn,",");
		List<String> sumtypelist = convertStringToList(sumtype,",");
		List<String> sumtypeCnlist = convertStringToList(sumtypeCn,",");
		
		String sql = "select "+ specialtype +"," + sumtype + " from DW_ASSET_TYPE t " +
		" where t.REMOVED='0' and t.TYPE = '"+flag +"'";
		if(line!=null && !"".equals(line)){
			sql += " and t.LINE = '"+line+"'";
		}
		sql += " order by t.UPDATE_TIME desc";
		//System.out.println(sql);
		List<Map<String,Object>> result = jt.queryForList(sql);
		//System.out.println(result.size());
		if(result.size()>0){
			for(Map<String,Object> map:result){
				InventoryVo vo = new InventoryVo();
				vo.specialtype = specialtypelist;
				vo.specialtypeCn = specialtypeCnlist;
				vo.sumtype = sumtypelist;
				vo.sumtypeCn = sumtypeCnlist;
//				System.out.println("map==="+map.size());
//				for(Map.Entry<String, Object> entry:map.entrySet()){
//					System.out.println(entry.getKey()+"==="+entry.getValue());
//				}
				for(String t:specialtypelist){
					vo.special.add(StringUtil.getNotNullValueString(map.get(t)));
				}
				for(String t:sumtypelist){
					vo.value.add(convertToDouble(map.get(t)));
				}
				list.add(vo);
			}
		}
		
		return list;
	}

	
	public Long convertToLong(Object o){
		Long result = 0L;
		String showValue = "";
		if(o!=null){
			showValue = o.toString();
			if(showValue.trim().equals("null")){
				showValue = "";
			}
		}
		try{
			result = Long.valueOf(showValue);
		}catch(Exception e){
			result = 0L;
		}
		
		return result;
	}	
	
	public Double convertToDouble(Object o){
		Double result = 0.00;
		String showValue = "";
		if(o!=null){
			showValue = o.toString();
			if(showValue.trim().equals("null")){
				showValue = "";
			}
		}
		try{
			result = Double.valueOf(showValue);
		}catch(Exception e){
			result = 0.00;
		}
		
		return result;
	}	
	
	public List<String> convertStringToList(String str , String split){
		List<String> list = new ArrayList<String>();
		if(str.length() > 0){
			String[] temp = str.split("\\"+split);
			for(String s:temp){
				list.add(s);
			}
		}
		return list;
	}
	


	public JdbcTemplate getJt() {
		return jt;
	}

	@Autowired(required=false)
	public void setJt(@Qualifier(value="jdbcTemplate")JdbcTemplate jt) {
		this.jt = jt;
	}


	
	
}
