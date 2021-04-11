/*     */ package com.compulynx.erevenue.bo.svc;
/*     */ 
/*     */ import com.compulynx.erevenue.bal.impl.ReportsBalImpl;
/*     */ import com.compulynx.erevenue.models.Application;
/*     */ import com.compulynx.erevenue.models.Reports;
/*     */ import com.compulynx.erevenue.models.ZDetails;
/*     */ import java.util.List;
/*     */ import javax.ws.rs.Consumes;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.Produces;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Path("/report")
/*     */ public class ReportSvc
/*     */ {
/*     */   @Autowired
/*     */   ReportsBalImpl reportBal;
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtUserTxns")
/*     */   public Response GetUserTxns(Reports report) {
/*  45 */     List<Reports> userTxns = this.reportBal.GetAllUserTxn();
/*  46 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtUserTxnDetail")
/*     */   public Response GetUserTxnDetails(Reports txnDetails) {
/*  54 */     List<Reports> userTxns = this.reportBal.GetUserTxnsDetails(txnDetails);
/*  55 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtDeviceTxns")
/*     */   public Response GetDeviceTxns() {
/*  62 */     List<Reports> userTxns = this.reportBal.GetAllDeviceTxn();
/*  63 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtDeviceTxnDetail")
/*     */   public Response GetDeviceTxnDetails(Reports txnDetails) {
/*  71 */     List<Reports> userTxns = this.reportBal.GetDeviceTxnsDetails(txnDetails);
/*  72 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtAllTxnDetail")
/*     */   public Response GetAllTxnDetails(Reports txnDetails) {
/*  97 */     List<Reports> userTxns = this.reportBal.GetAllTxnsDetails(txnDetails);
/*  98 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */ 
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtZDetails")
/*     */   public Response GetZDetails(ZDetails zDetails) {
/* 107 */     List<ZDetails> zList = this.reportBal.GetZDetails(zDetails);
/* 108 */     return Response.status(200).entity(zList).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/permits")
/*     */   public Response getPermits(Reports permitDetails) {
/* 116 */     List<Application> permits = null;
/* 117 */     return Response.status(200).entity(permits).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/gtCurrTxnDetail")
/*     */   public Response GetCurrentTxnDetails() {
/* 125 */     List<Reports> userTxns = this.reportBal.GetCurrentTxnsDetails();
/* 126 */     return Response.status(200).entity(userTxns).build();
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\ReportSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */