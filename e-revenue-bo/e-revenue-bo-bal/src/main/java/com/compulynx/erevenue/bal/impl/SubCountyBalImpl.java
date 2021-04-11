/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.SubCountyBal;
/*    */ import com.compulynx.erevenue.dal.impl.SubCountyDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.SubCounty;
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
/*    */ @Component
/*    */ public class SubCountyBalImpl
/*    */   implements SubCountyBal
/*    */ {
/*    */   @Autowired
/*    */   SubCountyDalImpl subCountyDal;
/*    */   
/*    */   public ErevenueResponse UpdateSubCounty(SubCounty subCounty) {
/* 26 */     return this.subCountyDal.UpdateSubCounty(subCounty);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<SubCounty> GetAllSubCounties() {
/* 32 */     return this.subCountyDal.GetAllSubCounties();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\SubCountyBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */