/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.ServiceBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Params;
/*    */ import com.compulynx.erevenue.models.Service;
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
/*    */ @Component
/*    */ @Path("/service")
/*    */ public class ServiceSvc
/*    */ {
/*    */   @Autowired
/*    */   ServiceBalImpl serviceBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtServices/")
/*    */   public Response GetServices() {
/* 41 */     List<Service> services = this.serviceBal.GetAllServices();
/* 42 */     return Response.status(200).entity(services).build();
/*    */   }
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtParams/")
/*    */   public Response GetParams() {
/* 48 */     List<Params> params = this.serviceBal.GetAllParams();
/* 49 */     return Response.status(200).entity(params).build();
/*    */   }
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updService")
/*    */   public Response UpdateService(Service service) {
/* 56 */     ErevenueResponse response = this.serviceBal.UpdateService(service);
/* 57 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtSubServices/{parentServiceId}")
/*    */   public Response GetSubServices(@PathParam("parentServiceId") int parentServiceId) {
/* 64 */     List<Service> services = this.serviceBal.GetSubServiceById(parentServiceId);
/* 65 */     return Response.status(200).entity(services).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updParam")
/*    */   public Response UpdateParam(Params param) {
/* 73 */     ErevenueResponse response = this.serviceBal.UpdateParam(param);
/* 74 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtSubServices/")
/*    */   public Response GetSubServices() {
/* 80 */     List<Service> services = this.serviceBal.GetAllSubServices();
/* 81 */     return Response.status(200).entity(services).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updSer")
/*    */   public Response UpdateSer(Service service) {
/* 89 */     ErevenueResponse response = this.serviceBal.UpdateSerPrice(service);
/* 90 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtMarketServices/{marketId}")
/*    */   public Response GetMarketServices(@PathParam("marketId") int marketId) {
/* 97 */     List<Service> services = this.serviceBal.GetMarketSubServices(marketId);
/* 98 */     return Response.status(200).entity(services).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\ServiceSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */