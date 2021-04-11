/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.UserGroupBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.RightsDetail;
/*    */ import com.compulynx.erevenue.models.UserGroup;
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
/*    */ @Component
/*    */ @Path("/userGroups")
/*    */ public class UserGroupSvc
/*    */ {
/*    */   @Autowired
/*    */   UserGroupBalImpl userGroupBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtGroups")
/*    */   public Response GetUserGroups() {
/* 39 */     List<UserGroup> userGroups = this.userGroupBal.GetUserGroups();
/* 40 */     return Response.status(200).entity(userGroups).build();
/*    */   }
/*    */ 
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtRights")
/*    */   public Response GetRights() {
/* 48 */     List<RightsDetail> rights = this.userGroupBal.GetRights();
/* 49 */     return Response.status(201).entity(rights).build();
/*    */   }
/*    */ 
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updGroup")
/*    */   public Response UpdateGroup(UserGroup group) {
/* 58 */     ErevenueResponse response = this.userGroupBal.UpdateUserGroup(group);
/* 59 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\UserGroupSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */