/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.UserBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.User;
/*    */ import com.compulynx.erevenue.models.UserGroup;
/*    */ import com.compulynx.erevenue.models.UserType;
/*    */ import java.util.List;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
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
/*    */ @Path("/user")
/*    */ public class UserSvc
/*    */ {
/*    */   @Autowired
/*    */   UserBalImpl userBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtUsers/")
/*    */   public Response GetUsers() {
/* 42 */     List<User> users = this.userBal.GetAllUsers();
/* 43 */     return Response.status(200).entity(users).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updUser")
/*    */   public Response UpdateUser(User user) {
/* 51 */     ErevenueResponse response = this.userBal.UpdateUser(user);
/* 52 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtUserTypes/")
/*    */   public Response GetUserTypes() {
/* 59 */     List<UserType> userTypes = this.userBal.GetUserTypes();
/* 60 */     return Response.status(200).entity(userTypes).build();
/*    */   }
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveGroups/")
/*    */   public Response GetActiveGroups() {
/* 66 */     List<UserGroup> groups = this.userBal.GetActiveGroups();
/* 67 */     return Response.status(200).entity(groups).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\UserSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */