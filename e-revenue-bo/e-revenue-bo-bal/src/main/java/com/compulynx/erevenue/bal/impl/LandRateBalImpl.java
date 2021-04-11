/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.LandRateBal;
/*    */ import com.compulynx.erevenue.dal.impl.LandRateDalImpl;
import com.compulynx.erevenue.models.CompasResponse;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.LandRate;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class LandRateBalImpl
/*    */   implements LandRateBal
/*    */ {
/*    */   @Autowired
/*    */   LandRateDalImpl landrateDal;
/*    */   
/*    */   public ErevenueResponse CreateRegistrationForm(LandRate lr) {
/* 23 */     return this.landrateDal.CreateRegistrationForm(lr);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<LandRate> GetAllRegs() {
/* 29 */     return this.landrateDal.GetAllRegs();
/*    */   }
/*    */   
/*    */   public List<LandRate> GetAllLandInvoices() {
/* 33 */     return this.landrateDal.GetAllLandInvoices();
/*    */   }
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateLandInvoiceStatus(LandRate lr) {
/* 38 */     return this.landrateDal.UpdateLandInvoiceStatus(lr);
/*    */   }
/*    */   
/*    */   public ErevenueResponse UpdateLandPermitRenewal(LandRate lr) {
/* 42 */     return this.landrateDal.UpdateLandPermitRenewal(lr);
/*    */   }
/*    */   public List<LandRate> GetPaidLandDetails() {
/* 45 */     return this.landrateDal.GetPaidLandDetails();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<LandRate> GetAllLandsByLinkId(int linkId, String nationalIdNo, int agentId) {
/* 50 */     return this.landrateDal.GetAllLandsByLinkId(linkId, nationalIdNo, agentId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<LandRate> GetAllLandInvoicesByLinkId(int linkId, String nationalIdNo, int agentId) {
/* 60 */     return this.landrateDal.GetAllLandInvoicesByLinkId(linkId, nationalIdNo, agentId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateLandInvocieStatus(LandRate lr) {
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<LandRate> GetAllLandPermits() {
/* 73 */     return this.landrateDal.GetAllLandPermits();
/*    */   }
public CompasResponse UploadPlot(String filePath,String fileName, String uploadedBy,int branchId) {
	return landrateDal.UploadPlot(filePath,fileName,uploadedBy,branchId);
}
public CompasResponse UploadLand(String filePath,String fileName, String uploadedBy,int branchId) {
	System.out.println("============here2=================");
	return landrateDal.UploadLand(filePath,fileName,uploadedBy,branchId);
}




/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\LandRateBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */