/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.UserBal;
/*    */ import com.compulynx.erevenue.dal.impl.UserDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.User;
/*    */ import com.compulynx.erevenue.models.UserGroup;
/*    */ import com.compulynx.erevenue.models.UserType;
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
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class UserBalImpl
/*    */   implements UserBal
/*    */ {
/*    */   @Autowired
/*    */   UserDalImpl userDal;
/*    */   
/*    */   public ErevenueResponse UpdateUser(User user) {
/* 32 */     return this.userDal.UpdateUser(user);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<User> GetAllUsers() {
/* 38 */     return this.userDal.GetAllUsers();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<UserGroup> GetActiveGroups() {
/* 44 */     return this.userDal.GetActiveGroups();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<UserType> GetUserTypes() {
/* 50 */     return this.userDal.GetUserTypes();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\UserBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */