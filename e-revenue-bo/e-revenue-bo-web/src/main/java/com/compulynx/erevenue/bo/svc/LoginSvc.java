/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.LoginBalImpl;
/*    */ import com.compulynx.erevenue.models.ConfigParams;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.LoginSession;
/*    */ import com.compulynx.erevenue.models.LoginUser;
/*    */ import com.compulynx.erevenue.models.User;
/*    */ import java.util.List;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.PathParam;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.core.Response;
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
/*    */ @Path("/login")
/*    */ public class LoginSvc
/*    */ {
/*    */   @Autowired
/*    */   LoginBalImpl loginBal;
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/manualAuth")
/*    */   public Response ValidateManualLogin(User user) {
/*    */     try {
/* 46 */       LoginUser loginUser = this.loginBal.ValidateManualLogin(user.userName, user.userPwd);
/* 47 */       return Response.status(loginUser.respCode).entity(loginUser).build();
/*    */     }
/* 49 */     catch (Exception ex) {
/* 50 */       ex.printStackTrace();
/* 51 */       return Response.status(404).entity(null).build();
/*    */     } 
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/getUserRights/{usrId}")
/*    */   public Response GetUserLoginSession(@PathParam("usrId") int usrId) {
/*    */     try {
/* 60 */       LoginSession loginSession = this.loginBal.GetUserAssgnRightsList(usrId);
/* 61 */       return Response.status(loginSession.respCode).entity(loginSession).build();
/*    */     }
/* 63 */     catch (Exception ex) {
/* 64 */       ex.printStackTrace();
/* 65 */       return Response.status(404).entity(null).build();
/*    */     } 
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtConfig")
/*    */   public Response GetConfigParams() {
/* 73 */     List<ConfigParams> params = this.loginBal.GetConfigParams();
/* 74 */     return Response.status(200).entity(params).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updConfig")
/*    */   public Response UpdateDevice(ConfigParams config) {
/* 82 */     ErevenueResponse response = this.loginBal.UpdateConfigParam(config);
/* 83 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\LoginSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */