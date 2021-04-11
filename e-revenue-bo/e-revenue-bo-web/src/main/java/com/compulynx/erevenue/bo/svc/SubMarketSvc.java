/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.SubMarketBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Market;
/*    */ import com.compulynx.erevenue.models.SubMarket;
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
/*    */ @Path("/submarket")
/*    */ public class SubMarketSvc
/*    */ {
/*    */   @Autowired
/*    */   SubMarketBalImpl subMarketBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtSubMarkets")
/*    */   public Response GetSubMarkets() {
/* 40 */     List<SubMarket> submarkets = this.subMarketBal.GetAllSubMarkets();
/* 41 */     return Response.status(200).entity(submarkets).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveMarkets")
/*    */   public Response GetActiveMarkets() {
/* 48 */     List<Market> markets = this.subMarketBal.GetActiveMarkets();
/* 49 */     return Response.status(200).entity(markets).build();
/*    */   }
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updSubMarket")
/*    */   public Response UpdateMarket(SubMarket submarket) {
/* 56 */     ErevenueResponse response = this.subMarketBal.UpdateSubMarket(submarket);
/* 57 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\SubMarketSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */