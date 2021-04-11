/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserType
/*    */ {
/*    */   public int userTypeId;
/*    */   public String userTypeName;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   
/*    */   public UserType(int userTypeId, String userTypeName) {
/* 19 */     this.userTypeId = userTypeId;
/* 20 */     this.userTypeName = userTypeName;
/*    */   }
/*    */   
/*    */   public UserType() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\UserType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */