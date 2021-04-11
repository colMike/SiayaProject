/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.LoginBal;
/*    */ import com.compulynx.erevenue.dal.impl.LoginDalImpl;
/*    */ import com.compulynx.erevenue.models.ConfigParams;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.LoginSession;
/*    */ import com.compulynx.erevenue.models.LoginUser;
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
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class LoginBalImpl
/*    */   implements LoginBal
/*    */ {
/*    */   @Autowired
/*    */   LoginDalImpl loginDal;
/*    */   
/*    */   public LoginUser ValidateManualLogin(String userName, String password) {
/* 34 */     return this.loginDal.GetUserIdManual(userName, password);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LoginSession GetUserAssgnRightsList(int userId) {
/* 40 */     return this.loginDal.GetUserAssgnRightsList(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ConfigParams> GetConfigParams() {
/* 46 */     return this.loginDal.GetConfigParams();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateConfigParam(ConfigParams params) {
/* 52 */     return this.loginDal.UpdateConfigParam(params);
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\LoginBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */