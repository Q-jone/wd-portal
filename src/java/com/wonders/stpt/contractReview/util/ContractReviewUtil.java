/**   
* @Title: ContractReviewUtil.java 
* @Package com.wonders.stpt.contractReview.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年8月5日 下午3:45:40 
* @version V1.0   
*/
package com.wonders.stpt.contractReview.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wonders.stpt.util.DbUtil;

/** 
 * @ClassName: ContractReviewUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年8月5日 下午3:45:40 
 *  
 */

@Component("contractReviewUtil")
public class ContractReviewUtil {
	private static DbUtil dbUtil;
	
	public static List<Map<String,Object>> getTypeByCode(String code){
		String sql = "select id,name from t_contract_review_code t "
				+ " where t.removed=0 and t.code = ? order by t.orders";
		return dbUtil.getJdbcTemplate().queryForList(sql, new Object[]{code});
	}
	
	public static List<Map<String,Object>> getTypeByPid(String pid){
		String sql = "select id,name from t_contract_review_code t "
				+ " where t.removed=0 and t.p_id = ? order by t.orders";
		return dbUtil.getJdbcTemplate().queryForList(sql, new Object[]{pid});
	}
	
	public static List<Map<String,Object>> getKPI(){
		String sql = "select id,name from c_contract_kpi t order by orders";
		return dbUtil.getJdbcTemplate().queryForList(sql);
	}

	public static DbUtil getDbUtil() {
		return dbUtil;
	}

	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		ContractReviewUtil.dbUtil = dbUtil;
	}
	
	
}
