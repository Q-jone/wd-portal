/*    */ package com.wonders.stpt.contract.action;
/*    */ 
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignStats;
/*    */ import com.wonders.stpt.contract.entity.vo.DwContractPriceNumberVo;
/*    */ import com.wonders.stpt.contract.service.DwContractAssignStatsService;
/*    */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*    */ import com.wonders.stpt.util.ActionWriter;
/*    */ import java.math.BigDecimal;
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
/*    */ @Controller("DwContractAssignStatsAction")
/*    */ @Scope("prototype")
/*    */ public class DwContractAssignStatsAction extends AbstractParamAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private DwContractAssignStatsService dwContractAssignStatsService;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractAssignStatsService(@Qualifier("dwContractAssignStatsService") DwContractAssignStatsService dwContractAssignStatsService)
/*    */   {
/* 32 */     this.dwContractAssignStatsService = dwContractAssignStatsService;
/*    */   }
/*    */   @Action("findDwContractAssignStats")
/*    */   public String findDwContractAssignStats() {
/* 37 */     String assignType = this.servletRequest.getParameter("assignType");
/* 38 */     String controlYear = this.servletRequest.getParameter("controlYear");
/* 39 */     String contractType = this.servletRequest.getParameter("contractType");
/* 40 */     String controlCompanyId = this.servletRequest.getParameter("controlCompanyId");
/* 41 */     String type = this.servletRequest.getParameter("type");
/* 42 */     List list = this.dwContractAssignStatsService.findDwContractAssignStats(assignType, controlYear, contractType, controlCompanyId, type);
/* 43 */     DwContractPriceNumberVo vo = new DwContractPriceNumberVo();
/* 44 */     if (assignType.equals("2")) {
/* 45 */       if ((list != null) && (list.size() > 0)) {
/* 46 */         List planPay = new ArrayList();
/* 47 */         List actualPay = new ArrayList();
/* 48 */         List controlDate = new ArrayList();
/* 49 */         for (int i = 0; i < list.size(); i++) {
/* 50 */           planPay.add(((DwContractAssignStats)list.get(i)).getValue1());
/* 51 */           actualPay.add(((DwContractAssignStats)list.get(i)).getValue2());
/* 52 */           controlDate.add(((DwContractAssignStats)list.get(i)).getControlDate());
/*    */         }
/* 54 */         vo.setContractPrice(planPay);
/* 55 */         vo.setContractNum(actualPay);
/* 56 */         vo.setControlDate(controlDate);
/*    */       }
/*    */     }
/* 59 */     else if ((list != null) && (list.size() > 0)) {
/* 60 */       List contractPrice = new ArrayList();
/* 61 */       List contractNum = new ArrayList();
/* 62 */       List controlDate = new ArrayList();
/* 63 */       for (int i = 0; i < list.size(); i++) {
/* 64 */         if (!((DwContractAssignStats)list.get(i)).getControlDate().equals(controlYear)) {
/* 65 */           contractPrice.add(((DwContractAssignStats)list.get(i)).getValue1());
/* 66 */           contractNum.add(((DwContractAssignStats)list.get(i)).getValue2());
/* 67 */           controlDate.add(((DwContractAssignStats)list.get(i)).getControlDate());
/*    */         } else {
/*    */           try {
/* 70 */             vo.setYearCount(((DwContractAssignStats)list.get(i)).getValue2().toString());
/* 71 */             vo.setYearMoney(((DwContractAssignStats)list.get(i)).getValue1().toString());
/*    */           }
/*    */           catch (Exception e) {
/* 74 */             e.printStackTrace();
/*    */           }
/*    */         }
/*    */       }
/* 78 */       vo.setContractPrice(contractPrice);
/* 79 */       vo.setContractNum(contractNum);
/* 80 */       vo.setControlDate(controlDate);
/*    */     }
/*    */ 
/* 84 */     ActionWriter aw = new ActionWriter(this.servletResponse);
/* 85 */     aw.writeJson(vo);
/* 86 */     return null;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.DwContractAssignStatsAction
 * JD-Core Version:    0.6.0
 */