/*     */ package com.compulynx.erevenue.bo.svc;
/*     */ 
/*     */ import com.compulynx.erevenue.bal.impl.DashBoardBalImpl;
/*     */ import com.compulynx.erevenue.models.DashBoard;
/*     */ import java.util.List;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.Produces;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Path("/dashBoard")
/*     */ public class DashBoardSvc
/*     */ {
/*     */   @Autowired
/*     */   DashBoardBalImpl dashBoardBal;
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtCountDetail")
/*     */   public Response GetDashBoardCountDetail() {
/*     */     try {
/*  27 */       List<DashBoard> detail = this.dashBoardBal.GetDashBoardCountDetail();
/*  28 */       if (detail != null) {
/*  29 */         return Response.status(200).entity(detail).build();
/*     */       }
/*  31 */       return Response.status(201).entity(null).build();
/*     */     }
/*  33 */     catch (Exception ex) {
/*  34 */       ex.printStackTrace();
/*  35 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtFlowChartDetail")
/*     */   public Response GetFlowChartDetail() {
/*     */     try {
/*  43 */       List<DashBoard> detail = this.dashBoardBal.GetFlowChartCountDetail();
/*  44 */       if (detail != null) {
/*  45 */         return Response.status(200).entity(detail).build();
/*     */       }
/*  47 */       return Response.status(201).entity(null).build();
/*     */     }
/*  49 */     catch (Exception ex) {
/*  50 */       ex.printStackTrace();
/*  51 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtAmountDetail")
/*     */   public Response GetDashBoardAmountDetail() {
/*     */     try {
/*  59 */       List<DashBoard> detail = this.dashBoardBal.GetDashBoardAmountDetail();
/*  60 */       if (detail != null) {
/*  61 */         return Response.status(200).entity(detail).build();
/*     */       }
/*  63 */       return Response.status(201).entity(null).build();
/*     */     }
/*  65 */     catch (Exception ex) {
/*  66 */       ex.printStackTrace();
/*  67 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtTransChartDetail")
/*     */   public Response GetTransChartDetail() {
/*     */     try {
/*  75 */       List<DashBoard> detail = this.dashBoardBal.GetTransChartDetail();
/*  76 */       if (detail != null) {
/*  77 */         return Response.status(200).entity(detail).build();
/*     */       }
/*  79 */       return Response.status(201).entity(null).build();
/*     */     }
/*  81 */     catch (Exception ex) {
/*  82 */       ex.printStackTrace();
/*  83 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtMonthStatisticDetail")
/*     */   public Response GetMonthStatisticsDetail() {
/*     */     try {
/*  91 */       DashBoard detail = this.dashBoardBal.GetMonthStatisticsDetails();
/*  92 */       if (detail != null) {
/*  93 */         return Response.status(200).entity(detail).build();
/*     */       }
/*  95 */       return Response.status(201).entity(null).build();
/*     */     }
/*  97 */     catch (Exception ex) {
/*  98 */       ex.printStackTrace();
/*  99 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtUserStatisticsDetail")
/*     */   public Response GetUserStatisticsDetail() {
/*     */     try {
/* 108 */       DashBoard detail = this.dashBoardBal.GetUserStatisticsDetails();
/* 109 */       if (detail != null) {
/* 110 */         return Response.status(200).entity(detail).build();
/*     */       }
/* 112 */       return Response.status(201).entity(null).build();
/*     */     }
/* 114 */     catch (Exception ex) {
/* 115 */       ex.printStackTrace();
/* 116 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtCollectionDetail")
/*     */   public Response GetCollectionDetail() {
/*     */     try {
/* 124 */       List<DashBoard> detail = this.dashBoardBal.GetDashBoardCollectionDetail();
/* 125 */       if (detail != null) {
/* 126 */         return Response.status(200).entity(detail).build();
/*     */       }
/* 128 */       return Response.status(201).entity(null).build();
/*     */     }
/* 130 */     catch (Exception ex) {
/* 131 */       ex.printStackTrace();
/* 132 */       return Response.status(404).entity(null).build();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\DashBoardSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */