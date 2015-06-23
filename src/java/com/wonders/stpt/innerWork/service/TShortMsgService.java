/** 
* @Title: TShortMsgService.java 
* @Package com.wonders.frame.iims.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lushuaifeng
* @version V1.0 
*/
package com.wonders.stpt.innerWork.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wonders.stpt.util.DbUtil;
import com.wonders.stpt.util.SpringBeanUtil;

/** 
 * @ClassName: TShortMsgService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 */
@Service
public class TShortMsgService {
	private DbUtil dbUtil;
	
	public boolean sendNewShortMsg(String loginName,String content){
		dbUtil=(DbUtil)SpringBeanUtil.getBean("dbUtil");
		boolean success=false;
		String sql="select nvl(nvl(a.mobile1,a.mobile2),0) mobile from cs_user a,T_CS_USER t where a.id=t.id and a.login_name like ? and t.msg_notice=1"; 
		List<Map<String,Object>> list=dbUtil.getJdbcTemplate().queryForList(sql, new Object[]{loginName});
		String mobile="0";
		for(int i=0;i<list.size();i++){
			Map<String,Object> rec=list.get(i);
			mobile=(String)rec.get("mobile");
			if(!mobile.equals("0")){break;}
		}
		if(!mobile.equals("0")){
			sql="insert into t_short_msg(id,status,mobile,content) values(sys_guid(),'0',?,?)";
			int successRec=dbUtil.getJdbcTemplate().update(sql, new Object[]{mobile,content});
			if(successRec>0){
				success=true;
			}
		}
		return success;
		
	}
	
	
	
}
