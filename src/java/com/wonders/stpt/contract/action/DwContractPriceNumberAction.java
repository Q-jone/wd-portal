/*     */ package com.wonders.stpt.contract.action;
/*     */ 
/*     */ import com.wonders.stpt.contract.entity.bo.DwContractPriceNumber;
/*     */ import com.wonders.stpt.contract.entity.vo.DwContractPriceNumberVo;
/*     */ import com.wonders.stpt.contract.service.DwContractPriceNumberService;
/*     */ import com.wonders.stpt.userMsg.action.AbstractParamAction;
/*     */ import com.wonders.stpt.util.ActionWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.struts2.convention.annotation.Action;
/*     */ import org.apache.struts2.convention.annotation.Namespace;
/*     */ import org.apache.struts2.convention.annotation.ParentPackage;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;
/*     */ import org.springframework.context.annotation.Scope;
/*     */ import org.springframework.stereotype.Controller;
/*     */ 
/*     */ @ParentPackage("struts-default")
/*     */ @Namespace("/contract")
/*     */ @Controller("DwContractPriceNumberAction")
/*     */ @Scope("prototype")
/*     */ public class DwContractPriceNumberAction extends AbstractParamAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private DwContractPriceNumberService dwContractPriceNumberService;
/*     */ 
/*     */   @Autowired(required=false)
/*     */   public void setDwContractPriceNumberService(@Qualifier("dwContractPriceNumberService") DwContractPriceNumberService dwContractPriceNumberService)
/*     */   {
/*  39 */     this.dwContractPriceNumberService = dwContractPriceNumberService;
/*     */   }
/*     */ 
/*     */   @Action("findDwContractPriceNumber")
/*     */   public String findDwContractPriceNumber() {
/*  45 */     int startYear = 2008;
/*  46 */     String controlYear = this.servletRequest.getParameter("controlYear");
/*  47 */     String controlTypeStr = this.servletRequest.getParameter("controlType");
/*  48 */     String companyId = this.servletRequest.getParameter("companyId");
/*  49 */     String type = this.servletRequest.getParameter("type");
/*  50 */     int controlType = 0;
/*  51 */     if (controlTypeStr != null) {
/*  52 */       controlType = Integer.valueOf(controlTypeStr).intValue();
/*     */     }
/*  54 */     List list = this.dwContractPriceNumberService.findDwContractPriceNumber(controlYear, controlType, companyId, type);
/*  55 */     DwContractPriceNumberVo vo = new DwContractPriceNumberVo();
/*  56 */     if ((list != null) && (list.size() > 0)) {
/*  57 */       List contractPrice = new ArrayList();
/*  58 */       List contractNum = new ArrayList();
/*  59 */       List controlDate = new ArrayList();
/*  60 */       BigDecimal num = new BigDecimal(0);
/*  61 */       BigDecimal price = new BigDecimal(0.0D);
/*  62 */       for (int i = 0; i < list.size(); i++) {
/*  63 */         if ("year".equals(type)) {
/*  64 */           num = num.add(((DwContractPriceNumber)list.get(i)).getContractNum());
/*  65 */           price = price.add(((DwContractPriceNumber)list.get(i)).getContractPrice());
/*  66 */           if ((i == list.size() - 1) || (!((DwContractPriceNumber)list.get(i)).getControlDate().substring(0, 4).equals(((DwContractPriceNumber)list.get(i + 1)).getControlDate().substring(0, 4)))) {
/*  67 */             contractNum.add(num);
/*  68 */             contractPrice.add(price);
/*  69 */             controlDate.add(((DwContractPriceNumber)list.get(i)).getControlDate().substring(0, 4));
/*  70 */             num = new BigDecimal(0);
/*     */           }
/*     */         } else {
/*  73 */           price = price.add(((DwContractPriceNumber)list.get(i)).getContractPrice());
/*  74 */           if ("".equals(companyId)) {
/*  75 */             num = num.add(((DwContractPriceNumber)list.get(i)).getContractNum());
/*  76 */             if ((i == list.size() - 1) || (!((DwContractPriceNumber)list.get(i)).getControlDate().equals(((DwContractPriceNumber)list.get(i + 1)).getControlDate()))) {
/*  77 */               contractNum.add(num);
/*  78 */               contractPrice.add(price);
/*  79 */               controlDate.add(((DwContractPriceNumber)list.get(i)).getControlDate());
/*  80 */               num = new BigDecimal(0);
/*     */             }
/*     */           } else {
/*  83 */             contractNum.add(((DwContractPriceNumber)list.get(i)).getContractNum());
/*  84 */             contractPrice.add(price);
/*  85 */             controlDate.add(((DwContractPriceNumber)list.get(i)).getControlDate());
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  91 */       Date toDate = new Date();
/*  92 */       DateFormat df1 = new SimpleDateFormat("yyyy-MM");
/*  93 */       DateFormat df2 = new SimpleDateFormat("yyyyMM");
/*  94 */       boolean flag = false;
/*  95 */       if ("year".equals(type)) {
/*  96 */         for (int i = startYear; i <= Integer.valueOf(controlYear).intValue(); i++)
/*  97 */           if (i - startYear < controlDate.size()) {
/*  98 */             for (int j = 0; j < controlDate.size(); j++) {
/*  99 */               if (((String)controlDate.get(j)).equals(String.valueOf(i))) {
/* 100 */                 flag = true;
/* 101 */                 break;
/*     */               }
/*     */             }
/* 104 */             if (!flag) {
/* 105 */               for (int j = 0; j < controlDate.size(); j++) {
/* 106 */                 if (Integer.valueOf((String)controlDate.get(j)).intValue() > i) {
/* 107 */                   contractNum.add(j, new BigDecimal(0));
/* 108 */                   if (j == 0)
/* 109 */                     contractPrice.add(j, new BigDecimal(0.0D));
/*     */                   else {
/* 111 */                     contractPrice.add(j, (BigDecimal)contractPrice.get(j - 1));
/*     */                   }
/* 113 */                   controlDate.add(j, String.valueOf(i));
/* 114 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/* 118 */             flag = false;
/*     */           } else {
/* 120 */             contractNum.add(new BigDecimal(0));
/* 121 */             if (i - startYear == 0)
/* 122 */               contractPrice.add(new BigDecimal(0.0D));
/*     */             else {
/* 124 */               contractPrice.add((BigDecimal)contractPrice.get(i - startYear - 1));
/*     */             }
/* 126 */             controlDate.add(String.valueOf(i));
/*     */           }
/*     */       }
/* 129 */       else if ("month".equals(type)) {
/* 130 */         for (int i = 1; i < 13; i++)
/* 131 */           if (i - 1 < controlDate.size()) {
/* 132 */             for (int j = 0; j < controlDate.size(); j++) {
/* 133 */               if (Integer.valueOf(((String)controlDate.get(j)).substring(5, 7)).intValue() == i) {
/* 134 */                 flag = true;
/* 135 */                 break;
/*     */               }
/*     */             }
/* 138 */             if (!flag) {
/* 139 */               for (int j = 0; j < controlDate.size(); j++) {
/* 140 */                 if (Integer.valueOf(((String)controlDate.get(j)).substring(5, 7)).intValue() > i) {
/* 141 */                   contractNum.add(j, new BigDecimal(0));
/* 142 */                   if (j == 0)
/* 143 */                     contractPrice.add(j, new BigDecimal(0.0D));
/*     */                   else {
/* 145 */                     contractPrice.add(j, (BigDecimal)contractPrice.get(j - 1));
/*     */                   }
/* 147 */                   String str = String.valueOf(i);
/* 148 */                   if (i < 10) {
/* 149 */                     str = "0" + str;
/*     */                   }
/* 151 */                   str = ((String)controlDate.get(j)).substring(0, 4) + "-" + str;
/* 152 */                   controlDate.add(j, str);
/* 153 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/* 157 */             flag = false;
/*     */           } else {
/* 159 */             contractNum.add(new BigDecimal(0));
/* 160 */             if (i - 1 == 0)
/* 161 */               contractPrice.add(new BigDecimal(0.0D));
/*     */             else {
/* 163 */               contractPrice.add((BigDecimal)contractPrice.get(i - 1 - 1));
/*     */             }
/* 165 */             String str = String.valueOf(i);
/* 166 */             if (i < 10) {
/* 167 */               str = "0" + str;
/*     */             }
/* 169 */             str = ((String)controlDate.get(i - 1 - 1)).substring(0, 4) + "-" + str;
/* 170 */             controlDate.add(str);
/*     */           }
/*     */       }
/*     */       else {
/* 174 */         int toYear = toDate.getYear();
/* 175 */         int toMonth = toDate.getMonth();
/* 176 */         for (int i = 0; i < 12; i++) {
/* 177 */           Date date = new Date();
/* 178 */           if (toMonth + 1 + i < 12) {
/* 179 */             date.setYear(toYear - 1);
/* 180 */             date.setMonth(toMonth + 1 + i);
/*     */           } else {
/* 182 */             date.setMonth(toMonth + 1 + i - 12);
/*     */           }
/* 184 */           if (i < controlDate.size()) {
/* 185 */             for (int j = 0; j < controlDate.size(); j++) {
/* 186 */               if (df1.format(date).equals(controlDate.get(j))) {
/* 187 */                 flag = true;
/* 188 */                 break;
/*     */               }
/*     */             }
/* 191 */             if (!flag) {
/* 192 */               for (int j = 0; j < controlDate.size(); j++) {
/* 193 */                 if (Integer.valueOf(((String)controlDate.get(j)).replace("-", "")).intValue() > Integer.valueOf(df2.format(date)).intValue()) {
/* 194 */                   contractNum.add(j, new BigDecimal(0));
/* 195 */                   if (j == 0)
/* 196 */                     contractPrice.add(j, new BigDecimal(0.0D));
/*     */                   else {
/* 198 */                     contractPrice.add(j, (BigDecimal)contractPrice.get(j - 1));
/*     */                   }
/* 200 */                   controlDate.add(j, df1.format(date));
/* 201 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/* 205 */             flag = false;
/*     */           } else {
/* 207 */             contractNum.add(new BigDecimal(0));
/* 208 */             if (i == 0)
/* 209 */               contractPrice.add(new BigDecimal(0.0D));
/*     */             else {
/* 211 */               contractPrice.add((BigDecimal)contractPrice.get(i - 1));
/*     */             }
/* 213 */             controlDate.add(df1.format(date));
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 218 */       vo.setContractPrice(contractPrice);
/* 219 */       vo.setContractNum(contractNum);
/* 220 */       vo.setControlDate(controlDate);
/*     */     }
/* 222 */     ActionWriter aw = new ActionWriter(this.servletResponse);
/* 223 */     aw.writeJson(vo);
/* 224 */     return null;
/*     */   }
/*     */   @Action("findDwContractPriceNumber2")
/*     */   public String findDwContractPriceNumber2() {
/* 229 */     String controlYear = this.servletRequest.getParameter("controlYear");
/* 230 */     String contractType = this.servletRequest.getParameter("contractType");
/* 231 */     String companyId = this.servletRequest.getParameter("companyId");
/* 232 */     String dataType = this.servletRequest.getParameter("dataType");
/* 233 */     List list = new ArrayList();
/* 234 */     if (controlYear == null)
/* 235 */       list = this.dwContractPriceNumberService.findDwContractPriceNumberThisMonth(contractType, companyId, dataType);
/*     */     else {
/* 237 */       list = this.dwContractPriceNumberService.findDwContractPriceNumberSum(controlYear, contractType, companyId, dataType);
/*     */     }
/* 239 */     BigDecimal num_sum = new BigDecimal(0);
/* 240 */     BigDecimal price_sum = new BigDecimal(0.0D);
/* 241 */     if ((list != null) && (list.size() > 0)) {
/* 242 */       for (int i = 0; i < list.size(); i++) {
/* 243 */         num_sum = num_sum.add(((DwContractPriceNumber)list.get(i)).getContractNum());
/* 244 */         price_sum = price_sum.add(((DwContractPriceNumber)list.get(i)).getContractPrice());
/*     */       }
/*     */     }
/* 247 */     List result = new ArrayList();
/* 248 */     result.add(num_sum);
/* 249 */     result.add(price_sum);
/* 250 */     ActionWriter aw = new ActionWriter(this.servletResponse);
/* 251 */     aw.writeJson(result);
/* 252 */     return null;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.DwContractPriceNumberAction
 * JD-Core Version:    0.6.0
 */