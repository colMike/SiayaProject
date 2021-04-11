/*    */ package com.compulynx.erevenue.models;
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
/*    */ public class PermitType
/*    */ {
/*    */   public int permitTypeId;
/*    */   public String permitTypeCode;
/*    */   public String permitTypeName;
/*    */   public String permitType;
/*    */   public double permitFee;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   
/*    */   public PermitType() {}
/*    */   
/*    */   public PermitType(int permitTypeId, String permitTypeCode, String permitTypeName, double permitFee, boolean active, int respCode, int count) {
/* 29 */     this.permitTypeId = permitTypeId;
/* 30 */     this.permitTypeCode = permitTypeCode;
/* 31 */     this.permitTypeName = permitTypeName;
/* 32 */     this.permitFee = permitFee;
/* 33 */     this.active = active;
/* 34 */     this.respCode = respCode;
/* 35 */     this.count = count;
/*    */   }
/*    */   
/*    */   public PermitType(int permitTypeId, String permitTypeName) {
/* 39 */     this.permitTypeId = permitTypeId;
/* 40 */     this.permitTypeName = permitTypeName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\PermitType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */