/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.ReportsBal;
/*    */ import com.compulynx.erevenue.dal.impl.ReportsDalImpl;
/*    */ import com.compulynx.erevenue.models.Reports;
/*    */ import com.compulynx.erevenue.models.ZDetails;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReportsBalImpl
/*    */   implements ReportsBal
/*    */ {
/*    */   @Autowired
/*    */   ReportsDalImpl reportsDal;
/*    */   
/*    */   public List<Reports> GetAllUserTxn() {
/* 29 */     return this.reportsDal.GetAllUserTxn();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Reports> GetUserTxnsDetails(Reports txnDetails) {
/* 35 */     return this.reportsDal.GetUserTxnsDetails(txnDetails);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Reports> GetAllDeviceTxn() {
/* 41 */     return this.reportsDal.GetAllDeviceTxn();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Reports> GetDeviceTxnsDetails(Reports txnDetails) {
/* 47 */     return this.reportsDal.GetDeviceTxnsDetails(txnDetails);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Reports> GetAllTxnsDetails(Reports txnDetails) {
/* 65 */     return this.reportsDal.GetAllTxnsDetails(txnDetails);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ZDetails> GetZDetails(ZDetails zDetails) {
/* 71 */     return this.reportsDal.GetZDetails(zDetails);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Reports> GetCurrentTxnsDetails() {
/* 77 */     return this.reportsDal.GetCurrentTxnsDetails();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\ReportsBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */