package com.wonders.stpt.userMsg.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.userMsg.entity.bo.TuserMsg;
import com.wonders.stpt.userMsg.entity.vo.UserSearchVo;

public interface TuserMsgService {
	public void addMsg(TuserMsg bo);
	public void updateMsg(TuserMsg bo);
	public TuserMsg findMsg(String msgId);
	//获取用户信息
	public List<UserSearchVo> getUserInfo(String maxRows , String name_startsWith);
	//分页获取记录值
	public PageResultSet<TuserMsg> queryByPage(TuserMsg msgBo,int pageSize, int page,String sendStart,String sendEnd,String seeStart,String seeEnd,String receiveFlag);
	//分页获取记录值
	public PageResultSet<TuserMsg> queryLitterByPage(TuserMsg msgBo,int pageSize, int page,String sendStart,String sendEnd,String seeStart,String seeEnd);

	public void addMsg(String sid,String rids,String title,String content);
}
