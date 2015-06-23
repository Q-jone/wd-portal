/*    */ package com.wonders.stpt.contract.action;
/*    */ 
/*    */ import com.wonders.stpt.contract.service.ContractService;
/*    */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*    */ import com.wonders.stpt.util.ActionWriter;
/*    */ import java.io.UnsupportedEncodingException;
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
/*    */ @Namespace("/htxx")
/*    */ @Controller("contractAction")
/*    */ @Scope("prototype")
/*    */ public class ContractAction extends AbstractParamAction
/*    */ {
/*    */ 
/*    */   @Autowired(required=false)
/*    */   private ContractService contractService;
/*    */ 
/*    */   public void setContractService(@Qualifier("contractService") ContractService contractService)
/*    */   {
/* 29 */     this.contractService = contractService;
/*    */   }
/* 33 */   @Action("findWithFuzzySearch")
/*    */   public String findWithFuzzySearch() throws UnsupportedEncodingException { this.servletRequest.setCharacterEncoding("UTF-8");
/* 34 */     String contractNo = this.servletRequest.getParameter("contractNo");
/* 35 */     contractNo = new String(contractNo.getBytes("ISO-8859-1"), "UTF-8");
/* 36 */     String contractType = this.servletRequest.getParameter("contractType");
/* 37 */     List list = this.contractService.findByContractNoAndContractType(contractNo, contractType);
/* 38 */     ActionWriter actionWriter = new ActionWriter(this.servletResponse);
/* 39 */     actionWriter.writeJson(list);
/* 40 */     return null;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.ContractAction
 * JD-Core Version:    0.6.0
 */