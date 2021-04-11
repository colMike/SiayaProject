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
/*    */ public class LoginUser
/*    */ {
/*    */   public int userId;
/*    */   public int respCode;
/*    */   public String respMessage;
/*    */   public int userBioID;
/*    */   
/*    */   public LoginUser(int userId, int respCode) {
/* 23 */     this.userId = userId;
/* 24 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginUser(int respCode) {
/* 29 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public LoginUser() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\LoginUser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */