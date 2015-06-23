package com.wonders.stpt.userMsg.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.userMsg.entity.bo.TuserMsg;
import com.wonders.stpt.userMsg.entity.vo.UserSearchVo;
import com.wonders.stpt.userMsg.service.TuserMsgService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/userMsg")
@Controller("tuserMsgAction")
@Scope("prototype")
public class TuserMsgAction extends AbstractParamAction implements ModelDriven<TuserMsg>{
	private TuserMsgService tuserMsgService;
	private TuserMsg msgBo = new TuserMsg();
	private PageResultSet<TuserMsg> pageResultSet;
	public TuserMsgService getTuserMsgService() {
		return tuserMsgService;
	}
	@Autowired(required=false)
	public void setTuserMsgService(@Qualifier("tuserMsgService")TuserMsgService tuserMsgService) {
		this.tuserMsgService = tuserMsgService;
	}
	@Override
	public TuserMsg getModel() {
		// TODO Auto-generated method stub
		return msgBo;
	}	
	public TuserMsg getMsgBo() {
		return msgBo;
	}
	public void setMsgBo(TuserMsg msgBo) {
		this.msgBo = msgBo;
	}
	
	public PageResultSet<TuserMsg> getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet<TuserMsg> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="msgReceiveBox",results={
			@Result(name="success",location="/userMsg/msgReceiveBox.jsp")
			})
	public String msgReceiveBox(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		String sendStart = StringUtil.getNotNullValueString(servletRequest.getParameter("sendStart"));
		String sendEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("sendEnd"));
		msgBo.setRid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),sendStart,sendEnd,"","","1");
		servletRequest.setAttribute("sendStart", sendStart);
		servletRequest.setAttribute("sendEnd", sendEnd);
		return SUCCESS;
	}
	
	@Action(value="msgReceiveBoxUnread",results={
			@Result(name="success",location="/userMsg/msgReceiveBoxUnread.jsp")
			})
	public String msgReceiveBoxUnread(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		String sendStart = StringUtil.getNotNullValueString(servletRequest.getParameter("sendStart"));
		String sendEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("sendEnd"));
		msgBo.setSeeState("0");
		msgBo.setRid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),sendStart,sendEnd,"","","1");
		servletRequest.setAttribute("sendStart", sendStart);
		servletRequest.setAttribute("sendEnd", sendEnd);
		return SUCCESS;
	}
	
	@Action(value="msgReceiveBoxAlread",results={
			@Result(name="success",location="/userMsg/msgReceiveBoxAlread.jsp")
			})
	public String msgReceiveBoxAlread(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		String sendStart = StringUtil.getNotNullValueString(servletRequest.getParameter("sendStart"));
		String sendEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("sendEnd"));
		msgBo.setSeeState("1");
		msgBo.setRid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),sendStart,sendEnd,"","","1");
		servletRequest.setAttribute("sendStart", sendStart);
		servletRequest.setAttribute("sendEnd", sendEnd);
		return SUCCESS;
	}
	
	@Action(value="msgSendBox",results={
			@Result(name="success",location="/userMsg/msgSendBox.jsp")
			})
	public String msgSendBox(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		String sendStart = StringUtil.getNotNullValueString(servletRequest.getParameter("sendStart"));
		String sendEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("sendEnd"));
		String seeStart = StringUtil.getNotNullValueString(servletRequest.getParameter("seeStart"));
		String seeEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("seeEnd"));
		msgBo.setSendState("1");
		msgBo.setSid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),sendStart,sendEnd,seeStart,seeEnd,"");
		servletRequest.setAttribute("sendStart", sendStart);
		servletRequest.setAttribute("sendEnd", sendEnd);
		servletRequest.setAttribute("seeStart", seeStart);
		servletRequest.setAttribute("seeEnd", seeEnd);
		return SUCCESS;
	}
	
	@Action(value="msgDraftBox",results={
			@Result(name="success",location="/userMsg/msgDraftBox.jsp")
			})
	public String msgDraftBox(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		msgBo.setSendState("0");
		msgBo.setSid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),"","","","","");
		return SUCCESS;
	}
	
	@Action(value="msgLitterBox",results={
			@Result(name="success",location="/userMsg/msgLitterBox.jsp")
			})
	public String msgLitterBox(){
		String pagesize = StringUtil.getNotNullValueString(servletRequest.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(servletRequest.getParameter("page"));
		String sendStart = StringUtil.getNotNullValueString(servletRequest.getParameter("sendStart"));
		String sendEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("sendEnd"));
		String seeStart = StringUtil.getNotNullValueString(servletRequest.getParameter("seeStart"));
		String seeEnd = StringUtil.getNotNullValueString(servletRequest.getParameter("seeEnd"));
		msgBo.setSid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		msgBo.setRid(Long.parseLong((String)servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_ID)));
		if("".equals(pagesize)){
			pagesize = "10";
		}
		if("".equals(page)){
			page = "1";
		}
		this.pageResultSet = this.tuserMsgService.queryLitterByPage(msgBo, Integer.parseInt(pagesize), Integer.parseInt(page),sendStart,sendEnd,seeStart,seeEnd);
		servletRequest.setAttribute("sendStart", sendStart);
		servletRequest.setAttribute("sendEnd", sendEnd);
		servletRequest.setAttribute("seeStart", seeStart);
		servletRequest.setAttribute("seeEnd", seeEnd);
		return SUCCESS;
	}
	
	@Action(value="msgUpdate",results={
			@Result(name="success",location="/userMsg/msgUpdate.jsp")
			})
	public String msgUpdate(){
		String msgId = StringUtil.getNotNullValueString(servletRequest.getParameter("msgId"));
		this.msgBo = this.tuserMsgService.findMsg(msgId);
		return SUCCESS;
	}
	
	@Action(value="msgAdd",results={
			@Result(name="success",location="/userMsg/msgSuccess.jsp")
	})
	public String msgAdd(){
		String msgId = StringUtil.getNotNullValueString(servletRequest.getParameter("msgId"));
		String rids = StringUtil.getNotNullValueString(servletRequest.getParameter("rids"));
		String sid = StringUtil.getNotNullValueString(servletRequest.getParameter("sid"));
		String sname = StringUtil.getNotNullValueString(servletRequest.getParameter("sname"));
		String rnames = StringUtil.getNotNullValueString(servletRequest.getParameter("rnames"));
		String title = StringUtil.getNotNullValueString(servletRequest.getParameter("title"));
		String content = StringUtil.getNotNullValueString(servletRequest.getParameter("content"));
		String attach = StringUtil.getNotNullValueString(servletRequest.getParameter("attach"));
		String[] rid = rids.split(",");
		String[] rname = rnames.split(",");
		if(!"".equals(msgId)){
			TuserMsg del = this.tuserMsgService.findMsg(msgId);
			if(del!=null){
				del.setSendState("2");
				this.tuserMsgService.updateMsg(del);
			}
		}
		if(rid!=null&&rid.length>0&&rname!=null&&rname.length>0)
		for(int i=0;i<rid.length;i++){
			TuserMsg bo = new TuserMsg();
			bo.setSendState("1");
			bo.setSendMode("1");
			bo.setSeeState("0");
			bo.setSendDate(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
			bo.setTitle(title);
			bo.setContent(content);
			bo.setRid(Long.parseLong(rid[i]));
			bo.setRname(rname[i]);
			bo.setAttach(attach);
			bo.setSid(Long.parseLong(sid));
			bo.setSname(sname);
			this.tuserMsgService.addMsg(bo);
		}
		//ActionWriter aw = new ActionWriter(servletResponse);
		//aw.writeAjax("<script>alert('发送成功！');window.opener=null;window.open('','_self');window.close();</script>");
		return "success";
	}
	
	@Action(value="msgSave",results={
			@Result(name="success",location="/userMsg/msgSuccess.jsp")
	})
	public String msgSave(){
		String msgId = StringUtil.getNotNullValueString(servletRequest.getParameter("msgId"));
		if(!"".equals(msgId)){
			TuserMsg upd = this.tuserMsgService.findMsg(msgId);
			if(upd!=null){
				upd.setContent(msgBo.getContent());
				upd.setTitle(msgBo.getTitle());
				upd.setAttach(msgBo.getAttach());
				this.tuserMsgService.updateMsg(upd);
			}
		}else{
			msgBo.setSendState("0");
			this.tuserMsgService.addMsg(msgBo);
		}
		//ActionWriter aw = new ActionWriter(servletResponse);
		//aw.writeAjax("<script>alert('保存成功！');window.opener=null;window.open('','_self');window.close();</script>");
		return "success";
	}
	
	@Action(value="msgRead",results={
			@Result(name="success",location="/userMsg/msgView.jsp")
			})
	public String msgRead(){
		String msgId = StringUtil.getNotNullValueString(servletRequest.getParameter("msgId"));
		this.msgBo = this.tuserMsgService.findMsg(msgId);
		this.msgBo.setSeeState("1");
		this.msgBo.setSeeDate(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
		this.tuserMsgService.updateMsg(msgBo);
		return SUCCESS;
	}
	
	@Action(value="msgView",results={
			@Result(name="success",location="/userMsg/msgView.jsp")
			})
	public String msgView(){
		String msgId = StringUtil.getNotNullValueString(servletRequest.getParameter("msgId"));
		this.msgBo = this.tuserMsgService.findMsg(msgId);
		return SUCCESS;
	}
	
	
	@Action(value="msgForward",results={
			@Result(name="success",location="/userMsg/msgSuccess.jsp")
	})
	public String msgForward(){
		String forwardId = StringUtil.getNotNullValueString(servletRequest.getParameter("forwardId"));
		String rids = StringUtil.getNotNullValueString(servletRequest.getParameter("rids"));
		String sid = StringUtil.getNotNullValueString(servletRequest.getParameter("sid"));
		String sname = StringUtil.getNotNullValueString(servletRequest.getParameter("sname"));
		String rnames = StringUtil.getNotNullValueString(servletRequest.getParameter("rnames"));
		String title = StringUtil.getNotNullValueString(servletRequest.getParameter("title"));
		String content = StringUtil.getNotNullValueString(servletRequest.getParameter("content"));
		String attach = StringUtil.getNotNullValueString(servletRequest.getParameter("attach"));
		String[] rid = rids.split(",");
		String[] rname = rnames.split(",");
		if(rid!=null&&rid.length>0&&rname!=null&&rname.length>0)
		for(int i=0;i<rid.length;i++){
			TuserMsg bo = new TuserMsg();
			bo.setSendState("1");
			bo.setSeeState("0");
			bo.setSendMode("1");
			bo.setSendDate(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
			bo.setTitle(title);
			bo.setContent(content);
			bo.setRid(Long.parseLong(rid[i]));
			bo.setRname(rname[i]);
			bo.setSid(Long.parseLong(sid));
			bo.setAttach(attach);
			bo.setSname(sname);
			bo.setForwardId(forwardId);
			this.tuserMsgService.addMsg(bo);
		}
		//ActionWriter aw = new ActionWriter(servletResponse);
		//aw.writeAjax("<script>alert('转发成功！');window.opener=null;window.open('','_self');window.close();</script>");
		return "success";
	}
	
	@Action(value="msgReply",results={
			@Result(name="success",location="/userMsg/msgSuccess.jsp")
	})
	public String msgReply(){
		String attachReply = StringUtil.getNotNullValueString(servletRequest.getParameter("attachReply"));
		String replyRid = StringUtil.getNotNullValueString(servletRequest.getParameter("replyRid"));
		String replyRname = StringUtil.getNotNullValueString(servletRequest.getParameter("replyRname"));
		this.msgBo.setRid(Long.parseLong(replyRid));
		this.msgBo.setRname(replyRname);
		this.msgBo.setSendState("1");
		this.msgBo.setSendMode("1");
		this.msgBo.setSeeState("0");
		this.msgBo.setAttach(attachReply);
		this.msgBo.setSendDate(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
		this.tuserMsgService.addMsg(msgBo);
		//ActionWriter aw = new ActionWriter(servletResponse);
		//aw.writeAjax("<script>alert('回复成功！');window.opener=null;window.open('','_self');window.close();</script>");
		return "success";
	}
	
	@Action(value="msgDelBatch")
	public String msgDelBatch(){
		String delIds = StringUtil.getNotNullValueString(servletRequest.getParameter("delIds"));
		String userType = StringUtil.getNotNullValueString(servletRequest.getParameter("userType"));
		String[] delId = delIds.split(",");
		if(delId!=null&&delId.length>0)
		for(int i=0;i<delId.length;i++){
			TuserMsg del = this.tuserMsgService.findMsg(delId[i]);
			if(del!=null){
				if("send".equals(userType)){
					del.setSendState("2");
				}else if("receive".equals(userType)){
					del.setSeeState("2");
				}
			}
			this.tuserMsgService.updateMsg(del);
			
		}
		ActionWriter aw = new ActionWriter(servletResponse);
		aw.writeAjax("1");
		return null;
	}
	
	@Action(value="getUser")
	public String getUser(){
		String maxRows=servletRequest.getParameter("maxRows");  
        String name_startsWith=super.getServletRequest().getParameter("name_startsWith");  
        ActionWriter aw = new ActionWriter(servletResponse);    
		List<UserSearchVo> list = this.tuserMsgService.getUserInfo(maxRows,name_startsWith);
		aw.writeJson(list);
		return null;  
	}
	
	@Action(value="sendMsg")
	public String sendMsg(){
		String sid = StringUtil.getNotNullValueString(servletRequest.getParameter("sid"));
		String rid = StringUtil.getNotNullValueString(servletRequest.getParameter("rid"));
		String title = StringUtil.getNotNullValueString(servletRequest.getParameter("title"));
		String content = StringUtil.getNotNullValueString(servletRequest.getParameter("content"));//encodeURI
		this.tuserMsgService.addMsg(sid, rid, title, content);
		return null;  
	}
	
}
