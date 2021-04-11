/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.SubCountyBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.SubCounty;
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
/*    */ @Component
/*    */ @Path("/subcounty")
/*    */ public class SubCountySvc
/*    */ {
/*    */   @Autowired
/*    */   SubCountyBalImpl subCountyBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtSubCounties")
/*    */   public Response GetSubCounties() {
/* 39 */     List<SubCounty> subCounties = this.subCountyBal.GetAllSubCounties();
/* 40 */     return Response.status(200).entity(subCounties).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updSubCounty")
/*    */   public Response UpdateSubCounty(SubCounty subCounty) {
/* 48 */     ErevenueResponse response = this.subCountyBal.UpdateSubCounty(subCounty);
/* 49 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\SubCountySvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */