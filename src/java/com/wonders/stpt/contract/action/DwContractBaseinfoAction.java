/*    */ package com.wonders.stpt.contract.action;
/*    */ 
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractBaseinfo;
/*    */ import com.wonders.stpt.contract.service.DwContractBaseinfoService;
/*    */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*    */ import com.wonders.stpt.util.ActionWriter;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.struts2.convention.annotation.Action;
/*    */ import org.apache.struts2.convention.annotation.Namespace;
/*    */ import org.apache.struts2.convention.annotation.ParentPackage;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Controller;
/*    */ 
/*    */ @ParentPackage("struts-default")
/*    */ @Namespace("/htxx")
/*    */ @Controller("dwContractBaseinfoAction")
/*    */ @Scope("prototype")
/*    */ public class DwContractBaseinfoAction extends AbstractParamAction
/*    */ {
/*    */ 
/*    */   @Autowired(required=false)
/*    */   private DwContractBaseinfoService dwContractBaseinfoService;
/*    */ 
/*    */   public void setDwContractBaseinfoService(@Qualifier("dwContractBaseinfoService") DwContractBaseinfoService dwContractBaseinfoService)
/*    */   {
/* 27 */     this.dwContractBaseinfoService = dwContractBaseinfoService;
/*    */   }
/*    */   @Action("showBaseinfo")
/*    */   public String showBaseinfo() {
/* 32 */     String contractType = this.servletRequest.getParameter("contractType");
/*    */ 
/* 34 */     DwContractBaseinfo baseInfo = this.dwContractBaseinfoService.findByType(contractType);
/*    */ 
/* 36 */     ActionWriter writer = new ActionWriter(this.servletResponse);
/* 37 */     writer.writeJson(baseInfo);
/*    */ 
/* 39 */     return null;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.DwContractBaseinfoAction
 * JD-Core Version:    0.6.0
 */