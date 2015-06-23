package com.wonders.stpt.processInfo.service.impl;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.processInfo.model.MsgCountVo;
import com.wonders.stpt.processInfo.service.MsgInfoService;
import com.wonders.stpt.util.DbUtil;

@Repository("msgInfoService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)

public class MsgInfoServiceImpl implements MsgInfoService{
	private DbUtil dbUtil;

	/**
	 * @return the dbUtil
	 */
	public DbUtil getDbUtil() {
		return dbUtil;
	}

	/**
	 * @param dbUtil the dbUtil to set
	 */
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MsgCountVo> query(String pname,String pincident, String orders){
		String param1 = "";
		String param2 = "";
		String paramSql = "";
		String[] p =  null;
		String[] i = null;
		String[] o = null;
		if(orders!=null&&orders.length()>0){
			p =  pname.split(",");
			i = pincident.split(",");
			o = orders.split(",");
		}
		List<MsgCountVo> list =  new ArrayList<MsgCountVo>();
		Map<String,MsgCountVo> map = new HashMap<String,MsgCountVo>();
		if(o!=null&&o.length>0){
			
			for(int n=0;n<p.length;n++){
				MsgCountVo m = new MsgCountVo();
					m.setIncidentNo(i[n]);
					m.setModelName(p[n]);
					m.setOrder(Integer.parseInt(o[n]));
					map.put(p[n]+":"+i[n], m);	
					param2 += "'"+i[n]+"'"+",";
					param1 += "'"+p[n]+"'"+",";
					paramSql += "(processname = '"+p[n]+"' and incidentno ='"+i[n]+"') or"; 
			}
			param2 = param2.substring(0, param2.length()-1);
			param1 = param1.substring(0, param1.length()-1);
			paramSql =  paramSql.substring(0, paramSql.length()-2);
			//已读
			String readSql = "select processname,incidentno,count(*) as count from (" +
						" select * from t_msg_usermessage t1,t_flowfunction_guanlian t2 " +
						" where t1.sid = t2.yewu_id and t2.removed = 0 and t1.state =1" +
						" and ( "+paramSql+")) group by processname,incidentno";
			
			//System.out.println(readSql);
			//未读
			String unReadSql = "select processname,incidentno,count(*) as count from (" +
						" select * from t_msg_usermessage t1,t_flowfunction_guanlian t2 " +
						" where t1.sid = t2.yewu_id and t2.removed = 0 and t1.state =0" +
						" and ("+paramSql+")) group by processname,incidentno";
			//System.out.println(unReadSql);	
			List<Map<String,Object>> result1 = dbUtil.getJdbcTemplate().queryForList(readSql);
			List<Map<String,Object>> result2 = dbUtil.getJdbcTemplate().queryForList(unReadSql);
			if(result1!=null&&result1.size()>0){
				for(Map<String,Object> m : result1){
					map.get(m.get("processname")+":"+m.get("incidentno")).setReadCount(Integer.valueOf(m.get("count")+""));
				}
			}
			if(result2!=null&&result2.size()>0){
				for(Map<String,Object> m : result2){
					map.get(m.get("processname")+":"+m.get("incidentno")).setUnReadCount(Integer.valueOf(m.get("count")+""));
				}
			}
			Iterator itr = map.entrySet().iterator();
			while (itr.hasNext()) { 
				Map.Entry<String,MsgCountVo> entry = (Map.Entry<String,MsgCountVo>)itr.next();
				MsgCountVo value = entry.getValue();
				list.add(value);
			}
		}
		
		return list;
	}
}
