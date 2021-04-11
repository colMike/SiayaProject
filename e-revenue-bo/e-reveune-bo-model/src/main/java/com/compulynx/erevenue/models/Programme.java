/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Programme
/*    */ {
/*    */   public int programmeId;
/*    */   public String programmeCode;
/*    */   public String programmeDesc;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public boolean isActive;
/*    */   public List<Service> services;
/*    */   
/*    */   public Programme(int programmeId, String programmeDesc, boolean isActive, int createdBy, int respCode) {
/* 17 */     this.programmeId = programmeId;
/* 18 */     this.programmeDesc = programmeDesc;
/* 19 */     this.isActive = isActive;
/* 20 */     this.createdBy = createdBy;
/* 21 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Programme() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Programme(int respCode) {
/* 41 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public Programme(int programmeId, String programmeCode, String programmeDesc, boolean active, int createdBy, int respCode) {
/* 46 */     this.programmeId = programmeId;
/* 47 */     this.programmeCode = programmeCode;
/* 48 */     this.programmeDesc = programmeDesc;
/* 49 */     this.active = active;
/* 50 */     this.createdBy = createdBy;
/* 51 */     this.respCode = respCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Programme.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */