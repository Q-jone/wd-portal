/*    */ package com.wonders.stpt.contract.action;
/*    */ 
/*    */ import com.wonders.stpt.contract.service.DwContractManagementService;
/*    */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*    */ import com.wonders.stpt.util.ActionWriter;
/*    */ import java.util.List;
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
/*    */ @Namespace("/contract")
/*    */ @Controller("DwContractManagementAction")
/*    */ @Scope("prototype")
/*    */ public class DwContractManagementAction extends AbstractParamAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private DwContractManagementService dwContractManagementService;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractManagementService(@Qualifier("dwContractManagementService") DwContractManagementService dwContractManagementService)
/*    */   {
/* 29 */     this.dwContractManagementService = dwContractManagementService;
/*    */   }
/*    */   @Action("findDwContractManagement")
/*    */   public String findDwContractManagement() {
/* 34 */     String companyId = this.servletRequest.getParameter("companyId");
/* 35 */     List list = this.dwContractManagementService.findDwContractManagement(companyId);
/* 36 */     ActionWriter aw = new ActionWriter(this.servletResponse);
/* 37 */     aw.writeJson(list);
/* 38 */     return null;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.DwContractManagementAction
 * JD-Core Version:    0.6.0
 */