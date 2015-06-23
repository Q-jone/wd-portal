package com.wonders.stpt.userMsg.dao;

import java.util.List;

import com.wonders.stpt.userMsg.entity.bo.TuserMsg;
import com.wonders.stpt.userMsg.entity.vo.UserSearchVo;

public interface TuserMsgDao {
	public void addMsg(TuserMsg bo);
	public void updateMsg(TuserMsg bo);
	public List<TuserMsg> getMsgsByPage(TuserMsg msgBo,int first,int size,String sendStart,String sendEnd,String seeStart,String seeEnd,String receiveFlag );
	public int getMsgsCounts(TuserMsg msgBo,String sendStart,String sendEnd,String seeStart,String seeEnd,String receiveFlag);
	public List<TuserMsg> getLitterMsgsByPage(TuserMsg msgBo,int first,int size,String sendStart,String sendEnd,String seeStart,String seeEnd );
	public int getLitterMsgsCounts(TuserMsg msgBo,String sendStart,String sendEnd,String seeStart,String seeEnd);
	public List<UserSearchVo> getUserInfo(String maxRows , String name_startsWith);
	public TuserMsg findMsg(String msgId);
	public String getUserName(String userId);
}
