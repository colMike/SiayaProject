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
/*    */ public class SubCounty
/*    */ {
/*    */   public int subCountyId;
/*    */   public String subCountyCode;
/*    */   public String subCountyName;
/*    */   public boolean active;
/*    */   public String status;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public double serviceValue;
/*    */   
/*    */   public SubCounty(int subCountyId, String subCountyCode, String subCountyName, boolean active, int respCode, int count, String status) {
/* 26 */     this.subCountyId = subCountyId;
/* 27 */     this.subCountyCode = subCountyCode;
/* 28 */     this.subCountyName = subCountyName;
/* 29 */     this.active = active;
/* 30 */     this.respCode = respCode;
/* 31 */     this.count = count;
/* 32 */     this.status = status;
/*    */   }
/*    */ 
/*    */   
/*    */   public SubCounty() {}
/*    */ 
/*    */   
/*    */   public SubCounty(int subCountyId, String subCountyName) {
/* 40 */     this.subCountyId = subCountyId;
/* 41 */     this.subCountyName = subCountyName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\SubCounty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */