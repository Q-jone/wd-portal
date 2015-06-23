package com.wonders.stpt.userMsg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.userMsg.dao.TuserMsgDao;
import com.wonders.stpt.userMsg.entity.bo.TuserMsg;
import com.wonders.stpt.userMsg.entity.vo.UserSearchVo;
import com.wonders.stpt.userMsg.service.TuserMsgService;

@Service("tuserMsgService")
@Scope("prototype")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TuserMsgServiceImpl implements TuserMsgService{
	private TuserMsgDao tuserMsgDao;

	public TuserMsgDao getTuserMsgDao() {
		return tuserMsgDao;
	}
	@Autowired(required=false)
	public void setTuserMsgDao(@Qualifier(value="tuserMsgDao")TuserMsgDao tuserMsgDao) {
		this.tuserMsgDao = tuserMsgDao;
	}
	
	public void addMsg(TuserMsg bo){
		this.tuserMsgDao.addMsg(bo);
	}
	public void updateMsg(TuserMsg bo){
		this.tuserMsgDao.updateMsg(bo);
	}
	
	public List<UserSearchVo> getUserInfo(String maxRows , String name_startsWith){
		return this.tuserMsgDao.getUserInfo(maxRows, name_startsWith);
	}
	
	public TuserMsg findMsg(String msgId){
		return this.tuserMsgDao.findMsg(msgId);
	}
	//分页获取记录值

	public PageResultSet<TuserMsg> queryByPage(TuserMsg msgBo,int pageSize, int page,String sendStart,String sendEnd,String seeStart,String seeEnd,String receiveFlag) {

		int totalRow = this.tuserMsgDao.getMsgsCounts(msgBo,sendStart,sendEnd,seeStart,seeEnd,receiveFlag); // 计算总记录个数
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	
		//获取该页的记录
		List<TuserMsg> list = this.tuserMsgDao.getMsgsByPage(msgBo, pageinfo.getBeginIndex(), pageinfo.getPageSize(),sendStart,sendEnd,seeStart,seeEnd,receiveFlag); 
		PageResultSet<TuserMsg> pageResultSet = new PageResultSet<TuserMsg>();
		pageResultSet.setList(list);
		pageResultSet.setPageInfo(pageinfo);
		return pageResultSet;

	}
	
	//litter
	public PageResultSet<TuserMsg> queryLitterByPage(TuserMsg msgBo,int pageSize, int page,String sendStart,String sendEnd,String seeStart,String seeEnd) {

		int totalRow = this.tuserMsgDao.getLitterMsgsCounts(msgBo,sendStart,sendEnd,seeStart,seeEnd); // 计算总记录个数
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);	
		//获取该页的记录
		List<TuserMsg> list = this.tuserMsgDao.getLitterMsgsByPage(msgBo, pageinfo.getBeginIndex(), pageinfo.getPageSize(),sendStart,sendEnd,seeStart,seeEnd); 
		PageResultSet<TuserMsg> pageResultSet = new PageResultSet<TuserMsg>();
		pageResultSet.setList(list);
		pageResultSet.setPageInfo(pageinfo);
		return pageResultSet;

	}
	
	
	
	
	//接口----------------------------------------------------------------------------------
	//1 批量发送通知 sendMode = 0 admin =1  发送
	public void addMsg(String sid,String rids,String title,String content){
		String sendDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		String sendMode = "1"; // 1用户 0系统
		if("1".equals(sid)){
			sendMode = "0";
		}
		String result = "0";
		String sname = this.tuserMsgDao.getUserName(sid);
		if(sname!=null&&!"".equals(sname)&&sid!=null&&!"".equals(sid)){
			if(rids!=null&&!"".equals(rids)){
				String[] rd = rids.split(",");
				for(String rid:rd){
					String rname = this.tuserMsgDao.getUserName(rid);
					if(rname!=null&&!"".equals(rname)){
						TuserMsg bo = new TuserMsg();
						bo.setSendMode(sendMode);
						bo.setRid(Long.parseLong(rid));
						bo.setSid(Long.parseLong(sid));
						bo.setSeeState("0");
						bo.setSendState("1");
						bo.setSendDate(sendDate);
						bo.setSname(sname);
						bo.setRname(rname);
						bo.setTitle(title);
						bo.setContent(content);
						this.tuserMsgDao.addMsg(bo);
					}
				}		
			}
		}
	}
}
