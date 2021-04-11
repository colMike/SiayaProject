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
/*    */ public class Params
/*    */ {
/*    */   public int paramId;
/*    */   public int serviceId;
/*    */   public String paramName;
/*    */   public String paramType;
/*    */   public boolean active;
/*    */   public String status;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public boolean isActive;
/*    */   public String paramValue;
/*    */   public int counter;
/*    */   
/*    */   public Params() {}
/*    */   
/*    */   public Params(int paramId, String paramName, String paramType, boolean active, int createdBy, int respCode, int counter, String status) {
/* 31 */     this.paramId = paramId;
/* 32 */     this.paramName = paramName;
/* 33 */     this.paramType = paramType;
/* 34 */     this.active = active;
/* 35 */     this.createdBy = createdBy;
/* 36 */     this.respCode = respCode;
/* 37 */     this.counter = counter;
/* 38 */     this.status = status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Params(int paramId, String paramName, boolean isActive, int respCode) {
/* 45 */     this.paramId = paramId;
/* 46 */     this.paramName = paramName;
/* 47 */     this.isActive = isActive;
/* 48 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public Params(int paramId, String paramName) {
/* 52 */     this.paramId = paramId;
/* 53 */     this.paramName = paramName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Params.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */