/**
 * 
 */
package com.wonders.stpt.core.stfb;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.wonders.module.common.ExecuteSql;

/** 
 * @ClassName: StfbNews 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-8 下午01:29:30 
 *  
 */
public class StfbMoreNews {
	public static List<MorePublishVo> findMoreNews(String sj_id,String part_id, int num){
		String sWhere = "";
		String sql = "";
		List<MorePublishVo> list = new ArrayList<MorePublishVo>();
		
		
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
		String today = format.format(new Date());
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		
		List<ColumnsVo> listColumns= findNameList(sj_id);
		if(listColumns!=null&&listColumns.size()>0){
			for(int i=0;i<listColumns.size();i++){
				sWhere = " where t.SJ_id = s.id and s.id="+listColumns.get(i).getId();
				if(!"".equals(part_id)){
					sWhere += "and t.part_id="+part_id;
				}
				sWhere += " and t.publish_flag=1 and " +
						" ( t.end_time > '" + today + "' or t.end_time is  null) " +
						" and (t.del_flag!='1' or t.del_flag is null) and " +
						" to_char(t.create_time,'yyyy-MM-dd')<='"+today+"' order by s.sequence asc,t.seq asc,t.create_time desc, t.id desc";
				sql +=  "select * from( select t.id,t.title,t.url,t.create_time,t.seq,t.focus_flag,t.keywords,t.source,t.publish_flag," + 
				            "t.browse_num,t.SJ_id,t.TP_id,t.identified_no,t.title_flag,t.source_flag,t.create_time_flag,t.part_id,s.sequence,s.url node_url,s.name" + 
				            " from tb_content t, tb_subject s " + sWhere+") where rownum<="+num;
				if(i!=listColumns.size()-1){
					sql += " union all ";
				}
			}
			try{
				//System.out.println("sql=:"+sql);
				ResultSet rs=dealsql.ExecuteDemandSql(sql);
				while(rs.next()){
					 MorePublishVo vo = new MorePublishVo();
				        vo.setId(rs.getInt("id"));
				        vo.setTitle(rs.getString("title"));
				        vo.setUrl(rs.getString("url"));
				        vo.setCreate_time(rs.getDate("create_time").toString());
				        vo.setSeq(rs.getInt("seq"));
				        vo.setFocus_flag(rs.getString("focus_flag"));
				        vo.setKeywords(rs.getString("keywords"));
				        vo.setSource(rs.getString("source"));
				        vo.setPublish_flag(rs.getString("publish_flag"));
				        vo.setBrowse_num(rs.getInt("browse_num"));
				        vo.setSJ_id(rs.getInt("SJ_id"));
				        vo.setTP_id(rs.getInt("TP_id"));
				        vo.setIdentified_no(rs.getInt("identified_no"));
				        vo.setTitle_flag(rs.getString("title_flag"));
				        vo.setSource_flag(rs.getString("source_flag"));
				        vo.setCreate_time_flag(rs.getString("create_time_flag"));
				        vo.setPart_id(rs.getInt("part_id"));
				        vo.setSequence(rs.getInt("sequence"));
				        vo.setNode_url(rs.getString("node_url"));
				        vo.setName(rs.getString("name"));
				        list.add(vo);
				}
				rs.close();
				dealsql.close();
			}catch(Exception e){
				//System.out.println("getDeptNameById has an error::"+e);
			}
		}
		
//		sWhere = " where t.SJ_id = s.id and s.parentid="+sj_id;
//		if(!"".equals(part_id)){
//			sWhere += "and t.part_id="+part_id;
//		}
//		sWhere += " and t.publish_flag=1 and " +
//				" ( t.end_time > '" + today + "' or t.end_time is  null) " +
//				" and (t.del_flag!='1' or t.del_flag is null) and " +
//				" to_char(t.create_time,'yyyy-MM-dd')<='"+today+"' order by s.sequence asc,t.seq asc,t.create_time desc, t.id desc";
//		String sql =  "select * from( select t.id,t.title,t.url,t.create_time,t.seq,t.focus_flag,t.keywords,t.source,t.publish_flag," + 
//		            "t.browse_num,t.SJ_id,t.TP_id,t.identified_no,t.title_flag,t.source_flag,t.create_time_flag,t.part_id,s.sequence,s.url node_url,s.name" + 
//		            " from tb_content t, tb_subject s " + sWhere+") where rownum<="+num;
//		System.out.println("sql=="+sql); 
		
		return list;
	}
	
