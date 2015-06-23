/**
 * 
 */
package com.wonders.stpt.contractReview.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.contractReview.dao.ContractReviewDao;
import com.wonders.stpt.contractReview.model.bo.ReviewMainBo;

/**
 * @ClassName: ContractReviewDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhoushun
 * @date 2013-6-9 上午10:04:48
 * 
 */
@Repository("contractReviewDao")
public class ContractReviewDaoImpl implements ContractReviewDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name = "hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// 批量增加
	public void addPatch(List<Object> list) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		for (int i = 0; i < list.size(); i++) {
			session.merge(list.get(i));
			// 以每50个数据作为一个处理单元
			if (i % 50 == 0) {
				// 只是将Hibernate缓存中的数据提交到数据库，保持与数据库数据的同步
				session.flush();
				// 清除内部缓存的全部数据，及时释放出占用的内存
				session.clear();
			}
		}
	}

	public List<ReviewMainBo> getList(String hql, Map<String, Object> args) {
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);
		if (args != null) {
			Set<String> keySet = args.keySet();
			for (String string : keySet) {
				Object obj = args.get(string);
				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(string, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(string, (Object[]) obj);
				} else {
					query.setParameter(string, obj);
				}
			}
		}

		List<ReviewMainBo> list = query.list();

		return list;
	}

	public String save(Object entity) {
		return (String) this.getHibernateTemplate().save(entity);
	}

	private StringBuffer getTodoSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("select r.contract_name, r.contract_identifier,r.contract_money_type_id,");
		sb.append(" r.contract_money_type,");
		sb.append(" r.contract_type1_id,r.contract_type2_id,");
		sb.append(" r.contract_type1,r.contract_type2,");
		sb.append(" r.project_charge_dept,r.contract_money,");
		sb.append(" r.contract_group_id,r.purchase_type_id,");
		sb.append(" r.contract_group,r.purchase_type,");
		sb.append(" r.kpi_control,r.process,r.incident,t.assignedtouser,t.steplabel,t.taskid,r.id");
		sb.append(" from t_contract_review r ,tasks t ");
		sb.append(" where t.status=1 ");
		sb.append(" and r.process = t.processname and r.incident=t.incident ");
		sb.append("and r.removed =0 and r.flag=0");
		sb.append(" and r.process='合同后审流程' and t.steplabel='合约部初审'");
		return sb;
	}

	private StringBuffer getTodoFullSql(StringBuffer sql, String contract_name,
			String contract_identifier, String contract_money_type_id,
			String contract_type1_id, String contract_type2_id,
			String contract_moneyLt, String contract_moneyGt,
			String project_charge_dept, String contract_group_id,
			String purchase_type_id, String kpi_control, String assignedtouser) {
		if (contract_name != null && !"".equals(contract_name)) {
			sql.append(" and contract_name like '%" + contract_name + "%' ");
		}
		if (contract_identifier != null && !"".equals(contract_identifier)) {
			sql.append(" and CONTRACT_SELF_NUM like '%" + contract_identifier
					+ "%' ");
		}
		if (assignedtouser != null && !"".equals(assignedtouser)) {
			sql.append(" and assignedtouser in (" + assignedtouser + ") ");
		}
		if (contract_money_type_id != null
				&& !"".equals(contract_money_type_id)) {
			sql.append(" and contract_money_type_id = '" + contract_identifier
					+ "' ");
		}
		if (contract_type1_id != null && !"".equals(contract_type1_id)) {
			sql.append(" and contract_type1_id = '" + contract_type1_id + "' ");
		}
		if (contract_type2_id != null && !"".equals(contract_type2_id)) {
			sql.append(" and contract_type2_id = '" + contract_type2_id + "' ");
		}
		if (contract_moneyLt != null && !"".equals(contract_moneyLt)) {
			sql.append(" and to_number(nvl(contract_money,0)) >= " + contract_moneyLt + " ");
		}
		if (contract_moneyGt != null && !"".equals(contract_moneyGt)) {
			sql.append(" and to_number(nvl(contract_money,0)) <= " + contract_moneyGt + " ");
		}
		if (project_charge_dept != null && !"".equals(project_charge_dept)) {
			sql.append(" and project_charge_dept like '%" + project_charge_dept
					+ "%' ");
		}
		if (contract_group_id != null && !"".equals(contract_group_id)) {
			sql.append(" and contract_group_id = '" + contract_group_id + "' ");
		}
		if (purchase_type_id != null && !"".equals(purchase_type_id)) {
			sql.append(" and purchase_type_id = '" + purchase_type_id + "' ");
		}
		if (kpi_control != null && !"".equals(kpi_control)) {
			String[] temp = kpi_control.split(",");
			if (temp.length == 1) {
				sql.append(" and instr(kpi_control,'" + kpi_control + "') > 0");
			} else {
				sql.append("and (");
				for (int i = 0; i < temp.length; i++) {
					if (i == temp.length - 1) {
						sql.append(" instr(kpi_control,'" + temp[i] + "') > 0");
					} else {
						sql.append(" instr(kpi_control,'" + temp[i]
								+ "') > 0 or");
					}
				}
				sql.append(" )");
			}
		}

		return sql;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findTodoByPage(int startRow, int pageSize,
			String contract_name, String contract_identifier,
			String contract_money_type_id, String contract_type1_id,
			String contract_type2_id, String contract_moneyLt,
			String contract_moneyGt, String project_charge_dept,
			String contract_group_id, String purchase_type_id,
			String kpi_control, String assignedtouser) {
		List<Object[]> list = new ArrayList<Object[]>();
		StringBuffer sql = getTodoFullSql(getTodoSql(), contract_name,
				contract_identifier, contract_money_type_id, contract_type1_id,
				contract_type2_id, contract_moneyLt, contract_moneyGt,
				project_charge_dept, contract_group_id, purchase_type_id,
				kpi_control, assignedtouser);
		//System.out.println("sql==" + sql);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString());
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;

	}

	public int countTodo(String contract_name, String contract_identifier,
			String contract_money_type_id, String contract_type1_id,
			String contract_type2_id, String contract_moneyLt,
			String contract_moneyGt, String project_charge_dept,
			String contract_group_id, String purchase_type_id,
			String kpi_control, String assignedtouser) {
		StringBuffer sql = getTodoFullSql(getTodoSql(), contract_name,
				contract_identifier, contract_money_type_id, contract_type1_id,
				contract_type2_id, contract_moneyLt, contract_moneyGt,
				project_charge_dept, contract_group_id, purchase_type_id,
				kpi_control, assignedtouser);
		//System.out.println("sql==" + sql);
		SQLQuery query = this
				.getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(
						"select count(*) count_num from (" + sql.toString()
								+ ") ");
		query.addScalar("count_num", Hibernate.INTEGER);

		return (Integer) query.uniqueResult();

	}

	private String getSql() {
		String sql = "select  * from "
				+ "(select case t.flag when '99' then '20762' when '0' then '13382' when '1' then '13383' else '' end as status,"
				+ " t.CONTRACT_SELF_NUM,t.CONTRACT_NAME,t.CONTRACT_MONEY,t.OPPOSITE_COMPANY,t.PROJECT_CHARGE,t.SIGN_TIME,"
				+ " t.flag pstatus,t.incident,t.process, id,t.PROJECT_CHARGE_DEPT "
				+ " from t_contract_review t where t.incident is not null "
				+ " and t.removed = 0 "
				+ " and t.flag in ('0','1','3') order by t.operate_time desc) where 1=1";
		sql = "select f.*,c2.taskid from ("
				+ sql
				+ ") f ,tasks c2 where c2.incident = f.incident and c2.processname = f.process and c2.steplabel = 'Begin' ";

		return sql;
	}

    public static void main(String[] args) {
        ContractReviewDaoImpl impl = new ContractReviewDaoImpl();
        System.out.println(impl.getSql());
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHtspOaByPage(int startRow, int pageSize,
			String contract_num, String contract_name, String contract_money,
			String opposite_company, String project_charge,
			String sign_time_start, String sign_time_end, String pstatus,
			String projectChargeDept) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getHtspOaFullSql(getSql(), contract_num, contract_name,
				contract_money, opposite_company, project_charge,
				sign_time_start, sign_time_end, pstatus, projectChargeDept);
		// System.out.println("sql=="+sql);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;

	}

	public int countHtspOa(String contract_num, String contract_name,
			String contract_money, String opposite_company,
			String project_charge, String sign_time_start,
			String sign_time_end, String pstatus, String projectChargeDept) {
		String sql = getHtspOaFullSql(getSql(), contract_num, contract_name,
				contract_money, opposite_company, project_charge,
				sign_time_start, sign_time_end, pstatus, projectChargeDept);
		// System.out.println("sql=="+sql);
		SQLQuery query = this
				.getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery("select count(*) count_num from (" + sql + ") ");
		query.addScalar("count_num", Hibernate.INTEGER);

		return (Integer) query.uniqueResult();

	}

	private String getHtspOaFullSql(String sql, String contract_num,
			String contract_name, String contract_money,
			String opposite_company, String project_charge,
			String sign_time_start, String sign_time_end, String pstatus,
			String projectChargeDept) {
		if (contract_num != null && !"".equals(contract_num)) {
			sql += " and CONTRACT_SELF_NUM like '%" + contract_num + "%' ";
		}
		if (contract_name != null && !"".equals(contract_name)) {
			sql += " and CONTRACT_NAME like '%" + contract_name + "%' ";
		}
		if (contract_money != null && !"".equals(contract_money)) {
			sql += " and CONTRACT_MONEY like '%" + contract_money + "%' ";
		}
		if (opposite_company != null && !"".equals(opposite_company)) {
			sql += " and OPPOSITE_COMPANY like '%" + opposite_company + "%' ";
		}
		if (project_charge != null && !"".equals(project_charge)) {
			sql += " and PROJECT_CHARGE like '%" + project_charge + "%' ";
		}
		if (pstatus != null && !"".equals(pstatus)) {
			sql += " and pstatus = '" + pstatus + "' ";
		}
		if (sign_time_start != null && !"".equals(sign_time_start)) {
			sql += " and SIGN_TIME >= '" + sign_time_start + "' ";
		}
		if (sign_time_end != null && !"".equals(sign_time_end)) {
			sql += " and SIGN_TIME <= '" + sign_time_end + "' ";
		}
		if (projectChargeDept != null && !"".equals(projectChargeDept)) {
			sql += " and PROJECT_CHARGE_DEPT like '%" + projectChargeDept
					+ "%' ";
		}

		return sql;
	}
	
	public List<Object[]> getSingleCheckByContractType(String type){
		String selCol = "";
		String groupCol = "";
		if("1".equals(type)){
			selCol = "PROJECT_CHARGE_DEPT, COUNT(1) STEP_NUM,PROJECT_NAME ";
			groupCol = "PROJECT_CHARGE_DEPT, PROJECT_NAME";
		}else {
			selCol = "PROJECT_CHARGE_DEPT, COUNT(1) STEP_NUM ";
			groupCol = "PROJECT_CHARGE_DEPT";
		}
		String sql = "SELECT " + selCol
			+" FROM  T_CONTRACT_REVIEW T "
			+" WHERE EXISTS (SELECT 1 FROM TASKS C2 WHERE C2.INCIDENT = T.INCIDENT "
			+" AND C2.PROCESSNAME = T.PROCESS AND C2.STEPLABEL='合约部拟办') "
			+" AND CONTRACT_TYPE1_ID = ? "
			+" AND REMOVED = '0' "
			+" AND FLAG ='1' GROUP BY " + groupCol;
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.setString(0, type);
		if(query.list()!=null && query.list().size()>0){
			return query.list();
		}
		return new ArrayList<Object[]>();
	}
	
	public List<Object[]> getBatchStoreByContractType(String type){
		String selCol = "";
		String groupCol = "";
		if("1".equals(type)){
			selCol = "PROJECT_CHARGE_DEPT, COUNT(1) DIRECT_NUM,PROJECT_NAME ";
			groupCol = "PROJECT_CHARGE_DEPT,PROJECT_NAME";
		}else{
			selCol = "PROJECT_CHARGE_DEPT, COUNT(1) DIRECT_NUM ";
			groupCol = "PROJECT_CHARGE_DEPT";
		}
		String sql = "SELECT " + selCol
			+" FROM  T_CONTRACT_REVIEW T "
			+" WHERE NOT EXISTS (SELECT 1 FROM TASKS C2 WHERE C2.INCIDENT = T.INCIDENT "
			+" AND C2.PROCESSNAME = T.PROCESS AND C2.STEPLABEL='合约部拟办') "
			+" AND CONTRACT_TYPE1_ID = ? "
			+" AND REMOVED = '0' "
			+" AND FLAG ='1' GROUP BY " + groupCol;
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.setString(0, type);
		if(query.list()!=null && query.list().size()>0){
			return query.list();
		}
		return new ArrayList<Object[]>();
	}
	
	public List<Object[]> getReturnCountByContractType(String type){
		String selCol = "";
		String groupCol = "";
		if("1".equals(type)){
			selCol = "PROJECT_CHARGE_DEPT,COUNT(1) RETURN_NUM,PROJECT_NAME ";
			groupCol = "PROJECT_CHARGE_DEPT, PROJECT_NAME";
		}else{
			selCol = "PROJECT_CHARGE_DEPT,COUNT(1) RETURN_NUM ";
			groupCol = "PROJECT_CHARGE_DEPT";
		}
		String sql = "SELECT " + selCol 
			+" FROM TASKS C2, T_CONTRACT_REVIEW T "
			+" WHERE C2.INCIDENT = T.INCIDENT "
			+" AND C2.PROCESSNAME = T.PROCESS "
			+" AND C2.STEPLABEL = '返回修改' "
			+" AND CONTRACT_TYPE1_ID = ? "
			+" AND REMOVED = '0' "
			+" AND FLAG IN ('0', '1') GROUP BY " + groupCol;
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.setString(0, type);
		if(query.list()!=null && query.list().size()>0){
			return query.list();
		}
		return new ArrayList<Object[]>();
	}
	
	public List<Object[]> getStatusCountByContractType(String type){
		String selCol = "";
		String groupCol = "";
		String orderCol = "";
		if("1".equals(type)){
			selCol = "PROJECT_CHARGE_DEPT, SUM(DECODE(T.FLAG, '0', 1, 0)) WORKING, "
					+" SUM(DECODE(T.FLAG, '1', 1, 0)) FINISHING, "
					+" COUNT(T.FLAG) TOTAL, PROJECT_NAME ";
			groupCol = "PROJECT_CHARGE_DEPT, PROJECT_NAME";
			orderCol = " order by PROJECT_CHARGE_DEPT, PROJECT_NAME";
		}else{
			selCol = "PROJECT_CHARGE_DEPT, SUM(DECODE(T.FLAG, '0', 1, 0)) WORKING, "
					+" SUM(DECODE(T.FLAG, '1', 1, 0)) FINISHING, "
					+" COUNT(T.FLAG) TOTAL ";
			groupCol = "PROJECT_CHARGE_DEPT";
			orderCol = " order by PROJECT_CHARGE_DEPT";
		}
		String sql = "SELECT " + selCol
			+" FROM T_CONTRACT_REVIEW T "
			+" WHERE CONTRACT_TYPE1_ID = ? "
			+" AND REMOVED = '0' "
			+" AND FLAG IN ('0', '1') "
			+" GROUP BY " + groupCol
			+ orderCol;
		Query query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.setString(0, type);
		if(query.list()!=null && query.list().size()>0){
			return query.list();
		}
		return new ArrayList<Object[]>();
	}
}
