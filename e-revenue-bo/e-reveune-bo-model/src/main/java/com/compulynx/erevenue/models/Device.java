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
/*    */ public class Device
/*    */ {
/*    */   public int id;
/*    */   public String devCode;
/*    */   public int regId;
/*    */   public String serialNo;
/*    */   public String macAddress;
/*    */   public String devType;
/*    */   public int userId;
/*    */   public String userName;
/*    */   public boolean status;
/*    */   public String last_active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public int issueId;
/*    */   public int devId;
/*    */   public int issuedTo;
/*    */   public String dateIssued;
/*    */   public String dateReturned;
/*    */   public String name;
/*    */   public String marketName;
/*    */   
/*    */   public Device() {}
/*    */   
/*    */   public Device(int id, String serialNo, boolean status, int createdBy, int count, int respCode) {
/* 42 */     this.id = id;
/* 43 */     this.serialNo = serialNo;
/* 44 */     this.status = status;
/* 45 */     this.createdBy = createdBy;
/* 46 */     this.count = count;
/* 47 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Device(int issueId, int devId, String serialNo, int userId, String userName, int createdBy, boolean status, int respCode, int count, String name, String marketName) {
/* 53 */     this.issueId = issueId;
/* 54 */     this.devId = devId;
/* 55 */     this.serialNo = serialNo;
/* 56 */     this.issuedTo = userId;
/* 57 */     this.userName = userName;
/* 58 */     this.createdBy = createdBy;
/* 59 */     this.status = status;
/* 60 */     this.respCode = respCode;
/* 61 */     this.count = count;
/* 62 */     this.name = name;
/* 63 */     this.marketName = marketName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Device.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */