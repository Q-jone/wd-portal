/**
 * 
 */
package com.wonders.stpt.core.stfb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
/** 
 * @ClassName: StfbPage 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午01:29:27 
 *  
 */
public class StfbPage {
	public static int getAllResultCount(String sj_id,String part_id,String searchWord){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
		String today = format.format(new Date());
		int count = 0;
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		String sql1 = "select c.id,c.identified_no, c.title,c.seq,c.create_time,c.keywords," +
				"c.content,c.url,s.name from tb_content c, tb_subject s " +
				"where c.sj_id =s.id and to_char(c.create_time, 'YYYY-MM-DD') <= '"+today+"'"+
				" and publish_flag='1' and (del_flag is null or del_flag !='1')";
		
		String sql2 = "select c.id,c.identified_no, c.title,c.seq,c.create_time,c.keywords," +
				"c.content,c.url,m.name from tb_content c, tb_cascade_menu m " +
				"where c.part_id = m.id and publish_flag='1' and to_char(c.create_time, 'YYYY-MM-DD') <= '"+today+"'"+
				" and (del_flag is null or del_flag !='1')";
		String sql = null;
		
		String sqlTop = "";
		if(!"".equals(sj_id)&&!"0".equals(sj_id)){			
			sqlTop = "and c.sj_id in ("+ sj_id+") ";
		}
		if(sj_id!=null&&sj_id.indexOf(",")>0){		
			sqlTop += " and c.focus_flag=2 ";
		}
		String sqlPart = "";
		if(!"".equals(part_id)&&!"0".equals(part_id)){				
			sqlPart = "and c.part_id in ("+ part_id+") ";
		}
		String sqlTail = "";
		if(!"".equals(searchWord)){
			sqlTail = " and title like '%"+searchWord+"%'";
		}

		if(!"".equals(part_id)&&!"0".equals(part_id)){			
				sql = sql2+sqlTop+sqlPart+sqlTail;
		}else{
		sql = sql1+sqlTop+sqlPart+sqlTail+" union all "+sql2+sqlTop+sqlPart+sqlTail;
		}
		sql = "select * from ("+sql+")";	
		
		//String sqlOrder = " order by seq asc,create_time desc,id desc ";
		sql = "select count(*) as count  from ("+sql+")";
		
		try{
			//System.out.println("sql1=:"+sql);
			ResultSet rs= dealsql.ExecuteDemandSql(sql);
			
			while(rs.next()){
				count = rs.getInt("count");
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return count;
	}
	
	
	public static List<InfoDetailVo> getAllResult(String sj_id,String part_id,String searchWord,int beginIndex,int pageSize){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
		String today = format.format(new Date());
		List<InfoDetailVo> list = new ArrayList<InfoDetailVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource3");
		String sql1 = "select c.id,c.identified_no, c.title,c.seq,c.create_time,c.keywords," +
				"c.content,c.url,s.name from tb_content c, tb_subject s " +
				"where c.sj_id =s.id and to_char(c.create_time, 'YYYY-MM-DD') <= '"+today+"'"+
				" and publish_flag='1' and (del_flag is null or del_flag !='1')";
		
		String sql2 = "select c.id,c.identified_no, c.title,c.seq,c.create_time,c.keywords," +
				"c.content,c.url,m.name from tb_content c, tb_cascade_menu m " +
				"where c.part_id = m.id and publish_flag='1' and to_char(c.create_time, 'YYYY-MM-DD') <= '"+today+"'"+
				" and (del_flag is null or del_flag !='1')";
		String sql = null;
		String sqlTop = "";
		
		if(!"".equals(sj_id)&&!"0".equals(sj_id)){			
			sqlTop = "and c.sj_id in ("+ sj_id+") ";
		}
		if(sj_id!=null&&sj_id.indexOf(",")>0){		
			sqlTop += " and c.focus_flag=2 ";
		}
		String sqlPart = "";
		if(!"".equals(part_id)&&!"0".equals(part_id)){				
			sqlPart = "and c.part_id in ("+ part_id+") ";
		}
		String sqlTail = "";
		if(!"".equals(searchWord)){
			sqlTail = " and title like '%"+searchWord+"%'";
		}

		if(!"".equals(part_id)&&!"0".equals(part_id)){			
			sql = sql2+sqlTop+sqlPart+sqlTail;
		}else{
			sql = sql1+sqlTop+sqlPart+sqlTail+" union all "+sql2+sqlTop+sqlPart+sqlTail;
		}
		sql = "select * from ("+sql+")";	
		String sqlOrder = " order by seq asc,create_time desc,id desc ";
		sql = "select * from ("+sql+")"+ sqlOrder;
		
		String sqlFinal = "select * from (select rownum as my_rownum,table_a.* from("+sql+") table_a where rownum<="+String.valueOf(beginIndex+pageSize)+") where my_rownum>="+String.valueOf(beginIndex+1);
		ResultSet rs = dealsql.ExecuteDemandSql(sqlFinal);
		try{
			//System.out.println("sql2=:"+sqlFinal);
			
			while(rs.next()){
				String create_time = (rs.getDate("create_time")).toString();
				String title = highlight(rs.getString("title"),searchWord);
				String content = rs.getClob("content").getSubString((long)1,(int)rs.getClob("content").length());
				String category = rs.getString("name");
				String id = rs.getInt("id")+"";
				
				String identifiedNo = rs.getInt("identified_no")+"";
				String url = rs.getString("url");
				content = splitAndFilterString(content,150);
				content = highlight(content,searchWord);
				InfoDetailVo vo = new InfoDetailVo();
				vo.setCreateTime(create_time);
				vo.setTitle(title);
				vo.setCategory(category);
				vo.setId(id);
				vo.setIdentifiedNo(identifiedNo);
				vo.setContent(content);
				vo.setUrl(url);
				list.add(vo);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	
	  /** 
     * 删除input字符串中的html格式 
     *  
     * @param input 
     * @param length 
     * @return 
     */  
    public static String splitAndFilterString(String input, int length) {  
        if (input == null || input.trim().equals("")) {  
            return "";  
        }  
        // 去掉所有html元素,  
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
                "<[^>]*>", "");  
        str = str.replaceAll("[(/>)<]", "");  
        int len = str.length();  
        if (len <= length) {  
            return str;  
        } else {  
            str = str.substring(0, length);  
            str += "......";  
        }  
        return str;  
    } 
    public static String highlight(String source,String searchWord){
		if(!"".equals(searchWord)&&searchWord!=null){
			String highlight = "<font class=\"L_01\">"+searchWord+"</font>";
			source = source.replaceAll(searchWord, highlight);
		}
		return source;
	}
	
}