	public static List<ColumnsVo> findNameList(String parentId){
		String sql = "select t.id,t.name from tb_subject t where t.parentid = "+parentId+" order by t.sequence";
		List<ColumnsVo> list = new ArrayList<ColumnsVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){	 
				ColumnsVo vo = new ColumnsVo();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
//	public static SubjectVo findByID(int sj_id){
//		String sql = "select * from tb_subject where id =" + sj_id;
//		ExecuteSql dealsql= new ExecuteSql("dataSource3");
//		SubjectVo vo = new SubjectVo();
//		try{
//			//System.out.println("sql=:"+sql);
//			ResultSet rs=dealsql.ExecuteDemandSql(sql);
//			if(rs.next()){	 
//				 vo.setId(rs.getInt("id"));
//			     vo.setName(rs.getString("name"));
//			     vo.setParentid(rs.getInt("parentid"));
//			     vo.setUrl(rs.getString("url"));
//			     vo.setDir(rs.getString("dir"));
//			}
//			rs.close();
//			dealsql.close();
//		}catch(Exception e){
//			//System.out.println("getDeptNameById has an error::"+e);
//		}
//		return vo;
//	}
//
//
//
//public static List<PicNewsVo> picNews(String sWhere,String sj_id,int num,boolean pic_needed){
//	List<PicNewsVo> list = new ArrayList<PicNewsVo>();
//	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//	format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
//	String today = format.format(new Date());
//	ExecuteSql dealsql= new ExecuteSql("dataSource3");
//	String sql= "";
//	String numWhere = "";
//	if(num>0){
//		numWhere = " where rownum <= "+num;
//	}
//	
//	if(!pic_needed){
//	sql = "select * from ("
//			+" select * from ("
//			+" (select * from (select * from tb_content tb where tb.sj_id in ("+sj_id+") and tb.del_flag = '0' and tb.publish_flag = '1'"
//			+" and to_char(tb.create_time, 'YYYY-MM-DD') <= '"+today+"' " + sWhere
//			+" order by seq asc, create_time desc,id desc) where 1=1) tb"
//			+" left join "
//			+" (select a.savedir,a.savename,a.resourceid,a.memo from attach a) a "
//			+" on (a.resourceid = tb.attachid))"
//			+" where 1=1  order by seq asc, create_time desc,id desc"
//			+") "+numWhere+"";
//	}else{
//		sql = "select * from ("
//			+" select * from ("
//			+" (select * from (select * from tb_content tb where tb.sj_id in ("+sj_id+") and tb.del_flag = '0' and tb.publish_flag = '1'"
//			+" and to_char(tb.create_time, 'YYYY-MM-DD') <= '"+today+"' " + sWhere
//			+" order by seq asc, create_time desc,id desc)) tb"
//			+" join "
//			+" (select a.savedir,a.savename,a.resourceid,a.memo from attach a) a "
//			+" on (a.resourceid = tb.attachid))"
//			+" where 1=1  order by seq asc, create_time desc,id desc"
//			+") "+numWhere+"";
//	}
//	
//	
//			
//	try{
//		ResultSet rs=dealsql.ExecuteDemandSql(sql);
//		while(rs.next()){
//			PicNewsVo vo = new PicNewsVo();
//			vo.setNid(rs.getInt("id"));
//			vo.setNtitle(rs.getString("title"));
//			vo.setNpic(rs.getString("savedir")+"/"+rs.getString("savename"));
//			vo.setNcreate_time(rs.getString("create_time").substring(0,10));
//			vo.setNsj_id(rs.getInt("sj_id"));
//			vo.setNi_no(rs.getInt("identified_no"));
//			String nmemo = rs.getString("memo");
//			if("".equals(nmemo)||nmemo==null){
//				String content = rs.getString("content");
//				content = StfbPage.splitAndFilterString(content,150);
//				vo.setNmemo(content);
//			}else{
//				vo.setNmemo(nmemo);
//			}
//			
//			list.add(vo);
//		}
//		rs.close();
//		dealsql.close();
//	}catch(Exception e){
//		//System.out.println("getDeptNameById has an error::"+e);
//	}
//		return list;
//  
//
//}
	
/*
 * section1 = "474";//网络运营动态





	section2 = "394";//网络运营通知
	section3 = "492";//运营全路网介绍





	section4 = "534";//每日剪报
	section5 = "493";//舆情分析
	section6 = "432";//每日签报
	section7 = "396";//运营日报
	section8 = "574";//运营月报
	section9 = "575";//运营年报
	section10 = "594";//运营信息
	section11 = "654";//基层党建
	section12 = "655";//企业文化
	section13 = "656";//文化现象大讨论





	section14 = "754";//运营分析
	section15 = "814";//电视直播简报

	section16 = "1174"; // 领导登乘



	section19 = "974";//安全生产
	section20 = "994";//服务月报
	
	section21 = "1014";//运营E报



	section22 = "1015";//直播月报
	section23 = "1016";//信访月报
	section24 = "1017";//IT月报
	
	section25 = "995";//网络运营快报
 * 
 * */
}
