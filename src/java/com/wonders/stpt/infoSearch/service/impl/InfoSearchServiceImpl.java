/**
 * 
 */
package com.wonders.stpt.infoSearch.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.PicNewsVo;
import com.wonders.stpt.core.stfb.PublishVo;
import com.wonders.stpt.core.stfb.StfbNews;
import com.wonders.stpt.core.stfb.StfbPage;
import com.wonders.stpt.core.stfb.SubjectVo;
import com.wonders.stpt.core.stfb.ZTTP;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.infoSearch.service.InfoSearchService;

/** 
 * @ClassName: InfoSearchServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午05:58:33 
 *  
 */
public class InfoSearchServiceImpl implements InfoSearchService{
	//分页获取记录值

	public PageResultSet<InfoDetailVo> queryByPage(String sj_id,String part_id,String searchWord,int pageSize, int page) {
		int totalRow = StfbPage.getAllResultCount(sj_id, part_id, searchWord); // 计算总记录个数
		//System.out.println("totalRow"+totalRow);
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	

		//获取该页的记录

		List<InfoDetailVo> list = StfbPage.getAllResult(sj_id, part_id, searchWord, pageinfo.getBeginIndex(), pageinfo.getPageSize()); 
		//System.out.println("lisr"+list.size());
		//System.out.println("-----------------------"+list.get(0).getContent());
		PageResultSet<InfoDetailVo> pageResultSet = new PageResultSet<InfoDetailVo>();

		pageResultSet.setList(list);

		pageResultSet.setPageInfo(pageinfo);

		return pageResultSet;

	}
	
	public List<StfbVo> findLatestStfbNews(String sj_id,String partId, int num, boolean isContent){
		List<StfbVo> stfbList = new ArrayList<StfbVo>();
		List<PublishVo> list = StfbNews.findNews(sj_id, partId, num,isContent);
		int identified_no = 0;
		int SJ_id = 0;
		int part_id = 0;
		String s_title = "";
		String copy_title = "";
		String create_time = "";
		String dir ="";
		String sbName = "";
		if(list!=null&&list.size()>0){
		for(PublishVo p : list){
			part_id = p.getPart_id();
		  identified_no = p.getIdentified_no();
		  s_title = p.getTitle();
		  copy_title = s_title;
		  if(s_title.length()>25)   s_title = s_title.substring(0,25)+"...";
		  create_time = p.getCreate_time();
		  SJ_id = p.getSJ_id();
		  
		  SubjectVo oSub = StfbNews.findByID(SJ_id);
		  sbName = oSub.getName();
		  if (oSub.getParentid()!= 0) {
			  SubjectVo oSub2 = StfbNews.findByID(oSub.getParentid());
			  SubjectVo oSub3 = StfbNews.findByID(oSub2.getParentid());
			//SJ_name = oSub2.getName() + "-" + oSub.getName();
			  if("".equals(oSub3.getDir())){
				  dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
			  }else{
				  dir = "/" + oSub3.getDir() + "/" + oSub2.getDir() + "/" + oSub.getDir();
			  }
		  }
		  else {
			//SJ_name = oSub.getName();
			dir = "/" + oSub.getDir();
		  }
		  dir =  oSub.getUrl();
		  StfbVo s = new StfbVo();
		  s.setSbName(sbName);
		  s.setSJ_ID(SJ_id+"");
		  s.setTitle(s_title);
		  s.setCopyTitle(copy_title);
		  s.setPartId(part_id+"");
		  s.setCreateTime(create_time);
		  s.setUrl(dir+"/"+create_time.substring(0,4)+create_time.substring(5,7)+"/con"+identified_no);
		  s.setMoreUrl(dir+"/index.htm");
		  if(isContent){
			  s.setContent(p.getContent());
		  }
		  stfbList.add(s);
		}
		  
		}
		return stfbList;
	}
	
	
	public List<StfbVo> findLatestStfbPicNews(String sj_id,int num,boolean pic_needed){
		List<StfbVo> stfbList = new ArrayList<StfbVo>();
		List<PicNewsVo> list =  new ArrayList<PicNewsVo>(); 
		if(sj_id!=null&&sj_id.indexOf(",")>0){
			list = StfbNews.picNews(" and tb.focus_flag=2 ", sj_id, num, pic_needed);
		}else{
			list = StfbNews.picNews("", sj_id, num, pic_needed);
		}
		int SJ_id = 0;
		int identified_no = 0;
		String s_title = "";
		String copy_title = "";
		String create_time = "";
		String dir ="";
		String picUrl = "";
		if(list!=null&&list.size()>0){
		for(PicNewsVo p : list){
			SJ_id = p.getNsj_id();
			identified_no = p.getNi_no();
			s_title = p.getNtitle();
			copy_title = s_title;
			if(s_title.length()>30)   s_title = s_title.substring(0,30)+"...";
			create_time = p.getNcreate_time();
			if(pic_needed){
				picUrl = p.getNpic();
			}
			
			SubjectVo oSub = StfbNews.findByID(SJ_id);
			if (oSub.getParentid()!= 0) {
				SubjectVo oSub2 = StfbNews.findByID(oSub.getParentid());
				 SubjectVo oSub3 = StfbNews.findByID(oSub2.getParentid());
				//SJ_name = oSub2.getName() + "-" + oSub.getName();
				  if("".equals(oSub3.getDir())){
					  dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
				  }else{
					  dir = "/" + oSub3.getDir() + "/" + oSub2.getDir() + "/" + oSub.getDir();
				  }
			}
			else {
				//SJ_name = oSub.getName();
				dir = "/" + oSub.getDir();
			}
			dir =  oSub.getUrl();
			StfbVo s = new StfbVo();
			s.setMemo(p.getNmemo());
			s.setSJ_ID(SJ_id+"");
			s.setTitle(s_title);
			s.setCopyTitle(copy_title);
			s.setCreateTime(create_time);
			s.setUrl(dir+"/"+create_time.substring(0,4)+create_time.substring(5,7)+"/con"+identified_no);
			if(pic_needed){
				s.setPicUrl(picUrl);
			}
			stfbList.add(s);
		}
		  
		}
		return stfbList;
	}
	
	public List<StfbVo> findLatestHeadStfbHeadNews(String sj_id){
		List<StfbVo> stfbList = new ArrayList<StfbVo>();
		List<PicNewsVo> list = StfbNews.picNews(" and tb.focus_flag=2 ", sj_id, 1, false);
		if(list!=null&&list.size()>=1){
			
		}else{
			list = StfbNews.picNews("", sj_id, 1, false);
		}
		int SJ_id = 0;
		int identified_no = 0;
		String s_title = "";
		String copy_title = "";
		String create_time = "";
		String dir ="";
		String memo = "";
		String copyMemo = "";
		if(list!=null&&list.size()>0){
			PicNewsVo p = list.get(0);
			SJ_id = p.getNsj_id();
			identified_no = p.getNi_no();
			s_title = p.getNtitle();
			memo = p.getNmemo();
			copy_title = s_title;
			if(s_title.length()>25)   s_title = s_title.substring(0,25)+"...";
			copyMemo = memo;
			if(memo.length()>70)   memo = memo.substring(0,70)+"...";
			create_time = p.getNcreate_time();
			
			SubjectVo oSub = StfbNews.findByID(SJ_id);
			if (oSub.getParentid()!= 0) {
				SubjectVo oSub2 = StfbNews.findByID(oSub.getParentid());
				 SubjectVo oSub3 = StfbNews.findByID(oSub2.getParentid());
				//SJ_name = oSub2.getName() + "-" + oSub.getName();
				  if("".equals(oSub3.getDir())){
					  dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
				  }else{
					  dir = "/" + oSub3.getDir() + "/" + oSub2.getDir() + "/" + oSub.getDir();
				  }
			}
			else {
				//SJ_name = oSub.getName();
				dir = "/" + oSub.getDir();
			}
			dir =  oSub.getUrl();
			StfbVo s = new StfbVo();
			s.setSJ_ID(SJ_id+"");
			s.setTitle(s_title);
			s.setCopyTitle(copy_title);
			s.setCreateTime(create_time);
			s.setUrl(dir+"/"+create_time.substring(0,4)+create_time.substring(5,7)+"/con"+identified_no);
			s.setMemo(memo);
			s.setCopyMemo(copyMemo);
			stfbList.add(s);
		}
		  
		return stfbList;
	}
	
	
	public List<ZTTP> findZTTPNews(String sWhere,int size){
		return ZTTP.listZTTP(sWhere, size);
	}
	
	public static void main(String[] args){
		File src = new File("G:/2012/portal/login.jsp");
		File tar = new File("G:/2012/portal/loginZs.jsp");
		File tarDir = new File("G:/2012/portal/center/");
		 System.out.println(src.getParent());//获取父目录及上级
		Calendar currentDate = Calendar.getInstance();
		try {
			java.util.Date d1 = new java.text.SimpleDateFormat("yyyy-MM-dd").parse("2012-10-18");
			java.util.Date d2 = new java.text.SimpleDateFormat("yyyy-MM-dd").parse("2012-10-19");
			System.out.println(new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new java.util.Date()));
			Calendar cal=Calendar.getInstance();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(new java.util.Date());
			cal.add(Calendar.DATE, -1);  //减1天
			System.out.println(sdf.format(cal.getTime()));
			System.out.println((d2.getTime()-d1.getTime())/1000/60/60);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		currentDate.getTime().getTime();
		try {
			//文本写入指定文件   
	        String name = "my name is panxiuyan";         
	        File file =  new File("g:\\name.txt");    
	       
	        FileUtils.writeStringToFile(file, name);
	        BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream("g:\\kk.dat"));    
	        InputStream ins = new FileInputStream(new File("g:\\name.txt"));    
	        IOUtils.copy(ins, write); 
	        write.flush();
	        write.close();
	        ins.close();
			FileUtils.copyFile(src,tar);
			FileUtils.copyFileToDirectory(src, tarDir);
			 System.out.println(file.getParent());//获取父目录及上级
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
