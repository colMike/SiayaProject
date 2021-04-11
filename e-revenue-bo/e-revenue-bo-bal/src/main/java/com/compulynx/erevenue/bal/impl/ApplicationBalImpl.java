/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.ApplicationBal;
/*    */ import com.compulynx.erevenue.dal.impl.ApplicationDalImpl;
/*    */ import com.compulynx.erevenue.models.Application;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.PermitType;
/*    */ import com.compulynx.erevenue.models.Reports;
/*    */ import com.compulynx.erevenue.models.Ward;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ApplicationBalImpl
/*    */   implements ApplicationBal
/*    */ {
/*    */   @Autowired
/*    */   ApplicationDalImpl applicationDal;
/*    */   
/*    */   public ErevenueResponse UpdateApplication(Application app) {
/* 27 */     return this.applicationDal.UpdateApplication(app);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PermitType> GetActivePermitTypes(String permitType) {
/* 32 */     return this.applicationDal.GetActivePermitTypes(permitType);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Application> GetAllApplications() {
/* 37 */     return this.applicationDal.GetAllApplications();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Application> GetAllInvoices() {
/* 42 */     return this.applicationDal.GetAllInvoices();
/*    */   }
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateInvocieStatus(Application app) {
/* 47 */     return this.applicationDal.UpdateInvocieStatus(app);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Application> GetAllPermits() {
/* 52 */     return this.applicationDal.GetAllPermits();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateQrImagePath(Application app) {
/* 58 */     return this.applicationDal.UpdateQrImagePath(app);
/*    */   }
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdatePermitRenewal(Application app) {
/* 63 */     return this.applicationDal.UpdatePermitRenewal(app);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Application> GetAllAppsByLinkId(int linkId, String nationalIdNo, int agentId) {
/* 68 */     return this.applicationDal.GetAllAppsByLinkId(linkId, nationalIdNo, agentId);
/*    */   }
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdatePermitStatus(Application app) {
/* 73 */     return this.applicationDal.UpdatePermitStatus(app);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Ward> GetWardsBySubCounty(int subCountyId) {
/* 78 */     return this.applicationDal.GetWardsBySubCounty(subCountyId);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Application> GetPermitsByDate(Reports permit) {
/* 83 */     return this.applicationDal.GetPermitsByDate(permit);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Application> GetAllInvoicesByLinkId(int linkId, String nationalIdNo, int agentId) {
/* 89 */     return this.applicationDal.GetAllInvoicesByLinkId(linkId, nationalIdNo, agentId);
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\ApplicationBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */