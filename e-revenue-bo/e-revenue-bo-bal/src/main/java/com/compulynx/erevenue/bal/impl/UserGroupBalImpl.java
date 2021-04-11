/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.UserGroupBal;
/*    */ import com.compulynx.erevenue.dal.impl.UserGroupDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.RightsDetail;
/*    */ import com.compulynx.erevenue.models.UserGroup;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ @Component
/*    */ public class UserGroupBalImpl
/*    */   implements UserGroupBal
/*    */ {
/*    */   @Autowired
/*    */   UserGroupDalImpl userGroupDal;
/*    */   
/*    */   public ErevenueResponse UpdateUserGroup(UserGroup group) {
/* 29 */     return this.userGroupDal.UpdateUserGroup(group);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<UserGroup> GetUserGroups() {
/* 35 */     return this.userGroupDal.GetUserGroups();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<RightsDetail> GetRights() {
/* 41 */     return this.userGroupDal.GetRights();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\UserGroupBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */