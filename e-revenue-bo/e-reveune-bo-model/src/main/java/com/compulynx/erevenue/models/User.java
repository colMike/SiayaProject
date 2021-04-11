/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class User
/*    */ {
/*    */   public int userId;
/*    */   public String userName;
/*    */   public String userFullName;
/*    */   public String userPwd;
/*    */   public String userEmail;
/*    */   public String userPhone;
/*    */   public int groupId;
/*    */   public String groupName;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public String deviceId;
/*    */   public int marketId;
/*    */   public long userLinkedID;
/*    */   public long userBioID;
/*    */   public int userTypeId;
/*    */   public String userNationalId;
/*    */   public int count;
/*    */   public String status;
/*    */   
/*    */   public User(int userId, String userName, String userFullName, String userPwd, String userEmail, String userPhone, int groupId, boolean active, int createdBy, int respCode, String userNationalId) {
/* 32 */     this.userId = userId;
/* 33 */     this.userName = userName;
/* 34 */     this.userFullName = userFullName;
/* 35 */     this.userPwd = userPwd;
/* 36 */     this.userEmail = userEmail;
/* 37 */     this.userPhone = userPhone;
/* 38 */     this.groupId = groupId;
/* 39 */     this.active = active;
/* 40 */     this.createdBy = createdBy;
/* 41 */     this.respCode = respCode;
/* 42 */     this.userNationalId = userNationalId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public User(int userId, String userName, String userFullName, String userPwd, int userGroupId, int agentId, int branchId, String userEmail, String userPhone, int userSecretQuestionId, String userSecretAns, boolean userBioLogin, long userLinkedID, long userBioID, boolean active, int createdBy, int respCode) {
/* 50 */     this.userId = userId;
/* 51 */     this.userName = userName;
/* 52 */     this.userFullName = userFullName;
/* 53 */     this.userPwd = userPwd;
/* 54 */     this.groupId = userGroupId;
/* 55 */     this.userEmail = userEmail;
/* 56 */     this.userPhone = userPhone;
/* 57 */     this.userLinkedID = userLinkedID;
/* 58 */     this.userBioID = userBioID;
/* 59 */     this.active = active;
/* 60 */     this.createdBy = createdBy;
/* 61 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public User(int respCode) {
/* 68 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public User() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\User.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */