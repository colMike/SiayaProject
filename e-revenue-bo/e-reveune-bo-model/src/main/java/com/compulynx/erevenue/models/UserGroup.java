/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class UserGroup
/*    */ {
/*    */   public int groupId;
/*    */   public String groupCode;
/*    */   public String groupName;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public List<RightsDetail> rights;
/*    */   public int groupClassId;
/*    */   public int groupLinkId;
/*    */   public int count;
/*    */   public int roleTypeId;
/*    */   
/*    */   public UserGroup(int groupId, String groupCode, String groupName, boolean active, int createdBy, int respCode) {
/* 20 */     this.groupId = groupId;
/* 21 */     this.groupCode = groupCode;
/* 22 */     this.groupName = groupName;
/* 23 */     this.active = active;
/* 24 */     this.createdBy = createdBy;
/* 25 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public UserGroup(int groupId, String groupName, int respCode) {
/* 29 */     this.groupId = groupId;
/* 30 */     this.groupName = groupName;
/* 31 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public UserGroup(int respCode) {
/* 36 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public UserGroup() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\UserGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */