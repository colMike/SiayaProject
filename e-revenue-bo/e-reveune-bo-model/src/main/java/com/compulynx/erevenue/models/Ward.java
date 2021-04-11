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
/*    */ 
/*    */ 
/*    */ public class Ward
/*    */ {
/*    */   public int wardId;
/*    */   public String wardCode;
/*    */   public String wardName;
/*    */   public int subCountyId;
/*    */   public String subCountyName;
/*    */   public boolean active;
/*    */   public String status;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public double serviceValue;
/*    */   
/*    */   public Ward() {}
/*    */   
/*    */   public Ward(int wardId, String wardCode, String wardName, int subCountyId, String subCountyName, boolean active, int respCode, int count, String status) {
/* 33 */     this.wardId = wardId;
/* 34 */     this.wardCode = wardCode;
/* 35 */     this.wardName = wardName;
/* 36 */     this.subCountyId = subCountyId;
/* 37 */     this.subCountyName = subCountyName;
/* 38 */     this.active = active;
/* 39 */     this.respCode = respCode;
/* 40 */     this.count = count;
/* 41 */     this.status = status;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ward(int wardId, String wardName) {
/* 47 */     this.wardId = wardId;
/* 48 */     this.wardName = wardName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Ward.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */