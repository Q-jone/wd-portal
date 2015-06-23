package com.wonders.stpt.exam.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.exam.service.ExamService;
import com.wonders.stpt.page.model.PageResultSet;

@Repository("examService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamServiceImpl implements ExamService{
	
	private ExamTDao<ExamMain> dao;
	public ExamTDao<ExamMain> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamMain> dao) {
		this.dao = dao;
	}

	@Override
	public ExamMain find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ExamMain.class);
	}
	
	@Override
	public ExamMain save(ExamMain bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}

	@Override
	public PageResultSet findPageResult(int pageNo, int pageSize){
		String queryHql = "select t.id,t.title,t.cUser,t.cTime,t.state,(select count(q.id) from ExamQuestion q where q.removed = 0 and q.mainId = t.id) as total_q from ExamMain t where t.removed=0 order by t.cTime desc";
		String countHql = "select count(t.id) from ExamMain t where t.removed=0";
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}
	
	@Override
	public int deleteById(String id) {
		dao.excuteHQLUpdate("update ExamMain set removed = 1 where id = ?", new Object[]{id});
		return 1;
	}
	
	public List getReport(String mainId) {
		
		StringBuffer sqls = new StringBuffer("");
		sqls.append("select tg.group_num,");
		sqls.append("       tq.quest_num,");
		sqls.append("       tq.title,");
		sqls.append("       op.op_code,");
		sqls.append("       op.op_value,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id in (");
		sqls.append("	       '2501','2502','2503','2504','2505','2506','2509','2507','2508','2510',");
		sqls.append("		'2511','2512','2513','2514','2515','2516','2517','2518','2519','2549','2550','2551','2943','2944','2963','2980',");
		sqls.append("		'3000') THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) jt,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2921' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gs1,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2923' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gs3,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2924' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gs4,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2920' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsy,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2925' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsw,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id in ('2941', '2942') THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsj,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2946' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsz,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2945' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsg,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2962' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsc,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id = '2959' THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsd,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id not in (");
		sqls.append("	       'GREATA',");
		sqls.append("		'2947','2948','2949','2950','2951','2952','2953','2954','2955','2956','2957','2958','2960','2961',");
		sqls.append("		'3020','2959','2962','2945','2946','2941','2942','2921','2920','2924','2925','2923','2501','2502','2503','2504','2505',");
		sqls.append("	       '2506','2509','2507','2508','2510','2511','2512','2513','2514','2515','2516','2517','2518','2519','2549','2550','2551','2943','2944','2963','2980',");
		sqls.append("		'3000') THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) qt,");
		sqls.append("       COUNT(CASE");
		sqls.append("               WHEN tuo.dept_id in (");
		sqls.append("	       'GREATA','2947','2948','2949','2950','2951','2952','2953','2954','2955','2956','2957','2958','2960','2961',");
		sqls.append("               '3020') THEN");
		sqls.append("                1");
		sqls.append("               ELSE");
		sqls.append("                NULL");
		sqls.append("             END) gsx,");
		sqls.append("       COUNT(tuo.id) hj");
		sqls.append("  from t_exam_option op");
		sqls.append("  left join t_exam_umain tu");
		sqls.append("    on tu.main_id = op.main_id");
		sqls.append("   and tu.state = 1");
		sqls.append("  left join t_exam_uoption tuo");
		sqls.append("    on tuo.main_id = op.main_id");
		sqls.append("   and tuo.option_id = op.id");
		sqls.append("   and tu.login_name = tuo.login_name, t_exam_question tq,");
		sqls.append(" t_exam_group tg");
		sqls.append(" where tq.main_id = op.main_id");
		sqls.append("   and op.main_id = ?");
		sqls.append("   and tg.main_id = tq.main_id");
		sqls.append("   and tq.group_id = tg.id");
		sqls.append("   and op.quest_id = tq.id");
		sqls.append("   and op.removed = 0 and tu.removed = 0 and tq.removed = 0");
		sqls.append(" group by tg.group_num, tq.quest_num, tq.title, op.op_code, op.op_value");
		sqls.append(" order by tg.group_num asc, tq.quest_num asc, op.op_code asc");
		
		
		/*String sql = "select tg.group_num,"+
				"       tq.quest_num,"+
				"       tq.title,"+
				"       op.op_code,"+
				"       op.op_value,"+
				"      COUNT(CASE"+
				"               WHEN tuo.dept_id in"+
				"                    ('2501', '2502', '2503', '2504', '2505', '2506', '2509', '2507',"+
				"                    '2508', '2510', '2511', '2512', '2513', '2514', '2515', '2516',"+
				"                    '2517', '2518', '2519', '2549', '2550', '2551', '2943', '2944',"+
				"                    '2963', '2980', '3000') THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) jt,"+//闆嗗洟
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2921' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gs1,"+//绗竴杩愯惀鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2923' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gs3,"+//绗笁杩愯惀鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2924' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gs4,"+//绗洓杩愯惀鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2920' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsy,"+//杩愮涓績
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2925' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsw,"+//缁翠繚涓績
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id in ('2941', '2942') THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsj,"+//鎶€鏈腑蹇?
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2946' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsz,"+//璧勪骇鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2945' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsg,"+//鑲′唤鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2962' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsc,"+//纾佹诞鍏徃
				"      COUNT(CASE"+
				"              WHEN tuo.dept_id = '2959' THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsd,"+//澶фˉ鍏徃
				"       COUNT(CASE"+
				"              WHEN tuo.dept_id not in ('GREATA', '2947', '2948', '2949', '2950',"+
				"                     '2951', '2952', '2953', '2954', '2955', '2956',"+
				"                    '2957', '2958', '2960', '2961', '3020','2959','2962',"+
				"	'2945','2946','2941', '2942','2921','2920','2924','2925'"+
				",'2923','2501', '2502', '2503', '2504', '2505', '2506', '2509', '2507',"+
				"                    '2508', '2510', '2511', '2512', '2513', '2514', '2515', '2516',"+
				"                    '2517', '2518', '2519', '2549', '2550', '2551', '2943', '2944',"+
				" '2963', '2980', '3000') THEN 1"+
				"              ELSE"+
				"               NULL"+
				"            END) qt,"+//鍏朵粬
				"       COUNT(CASE"+
				"              WHEN tuo.dept_id in ('GREATA', '2947', '2948', '2949', '2950',"+
				"                     '2951', '2952', '2953', '2954', '2955', '2956',"+
				"                    '2957', '2958', '2960', '2961', '3020') THEN"+
				"               1"+
				"              ELSE"+
				"               NULL"+
				"            END) gsx,"+//椤圭洰鍏徃
				"      COUNT(tuo.id) hj"+
				"  from t_exam_option op"+
				"  left join t_exam_umain tu on tu.main_id = op.main_id"+
				"                          and tu.state = 1"+
				"  left join t_exam_uoption tuo on tuo.main_id = op.main_id"+
				"                             and tuo.option_id = op.id"+
				"                              and tu.login_name = tuo.login_name,"+
				" t_exam_question tq, t_exam_group tg"+
				" where tq.main_id = op.main_id"+
				"  and op.main_id = ? "+
				"  and tg.main_id = tq.main_id"+
				"  and tq.group_id = tg.id"+
				"  and op.quest_id = tq.id"+
				" group by tg.group_num, tq.quest_num, tq.title, op.op_code, op.op_value"+
				" order by tg.group_num asc, tq.quest_num asc, op.op_code asc";*/

		return dao.excuteSQLQuery(sqls.toString(), new Object[]{mainId});
	}

}
