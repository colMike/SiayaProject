/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginSession
/*    */ {
/*    */   public int sessionId;
/*    */   public int linkId;
/*    */   public int userGroupId;
/*    */   public String linkName;
/*    */   public String sessionName;
/*    */   public String sessionFullName;
/*    */   public String linkExtInfo;
/*    */   public int userClassId;
/*    */   public String appName;
/*    */   public List<Rights> rightsHeaderList;
/*    */   public int respCode;
/*    */   
/*    */   public void setLinkExtInfo(String linkExtInfo) {
/* 24 */     this.linkExtInfo = linkExtInfo;
/*    */   }
/*    */   
/*    */   public void setUserGroupId(int userGroupId) {
/* 28 */     this.userGroupId = userGroupId;
/*    */   }
/*    */   
/*    */   public void setSessionFullName(String sessionFullName) {
/* 32 */     this.sessionFullName = sessionFullName;
/*    */   }
/*    */   
/*    */   public void setSessionName(String sessionName) {
/* 36 */     this.sessionName = sessionName;
/*    */   }
/*    */   public void setUserClassID(int userClassID) {
/* 39 */     this.userClassId = userClassID;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoginSession() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRightsList(List<Rights> rightsList) {
/* 50 */     this.rightsHeaderList = rightsList;
/*    */   }
/*    */   
/*    */   public void setLinkId(int linkId) {
/* 54 */     this.linkId = linkId;
/*    */   }
/*    */   
/*    */   public void setSessionId(int sessionId) {
/* 58 */     this.sessionId = sessionId;
/*    */   }
/*    */   
/*    */   public void setLinkName(String linkName) {
/* 62 */     this.linkName = linkName;
/*    */   }
/*    */   
/*    */   public void setRespCode(int respCode) {
/* 66 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginSession(int respCode) {
/* 71 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public String getAppName() {
/* 75 */     return this.appName;
/*    */   }
/*    */   
/*    */   public void setAppName(String appName) {
/* 79 */     this.appName = appName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\LoginSession.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */