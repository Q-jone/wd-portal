/**
 * 
 */
package com.wonders.stpt.jeecms.service.impl;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.wonders.stpt.infoSearch.entity.vo.StfbVo;
import com.wonders.stpt.jeecms.ShowJeecmsInfo;
import com.wonders.stpt.jeecms.StringUtil;
import com.wonders.stpt.jeecms.dao.JeecmsInfoDao;
import com.wonders.stpt.jeecms.entity.vo.InfoChannelVo;
import com.wonders.stpt.jeecms.entity.vo.InfoDetailVo;
import com.wonders.stpt.jeecms.service.JeecmsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @ClassName: InfoSearchServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author huzhiming
 * @date 2013-01-10 下午17:44:33 
 *  
 */
public class JeecmsServiceImpl implements JeecmsService{
	//分页获取记录值
	public PageResultSet<InfoDetailVo> queryByPage(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String channelId,String searchWord,int pageSize, int page,String latestDays) {
		ShowJeecmsInfo dwgk=new ShowJeecmsInfo(servletRequest,servletResponse);

		String sJsonParams="";
		sJsonParams="{'channelId':'"+channelId+"'";
		if(searchWord!=null&&!searchWord.equals("")){
			sJsonParams += ",'title':'"+searchWord+"'";
		}
		if(latestDays!=null&&!latestDays.equals("")){
			sJsonParams += ",'latestDays':'"+latestDays+"'";
		}
		sJsonParams += "}";
		JSONArray jsonResultList = dwgk.getInfoList("JEECMS.SEARCH_CONTENT_NUM",sJsonParams);
		JSONObject obj=null; 
		if(jsonResultList.size()>0){obj=jsonResultList.getJSONObject(0);}
		int totalRow=obj.getInt("contentNum");
		
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	

		//获取该页的记录
		sJsonParams="{'channelId':'"+channelId+"','page':"+page+",'rownum':"+pageSize;
		if(searchWord!=null&&!searchWord.equals("")){
			sJsonParams += ",'title':'"+searchWord+"'";
		}
		if(latestDays!=null&&!latestDays.equals("")){
			sJsonParams += ",'latestDays':'"+latestDays+"'";
		}
		sJsonParams += "}";
		
		 jsonResultList = dwgk.getInfoList("JEECMS.SEARCH_CONTENT_LIST",sJsonParams);
		 
		 if(null==jsonResultList){
			 return null;
		 }
		 List<InfoDetailVo>list=new ArrayList<InfoDetailVo>();
		for(int i=0;i<jsonResultList.size();i++){
			obj=jsonResultList.getJSONObject(i);
			InfoDetailVo info=new InfoDetailVo();
			String description=obj.getString("description");
			String txt=obj.getString("txt");
			if(description=="null"){
				if(txt=="null"){
					description=StringUtil.MySubstring(obj.getString("title"), 150);
				}else{
					description=StringUtil.splitAndFilterString(txt, 150);
				}					
			}else{
				description=StringUtil.MySubstring(description, 150);
			}
			
			info.setChannelName(obj.getString("channelName"));
			info.setContentId(obj.getString("contentId"));
			info.setShortTitle(obj.getString("shortTitle"));
			info.setTitle(obj.getString("title").replace(searchWord, "<font color='red'>"+searchWord+"</font>"));
			info.setDescription(description.replace(searchWord, "<font color='red'>"+searchWord+"</font>"));
			info.setReleaseDate(obj.getString("releaseDate"));
	
			list.add(info);
		}
		
		PageResultSet<InfoDetailVo> pageResultSet = new PageResultSet<InfoDetailVo>();

		pageResultSet.setList(list);

		pageResultSet.setPageInfo(pageinfo);

		return pageResultSet;

	}

	public List<InfoChannelVo> findNewsByType(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String parentId,String latestDays) {
		// TODO Auto-generated method stub
		ShowJeecmsInfo dwgk=new ShowJeecmsInfo(servletRequest,servletResponse);
		String params = "{'parentId':'"+parentId+"'";
		if(latestDays!=null&&!"".equals(latestDays)){
			params += ",'latestDays':'"+latestDays+"'";
		}
		params += "}";
		JSONArray jsonResultList = dwgk.getInfoList("JEECMS.GET_CHANNEL_LIST",params);
		JSONObject obj=null; 

		 List<InfoChannelVo>list=new ArrayList<InfoChannelVo>();
		 if(jsonResultList!=null){
			for(int i=0;i<jsonResultList.size();i++){
				obj=jsonResultList.getJSONObject(i);
				InfoChannelVo info=new InfoChannelVo();
				info.setChannelName(obj.getString("channelName"));
				info.setChannelId(obj.getString("channelId"));
				info.setParentId(obj.getString("parentId"));
				info.setContentNum(obj.getString("contentNum"));
				info.setChannelPath(obj.getString("channelPath"));
				info.setSiteId(obj.getString("siteId"));
		
				list.add(info);
			}
		 }
		
		return list;
	}
	
	private JeecmsInfoDao jeecmsInfoDao;
	
	public JeecmsInfoDao getJeecmsInfoDao() {
		return jeecmsInfoDao;
	}

	public void setJeecmsInfoDao(JeecmsInfoDao jeecmsInfoDao) {
		this.jeecmsInfoDao = jeecmsInfoDao;
	}

	public List<Object> findWsLeaders(){
		return jeecmsInfoDao.findWsLeaders();
	}
}
