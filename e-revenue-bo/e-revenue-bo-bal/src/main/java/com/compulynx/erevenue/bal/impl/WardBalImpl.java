/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.WardBal;
/*    */ import com.compulynx.erevenue.dal.impl.WardDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.SubCounty;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class WardBalImpl
/*    */   implements WardBal
/*    */ {
/*    */   @Autowired
/*    */   WardDalImpl wardDal;
/*    */   
/*    */   public ErevenueResponse UpdateWard(Ward ward) {
/* 31 */     return this.wardDal.UpdateWard(ward);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Ward> GetAllWards() {
/* 37 */     return this.wardDal.GetAllWards();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<SubCounty> GetActiveSubCounties() {
/* 43 */     return this.wardDal.GetActiveSubCounties();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\WardBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */