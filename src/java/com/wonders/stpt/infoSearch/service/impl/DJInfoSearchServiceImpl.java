/**
 * 
 */
package com.wonders.stpt.infoSearch.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.core.stfb.DJStfbNews;
import com.wonders.stpt.core.stfb.DJStfbPage;
import com.wonders.stpt.core.stfb.PicNewsVo;
import com.wonders.stpt.core.stfb.PublishVo;
import com.wonders.stpt.core.stfb.StfbNews;
import com.wonders.stpt.core.stfb.StfbPage;
import com.wonders.stpt.core.stfb.SubjectVo;
import com.wonders.stpt.infoSearch.entity.vo.InfoDetailVo;
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.infoSearch.service.DJInfoSearchService;

/** 
 * @ClassName: InfoSearchServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午05:58:33 
 *  
 */
public class DJInfoSearchServiceImpl implements DJInfoSearchService{
	
	/**
	 * 党纪工团首页显示法规服务
	 */
	public List<StfbVo> findLatestStfbNews(String sj_id,String part_id,int num,String parent_id){
		List<StfbVo> stfbList = new ArrayList<StfbVo>();
		List<PublishVo> list = DJStfbNews.findNews(sj_id,part_id,num,parent_id);
		int identified_no = 0;
		int SJ_id = 0;
		int PART_id = 0;
		String s_title = "";
		String copy_title = "";
		String create_time = "";
		String dir ="";
		String seq = "";
		if(list!=null&&list.size()>0){
		for(PublishVo p : list){
			PART_id = p.getPart_id();
		  identified_no = p.getIdentified_no();
		  s_title = p.getTitle();
		  copy_title = s_title;
		  seq = p.getSeq()+"";
		  if(s_title.length()>25)   s_title = s_title.substring(0,25)+"...";
		  create_time = p.getCreate_time();
		  SJ_id = p.getSJ_id();
		  
		  SubjectVo oSub = StfbNews.findByID(SJ_id);
		  if (oSub.getParentid()!= 0) {
			  SubjectVo oSub2 = StfbNews.findByID(oSub.getParentid());
			//SJ_name = oSub2.getName() + "-" + oSub.getName();
			dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
		  }
		  else {
			//SJ_name = oSub.getName();
			dir = "/" + oSub.getDir();
		  }
		  
		  StfbVo s = new StfbVo();
		  s.setSJ_ID(SJ_id+"");
		  s.setTitle(s_title);
		  s.setCopyTitle(copy_title);
		  s.setPartId(PART_id+"");
		  s.setCreateTime(create_time);
		  s.setUrl(dir+"/"+create_time.substring(0,4)+create_time.substring(5,7)+"/con"+identified_no);
		  s.setMoreUrl(dir+"/index.htm");
		  s.setSeq(p.getSeq()+"");
		  
		  stfbList.add(s);
		}
		  
		}
		return stfbList;
	}
	
	/**
	 * 查询所有子栏目
	 */
	@Override
	public List<SubjectVo> findNewsByType(String parent_id) {
		return DJStfbNews.findNewsByType(parent_id);
	}
	
	/**
	 * 分页查询栏目下的数据
	 */
	@Override
	public PageResultSet<InfoDetailVo> findDataByPage(String sj_id,String searchWord,int pageSize,int page) {
	
		int totalRow = DJStfbPage.getAllResultCount(sj_id,searchWord,pageSize,page); // 计算总记录个数
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	
		//获取该页的记录
		List<InfoDetailVo> list = DJStfbPage.getAllResult(sj_id,searchWord, pageinfo.getBeginIndex(), pageinfo.getPageSize()); 
		PageResultSet<InfoDetailVo> pageResultSet = new PageResultSet<InfoDetailVo>();
		pageResultSet.setList(list);
		pageResultSet.setPageInfo(pageinfo);
		return pageResultSet;
	}
	
	
	
	/**
	 * 从相关栏目下，分页取数据
	 */
	public PageResultSet<InfoDetailVo> queryByPage(String sj_id,String part_id,String searchWord,int pageSize, int page) {

		int totalRow = StfbPage.getAllResultCount(sj_id, part_id, searchWord); // 计算总记录个数
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	

		//获取该页的记录

		List<InfoDetailVo> list = StfbPage.getAllResult(sj_id, part_id, searchWord, pageinfo.getBeginIndex(), pageinfo.getPageSize()); 
		
		PageResultSet<InfoDetailVo> pageResultSet = new PageResultSet<InfoDetailVo>();

		pageResultSet.setList(list);

		pageResultSet.setPageInfo(pageinfo);

		return pageResultSet;

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
				//SJ_name = oSub2.getName() + "-" + oSub.getName();
				dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
			}
			else {
				//SJ_name = oSub.getName();
				dir = "/" + oSub.getDir();
			}
		  
			StfbVo s = new StfbVo();
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
				//SJ_name = oSub2.getName() + "-" + oSub.getName();
				dir = "/" + oSub2.getDir() + "/" + oSub.getDir();
			}
			else {
				//SJ_name = oSub.getName();
				dir = "/" + oSub.getDir();
			}
		  
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


	



	

	
	

}
