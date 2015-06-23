/*    */ package com.wonders.stpt.contract.action;
/*    */ 
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignType;
/*    */ import com.wonders.stpt.contract.service.DwContractAssignTypeService;
/*    */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*    */ import com.wonders.stpt.util.ActionWriter;
/*    */ import java.util.ArrayList;
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
/*    */ @Controller("DwContractAssignTypeAction")
/*    */ @Scope("prototype")
/*    */ public class DwContractAssignTypeAction extends AbstractParamAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private DwContractAssignTypeService dwContractAssignTypeService;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractAssignTypeService(@Qualifier("dwContractAssignTypeService") DwContractAssignTypeService dwContractAssignTypeService)
/*    */   {
/* 34 */     this.dwContractAssignTypeService = dwContractAssignTypeService;
/*    */   }
/*    */   @Action("findDwContractAssignType")
/*    */   public String findDwContractAssignType() {
/* 39 */     String assignType = this.servletRequest.getParameter("assignType");
/* 40 */     String controlYear = this.servletRequest.getParameter("controlYear");
/* 41 */     String contractType = this.servletRequest.getParameter("contractType");
/* 42 */     String controlCompanyId = this.servletRequest.getParameter("controlCompanyId");
/* 43 */     String type = this.servletRequest.getParameter("type");
/*    */ 
/* 45 */     List list = this.dwContractAssignTypeService.findDwContractAssignType(assignType, controlYear, contractType, controlCompanyId, type);
/* 46 */     List listNum = new ArrayList();
/* 47 */     if ((list != null) && (list.size() > 0)) {
/* 48 */       DwContractAssignType bo = (DwContractAssignType)list.get(0);
/* 49 */       listNum.add(bo.getExe1());
/* 50 */       listNum.add(bo.getExe2());
/* 51 */       listNum.add(bo.getExe3());
/* 52 */       if (!"1".equals(assignType))
/*    */       {
/* 54 */         if ("2".equals(assignType))
/* 55 */           listNum.add(bo.getExe4());
/*    */       }
/*    */     }
/* 58 */     ActionWriter aw = new ActionWriter(this.servletResponse);
/* 59 */     aw.writeJson(listNum);
/* 60 */     return null;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.DwContractAssignTypeAction
 * JD-Core Version:    0.6.0
 */