/*     */ package com.compulynx.erevenue.bo.svc;
/*     */ 
/*     */ import com.compulynx.erevenue.bal.impl.ApplicationBalImpl;
/*     */ import com.compulynx.erevenue.models.Application;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.PermitType;
/*     */ import com.compulynx.erevenue.models.Reports;
/*     */ import com.compulynx.erevenue.models.Ward;
/*     */ import java.util.List;
/*     */ import javax.ws.rs.Consumes;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.PathParam;
/*     */ import javax.ws.rs.Produces;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Path("/application")
/*     */ public class ApplicationSvc
/*     */ {
/*     */   @Autowired
/*     */   ApplicationBalImpl applicationBal;
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtApps")
/*     */   public Response GetAllApplications() {
/*  33 */     List<Application> apps = this.applicationBal.GetAllApplications();
/*  34 */     return Response.status(200).entity(apps).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtWardsBySubCounty/{subCountyId}")
/*     */   public Response GetWardsBySubCounty(@PathParam("subCountyId") int subCountyId) {
/*  40 */     List<Ward> apps = this.applicationBal.GetWardsBySubCounty(subCountyId);
/*  41 */     return Response.status(200).entity(apps).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtAppsByLinkId/{linkId},{nationalIdNo},{agentId}")
/*     */   public Response GetAllAppsByLinkId(@PathParam("linkId") int linkId, @PathParam("nationalIdNo") String nationalIdNo, @PathParam("agentId") int agentId) {
/*  47 */     List<Application> apps = this.applicationBal.GetAllAppsByLinkId(linkId, nationalIdNo, agentId);
/*  48 */     return Response.status(200).entity(apps).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updApp")
/*     */   public Response UpdateApplication(Application app) {
/*  56 */     ErevenueResponse response = this.applicationBal.UpdateApplication(app);
/*  57 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtActivePermitTypes/{permitType}")
/*     */   public Response GetActivePermitTypes(@PathParam("permitType") String permitType) {
/*  63 */     List<PermitType> permittypes = this.applicationBal.GetActivePermitTypes(permitType);
/*  64 */     return Response.status(200).entity(permittypes).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtInvoices")
/*     */   public Response GetAllInvoices() {
/*  71 */     List<Application> invoices = this.applicationBal.GetAllInvoices();
/*  72 */     return Response.status(200).entity(invoices).build();
/*     */   }
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updInvoiceStatus")
/*     */   public Response UpdateInvocieStatus(Application app) {
/*  79 */     ErevenueResponse response = this.applicationBal.UpdateInvocieStatus(app);
/*  80 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtPermits")
/*     */   public Response GetPermits() {
/*  87 */     List<Application> apps = this.applicationBal.GetAllPermits();
/*  88 */     return Response.status(200).entity(apps).build();
/*     */   }
/*     */ 
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updQrImagePath")
/*     */   public Response UpdateQrImagePath(Application app) {
/*  97 */     ErevenueResponse response = this.applicationBal.UpdateQrImagePath(app);
/*  98 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updPermitRenew")
/*     */   public Response UpdatePermitRenewal(Application app) {
/* 106 */     ErevenueResponse response = this.applicationBal.UpdatePermitRenewal(app);
/* 107 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updPermitStatus")
/*     */   public Response UpdatePermitStatus(Application app) {
/* 115 */     ErevenueResponse response = this.applicationBal.UpdatePermitStatus(app);
/* 116 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtPermitsByDate")
/*     */   public Response GetPermits(Reports permit) {
/* 123 */     List<Application> apps = this.applicationBal.GetPermitsByDate(permit);
/* 124 */     return Response.status(200).entity(apps).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtInvoicesByLinkId/{linkId},{nationalIdNo},{agentId}")
/*     */   public Response GetAllInvoicesByLinkId(@PathParam("linkId") int linkId, @PathParam("nationalIdNo") String nationalIdNo, @PathParam("agentId") int agentId) {
/* 130 */     List<Application> invoices = this.applicationBal.GetAllInvoicesByLinkId(linkId, nationalIdNo, agentId);
/* 131 */     return Response.status(200).entity(invoices).build();
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\ApplicationSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */