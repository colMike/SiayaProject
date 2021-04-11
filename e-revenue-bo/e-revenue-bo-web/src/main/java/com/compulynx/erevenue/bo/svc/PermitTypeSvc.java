/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.PermitTypeBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.PermitType;
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
/*    */ @Component
/*    */ @Path("/permittype")
/*    */ public class PermitTypeSvc
/*    */ {
/*    */   @Autowired
/*    */   PermitTypeBalImpl permitTypeBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtPermitTypes/")
/*    */   public Response GetPermitTypes() {
/* 33 */     List<PermitType> permittypes = this.permitTypeBal.GetAllPermitTypes();
/* 34 */     return Response.status(200).entity(permittypes).build();
/*    */   }
/*    */ 
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updPermitType")
/*    */   public Response UpdatePermitType(PermitType permitType) {
/* 43 */     ErevenueResponse response = this.permitTypeBal.UpdatePermitType(permitType);
/* 44 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\PermitTypeSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */