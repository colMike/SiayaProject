/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.PermitTypeBal;
/*    */ import com.compulynx.erevenue.dal.impl.PermitTypeDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.PermitType;
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
/*    */ public class PermitTypeBalImpl
/*    */   implements PermitTypeBal
/*    */ {
/*    */   @Autowired
/*    */   PermitTypeDalImpl permitTypeDal;
/*    */   
/*    */   public ErevenueResponse UpdatePermitType(PermitType permitType) {
/* 30 */     return this.permitTypeDal.UpdatePermitType(permitType);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<PermitType> GetAllPermitTypes() {
/* 36 */     return this.permitTypeDal.GetAllPermitTypes();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\PermitTypeBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */