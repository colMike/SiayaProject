/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.WardBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.SubCounty;
/*    */ import com.compulynx.erevenue.models.Ward;
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
/*    */ 
/*    */ @Component
/*    */ @Path("/ward")
/*    */ public class WardSvc
/*    */ {
/*    */   @Autowired
/*    */   WardBalImpl wardBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtWards")
/*    */   public Response GetWards() {
/* 42 */     List<Ward> wards = this.wardBal.GetAllWards();
/* 43 */     return Response.status(200).entity(wards).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveSubCounties")
/*    */   public Response GetActiveWards() {
/* 50 */     List<SubCounty> wards = this.wardBal.GetActiveSubCounties();
/* 51 */     return Response.status(200).entity(wards).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updWard")
/*    */   public Response UpdateWard(Ward ward) {
/* 59 */     ErevenueResponse response = this.wardBal.UpdateWard(ward);
/* 60 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\WardSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */