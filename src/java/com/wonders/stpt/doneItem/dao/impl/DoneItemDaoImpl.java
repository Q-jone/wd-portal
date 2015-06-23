package com.wonders.stpt.doneItem.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.doneItem.dao.DoneItemDao;
import com.wonders.stpt.doneItem.model.vo.DoneInfo;
import com.wonders.stpt.doneItem.model.vo.DoneStatInfo;
import com.wonders.stpt.todoItem.model.vo.TodoInfo;
import com.wonders.stpt.util.ConfigDbUtil;

@Repository("doneItemDao")
public class DoneItemDaoImpl implements DoneItemDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

    /**
     * 批量取消跟踪
     * @param source
     * @param type
     * @param trackStatus
     * @return
     */
    public boolean trackBatch(List<String> source,String type,String trackStatus){
        StringBuilder userSql = new StringBuilder();
        if(source != null && source.size() > 0){
            userSql.append(" and t.task_user in (");
            for(int i=0;i<source.size();i++){
                if(i==source.size()-1){
                    userSql.append("?");
                }else{
                    userSql.append("?,");
                }
            }
            userSql.append(")");
        }
        List<String> query = new ArrayList<String>();
        query.add(trackStatus);query.add(type);query.addAll(source);
        StringBuilder sql = new StringBuilder();
        sql.append("update t_done_record t set t.track_status=?");
        sql.append("where t.type = ?  ");
        sql.append(userSql);
        int result = ConfigDbUtil.getJdbcTemplate("").update(sql.toString(), query.toArray());
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 事项跟踪
     * @param source
     * @param id
     * @param trackStatus
     * @return
     */
	public boolean track(List<String> source,String id,String trackStatus){
		StringBuilder userSql = new StringBuilder();
		if(source != null && source.size() > 0){
			userSql.append(" and t.task_user in (");
			for(int i=0;i<source.size();i++){
				if(i==source.size()-1){
					userSql.append("?");
				}else{
					userSql.append("?,");
				}
			}
			userSql.append(")");
		}
		List<String> query = new ArrayList<String>();
		query.add(trackStatus);query.add(id);query.addAll(source);
		StringBuilder sql = new StringBuilder();
		sql.append("update t_done_record t set t.track_status=?"); 
		sql.append("where t.id = ?  ");
		sql.append(userSql);
		int result = ConfigDbUtil.getJdbcTemplate("").update(sql.toString(), query.toArray());
		if(result > 0){
			return true;
		}
		return false;
	}

    /**
     * 获取事项类型
     * @param source
     * @param processStatus
     * @param trackStatus
     * @return
     */
	public List<String> getDoneType(List<String> source,String processStatus,String trackStatus){
		StringBuilder userSql = new StringBuilder();
		if(source != null && source.size() > 0){
			userSql.append(" and t.task_user in (");
			for(int i=0;i<source.size();i++){
				if(i==source.size()-1){
					userSql.append("?");
				}else{
					userSql.append("?,");
				}
			}
			userSql.append(")");
		}
		StringBuilder typeSql = new StringBuilder();
		typeSql.append("select TYPE ||'（'|| COUNT(TYPE)||'）'  result ");
		typeSql.append(" from t_done_record t where t.removed = 0 ");
		typeSql.append(userSql);
		typeSql.append(" and t.process_status=? and t.track_status=? ");
		typeSql.append(" group by TYPE,orders ORDER BY to_number(ORDERS)");
		List<String> typeQuery = new ArrayList<String>();
		typeQuery.addAll(source);typeQuery.add(processStatus);typeQuery.add(trackStatus);
		List<String> type = ConfigDbUtil.getJdbcTemplate("").queryForList
				(typeSql.toString(),typeQuery.toArray(),String.class);
		return type;		
	}

    /**
     * 获取事项跟踪页面
     * @param source
     * @param processStatus
     * @param trackStatus
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer,List<DoneInfo>> getDoneResult(List<String> source,String processStatus,String trackStatus){
		Map<Integer,List<DoneInfo>> map = new TreeMap<Integer,List<DoneInfo>>();
		StringBuilder userSql = new StringBuilder();
		if(source != null && source.size() > 0){
			userSql.append(" and t.task_user in (");
			for(int i=0;i<source.size();i++){
				if(i==source.size()-1){
					userSql.append("?");
				}else{
					userSql.append("?,");
				}
			}
			userSql.append(")");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select substr(t.done_time,1,10) donetime,id,type,pname,pincident,summary,t.step_name stepname,t.process_status processstatus,t.track_status trackstatus,substr(t.operate_time,1,10) operatetime,to_number(ORDERS) orders,task_id taskid ");
		sql.append(" from t_done_record t where t.removed = 0 ");
		sql.append(userSql);
		sql.append(" and t.process_status=? and t.track_status=? ");
		sql.append(" ORDER BY to_number(ORDERS)");
        if("1".equals(processStatus)){
            sql.append(", done_time desc");
        }else if("0".equals(processStatus)){
            sql.append(", operate_time desc");
        }
		List<String> query = new ArrayList<String>();
		query.addAll(source);query.add(processStatus);query.add(trackStatus);	
		List<DoneInfo> list = ConfigDbUtil.getJdbcTemplate("").query(sql.toString(), 
				query.toArray(),
				new BeanPropertyRowMapper(DoneInfo.class));
		if(list != null && list.size() > 0){
			for(DoneInfo bo : list){
				if(map.containsKey(bo.getOrders())){
					map.get(bo.getOrders()).add(bo);
				}else{
					List<DoneInfo> tmp = new ArrayList<DoneInfo>();
					tmp.add(bo);
					map.put(bo.getOrders(), tmp);
				}
			}
		}
		return map;
	}

    /**
     * 获取事项信息
     * @param source
     * @param processStatus
     * @param trackStatus
     * @return
     */
	public DoneStatInfo getDoneInfo(List<String> source,String processStatus,String trackStatus){
		DoneStatInfo doneStatInfo = new DoneStatInfo();
		StringBuilder userSql = new StringBuilder();
		if(source != null && source.size() > 0){
			userSql.append(" and t.task_user in (");
			for(int i=0;i<source.size();i++){
				if(i==source.size()-1){
					userSql.append("?");
				}else{
					userSql.append("?,");
				}
			}
			userSql.append(")");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select substr(t.done_time,1,10) donetime, id,type,pname,pincident,summary,t.step_name stepname,t.process_status processstatus,t.track_status trackstatus,substr(t.operate_time,1,10) operatetime, to_number(ORDERS) orders,task_id taskid");
		sql.append(" from t_done_record t where t.removed = 0 ");
		sql.append(userSql);
		sql.append(" and t.process_status=? and t.track_status=? ");
		sql.append(" ORDER BY to_number(ORDERS)");
        if("1".equals(processStatus)){
            sql.append(", done_time desc");
        }else if("0".equals(processStatus)){
            sql.append(", operate_time desc");
        }
		List<String> query = new ArrayList<String>();
		query.addAll(source);query.add(processStatus);query.add(trackStatus);
		List<DoneInfo> list = ConfigDbUtil.getJdbcTemplate("").query(sql.toString(), 
				query.toArray(),
				new BeanPropertyRowMapper<DoneInfo>(DoneInfo.class));
		
		StringBuilder typeSql = new StringBuilder();
		typeSql.append("select TYPE ||'（'|| COUNT(TYPE)||'）'  result ");
		typeSql.append(" from t_done_record t where t.removed = 0 ");
		typeSql.append(userSql);
		typeSql.append(" and t.process_status=? and t.track_status=? ");
		typeSql.append(" group by TYPE,orders ORDER BY to_number(ORDERS)");
		List<String> typeQuery = new ArrayList<String>();
		typeQuery.addAll(source);typeQuery.add(processStatus);typeQuery.add(trackStatus);
		List<String> type = ConfigDbUtil.getJdbcTemplate("").queryForList
				(typeSql.toString(),typeQuery.toArray(),String.class);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) doneCount,count(case t.track_status when '0' then 1 else null end) unTrackCount ");
		countSql.append(" from t_done_record t where t.removed = 0 ");
		countSql.append(userSql);
		countSql.append(" and t.process_status=?");
		List<String> countQuery = new ArrayList<String>();
		countQuery.addAll(source);countQuery.add(processStatus);
		Map<String,Object> map = ConfigDbUtil.getJdbcTemplate("").queryForMap
				(countSql.toString(),countQuery.toArray());
		
		doneStatInfo.setDoneCount(((BigDecimal)map.get("doneCount")).intValue());
		doneStatInfo.setUnTrackCount(((BigDecimal)map.get("unTrackCount")).intValue());
		doneStatInfo.setType(type);
		doneStatInfo.setDoneInfo(list);
		return doneStatInfo;
	}
}
