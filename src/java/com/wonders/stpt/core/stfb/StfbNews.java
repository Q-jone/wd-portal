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
public class StfbNews {
	public static List<PublishVo> findNews(String sj_id,String part_id, int num,boolean isContent){
		List<PublishVo> list = new ArrayList<PublishVo>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
		String today = format.format(new Date());
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		String sWhere = " where SJ_id in ("+sj_id+")";
		//String sWhere = " where SJ_id ="+sj_id;
		if(!"".equals(part_id)){
			sWhere += "and part_id="+part_id;
		}
		sWhere += " and publish_flag=1 and " +
				" ( end_time > '" + today + "' or end_time is  null) " +
				" and (del_flag!='1' or del_flag is null) and " +
				" to_char(create_time,'yyyy-MM-dd')<='"+today+"' order by seq asc,create_time desc, id desc";
		String sql =  "select * from( select content,id,title,url,create_time,seq,focus_flag,keywords,source,publish_flag," + 
		            "browse_num,SJ_id,TP_id,identified_no,title_flag,source_flag,create_time_flag,part_id" + 
		            " from tb_content " + sWhere+") where rownum<="+num;
		      
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				 PublishVo vo = new PublishVo();
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
			        if(isContent){
						String content = rs.getString("content");
						content = StfbPage.splitAndFilterString(content,150);
						vo.setContent(content);
			        }
			        list.add(vo);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	public static SubjectVo findByID(int sj_id){
		String sql = "select * from tb_subject where id =" + sj_id;
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		SubjectVo vo = new SubjectVo();
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			if(rs.next()){	 
				 vo.setId(rs.getInt("id"));
			     vo.setName(rs.getString("name"));
			     vo.setParentid(rs.getInt("parentid"));
			     vo.setUrl(rs.getString("url"));
			     vo.setDir(rs.getString("dir"));
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return vo;
	}



public static List<PicNewsVo> picNews(String sWhere,String sj_id,int num,boolean pic_needed){
	List<PicNewsVo> list = new ArrayList<PicNewsVo>();
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
	String today = format.format(new Date());
	ExecuteSql dealsql= new ExecuteSql("dataSource3");
	String sql= "";
	String numWhere = "";
	if(num>0){
		numWhere = " where rownum <= "+num;
	}
	
	if(!pic_needed){
	sql = "select * from ("
			+" select * from ("
			+" (select * from (select * from tb_content tb where tb.sj_id in ("+sj_id+") and tb.del_flag = '0' and tb.publish_flag = '1'"
			+" and to_char(tb.create_time, 'YYYY-MM-DD') <= '"+today+"' " + sWhere
			+" order by seq asc, create_time desc,id desc) where 1=1) tb"
			+" left join "
			+" (select a.savedir,a.savename,a.resourceid,a.memo from attach a) a "
			+" on (a.resourceid = tb.attachid))"
			+" where 1=1  order by seq asc, create_time desc,id desc"
			+") "+numWhere+"";
	}else{
		sql = "select * from ("
			+" select * from ("
			+" (select * from (select * from tb_content tb where tb.sj_id in ("+sj_id+") and tb.del_flag = '0' and tb.publish_flag = '1'"
			+" and to_char(tb.create_time, 'YYYY-MM-DD') <= '"+today+"' " + sWhere
			+" order by seq asc, create_time desc,id desc)) tb"
			+" join "
			+" (select a.savedir,a.savename,a.resourceid,a.memo from attach a) a "
			+" on (a.resourceid = tb.attachid))"
			+" where 1=1  order by seq asc, create_time desc,id desc"
			+") "+numWhere+"";
	}
	
	
			
	try{
		ResultSet rs=dealsql.ExecuteDemandSql(sql);
		while(rs.next()){
			PicNewsVo vo = new PicNewsVo();
			vo.setNid(rs.getInt("id"));
			vo.setNtitle(rs.getString("title"));
			vo.setNpic(rs.getString("savedir")+"/"+rs.getString("savename"));
			vo.setNcreate_time(rs.getString("create_time").substring(0,10));
			vo.setNsj_id(rs.getInt("sj_id"));
			vo.setNi_no(rs.getInt("identified_no"));
			String nmemo = rs.getString("memo");
			/*if("".equals(nmemo)||nmemo==null){
				String content = rs.getString("content");
				content = StfbPage.splitAndFilterString(content,150);
				vo.setNmemo(content);
			}else{
				vo.setNmemo(nmemo);
			}*/
			String content = rs.getString("content");
			content = StfbPage.splitAndFilterString(content,150);
			vo.setNmemo(content);
			list.add(vo);
		}
		rs.close();
		dealsql.close();
	}catch(Exception e){
		//System.out.println("getDeptNameById has an error::"+e);
	}
		return list;
  

}
	
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
